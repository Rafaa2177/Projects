CREATE OR REPLACE PROCEDURE prcOperacoesRegister(data DATE, parcelaId INT, quantidade INT, unidadeId INT, tipoOperacaoId INT)
AS
BEGIN
    SELECT MAX(id) INTO ultimo_id FROM Operacoes;
    INSERT INTO Operacoes (id, data, ParcelaInstalacaoid, quantidade, Unidadeid, Tipo_Operacaoid)
        VALUES (ultimo_id+1, data, parcelaId, quantidade, unidadeId, tipoOperacaoId);
END;
/

CREATE OR REPLACE PROCEDURE prcRegaRegister(operacoesId INT, hora TIMESTAMP, setorId INT)
AS
BEGIN
    INSERT INTO Rega (Operacoesid, hora, Setorid)
        VALUES (operacoesId,hora,setorId);
END;

CREATE OR REPLACE FUNCTION obterIdOperacao(nome VARCHAR(255))
IS
    operacaoID int;
BEGIN

    SELECT id FROM tipo_operacoes
        WHERE nome = nome;

    RETURN operacaoId;
END;