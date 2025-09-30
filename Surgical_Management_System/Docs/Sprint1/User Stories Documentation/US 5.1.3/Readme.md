# US 5.1.3 - Register for HealthCare application


## 1. Requirements Engineering

### 1.1. User Story Description

As a Patient, I want to register for the healthcare application, so that I can create a user profile and book appointments online.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

### `Attributes:`

   * First Name;
   * Last Name;
   * Full name;
   * Date of Birth;
   * Gender;
   * Medical Record Number (ID);
   * Email;
   * Phone Number;
   * Allergies/ Medical Conditions (optional);
   * Emergency Contact;
   * Appointment History (List of previous and upcoming appointments);


### `Rules:`

  * A patient must be unique in terms of `Medical Record Number`, `Email` and `Phone`.
  * Sensitive data (like medical history) must comply with GDPR, allowing patients to control their data access.

**From the client clarifications:**

> **Question:** 
>
> **Answer:** 


### 1.3. Acceptance Criteria

* **AC1:** Patients can self-register using the external IAM system.
* **AC2:** During registration, patients provide personal details (e.g., name, email, phone) and create a profile. 
* **AC3:** The system validates the email address by sending a verification email with a confirmation link.
* **AC3:** Patients cannot list their appointments without completing the registration process.


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

![System Sequence Diagram](system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

