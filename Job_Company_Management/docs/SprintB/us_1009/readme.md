# US 1009 - As a Costumer Manager, I want to select the requirments specification to be used for a job opening

## 1. Context
### 1.1. User Story Description

### 1.2. Customer Specifications and Clarifications

> *Q70 Rodrigo* – US1009 – Acerca da User Story da seleção do Job Requirement Specification para o Job Opening, será que o Customer Manager poderá escolher um Job Opening que já tenha um Job Requirement Specification?
>
> *A70.* Admito que essa situação seja possível para qualquer user story similar. Ou seja, a situação que descreve é equivalente a qualquer situação em que seja necessário fazer uma seleção mas que o utilizador se tenha enganado e deseje optar por outra opção. Deve-se, no entanto, garantir que o sistema se mantenha num estado consistente.

> *Q85 Leite* – US1007/1009 – Depois de definir os estados de recrutamento para uma JobOpening, o Customer Manager poderá selecionar o Job Requirement Specification para esse mesmo Job Opening? Se sim, até que fase de recrutamento poderá executar a ação?
>
>*A85.* O Customer Manager define as fases do processo de recrutamento, não define os estados. A avaliação de requisitos é feita na fase de Screening. Assim, não percebo bem a questão colocada.


> *Q41 Jorge* - Como é que o Language Engineer faz o interview model e os job requirements? É texto? Ou ele seleciona perguntas para a interview e requirements para a job opening? E isso é quando se está a criar uma entrevista ou uma job opening ou para-se a meio para fazer isso e depois continua se?
>
>*A41.* O language enginner com informação passada pelo customer manager (que obteve do customer) vai desenvolver em java um jar correspondente ao modulo/plugin. Para esse desenvolvimento terá de utilizar técnicas de desenvolvimento de gramáticas/linguagens como o antlr. Esse código ficará num jar que depois o language engineer “instala/regista” na aplicação (US1008, por exemplo, associando um nome ao jar num ficheiro de configuração – “5 anos experiencia java”, “req-model-5-years-java.jar”). A aplicação com essa informação carrega dinamicamente esse jar. Na gramátca usada no jar é que vão estar espelhadas a estrutura das perguntas a usar nesse modelo e sua avaliação. Estas atividades têm de ser feitas antes de se poder fazer a US1008. Esse trabalho é feito “fora” dos sistema, apenas se registando o modelo (quando está pronto) na US1008. A US 1009 e US1011 permitem selecionar modelos a usar (dos que foram devidamente registados no sistema).


> *Q113 Beatriz* - US1006 - Informações do candidato - Na us1006 é necessário mostrar os dados pessoais dos candidatos. Para isso, que informações acha pertinente demonstrar?
>
>*A113.* Será toda a informação pessoal de um candidato que o sistema tenha registado.


> *Q128 Rocha* - US1006- Na us 1006, seria aceitavel perguntar ao costumer manager o id do candidato de que deseja visualizar os dados, ou seria mais pertinente dar uma lista de todos os candidos e deixa-lo escolher dessa lista, assumindo que este não tem maneira de saber o id do candidato de que quer obter dados.
>
>*A128.*Pergunta similar à anterior Q127. Note-se que a US1005 permite obter as candidaturas para uma job opening. Esta US é para mostrar os dados de um candidato. Portanto parece existir forma de aceder ao id do candidato, caso não se saiba qual é. Mais uma vez, espero que apliquem boas práticas de UI/UX.

## 2. Requirements


### 2.1. Acceptance Criteria


### 2.2. Found out Dependencies


### 2.3 Input and Output Data


## 4. Observations

N/A