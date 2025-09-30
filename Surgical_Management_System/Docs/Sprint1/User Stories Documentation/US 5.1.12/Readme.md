# US 5.1.12 - Create a new staff profile


## 1. Requirements Engineering

### 1.1. User Story Description

As an Admin, I want to create a new staff profile, so that I can add them to the hospital’s roster.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

### `Attributes:`

   * First Name;
   * Last Name;
   * Specialization;
   * Phone Number;
   * Email.

### `Rules:`

  * A staff must be unique in terms of `License Number`, `Email` and ‘Phone’.
  * Staff define the availability slots, e.g. slot 1: 2024-09-25:14h00-18h00; slot2: 2024-09-25:19h00/2024-09-26:02h00.
  * The availability slots remain unchanged when appointments are used for an appointment.
  * Staff can handle multiple appointments but cannot be double-booked at the same time.

**From the client clarifications:**

> **Question:** Boa tarde, gostaria de saber se é objetivo o sistema diferenciar as especializações para cada tipo de staff. Ou seja se temos de validar que a especialização x só pode ser atribuída por exemplo a um membro do staff que seja doctor, ou se consideramos que qualquer especialização existente no sistema pode ser atribuída a qualquer staff ficando da autoria do responsável por criar os perfis atribuir especializações válidas de acordo com a role do staff.
>
> **Answer:** as especializações são independentes do professional ser médico ou enfermeiro

>  **Question:** Boa noite, Professor.
-Médicos e enfermeiros podem ter apenas uma especialidade ou podem ser especialistas em várias?
-Quem faz parte do staff? Toda a gente na sala de operação? Se sim, todos eles tem as suas respetivas especialidades, incluindo técnicos?
>
> **Answer:** um médico ou enfermeiro apenas tem uma especialização, no staff apenas consideramos médicos e enfermeiros

> **Question:** How should the specialization be assigned to a staff? Should the admin write it like a first name? Or should the admin select the specialization?
> 
> **Answer:** the system has a list of specializations. Staff is assigned a specialization from that list

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**


### 1.3. Acceptance Criteria

* **AC1:** Admins can input staff details such as first name, last name, contact information, and specialization.
* **AC2:** A unique staff ID (License Number) is generated upon profile creation. 
* **AC3:** The system ensures that the staff’s email and phone number are unique.
* **AC3:** The profile is stored securely, and access is based on role-based permissions.


### 1.4. Found out Dependencies

* `No dependencies.`

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * First Name;
    * Last Name;
    * Specialization;
    * Phone Number;
    * Email.


**Output Data:**

* Profile was created successfully.


### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](Level 1/process-view-System Sequence Diagram.svg)

### Sequence Diagram (SD)

![Sequence Diagram](Level 2/process_view-Sequence Diagram.svg)


### 1.7 Other Relevant Remarks

