# 1019 - As Customer Manager, I want to get an ordered list of candidates, using the job interview points (grades), to help me analyze the candidates.

## 1. Context
### 1.1. User Story Description

N/a

### 1.2. Customer Specifications and Clarifications

>* Question 169: US1019 - Relativamente a esta user story, "US 1019 - As Customer Manager, I want to get an ordered list of candidates, using the job interview points (grades), to help me analyze the candidates.", a lista que pretende é relacionada a uma job opening correto? A maneira de ordenação é ascendente ou quer uma opção que inclua ascendente e descendente?
>  * Answer 169: Sim, a ordenação é relativa a candidaturas para um job opening. A ordenação deve ser descendente, do que tem maior pontuação para o que tem menor pontuação.
  
>* Question 177: US 1019 Esclarecimento - Na US 1019: As Customer Manager, I want to get an ordered list of candidates, using the job interview points (grades), to help me analyze the candidates. Pretende que para uma determinada Job Opening de um cliente meu, retorno uma lista ordenada de candidatos e suas notas da entrevista. Penso implementar essa funcionalidade da seguinte forma:
>  
>    Job Opening : XXX
>
>    Nome | Email | Grade
>
>    Jane Doe| jane@doe.pt | 85
>    
>    John Doe | john@doe.pt | 70
>    
>    Ou seja com ordenação descendente.
>    
>    Conforme refere Q153 consegue ver numa instancia esta lista, e noutra instancia faz o ranking que achar pertinente.
>    
>    Acha bem
>
>  * Answer 177: Penso que queira fazer referência a Q163. Relativamente ao exemplo que apresenta parece-me que satisfaz o que pretendo.

>* Question 197: US1019 – Na questao Q169 é mencionado para a listagem ser ordenada descendentemente da nota da entrevista (como mencionado tambem na própria US), no entanto, a questão é, como idealiza a ordenação caso a job opening não possua entrevista?
>  * Answer 197: Esta US não faz sentido para processos que não tenham entrevista
  
>* Question 204: US 1019– segundo a resposta A197, devemos então apenas permitir a listagem de job openings que tenham entrevista?
>  * Answer 204: Penso que não percebi bem a referência à listagem de job openings. Esta US não faz sentido para job openings que não tenham entrevista, uma vez que assenta na listagem dos candidatos e dos seus pontos nas entrevista.

## 2. Requirements


### 2.1. Acceptance Criteria

**AC1:** The list is sorted in descending order of the interview points.

### 2.2. Found out Dependencies

* There is a dependency with US 1002 and 2002. Since a job opening must exist to have candidate applications.

### 2.3 Input and Output Data

*Input Data:*
* Select a customer
* Select a job opening

*Output Data:*
* List of all the customers
* List of all the job openings created by the chosen customer
* List of all the top candidates from the chosen job opening


### 3. Sequence Diagram (SD)

![Sequence Diagram](/home/tiago/Documents/IdeaProjects/sem4pi-23-24-2dc4/docs/SprintC/us_1019/svg/SD_US1019.svg)