# US010 -  Place an order to purchase the property,

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                                                  | Question: Which class is responsible for...      | Answer                  | Justification (with patterns)                                                                                 |
|:--------------------------------------------------------------------------------|:-------------------------------------------------|:------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  Chooses one property to buy 		                                          | ... instantiating the class that handles the UI  | RegisterOfferUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		                                                                         | 	... interacting with the client?	               | RegisterOfferUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		                                                                         | ... coordinating the US?	                        | RegisterOfferController | Controller                                                                                                    |
| 			  		                                                                         | ... knowing the user using the system?	          | UserSession             | IE: cf. A&A component documentation.                                                                          |
| 			  		                                                                         | ...displaying the UI for the user input Data?    | RegisterOfferUI         | IE: is responsible for user interactions                                                                      |
| 			  		                                                                         | ...temporarily keeping the input Data?           | RegisterOfferUI         | IE: is responsible for user interactions                                                                      |
| 			  		                                                                         | ... getting the propertyRepository               | Repositories            | IE: is responsible for saving all repositories                                                                |
| 			  		                                                                         | ...validating input data?	                       | RegisterOfferUI         | IE: is responsible for user interactions                                                                      |
| Step 2  displaying available announcements and asking to select an announcement | ...showing available announcement?               | RegisterOfferUI         | UI is responsible for user interactions.                                                                      |
| 			  		                                                                         | ...asking to select an announcement?             | RegisterOfferUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 3  chooses an announcement                                                 | ...chooses the announcement?                     | RegisterOfferUI         | UI is responsible for user interactions.                                                                      |
| Step 4  checks if order is pending		                                            | ...check if order is pending?                    | RegisterOfferController | Controller.                                                                                                   |
| Step 5  Requests order amount		                                                 | 	... requesting order amount?                    | RegisterOfferUI         | UI is responsible for user interactions.                                                                      |
| 			  		                                                                         | 	... checking if order amount respects criteria  | RegisterOfferController | IE: is responsible for checking the amount order requested by the client, Pure Fabrication.                   |
| Step 6 asks the user to confirm the order		                                     | asking the user to confirm the order?							     | RegisterOfferUI         | UI is responsible for user interactions                                                                       |              
| 		                                                                              | 	...checking if the previous order was declined? | RegisterOfferController | IE: is responsible for checking if the previous order was declined or accepted.                               | 
| Step 7 asks the user to submit the order	                                       | 	... asking the user to submit the order?        | RegisterOfferUI         | UI is responsible for user interactions.                                                                      | 
|                                                                                 | 	... confirming inserted data?                   | RegisterOfferUI         | UI is responsible for user interactions.                                                                      | 
|                                                                                 | 	... submits the order?                          | RegisterOfferUI         | UI is responsible for user interactions.                                                                      | 

    
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Repositories
 * PurchaseOrderRepository
 * AnnouncementRepository

Other software classes (i.e. Pure Fabrication) identified: 

 * RegisterOfferUI 
 * RegisterOfferController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.
![Sequence Diagram - Full](svg/us010-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us010-class-diagram.svg)