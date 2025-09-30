SELECT
    E.designacao AS Parcela,
    TP.tipo AS Tipo_de_Operacao,
    COUNT(*) AS Numero_de_Operacoes
FROM
    Exploracao E
        JOIN
    Operacoes O ON O.Exploracaoid = E.id
        JOIN
    Tipo_Operacao TP ON TP.id = O.Tipo_Operacaoid
WHERE
        E.idExploracao = 107
  AND
        O.Data >= TO_DATE('2010-01-01', 'YYYY-MM-DD') AND O.Data <= TO_DATE('2020-12-31', 'YYYY-MM-DD')
GROUP BY
    E.designacao, TP.tipo
ORDER BY
    E.designacao