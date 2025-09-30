WITH Subquery AS (
    SELECT FP.Produtoid, P.Designacao, COUNT(FP.Produtoid) AS COUNT_Produto
    FROM Fator_Producao FP
             INNER JOIN Operacoes O ON FP.Regaid = O.Regaid
             INNER JOIN Produto P ON P.id = FP.Produtoid
    WHERE O.data BETWEEN TO_DATE('28/04/2014', 'dd/mm/yyyy') AND TO_DATE('01/01/2028', 'dd/mm/yyyy')
    GROUP BY FP.Produtoid, P.Designacao
)
SELECT Produtoid, Designacao, COUNT_Produto
FROM Subquery
WHERE COUNT_Produto = (SELECT MAX(COUNT_Produto) FROM Subquery);