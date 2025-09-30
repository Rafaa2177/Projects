create or replace procedure insert_monda(
    campo in varchar, 
    datap in varchar, 
    planta in varchar, 
    area_param in number
)
AS
    data_invalido exception;
    campo_inexistente exception;
    planta_inexistente exception;
    area_excedida exception;
BEGIN
    DECLARE
    id_operacao number(10);
    id_instalacao number(10);
    id_tipo number(10);
    id_planta number;
    area double precision; 
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
        from Planta p 
        inner join Especie e on p.Especieid = e.id 
        where UPPER(e.nome || ' ' || p.variedade) = UPPER(planta);
        if v_count = 0 then
            raise planta_inexistente;
        end if;
        select p.id into id_planta from Planta p inner join Especie e on p.Especieid = e.id 
        where UPPER(e.nome || ' ' || p.variedade) = UPPER(planta);
        
        select area, Unidadeid 
        into area, id_unidade 
        from parcela 
        where Instalacaoid = id_instalacao;
        if TO_NUMBER(area_param) > area then
            raise area_excedida;
        end if;
    
        select id into id_tipo from Tipo_Operacao where tipo = 'Monda';
    
        SELECT id into id_operacao FROM Operacoes ORDER BY id DESC FETCH FIRST 1 ROW ONLY;
        
        insert into Operacoes (id, data, ParcelaInstalacaoid, quantidade, Unidadeid, Tipo_Operacaoid) values (id_operacao+1,to_date(datap,'DD/MM/YYYY'),id_instalacao,area_param,id_unidade,id_tipo);
        insert into Agricultura(Operacoesid, Plantaid, PlantaNaoExistente) values (id_operacao+1, id_planta, null);

    END;
EXCEPTION
    WHEN data_invalido THEN
        raise_application_error(-20001, 'Data inválida');
    WHEN campo_inexistente THEN
        raise_application_error(-20002, 'Campo inexistente');
    WHEN planta_inexistente THEN
        raise_application_error(-20003, 'Planta inexistente');
    WHEN area_excedida THEN
        raise_application_error(-20004, 'Àrea excedida');
END;
/

DECLARE
    area_param1 NUMBER := 0.4;
BEGIN
  insert_monda('Campo Novo', '05-11-2023', 'Cenoura Danvers Half Long', area_param1);
END;
/