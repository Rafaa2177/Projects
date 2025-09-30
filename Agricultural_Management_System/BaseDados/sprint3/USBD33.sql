WITH Subquery AS (
    SELECT
        p.variedade,
        SUM(r.quantidade) AS QTT_MIN,
        RANK() OVER (ORDER BY SUM(r.quantidade) DESC) AS ranking
    FROM ciclo_rega cr
             INNER JOIN Ciclo c ON cr.cicloid = c.id
             INNER JOIN Planta p ON c.plantaid = p.id
             INNER JOIN Setor s ON cr.setorid = s.id
             INNER JOIN Rega r ON r.setorid = cr.setorid
             INNER JOIN Operacoes o ON o.id = r.operacoesid
    WHERE o.data BETWEEN cr.data_inicial AND NVL(cr.data_final, o.data)
      AND EXTRACT(YEAR FROM o.data) = 2023
    GROUP BY p.variedade
)
SELECT variedade, QTT_MIN
FROM Subquery
WHERE ranking = 1;