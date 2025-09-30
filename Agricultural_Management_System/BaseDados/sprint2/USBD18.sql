create or replace function obter_lista_operacoes(
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

BEGIN
select id into id_instalacao from Instalacao where designacao = campo;
if id_instalacao is null then
            raise campo_inexistente;
end if;

        if to_date(data_inicio,'DD-MM-YYYY') > CURRENT_DATE then
            raise data_invalido;
end if;

        if to_date(data_fim,'DD-MM-YYYY') > CURRENT_DATE then
            raise data_invalido;
end if;

open v_rc for
select o.id, o.data, t.id, t.tipo
from instalacao i inner join Parcela on i.id = Instalacaoid
                  inner join Operacoes o on Instalacaoid = o.ParcelaInstalacaoid
                  inner join Tipo_Operacao t on o.Tipo_Operacaoid = t.id
where i.designacao = campo and o.data between to_date(data_inicio,'DD-MM-YYYY') and to_date(data_fim,'DD-MM-YYYY')
order by t.id;
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
    v_data date;
    v_idtipo number(10);
    v_tipo varchar(255);
    p_nome varchar(255);
    t_anterior number(10);
BEGIN
    v_rc := obter_lista_operacoes('Campo Novo', '20-05-2023', '06-11-2023');
    loop
fetch v_rc into v_id, v_data, v_idtipo, v_tipo;
        exit when v_rc%notfound;
        if t_anterior <> v_idtipo or t_anterior is null then
            dbms_output.put_line(v_tipo || ':');
end if;
        p_nome:='';
        if v_tipo = 'Aplicação fitofármaco' then
select Plantaid into p_nome from Aplicacao where operacoesid = v_id;
if p_nome is not null then
select (e.nome || ' ' || p.variedade) into p_nome from Aplicacao v
                                                           inner join Planta p on v.Plantaid = p.id
                                                           inner join Especie e on p.Especieid = e.id
where v.operacoesid = v_id;
end if;
        elsif v_tipo = 'Fertilização' then
select Plantaid into p_nome from Fertilizacao where operacoesid = v_id;
if p_nome is not null then
select (e.nome || ' ' || p.variedade) into p_nome from Fertilizacao v
                                                           inner join Planta p on v.Plantaid = p.id
                                                           inner join Especie e on p.Especieid = e.id
where v.operacoesid = v_id;
end if;
        elsif v_tipo = 'Colheita' then
select (e.nome || ' ' || p.variedade) into p_nome from Colheita v
                                                           inner join Fruto f on v.Frutoid = f.id
                                                           inner join Planta p on f.Plantaid = p.id
                                                           inner join Especie e on p.Especieid = e.id
where v.operacoesid = v_id;
elsif v_tipo = 'Semeadura' then
select Plantaid into p_nome from Semeadura where operacoesid = v_id;
if p_nome is not null then
select (e.nome || ' ' || p.variedade) into p_nome from Semeadura v
                                                           inner join Planta p on v.Plantaid = p.id
                                                           inner join Especie e on p.Especieid = e.id
where v.operacoesid = v_id;
end if;
        elsif v_tipo = 'Rega' then
            p_nome:='';
else
select Plantaid into p_nome from Agricultura where operacoesid = v_id;

if p_nome is not null then
select (e.nome || ' ' || p.variedade) into p_nome from Agricultura v
                                                           inner join Planta p on v.Plantaid = p.id
                                                           inner join Especie e on p.Especieid = e.id
where v.operacoesid = v_id;
end if;
end if;

        dbms_output.put_line('  ' || v_data || ' operação de ' || v_tipo || ' ' || p_nome);

        t_anterior := v_idtipo;
end loop;
END;
/