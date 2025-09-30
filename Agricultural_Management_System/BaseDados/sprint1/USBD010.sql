WITH Subquery AS (
SELECT E.Designacao, COUNT(O.Tipo_Operacaoid) AS QTT FROM Exploracao E
JOIN Operacoes O ON E.id = O.Exploracaoid
JOIN Tipo_Operacao TPO ON O.Tipo_Operacaoid = TPO.id
WHERE TPO.tipo = 'Rega' AND O.data BETWEEN TO_DATE('28/04/2014', 'dd/mm/yyyy') AND TO_DATE('01/01/2028', 'dd/mm/yyyy')
GROUP BY E.Designacao
)
SELECT Designacao, QTT
FROM Subquery
WHERE QTT = (SELECT MAX(QTT) FROM Subquery)