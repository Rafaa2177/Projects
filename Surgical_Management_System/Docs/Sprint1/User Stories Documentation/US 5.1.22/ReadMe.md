# US 5.1.22 - Remove obsolete types of operations


## 1. Requirements Engineering

### 1.1. User Story Description

As an Admin, I want to remove obsolete or no longer performed operation types, so that the system stays current with hospital practices.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Admins can mark operation types as inactive, though they´re not available, remain in historical data.

**From the client clarifications:**

> **Question:** What filters are you looking for in the search/list of staff and patient profiles? And what about operation types?
>
> **Answer:** answered in 2024.10.04 class

> **Question:** 
>
> **Answer:** 

> **Question:** 
>
> **Answer:** 


### 1.3. Acceptance Criteria

* **AC1:** Admins can search for and mark operation types as inactive (rather than deleting them) to preserve historical records.
* **AC2:** Inactive operation types are no longer available for future scheduling but remain in historical data.
* **AC3:** A confirmation prompt is shown before deactivating an operation type.

### 1.4. Found out Dependencies

* This US depends on the US 5.1.20 'As an Admin, I want to add new types of operations, so that I can reflect the available medical procedures in the system.' because to be possible to remove types of operations they need to exist in the first place.


### 1.5 Input and Output Data

**Input Data:**


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us001-system-sequence-diagram-alternative-two.svg)


### 1.7 Other Relevant Remarks

