CREATE OR REPLACE PROCEDURE USBD30(
    id_operacao in number
)
AS
    invalid exception;
BEGIN
    DECLARE
        data_operacao date;
    BEGIN
        select data into data_operacao from operacoes where id = id_operacao;
        if (to_date(CURRENT_DATE, 'YYYY-MM-DD') - to_date(data_operacao, 'YYYY-MM-DD')) <= 3 then
            update operacoes set anulado= 'T' where id=id_operacao;
        else
            raise invalid;
        end if;
    END;
EXCEPTION 
    WHEN invalid THEN
        raise_application_error(-20001, 'Operação demasiado antiga!');
END;
/


BEGIN
  USBD30(291);
END;
/

SELECT * FROM OPERACOES;
INSERT INTO Operacoes (data, ParcelaInstalacaoid) VALUES (to_date('2024-01-02','YYYY-MM-DD'),104.0);
select data from operacoes where id = 295;
