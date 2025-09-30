# User Story: US 017 - I want to list all deals made

## 3. Design - User Story Realization

### 3.1. Rationale

SSD - Alternative 1 is adopted.

| Interaction                          | Question: Which class is responsible for... | Answer              | Justification (with patterns) |
|--------------------------------------|---------------------------------------------|---------------------|-------------------------------|
| Step 1: Select display deals option  | ... interacting with the actor?             | ListDealsUI         | Pure Fabrication              |
|                                      | ... coordinating the US?                    | ListDealsController | Controller                    |
| Step 2: Ask the algorithm option     | ...request sorting algorithm                | ListDealsUI         | Pure Fabrication              |
| Step 3: Selects the Algorithm option | ...saving and validating selected option    | ListDealsUI         | Pure Fabrication              |
| Step 4: Ask the Order option         | ...request order options                    | LiSTDealsUI         | Pure Fabrication              |
| Step 5: Selects the Order option     | ...saving and validating selected option    | ListDealsUI         | Pure Fabrication              |
| Step 6: Ask to confirm the operation | ...request confirmation                     | ListDealsUI         | Pure Fabrication              |
| Step 7: Confirms the operation       | ...saving                                   | ListDealsUI         | Pure Fabrication              |
|                                      | ...get list sorted                          | ListDealsController |    Controller                 |
| Step 8: Shows the deals made         | ... informing operation success?            | ListDealsUI         | Pure Fabrication              |

### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

- Deals
- Bubble Sort
- Selection Sort

Other software classes (i.e., Pure Fabrication) identified:

- ListDealsUI
- ListDealsController

### 3.2. Sequence Diagram (SD)

![us017-sequence-diagram.svg](svg%2Fus017-sequence-diagram.svg)

