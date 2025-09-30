CREATE OR REPLACE PROCEDURE inserir_rega(dataOp varchar, setorId INT, quantidade INT, horaI varchar, receitaId INT)
AS
BEGIN
    DECLARE 
    ultimo_id number(10);
    unidadeId number(10);
    tipoOperacao_id number(10);
    
    BEGIN
                
        select id into unidadeId from Unidade where nome='min';
        select id into tipoOperacao_id from Tipo_operacao where tipo='Rega';
        
        PRAGMA AUTONOMOUS_TRANSACTION;
        DECLARE
            CURSOR c_dados IS
            SELECT C.parcelainstalacaoid as col1, c.data_inicial as col2, c.data_final as col3
            FROM Ciclo C
            INNER JOIN Ciclo_Rega CR ON cr.cicloid = c.id
            WHERE cr.setorid = 21;
            
            datap TIMESTAMP := to_date(dataOp, 'YYYY-MM-DD');
        BEGIN
            EXECUTE IMMEDIATE 'DROP TABLE testeTableee';
            EXECUTE IMMEDIATE 'CREATE GLOBAL TEMPORARY TABLE testeTableee (
                                    idParcelaInstalacao number(10),
                                    dataIniciall TIMESTAMP,
                                    dataFinall TIMESTAMP
                                )';
            FOR dados IN c_dados
            
            LOOP
                DECLARE
                    v_count NUMBER;
                BEGIN
                            
                    SELECT COUNT(*) INTO v_count FROM testeTableee WHERE idParcelaInstalacao = dados.col1;
                    IF v_count = 0 THEN
                        IF datap > dados.col2 and datap < NVL(dados.col3, to_date('31-12-9999', 'DD-MM-YYYY')) THEN
                            select MAX(id) into ultimo_id from Operacoes;
                            INSERT INTO testeTableee (idParcelaInstalacao, dataIniciall, dataFinall) VALUES (dados.col1, dados.col2, dados.col3);
                            INSERT INTO Operacoes (id, data, ParcelaInstalacaoid, quantidade, Unidadeid, Tipo_Operacaoid) values (ultimo_id+1,datap,TO_NUMBER(dados.col1),quantidade,unidadeId,tipoOperacao_id);
                            INSERT INTO Rega (Operacoesid, hora, Setorid) VALUES (ultimo_id+1, TO_DATE(horaI,'HH24:MI'), setorId);
                        ELSE
                           DBMS_OUTPUT.PUT_LINE('Datas n�o correspondentes');
                        END IF;
                    ELSE
                        DBMS_OUTPUT.PUT_LINE('Id j� existe');
                    END IF;
                END;
                DBMS_OUTPUT.PUT_LINE('Column1: ' || dados.col1 || ', Column2: ' || dados.col2  || ', Column3: ' || dados.col3);
            END LOOP;
        END;
    END;
END;
/

DECLARE
    setor NUMBER := 21;
    qtt NUMBER := 14;
BEGIN
  inserir_rega('26/11/2023', setor, qtt, '17:40');
END;
/