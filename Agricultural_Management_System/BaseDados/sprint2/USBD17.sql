create or replace function obter_lista_produtos(
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
    v_rc sys_refcursor;
    v_count int;
    
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
            select unique p.id, p.designacao, s.nome, ft.quantidade
            from instalacao i inner join Parcela on i.id = Instalacaoid   
            inner join Operacoes o on Instalacaoid = o.ParcelaInstalacaoid
            inner join Fertilizacao f on o.id = f.Operacoesid
            inner join Produto p on f.Produtoid = p.id
            inner join Ficha_Tecnica ft on p.id = ft.Produtoid
            inner join Substancia s on ft.Substanciaid = s.id
            where i.designacao = campo and o.data between to_date(data_inicio,'DD-MM-YYYY') and to_date(data_fim,'DD-MM-YYYY');
        return (v_rc);
    END;

    EXCEPTION
        WHEN data_invalido THEN
            raise_application_error(-20001, 'Data inválida');
        WHEN campo_inexistente THEN
            raise_application_error(-20002, 'Campo inexistente');
END;
/

DECLARE
    v_rc sys_refcursor;
    v_id number(10);
    v_designacao varchar(255);
    v_substancia varchar(255);
    v_qtt varchar(255);
    p_anterior number(10);
BEGIN
    v_rc := obter_lista_produtos('Campo Novo', '20-05-2023', '06-11-2023');
    loop
        fetch v_rc into v_id, v_designacao, v_substancia, v_qtt;
        exit when v_rc%notfound;
        if p_anterior <> v_id or p_anterior is null then
            dbms_output.put_line(v_designacao || ':');
        end if;
        dbms_output.put_line('  ' || v_substancia || ' - ' || v_qtt);
        p_anterior := v_id;
    end loop;
END;
/