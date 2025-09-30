CREATE OR REPLACE TRIGGER USBD24
BEFORE INSERT ON operacoes
FOR EACH ROW
BEGIN
    :NEW.dataRegisto := SYSTIMESTAMP;
END;
/

CREATE OR REPLACE TRIGGER CAMPO_ANULADO
BEFORE INSERT ON operacoes
FOR EACH ROW
BEGIN
    :NEW.anulado := 'F';
END;
/

CREATE SEQUENCE operacoes_seq START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER USBD25
BEFORE INSERT ON operacoes
FOR EACH ROW
DECLARE
proximo_id NUMBER;
BEGIN
SELECT operacoes_seq.NEXTVAL INTO proximo_id FROM dual;

:NEW.id := proximo_id;
    DBMS_OUTPUT.PUT_LINE('ID INSERIDO: ' || proximo_id);

EXCEPTION
    WHEN OTHERS THEN
    
    DBMS_OUTPUT.PUT_LINE('Erro ao inserir uma operac�o');
END;
/

CREATE SEQUENCE log_seq START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER USBD26
AFTER INSERT OR UPDATE ON operacoes
                           FOR EACH ROW
DECLARE
mensagem VARCHAR(255);
    proximo_logid NUMBER;
    tipo_operacao VARCHAR(255);
    
    colheitaV NUMBER;
    fertilizacaoV NUMBER;
    aplicacaoV NUMBER;
    semeaduraV NUMBER;
    plantacaoV NUMBER;
    mobilizacaosoloV NUMBER;
    fertirregaV NUMBER;
    regaV NUMBER;
    agriculturaV NUMBER;

BEGIN
SELECT log_seq.NEXTVAL INTO proximo_logid FROM dual;

SELECT count(*) INTO colheitaV FROM colheita WHERE operacoesid = :NEW.id;
SELECT count(*) INTO fertilizacaoV FROM fertilizacao WHERE operacoesid = :NEW.id;
SELECT count(*) INTO aplicacaoV FROM aplicacao WHERE operacoesid = :NEW.id;
SELECT count(*) INTO semeaduraV FROM semeadura WHERE operacoesid = :NEW.id;
SELECT count(*) INTO plantacaoV FROM plantacao WHERE operacoesid = :NEW.id;
SELECT count(*) INTO mobilizacaosoloV FROM mobilizacaosolo WHERE operacoesid = :NEW.id;
SELECT count(*) INTO fertirregaV FROM fertirrega WHERE regaoperacoesid = :NEW.id;
SELECT count(*) INTO regaV FROM rega WHERE operacoesid = :NEW.id;
SELECT count(*) INTO agriculturaV FROM agricultura WHERE operacoesid = :NEW.id;

IF colheitaV > 0
    THEN
        tipo_operacao := 'Colheita';
    ELSIF fertilizacaoV > 0
    THEN
        tipo_operacao := 'Fertiliza��o';
    ELSIF aplicacaoV > 0
    THEN
        tipo_operacao := 'Aplica��o';
    ELSIF semeaduraV > 0
    THEN
        tipo_operacao := 'Semeadura';
    ELSIF plantacaoV > 0
    THEN
        tipo_operacao := 'Planta��o';
    ELSIF mobilizacaosoloV > 0
    THEN
        tipo_operacao := 'Mobiliza��o de Solo';
    ELSIF fertirregaV > 0
    THEN
        tipo_operacao := 'Fertirrega'; 
    ELSIF regaV > 0 AND
    fertirregaV = 0
    THEN
        tipo_operacao := 'Rega';
    ELSIF agriculturaV > 0
    THEN
        tipo_operacao := 'Agricultura';
ELSE
        tipo_operacao := 'N�o identificado';
END IF;
        
    IF :OLD.id IS NULL THEN
        mensagem := 'Opera��o criada';
ELSE
        mensagem := 'Opera��o ' || tipo_operacao || ' anulada' ;
END IF;

INSERT INTO Logs_Operacoes (idLog, Operacoesid, mensagem, dataRegisto) VALUES (proximo_logid, :NEW.id, mensagem, SYSTIMESTAMP);

EXCEPTION
    WHEN OTHERS THEN
    
        DBMS_OUTPUT.PUT_LINE('Erro ao inserir/atualizar uma operac�o');
END;
/

CREATE OR REPLACE TRIGGER USBD27
BEFORE DELETE OR UPDATE ON Logs_Operacoes
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar ou atualizar registos da tabela de logs.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_Operacoes
BEFORE DELETE OR UPDATE ON Operacoes
FOR EACH ROW
BEGIN
    IF UPDATING ('id') OR  UPDATING ('data') OR UPDATING ('parcelainstalacaoid') OR UPDATING ('dataRegisto')THEN
        RAISE_APPLICATION_ERROR (-20001, 'Apenas � permitido anular uma opera��o.');
    ELSIF (DELETING) THEN
        RAISE_APPLICATION_ERROR (-20002, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END IF;
END;
/

CREATE OR REPLACE TRIGGER USBD28_Colheita
BEFORE DELETE ON Colheita
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_Fertilizacao
BEFORE DELETE ON Fertilizacao
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_Aplicacao
BEFORE DELETE ON Aplicacao
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_Semeadura
BEFORE DELETE ON Semeadura
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_Plantacao
BEFORE DELETE ON Plantacao
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_Agricultura
BEFORE DELETE ON Agricultura
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_MobilizacaoSolo
BEFORE DELETE ON MobilizacaoSolo
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_Rega
BEFORE DELETE ON Rega
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

CREATE OR REPLACE TRIGGER USBD28_Fertirrega
BEFORE DELETE ON Fertirrega
FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR (-20001, 'N�o � permitido eliminar opera��es da tabela, apenas anular.');
END;
/

SET SERVEROUTPUT ON
    
    
