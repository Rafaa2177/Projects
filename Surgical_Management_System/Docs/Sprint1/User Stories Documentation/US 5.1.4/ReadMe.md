# US 5.1.8 - Update User Profile


## 1. Requirements Engineering

### 1.1. User Story Description

As a Patient, I want to update my user profile, so that I can change my personal details and preferences.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**


>

**Attributes**:
* First Name
* Last Name
* Full Name
* Date Of Birth
* Gender
* Medical Record Number
* Contact Information
* Allergies/ Medical Conditions
* Emergency Contact
* Appointment History


**Rules**:
* A patient must be unique in terms of Medical Record Number, Email and Phone.
* Sensitive data(like medical history)must comply with GDPR,allowing patients to control their data access.

**From the client clarifications:**





### 1.3. Acceptance Criteria

* **AC1:** Patients can log in and update their profile details (e.g., name, contact information, preferences).
* **AC2:** Changes to sensitive data, such as email, trigger an additional verification step (e.g., confirmation email).
* **AC3:** All profile updates are securely stored in the system.
* **AC4:** The system logs all changes made to the patient's profile for audit purposes.


### 1.4. Found out Dependencies

* There is a dependency with US 5.1.3. because to update a user profile it is necessary to have a user profile created.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * 
    * 
    *
    * 
    * 
    * 


**Output Data:**
* 
* 


### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](SystemSequenceDiagram.png)

### 1.7. Sequence Diagram (SD)

![Sequence Diagram - Alternative One](SequenceDiagram.png)


### 1.8 Other Relevant Remarks
n\a

