create or replace function obter_lista_produtos_operacoes(
    campo in varchar,
    data_inicio in varchar,
    data_fim in varchar
) return sys_refcursor
AS
    campo_inexistente exception;
    data_invalido exception;
BEGIN
    DECLARE
    id_instalacao number(10);
    v_count int;
    v_rc sys_refcursor;
    
    BEGIN
        select count(*) into v_count from Instalacao where designacao = campo;
        if v_count = 0 then
            raise campo_inexistente;
        end if;
        select id into id_instalacao from Instalacao where designacao = campo;
        
        
        if to_date(data_inicio,'DD-MM-YYYY') > CURRENT_DATE then
            raise data_invalido;
        end if;

        if to_date(data_fim,'DD-MM-YYYY') > CURRENT_DATE then
            raise data_invalido;
        end if;
        
        open v_rc for
            select * 
            from(
            select o.id, o.data, p.designacao, tp.id as tipoId, tp.tipo, o.Tipo_Operacaoid
            from instalacao i 
            inner join Parcela on i.id = Instalacaoid 
            inner join Operacoes o on Instalacaoid = o.ParcelaInstalacaoid
            inner join Fertilizacao f on o.id = f.Operacoesid
            inner join Produto p on f.Produtoid = p.id
            inner join Tipo_Produto tp on p.Tipo_Produtoid = tp.id
            where i.designacao = campo and o.data between to_date(data_inicio,'DD-MM-YYYY') and to_date(data_fim,'DD-MM-YYYY')
            union all
            select o.id, o.data, p.designacao, tp.id, tp.tipo, o.Tipo_Operacaoid
            from instalacao i inner join Parcela on i.id = Instalacaoid 
            inner join Operacoes o on Instalacaoid = o.ParcelaInstalacaoid
            inner join Aplicacao f on o.id = f.Operacoesid
            inner join Produto p on f.Produtoid = p.id
            inner join Tipo_Produto tp on p.Tipo_Produtoid = tp.id
            where i.designacao = campo and o.data between to_date(data_inicio,'DD-MM-YYYY') and to_date(data_fim,'DD-MM-YYYY')
            )
            order by tipoId;
        return (v_rc);
    END;

    EXCEPTION
        WHEN data_invalido THEN
            raise_application_error(-20001, 'Data inv�lida');
        WHEN campo_inexistente THEN
            raise_application_error(-20002, 'Campo inexistente');
END;
/

DECLARE
    v_rc sys_refcursor;
    v_id number(10);
    v_data date;
    v_idtipo number(10);
    v_tipo varchar(255);
    p_nome varchar(255);
    p_idtipo number(10);
    p_tipo varchar(255);
    p_produto varchar(255);
    p_anterior number(10);
BEGIN
    v_rc := obter_lista_operacoes('Lameiro do moinho', '01/01/2019', '06/07/2023');
    loop
        fetch v_rc into v_id, v_data, p_produto, p_idtipo, p_tipo, v_idtipo;
        exit when v_rc%notfound;
        if p_anterior <> p_idtipo or p_anterior is null then
            dbms_output.put_line(p_tipo || ':');
        end if;

        select tipo into v_tipo from Tipo_Operacao where id = v_idtipo;

        p_nome:='';
        if v_tipo = 'Fertiliza��o' then
            select Plantaid into p_nome from Fertilizacao where Operacoesid = v_id;
            if p_nome is not null then
                select (e.nome || ' ' || p.variedade) into p_nome from Fertilizacao v
                inner join Planta p on v.Plantaid = p.id
                inner join Especie e on p.Especieid = e.id
                where v.Operacoesid = v_id;
            end if;
        else
            select Plantaid into p_nome from Aplicacao where Operacoesid = v_id;
            if p_nome is not null then
                select (e.nome || ' ' || p.variedade) into p_nome from Aplicacao v
                inner join Planta p on v.Plantaid = p.id
                inner join Especie e on p.Especieid = e.id
                where v.Operacoesid = v_id;
            end if;
        end if;

        dbms_output.put_line('  ' || v_data || ', ' || p_produto || ', ' || p_nome);

        p_anterior := p_idtipo;
    end loop;
END;
/