SELECT
    E.designacao AS Parcela,
    P.designacao AS Tipo_de_Fator,
    COUNT(*) AS Numero_de_Fatores
FROM
    Exploracao E
        JOIN
    Operacoes O ON E.id = O.Exploracaoid
        JOIN
    Fator_Producao FP ON O.Regaid = FP.Regaid
        JOIN
    Produto P ON FP.produtoId = P.id
WHERE
        E.idExploracao = 107
  AND
        O.Data >= TO_DATE('2010-01-01', 'YYYY-MM-DD') AND O.Data <= TO_DATE('2023-12-31', 'YYYY-MM-DD')
GROUP BY
    P.tipo, E.designacao
ORDER BY
    P.designacao