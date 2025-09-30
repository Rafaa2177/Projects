CREATE OR REPLACE PROCEDURE inserir_rega(dataOp varchar, setorId INT, quantidade INT, horaI varchar, receitaId INT)
AS
BEGIN
    DECLARE
ultimo_id number(10);
    unidadeId number(10);

BEGIN

select id into unidadeId from Unidade where nome='min';

DECLARE
CURSOR c_dados IS
SELECT C.parcelainstalacaoid as col1, c.data_inicial as col2, c.data_final as col3
FROM Ciclo C
         INNER JOIN Ciclo_Rega CR ON cr.cicloid = c.id
WHERE cr.setorid = setorId;

datap TIMESTAMP := to_date(dataOp, 'YYYY-MM-DD');
BEGIN

FOR dados IN c_dados
                LOOP
                   DBMS_OUTPUT.PUT_LINE('Column1: ' || dados.col1 || ', Column2: ' || dados.col2  || ', Column3: ' || dados.col3);

                    DECLARE
v_count NUMBER;
BEGIN
                        IF datap > dados.col2 and datap < NVL(dados.col3, to_date('31-12-9999', 'DD-MM-YYYY')) THEN
SELECT NVL(MAX(id), 0)+1 INTO ultimo_id FROM Operacoes;

INSERT INTO operacoes(id,data,parcelainstalacaoid,dataregisto,anulado) VALUES(ultimo_id, datap, TO_NUMBER(dados.col1), TO_DATE(CURRENT_DATE, 'YYYY-MM-DD'),'F');
INSERT INTO rega(operacoesid,hora,setorid,quantidade,unidadeid) VALUES (ultimo_id,TO_DATE(horaI,'HH24:MI'),setorId,quantidade,unidadeId);

DBMS_OUTPUT.PUT_LINE('entrei');

                            IF receitaId <> 0 THEN
                                INSERT INTO Fertirrega (RegaOperacoesid, Receitaid) VALUES (ultimo_id, receitaId);
END IF;
ELSE
                            DBMS_OUTPUT.PUT_LINE('Datas n?o correspondentes');
END IF;

END;
END LOOP;
END;
END;
END;
/

DECLARE
setor NUMBER := 10;
    qtt NUMBER := 14;
BEGIN
  inserir_rega('2023-03-01', setor, qtt, '08:44', 10);
END;
/

CREATE OR REPLACE PROCEDURE inserir_rega(dataOp varchar, setorId INT, quantidade INT, horaI varchar, receitaId INT)
AS
BEGIN
    DECLARE
ultimo_id number(10);
    unidadeId number(10);

BEGIN

select id into unidadeId from Unidade where nome='min';

DECLARE
CURSOR c_dados IS
SELECT C.parcelainstalacaoid as col1, c.data_inicial as col2, c.data_final as col3
FROM Ciclo C
         INNER JOIN Ciclo_Rega CR ON cr.cicloid = c.id
WHERE cr.setorid = setorId;

datap TIMESTAMP := to_date(dataOp, 'YYYY-MM-DD');
BEGIN

FOR dados IN c_dados
                LOOP
                   DBMS_OUTPUT.PUT_LINE('Column1: ' || dados.col1 || ', Column2: ' || dados.col2  || ', Column3: ' || dados.col3);

                    DECLARE
v_count NUMBER;
BEGIN
                        IF datap > dados.col2 and datap < NVL(dados.col3, to_date('31-12-9999', 'DD-MM-YYYY')) THEN
SELECT NVL(MAX(id), 0)+1 INTO ultimo_id FROM Operacoes;

INSERT INTO operacoes(id,data,parcelainstalacaoid,dataregisto,anulado) VALUES(ultimo_id, datap, TO_NUMBER(dados.col1), TO_DATE(CURRENT_DATE, 'YYYY-MM-DD'),'F');
INSERT INTO rega(operacoesid,hora,setorid,quantidade,unidadeid) VALUES (ultimo_id,TO_DATE(horaI,'HH24:MI'),setorId,quantidade,unidadeId);

DBMS_OUTPUT.PUT_LINE('entrei');

                            IF receitaId <> 0 THEN
                                INSERT INTO Fertirrega (RegaOperacoesid, Receitaid) VALUES (ultimo_id, receitaId);
END IF;
ELSE
                            DBMS_OUTPUT.PUT_LINE('Datas n?o correspondentes');
END IF;

END;
END LOOP;
END;
END;
END;
/

/*DECLARE
setor NUMBER := 10;
    qtt NUMBER := 14;
BEGIN
  inserir_rega('2023-03-01', setor, qtt, '08:44', 10);
END;
/
inserir_rega(dataOp varchar, setorId INT, quantidade INT, horaI varchar, receitaId INT)
SELECT * FROM Operacoes;
SELECT * FROM Rega;
SELECT * FROM Fertirrega;
INSERT INTO Operacoes (id, data, ParcelaInstalacaoid, dataRegisto, anulado) values (500, to_date('26/11/2023', 'YYYY-MM-DD'), 104, SYSTIMESTAMP, 'F');
INSERT INTO Operacoes (data, ParcelaInstalacaoid) VALUES (to_date('2018-12-16','YYYY-MM-DD'),107.0);

INSERT INTO Rega (Operacoesid, hora, Setorid, quantidade, Unidadeid) VALUES (1, TO_DATE('22:00','HH24:MI'), 10, 60, 6);

select * from setor
select id  from Unidade where nome='min';
SELECT NVL(MAX(id), 0) FROM Operacoes;
SELECT NVL(MAX(id), 0)+1  FROM Operacoes;

SELECT C.parcelainstalacaoid as col1, c.data_inicial as col2, c.data_final as col3
FROM Ciclo C
         INNER JOIN Ciclo_Rega CR ON cr.cicloid = c.id
WHERE cr.setorid = 10;*/