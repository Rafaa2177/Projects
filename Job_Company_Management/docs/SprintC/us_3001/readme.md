# US 1015 - As Customer Manager, I want to execute the process of verification of requirements of applications for a job opening.

## 1. Context
### 1.1. User Story Description

N/A

### 1.2. Customer Specifications and Clarifications

* Question : Q174 Artur – US3001 - Na US 3001 pretende que o candidato seja notificado na sua aplicação quando o estado de uma aplicação mudar. De que forma pretende que o candidato seja notificado? E caso o candidato não esteja a correr a aplicação, essa notificação é perdida?
    * Answer : A174 O candidato deve ser notificado quando a sua “app” está em execução. Relativamente a notificações que “acontecem” quando não está a correr a aplicação, seria interessante que as recebesse da próxima vez que executasse a aplicação.

* Question : Q175 Isabel – US3001 - Questão âmbito notificações - O candidato será notificado, quando a sua candidatura mudar de estado. O que se entende por notificado, é receber um email, quando entra na aplicação tem uma fila de 'inbox' ? Quando a aplicação está ligada recebo email? É o candidato que faz o pedido(cliente) (Pop) inicia assim a comunicação, e recebe a resposta/notificação (servidor). E como encaixo o cenário de notificação(push)?
    * Answer : A175 Ver Q174. Neste caso as notificações são na aplicação do candidato, não são por email.

* Question : Q189	Varela – US3001 – Application State – I'd like some clarifications regarding the state that US3001 mentions. Is it the "accepted/not accepted" state, or a state regarding the phases of the recruitment process of which the application is associated to?
    * Answer : A189. This US is focused on the Candidate perspective. He/she may not be aware of the internal phases of the recruitment process. But he/she is interested in knowing the “external” state of his/her applications. For instance, as a candidate I would like to know if my application was received. Then I would like to know if my application was accepted or not and, finally, if I was selected or not. 

* Question : Q229 Jose Afonso – US 3001 - Segundo a nossa perspetiva sobre esta funcionalidade, achámos melhor o utilizador ter na sua aplicação uma espécie de inbox de notificações. Quando o utilizador está na funcionalidade das notificações este recebe as notificações que tinha e que não foram enviadas porque não estava na aplicação e depois fica á espera de novas notificações que apareçam entretanto, até o utilizador pedir para sair da funcionalidade. Esta abordagem está alinhada com suas expectativas?
    * Answer : A229. Pode ser. Mas não seria possível receber as notificação mesmo não estando nessa “opção de menu”? Sendo uma aplicação “console” limita um pouco a UI, mas não seria possível receber as notificações desde que tenha a aplicação em execução mas só mostrar essas notificações quando o utilizador seleciona a opção? Em termos de UI a diferença é mínima, em termos de implementação pode ser significativa. Talvez esta seja até mais uma questão para RCOMP.

* Question : Q233 Jose Afonso – US 3001 – Notifications - Em questões anteriores sobre esta funcionalidade menciona que como candidate gostaria de saber se a sua candidatura foi recebida, aceite e escolhida. Pedimos que descreva a que se refere quando diz que uma candidatura foi aceite, e quando foi escolhida.
    * Answer : A233. É aceite se passa o processo de verificação de requisitos. É escolhida se após o ranking está dentro dos lugares das vagas para o job opening.