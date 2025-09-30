create or replace procedure insert_semeadura(
    campo in varchar,
    datap in varchar,
    planta in varchar,
    area_param in number,
    area_unidade in varchar,
    qtt in number,
    qtt_unidade in varchar
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
    area number;
    id_area_unidade number(10);
    id_qtt_unidade number(10);
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
        
        select p.id into id_planta from Planta p inner join Especie e on p.Especieid = e.id where UPPER(e.nome || ' ' || p.variedade) = UPPER(planta);
        
        
        select area into area from parcela where Instalacaoid = id_instalacao;
        if area_param > area then
            raise area_excedida;
        end if;

        select id into id_area_unidade from Unidade where nome = area_unidade;
        select id into id_qtt_unidade from Unidade where nome = qtt_unidade;

        select id into id_tipo from Tipo_Operacao where tipo = 'Semeadura';
    
        select id into id_operacao FROM Operacoes ORDER BY id DESC FETCH FIRST 1 ROW ONLY;
        
        insert into Operacoes(id, data, ParcelaInstalacaoid, quantidade, Unidadeid, Tipo_Operacaoid) values (id_operacao+1,to_date(datap,'DD/MM/YYYY'),id_instalacao,qtt,id_area_unidade,id_tipo);
        insert into Semeadura(Operacoesid, area, Unidadeid, Plantaid, PlantaNaoExistente) values (id_operacao+1, area_param, id_area_unidade, id_planta, null);

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
    WHEN NO_DATA_FOUND THEN 
        raise_application_error(-20004, 'Dado errado');
END;
/

DECLARE
    area_param1 NUMBER := 5.5;
    qtt1 NUMBER := 1.2;
BEGIN
  insert_semeadura('Campo Novo', '05/11/2023', 'Cenoura Danvers Half Long', area_param1, 'ha', qtt1, 'kg');
END;
/