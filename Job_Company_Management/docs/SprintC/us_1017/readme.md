# 1017 - As Customer Manager, I want to upload a text file with the candidate responses for an interview

## 1. Context
### 1.1. User Story Description

See NFR09(LPROG).

### 1.2. Customer Specifications and Clarifications

>* Question 214: US1017/1018 – O nosso grupo tem uma dúvida em relação ao processamento dos ficheiros de respostas dos candidatos para a entrevista. No caso de upload de um ficheiro, se a pergunta que requer um número como resposta for preenchida com um formato inválido, por exemplo, uma letra, devemos considerar isso como um formato inválido na US 1017 (e pedir para o user voltar a dar upload a um ficheiro válido) ou devemos, na US1018, considerar que está incorreta e atribuir 0 pontos automaticamente para essa resposta inválida? Isto é, na US 1017, devemos apenas verificar o formato do ficheiro ou devemos verificar também se as respostas são preenchidas com o tipo de dados correto
>  * Answer 214: O caso mencionado deve ser considerado um erro de validação do ficheiro (ou seja, o ficheiro submetido não corresponde à gramática definida).

>* Question 227: US 1017 - Em termos "upload" certamente passa pela verificação com a gramática e após sucesso colocar o ficheiro das respostas da entrevista junto da pasta com os ficheiros da "Application" certo?
>    * Answer 227:  Sim, a sintaxe deve ser verificada e, se tudo estiver correto, o ficheiro deve ser “importado” para o sistema de forma a que possa ser usado posteriormente, por exemplo, no âmbito da US 1018. Qual a solução para o “importar” faz parte da vossa solução. Idealmente julgo que faria sentido que ficasse integrado na base de dados. Não sendo possível, penso que é aceitável que fique numa pasta/diretório de um servidor.   

## 2. Requirements

### 2.1. Acceptance Criteria

**AC1:** Given answers must align with system's specifications.

### 2.2. Found out Dependencies

* There is a dependency with US 1002 and 2002. Since a job opening must exist for a candidate to apply, in order to upload his responses for the respective interview model.

### 2.3 Input and Output Data

*Input Data:*
* Select a job opening
* Select a candidate
* Type the responses file path

*Output Data:*
* List of all the job openings
* List of all the candidates for the chosen job opening
