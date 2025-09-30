SELECT E.designacao, SUM(o.quantidade) AS QTT, P.Variedade AS Numero_Produtos_Colhidos FROM Exploracao E
JOIN Operacoes O ON E.id = O.Exploracaoid
JOIN Planta P ON O.Plantaid = P.id
WHERE O.Tipo_Operacaoid = 7 and  o.Data >= TO_DATE('2010-01-01', 'YYYY-MM-DD') AND o.Data <= TO_DATE('2023-12-31', 'YYYY-MM-DD') AND E.designacao = 'Campo da bouça'
GROUP BY E.designacao, P.Variedade
ORDER BY E.designacao;
