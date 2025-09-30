CREATE OR REPLACE PROCEDURE USBD31(
    id_receita in number,
    produtos in varchar
)
AS
    produto_inexistente exception;
    receita_duplicada exception;
BEGIN
    DECLARE
receita_count number;
        id_produto number;
        qtt_produto double precision;
        und_produto number;
        nome_raw varchar(255);
        fab_raw varchar(255);
        i number;
BEGIN
SELECT count(*) INTO receita_count FROM receita WHERE id=id_receita;
IF receita_count=0 THEN
            INSERT INTO receita(id) VALUES (id_receita);
FOR product IN (
                SELECT REGEXP_SUBSTR(REPLACE(produtos,CHR(10),'|'), '[^|]+', 1, level) AS value FROM dual 
                CONNECT BY REGEXP_SUBSTR(REPLACE(produtos,CHR(10),'|'), '[^|]+', 1, level) IS NOT NULL
            )
            LOOP
                i:=0;

FOR product_info IN (
                    SELECT TRIM(REGEXP_SUBSTR(product.value, '[^,]+', 1, level)) AS value FROM dual 
                    CONNECT BY REGEXP_SUBSTR(product.value, '[^,]+', 1, level) IS NOT NULL
                )
                LOOP
                    IF i=0 THEN
                        nome_raw:=product_info.value;
                    ELSIF i=1 THEN
                        fab_raw:=product_info.value;
ELSE
                        FOR qtt_info IN (
                            SELECT REGEXP_SUBSTR(product_info.value, '[^ ]+', 1, level) AS value FROM dual 
                            CONNECT BY REGEXP_SUBSTR(product_info.value, '[^ ]+', 1, level) IS NOT NULL
                        )
                        LOOP
                            IF i=2 THEN
                                qtt_produto:=TO_NUMBER(qtt_info.value, '999.99');
ELSE
SELECT id INTO und_produto FROM unidade where nome = qtt_info.value;
END IF;
                            i:=i+1;
END LOOP;
END IF;
                    i:=i+1;
END LOOP;

SELECT count(*) INTO id_produto FROM
    PRODUTO p INNER JOIN FABRICANTE f ON p.fabricanteid=f.id
WHERE p.designacao = UPPER(nome_raw) AND f.nome = UPPER(fab_raw);

IF id_produto=0 THEN
                    raise produto_inexistente;
ELSE
SELECT p.id INTO id_produto FROM
    PRODUTO p INNER JOIN FABRICANTE f ON p.fabricanteid=f.id
WHERE p.designacao = UPPER(nome_raw) AND f.nome = UPPER(fab_raw);

INSERT INTO Receita_Produto(Receitaid, Produtoid, quantidade, Unidadeid) VALUES (id_receita,id_produto,qtt_produto,und_produto);
END IF;
END LOOP;
ELSE
            raise receita_duplicada;
END IF;
END;
EXCEPTION
    WHEN produto_inexistente THEN
        raise_application_error(-20001, 'Componente inexistente');
WHEN receita_duplicada THEN
        raise_application_error(-20002, 'ID receita duplicada');
END;
/


BEGIN
  USBD31(23,
'Tecniferti MOL, Tecniferti, 60 l/ha
Kiplant AllGrip, Asfertglobal, 2 l/ha
soluSOP 52, K+S, 2 kg/ha');
END;
/

select * from receita;
select * from receita_produto;