# US 5.1.15 - List Staff Profiles


## 1. Requirements Engineering

### 1.1. User Story Description

As an Admin, I want to list/search staff profiles, so that I can see the details, edit, and remove staff profiles.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

### `Attributes:`

* Name;
* Specialization;
* Email;

### `Rules:`

* The results must be paginated;

**From the client clarifications:**

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

* **AC1:** Admins can search staff profiles by attributes such as name, email, or specialization.
* **AC2:** The system displays search results in a list view with key staff information (name, email, specialization).
* **AC3:** Admins can select a profile from the list to view, edit, or deactivate.
* **AC4:** The search results are paginated, and filters are available for refining the search results.


### 1.4. Found out Dependencies

* `This user story has a dependency with US 5.1.12, As an Admin, I want to create a new staff profile, so that I can add them to the
hospital’s roster, because the staff profile must be created before it can be listed for editing, viewing or deactivation.`

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * Name;
  * Specialization;
  * Email;


**Output Data:**

* The profile was edit successfully;
* The profile was deactivated successfully;


### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](system-sequence-diagram.svg)

### Sequence Diagram (SD)

![Sequence Diagram](sequence-diagram.svg)


### 1.7 Other Relevant Remarks

