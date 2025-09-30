SELECT tipo,COUNT(*) FROM OPERACOES o
      LEFT JOIN REGA r ON o.regaid=r.id
      LEFT JOIN FATOR_PRODUCAO f ON r.id=f.regaid
      LEFT JOIN PRODUTO p ON f.produtoid=p.id
      LEFT JOIN TIPO_PRODUTO t ON p.tipo_produtoid=t.id
WHERE tipo IS NOT NULL AND o.data BETWEEN TO_DATE('01/12/2019', 'dd/mm/yyyy') AND TO_DATE('15/05/2023', 'dd/mm/yyyy')
GROUP BY tipo