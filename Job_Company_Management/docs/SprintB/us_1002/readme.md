# US 1002 - As Customer Manager, I want to register a job opening

## 1. Context
### 1.1. User Story Description

Alternatively this can be achieved by a bootstrap process.

### 1.2. Customer Specifications and Clarifications

* Question 7: No contexto em que o Customer Manager regista uma oferta de emprego, como são selecionados/definidos os requisitos para essa job offer?
  * Answer 7:  O Customer manager regista a job opening (US 1002) e de seguida (normalmente) seleciona qual o requirements specification que é adequado a esse job opening. O requirements specification será um dos que foi “criado” pelo language engineer e registado no sistema.

* Question 25: No job opening é tudo de preenchimento obrigatório ou existem opcionais?
  * Answer 25:  Os campos referidos na secção 2.2.2 são de preenchimento obrigatório. Os requirements vão ser dinâmicos uma vez que dependem do requirements specification selecionado para aquele job opening (que se baseia numa linguagem).

* Question 35: Customer tem que ter morada e nome da empresa ou se basta essa informação estar no job opening?
  * Answer 35: Devemos registar nome e morada do customer. Para cada job opening a morada pode ser especifica (e diferente da morada do customer).

* Question 77: US1002 Critérios de Aceitação – Relativamente à US1002 - "As Customer Manager, I want to register a job opening", existe algum critério de aceitação ainda não mencionado, relacionado com os atributos? Ou fica ao critério da equipa de desenvolvimento consoante boas práticas e senso comum? Alguns exemplos: - O "Number of Vacancies" não poder ser menor ou igual a 0 ou pode ser opcional; - A "Description" ter um tamanho de caracteres limite ou ser opcional;
  * Answer 77: Sobre se existem critérios de aceitação não mencionados não me vou pronunciar, faz parte do processo descobrirem. Eu sugeria usarem algo mais que o senso comum

* Question 79: US1002 - Could we use the same interview model for more than one job opening?
  * Answer 79: Yes.

* Question 88: US1002 – Quando o Customer Manager regista uma job offer, é ele que cria as requirement specifications e as interview models ou é-lhe dada uma lista destes para ele selecionar?
  * Answer 88: Existe a US1002 e as US1009 e US1011. Penso que está claro qual a responsabilidade de cada uma. A criação dos modelos das entrevistas e dos requisitos é um caso de uso especifico e com um US especifica para registar no sistema os respectivos plugins (US1008).

* Question 91: [1002, 1007, 1009] - Validez de uma Job Openings – A nossa questão principal seria: quando é que uma job opening é considerada válida? Tendo em conta as funcionalidades 1002, 1007, 1009, surgiu-nos uma duvida relativamente às job openings e à sua constituiçao. Na US1002, é suposto resgistar uma job opening e apenas depois, na US1009, devemos selecionar os requirements specifications para a dada job opening. Posto isto, aquando o registo da job opening, esta não iria possuir toda a informaçao obrigatória como requerido. Assim sendo, deveria haver uma ligação direta entre estas user stories de forma a que, aquando o registo, passamos automaticamente a selecionar os requirements obtendo assim uma job opening válida? Adicionalmente, queremos esclarecer se o recruitment process é algo obrigatório para a validez de um job opening.
  * Answer 91: O product owner não é especialista do dominio da solução (apenas têm conhecimentos do problema) mas, quanto à primeira questão, sugere que talvez user stories não sejam (podem não ser) opções de menu “distintas”. Quanto à segunda questão (recruitment process) julgo que também está mais ligada à solução que ao problema, pelo que não vou sugerir nada que possa até complicar mais do que esclarecer.


* Question 109: [US1002] Modo de Trabalho e Tipo de Contrato- Numa job opening, o tipo de contrato e o modo de trabalho são elementos fixos, na medida em que os tipos apresentados na documentação são estáticos e não mudam, ou poderão haver mais tipos deste dois elementos?
  * Answer 109: Os mais “normais” serão os que aparecem na documentação. Mas penso que faz sentido o sistema aceitar outros diferentes caso o Customer Manager assim o entender

* Question 114: US1002 - Um customer manager tem a responsabilidade de criar job openings para os customers em que é responsável. Na hora da criação da job opening, como é que ele refere qual é o customer daquela job opening? Seleciona um customer dentro dos que está responsável?
  * Answer 114: Sim, pode ser como indica

* Question 114: US1002, 1007, 1009 - Na US1002 ao registar um job opening é imperativo selecionar também o job requirement e/ou as fases de recrutamento?
  * Answer 114: São US diferentes e, do meu ponto de vista, podem ser executadas em momentos diferentes. Apenas lembro que, como é evidente, desejo que o sistema esteja sempre num estado consistente.


## 2. Requirements

AC1 - Job opening required fields:
- Job Reference;
- Title;
- Contract Type;
- Mode;
- Address;
- Company;
- Number of vacancies;
- Description;
- Requirements (at least one).

## 3. Analysis

TO DO

## 4. Design

### 4.1. Realization

TO DO


### 4.2. Tests

TO DO


@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
...
}
`


## 5. Implementation

TO DO


## 6. Integration/Demonstration

TO DO


## 7. Observations

N/A