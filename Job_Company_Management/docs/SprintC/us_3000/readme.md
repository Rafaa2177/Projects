# 3000- As Candidate, I want to list all my applications and their state (including the number of applicants).

## 1. Context
### 1.1. User Story Description

The provided application protocol (Chapter 4) must be used for all the communications between the client applications (Candidate App and Customer App) and the Follow Up Server. Direct interaction between the client applications (Candidate App and Customer App) and the database server is not allowed.

### 1.2. Customer Specifications and Clarifications

>* **Q176 Artur – US3000 - Na US3000 pede que, para alem de listar as aplicações e o estado das mesmas de um candidato, que liste o numero de candidatos. Este numero de candidatos é um somatório da quantidade de candidatos que fizeram uma aplicação para as mesmas Job Openings deste primeiro candidato (que esta a executar o caso de uso)?**
A176 Devem ser listadas todas as “applications” (candidaturas) do candidato, o estado delas, assim como o número de candidaturas que cada job opening teve (assim o candidato tem uma noção da “concorrência” que teve para cada uma das suas candidaturas).



## 2. Requirements

**AC1:** The list is sorted in descending order of applying to a job opening.

**AC2:** The user must login as a candidate.

**AC3:** A message should indicate if there are no applications from the user.

--- 

**Input Data:**
- User login information

**Output Data:**
- A list with all of user's applications including the number of applicants of that job offer.
- In case of no applications, a message indicating that the user does not have applications yet.


