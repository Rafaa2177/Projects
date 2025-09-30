create or replace procedure insert_colheita(
    campo in varchar,
    datap in varchar,
    fruto_param in varchar,
    qtt in number,
    qtt_unidade in varchar
)
AS
    data_invalido exception;
    campo_inexistente exception;
    fruto_inexistente exception;
BEGIN
    DECLARE
    id_operacao number(10);
    id_instalacao number(10);
    id_tipo number(10);
    id_fruto number;
    id_unidade number(10);
    v_count int;
    BEGIN
        select count(*) into v_count 
        from Instalacao 
        where designacao = campo;
        if v_count = 0 then
            raise campo_inexistente;
        end if;
        select id into id_instalacao from Instalacao where designacao = campo;

        if to_date(datap,'DD-MM-YYYY') > CURRENT_DATE then
            raise data_invalido;
        end if;
        
        select count(*) into v_count 
        from Fruto
        where UPPER(fruto) = UPPER(fruto_param);
        if v_count = 0 then
            raise fruto_inexistente;
        end if;
        select id into id_fruto 
        from Fruto
        where UPPER(fruto) = UPPER(fruto_param);


        select id into id_unidade from Unidade where nome = qtt_unidade;

        select id into id_tipo from Tipo_Operacao where tipo = 'Colheita';

        select id into id_operacao FROM Operacoes ORDER BY id DESC FETCH FIRST 1 ROW ONLY;

        insert into Operacoes(id, data, ParcelaInstalacaoid, quantidade, Unidadeid, Tipo_Operacaoid) values (id_operacao+1,to_date(datap,'DD/MM/YYYY'),id_instalacao,qtt,id_unidade,id_tipo);
        insert into Colheita(Operacoesid, Frutoid) values (id_operacao+1, id_fruto);

    END;
EXCEPTION
    WHEN data_invalido THEN
        raise_application_error(-20001, 'Data inválida');
    WHEN campo_inexistente THEN
        raise_application_error(-20002, 'Campo inexistente');
    WHEN fruto_inexistente THEN
        raise_application_error(-20003, 'Fruto inexistente');
END;
/

DECLARE
    qtt1 NUMBER := 3.4;
BEGIN
  insert_colheita('Campo grande', '05-11-2023', 'Azeitona Galega', qtt1, 'kg');
END;
/