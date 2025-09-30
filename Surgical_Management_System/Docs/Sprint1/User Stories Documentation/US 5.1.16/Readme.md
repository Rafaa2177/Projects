# US 5.1.16 - Request Operations


## 1. Requirements Engineering

### 1.1. User Story Description

As a Doctor, I want to request an operation, so that the Patient has access to the
necessary healthcare.
### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

### `Attributes:`

* PatientId;
* DoctorId;
* OperationType;
* Deadline;
* Priority
* 
### `Rules:`

- Doctors can create an operation request by selecting the patient, operation type, priority, and
  suggested deadline.
- The system validates that the operation type matches the doctor’s specialization.
- The operation request includes:
- Patient ID
- Doctor ID
- Operation Type
- Deadline
- Priority
- The system confirms successful submission of the operation request and logs the request in
  the patient’s medical history.
**From the client clarifications:**

> **Question:**
>
> **Answer:**



### 1.3. Acceptance Criteria

- Doctors can create an operation request by selecting the patient, operation type, priority, and
  suggested deadline.
- The system validates that the operation type matches the doctor’s specialization.
- The operation request includes:
- Patient ID
- Doctor ID
- Operation Type
- Deadline
- Priority
- The system confirms successful submission of the operation request and logs the request in
  the patient’s medical history.

### 1.4. Found out Dependencies

* This US has dependencies in creating Users and Patients
### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * PatientId
  * DoctorId
  * OperationType
  * Deadline
  * Priority


**Output Data:**

* The request was successfull;


### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](system-sequence-diagram.svg)

### Sequence Diagram (SD)

![Sequence Diagram](sequence-diagram.svg)


### 1.7 Other Relevant Remarks

