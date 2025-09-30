# User Story: US 017 - I want to list all deals made

## 1. Requirements Engineering

### 1.1. User Story Description
As a network manager, I want to list all deals made.

### 1.2. Customer Specifications and Clarifications
From the specifications document:

N/A

From the client clarifications:
- The default order of the deals when displaying them to the network manager should be sorted from the most recent ones to the oldest ones.
- A deal takes place when the proposed purchase/renting is accepted.
- The deals made in all branches should be included.

### 1.3. Acceptance Criteria
AC1: The actor should be able to sort all properties by property area (square feet) in descending/ascending order.
AC2: Two sorting algorithms should be implemented, and the network manager should be able to choose the sorting algorithm manually.
AC3: The worst-case time complexity of each algorithm should be documented in the application user manual.

### 1.4. Found Dependencies
- Dependency with US018 (analyzing the deals listed in US017).

### 1.5. Input and Output Data
**Input Data:**
- Selected data: Deals List, Sorting Option, Sorting Algorithm Option

**Output Data:**
- List of deals made

### 1.6. System Sequence Diagram (SSD)
![us017-system-sequence-diagram.svg](svg%2Fus017-system-sequence-diagram.svg)