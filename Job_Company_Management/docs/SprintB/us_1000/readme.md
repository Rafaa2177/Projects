# US 1000 - As Administrator, I want to be able to register, disable/enable, and list users of the backoffice.

## 1. Context
### 1.1. User Story Description

Alternatively this can be achieved by a bootstrap process.

### 1.2. Customer Specifications and Clarifications

* Q10 - O Admin é responsável por gerir apenas Customer managers ou outros, como por exemplo Operators? E qual o significado dessa responsabilidade?
  * *A ideia é que o Admin fará a gestão desses utilizadores (e no limite também dos Operators). Na prática, as US que remetem para essas funcionalidades, podem ser “substituidas” por um processo de “bootstrap” que faça inicializações na base de dados para suportar esses utilizadores/papeis (tal como mencionado no texto das US).*

* Q10 - O Admin é responsável por gerir apenas Customer managers ou outros, como por exemplo Operators? E qual o significado dessa responsabilidade?
  * *A ideia é que o Admin fará a gestão desses utilizadores (e no limite também dos Operators). Na prática, as US que remetem para essas funcionalidades, podem ser “substituidas” por um processo de “bootstrap” que faça inicializações na base de dados para suportar esses utilizadores/papeis (tal como mencionado no texto das US).*

* Q11 – No enunciado não está explicita a informação a recolher para os Customers? Qual a informação necessária? E quando aos funcionários da empresa?

  * *De facto isso não está explicito. No entanto, são referidos no nome da empresa e o seu endereço no âmbito de um job opening. Quanto aos utilizadores (representante da empresa que acede à Customer App) eu diria que serão dados similares ao do Candidate. Quando aos funcionários da empresa, eu diria que é importante garantir que é usado o email para identificar qualquer utilizador do sistema. Penso que será importante para cada utilizador termos o nome completo assim como um short user name (que deverá ser único). Actualização em 2024-03-21: O Product Owner reconsiderou e decidiu que o short user name é dispensável uma vez que para autenticação dos utilizadores se deve usar apenas o email e a password.*

* Q72 - Multiple enable/disable (US1000) – Can a user (from the backoffice, for example) be enabled/disabled multiple times?
  
  * *Yes.*

* Q23 – A mudança de estado é referente ao candidato ou à candidatura individual e como se relaciona com o enable/disable dos utilizadores?

  * *O enable/disable dos users é apenas para controlar os acessos ao sistema. O estado, no processo de candidatura, é o estado da candidatura de um candidato a um job opening, não está diretamente relacionado com o enable/disable dos users.*

## 2. Requirements

**AC1:** As an Admin, I want to be able the enable/disable an user multiple times.

The user must have the following attributes:
* First Name - String
* Last Name - String
* Address - String
* Email - String 
* Password - String 
* Phone Number - String


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