CREATE OR REPLACE PROCEDURE USBD32(
    data_operacao varchar,
    id_setor number,
    tempo varchar,
    hora varchar,
    id_receita number,
    campo varchar
)

AS
    receita_inexistente exception;
    campo_inexistente exception;
BEGIN
    DECLARE
        op_tempo number;
        op_unn number;
        op_campo number;
        receita_v number;
        i number;
        ultimo_id number(10);
    BEGIN
        i:=0;
        FOR tempo_info IN(
            SELECT REGEXP_SUBSTR(tempo, '[^ ]+', 1, level) AS value FROM dual 
            CONNECT BY REGEXP_SUBSTR(tempo, '[^ ]+', 1, level) IS NOT NULL
        )
        LOOP
            IF i=0 THEN
                op_tempo:=tempo_info.value;
            ELSE
                SELECT id INTO op_unn FROM unidade WHERE nome=tempo_info.value;
            END IF;
            i:=i+1;
        END LOOP;

        SELECT count(*) INTO op_campo FROM instalacao WHERE designacao=campo;
        IF op_campo = 0 THEN
            raise campo_inexistente;
        ELSE
            SELECT id INTO op_campo FROM instalacao WHERE designacao=campo;
        END IF;

        SELECT NVL(MAX(id), 0) INTO ultimo_id FROM Operacoes;
                
        INSERT INTO operacoes(id,data,parcelainstalacaoid,dataregisto,anulado) VALUES(ultimo_id + 1, TO_DATE(data_operacao, 'YYYY-MM-DD'),op_campo, TO_DATE(CURRENT_DATE, 'YYYY-MM-DD'),'F');
        INSERT INTO rega(operacoesid,hora,setorid,quantidade,unidadeid) VALUES (ultimo_id + 1,TO_DATE(hora,'HH24:MI'),id_setor,op_tempo,op_unn);

        IF id_receita <> 0 THEN

            SELECT count(*) INTO receita_v FROM receita WHERE id=id_receita;
            IF receita_v = 0 THEN
                raise receita_inexistente;
            END IF;
            INSERT INTO fertirrega(regaoperacoesid,receitaid) VALUES(ultimo_id + 1,id_receita);
        END IF;
    END;
EXCEPTION
    WHEN receita_inexistente THEN
        raise_application_error(-20001, 'Receita inexistente');
    WHEN campo_inexistente THEN
        raise_application_error(-20002, 'Campo inexistente');
END;
/

BEGIN
  USBD32('2023-09-02', 10, '90 min', '05:00', 10, 'CAMPO GRANDE');
END;
/

select * from operacoes;
SELECT * FROM REGA;
SELECT * FROM fertirREGA;


SELECT count(*) FROM receita WHERE id=10;







