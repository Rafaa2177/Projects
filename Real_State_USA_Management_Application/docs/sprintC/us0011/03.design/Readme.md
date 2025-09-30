# US 011 - As an agent,I want to list real estate purchase orders to accept or decline a purchase order for a property. After accepting or declining, an email notification should be sent to the customer.

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...                         | Answer             | Justification (with patterns)                                                                                 |
|:---------------|:--------------------------------------------------------------------|:-------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1 		      | 	... interacting with the actor?                                    | SetOrderUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                                           | SetOrderController | Controller                                                                                                    |
| 		  		         | 	                        ... knowing the user using the system?     | UserSession        |                                                                                                               |
| 			  		        |                                                                     | User               | Information Expert                                                                                            |
| 			  		        | ... saving the announcement and the respective purchase order?					 | Employee           | Pure Fabrication                                                                                              |
| Step 2	        | 							                                                             |                    |                                                                                                               |
| Step 3  	      | 	...saving the respective id order?						                           | Order              | Information Expert                                                                                            |
| 	Step 4 	      | 	...saving the option selected?                                     | System             | Information Expert                                                                                            |
| Step 5 		      | 	                                                                   |                    |                                                                                                               |
| Step 6  		     | 	... validating all data?                                           | Employee           | Information Expert                                                                                            |
| 		             | 				...getting the client?			                                       | Employee           | Information Expert                                                                                            |              
| 		             | 	... sending an email notification to the client?                   | Employee           | Information Expert                                                                                            | 
| 			  		        | 	... removing orders of the announcement?                           | Announcement       |       Information Expert                                                                                 | 
| 	Step 7 		  		 | 	... informing operation success?                                   | SetOrdersUI        | Information Expert                                                                                        | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Order
 * Announcement
 * Employee

Other software classes (i.e. Pure Fabrication) identified: 

 * SetOrderUI  
 * SetOrderController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us0011-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us0011-class-diagram.svg)

