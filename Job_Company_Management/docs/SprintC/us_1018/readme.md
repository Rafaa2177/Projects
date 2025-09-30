# 1018 - As Customer Manager, I want to execute the process that evaluates (grades) the interviews for a job opening.

## 1. Context
### 1.1. User Story Description

The support for this functionality must follow specific technical requirements, specified in LPROG. The ANTLR tool should be used (https://www.antlr.org/).

### 1.2. Customer Specifications and Clarifications

>* **Q199 Ribeiro – US1018 – Relativamente à US1018, após a execução do processo de avalição de todas as entrevistas da job opening, a fase em que esta se encontra deve ser automaticamente mudado para "Result" ou deve ser mantida em "Analysis" e apenas pode ser mudada pela execução da US1010?**
A199. A US1018 não deve alterar a fase actual. A US1010 permite fazer a mudança de fases do processo de recrutamento.

>* **Q214 Pedro – US1017/1018 – O nosso grupo tem uma dúvida em relação ao processamento dos ficheiros de respostas dos candidatos para a entrevista. No caso de upload de um ficheiro, se a pergunta que requer um número como resposta for preenchida com um formato inválido, por exemplo, uma letra, devemos considerar isso como um formato inválido na US 1017 (e pedir para o user voltar a dar upload a um ficheiro válido) ou devemos, na US1018, considerar que está incorreta e atribuir 0 pontos automaticamente para essa resposta inválida? Isto é, na US 1017, devemos apenas verificar o formato do ficheiro ou devemos verificar também se as respostas são preenchidas com o tipo de dados correto?**
A214. O caso mencionado deve ser considerado um erro de validação do ficheiro (ou seja, o ficheiro submetido não corresponde à gramática definida).


## 2. Requirements

**AC1:** The grade must be only available for job openings in phase "interview".

**AC2:** The grade system must contemplate the multiple choice options, and differentiate the 'subgrade' among the same answer number.

**AC3:** The grade is a range between 0 to 100.

**AC4:** The CM must return the grade to every candidate/application.

--- 

**Input Data:**
- Selection of a job opening;

**Output Data:**
- Every application with the correspondent grade.

