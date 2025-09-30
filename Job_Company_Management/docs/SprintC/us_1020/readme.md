# 1020 - As Customer Manager, I want to publish the results of the selection of candidates for a job opening, so that candidates and customer are notified by email of the result.

## 1. Context
### 1.1. User Story Description

N/a

### 1.2. Customer Specifications and Clarifications

>* Question 48: US1016 e US1020, relativamente ao envio das notificações por email, é necessário guardar que esse envio foi feito?
>    * Answer 48: No documento nada de explicito é dito sobre este assunto. No entanto, do ponto de vista de gestão do processo da jobs4u parece-me adequado que essa informação fique registada.

>* Question 51: US 1020, qual é o formato para essa publicação
>  * Answer 51: A publicação refere-se a informar os candidatos e o cliente, por email. Os candidatos que são selecionados devem receber um email a indicar que para a sua candidatura ao job opening foram selecionados e irão ser contactados pela empresa. No que se refere à empresa, esta deve receber um email com a lista dos candidatos selecionados que deve incluir o nome e dados de contacto do candidato.

>* Question 190: US1020 – Regarding the selection of candidates, should we assume that the first N candidates in the ranking (where N is the number of job vacancies) are chosen, or should we allow the customer manager to select the N candidates
>    * Answer 190: The first option (using the results from US1013).

>* Question 224: US 1020 - Esta US pede que seja publicado os resultados das candidaturas, a minha pergunta seria se este processo só pode acontecer quando a job opening estiver encerrada ou se executar esta operação terminaria a job opening.
>  * Answer 224: Esta US é relativa à última fase do processo. Se as notificações executarem todas com sucesso em princípio já não existe mais nada a fazer neste processo.

## 2. Requirements


### 2.1. Acceptance Criteria

N/A

### 2.2. Found out Dependencies

* There is a dependency with US 1002 and 2002. Since a job opening must exist to have candidate applications, in order to notify its results.

### 2.3 Input and Output Data

*Input Data:*
* Select a job opening

*Output Data:*
* List of all job openings
* Operation status
