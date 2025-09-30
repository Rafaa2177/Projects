create or replace procedure insert_poda(
    campo in varchar, 
    datap in varchar, 
    planta in varchar, 
    nume in number
)
AS
    data_invalido exception;
    campo_inexistente exception;
    planta_inexistente exception;
    numeroUnidades_excedida exception;
BEGIN
    DECLARE
    id_operacao number(10);
    id_instalacao number(10);
    id_tipo number(10);
    id_planta number;
    numeqtt double precision;
    id_unidade number(10);
    v_count int;
    BEGIN
        select count(*) into v_count from Instalacao where designacao = campo;
        if v_count = 0 then
            raise campo_inexistente;
        end if;
        select id into id_instalacao from Instalacao where designacao = campo;
        
        if to_date(datap,'DD-MM-YYYY') > CURRENT_DATE then
            raise data_invalido;
        end if;

        select count(*) into v_count from Planta p inner join Especie e on p.Especieid = e.id
        where UPPER(e.nome || ' ' || p.variedade) = UPPER(planta);
        if v_count = 0 then
            raise planta_inexistente;
        end if;
        select p.id into id_planta from Planta p inner join Especie e on p.Especieid = e.id 
        where UPPER(e.nome || ' ' || p.variedade) = UPPER(planta);
        
        select qtt, Unidadeid into numeqtt, id_unidade from ciclo c
        where c.parcelainstalacaoid = id_instalacao and c.plantaid = id_planta
        and to_date(datap,'DD-MM-YYYY') > c.data_inicial and to_date(datap,'DD-MM-YYYY') < NVL(c.data_final, CURRENT_DATE);
        if TO_NUMBER(nume) > numeqtt then
            raise numeroUnidades_excedida;
        end if;
        
        select id into id_tipo from Tipo_Operacao where tipo = 'Poda';
    
        SELECT id into id_operacao FROM Operacoes ORDER BY id DESC FETCH FIRST 1 ROW ONLY;
        
        insert into Operacoes (id, data, ParcelaInstalacaoid, quantidade, Unidadeid, Tipo_Operacaoid) values (id_operacao+1,to_date(datap,'DD/MM/YYYY'),id_instalacao,nume,id_unidade,id_tipo);
        insert into Agricultura(Operacoesid, Plantaid, PlantaNaoExistente) values (id_operacao+1, id_planta, null);

    END;
EXCEPTION
    WHEN data_invalido THEN
        raise_application_error(-20001, 'Data inválida');
    WHEN campo_inexistente THEN
        raise_application_error(-20002, 'Campo inexistente');
    WHEN planta_inexistente THEN
        raise_application_error(-20003, 'Planta inexistente');
    WHEN numeroUnidades_excedida THEN
        raise_application_error(-20004, 'Número de unidades excedido');
END;
/

DECLARE
    nume NUMBER := 19;
BEGIN
  insert_poda('Campo grande', '06-11-2023', 'Oliveira PICUAL', nume);
END;
/
