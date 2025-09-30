# US 006 - To create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...  | Answer                                       | Justification (with patterns)                                                                                 |
|:---------------|:---------------------------------------------|:---------------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?             | PublishAnnouncementUI                        | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                    | PublishAnnouncementController                | Controller                                                                                                    |
| 			  		        | ... knowing the user using the system?       | UserSession                                  | IE: cf. A&A component documentation.                                                                          |
| Step 2  		     | 		...Knowing the list to show				            | RequestRepository                            | Pure Fabrication : Knows all advertisment requests                                                            |
| Step 3  		     | 	...saving the selected announcement request | Announcement                                 | IE: object owns its data.                                                                                     |
| Step 4  		     | 	...asking for comission options             | PublishAnnouncementUI                        | IE: knows what to ask                                                                                         |
| Step 5  		     | 	... saving the selected commission          | Announcement                                 | IE: Advetisement owns its data                                                                                |
| Step 6  		     | 	... publishing a request						              | AnnouncementRepository                       | IE: It keeps all published announcements                                                                      |              
| Step 7  		     | 	... asking to write an email to the owner   | PublishAnnouncementUI                        | IE: Knows what to ask                                                                                         | 
| Step 8         | ... Keeping the message                      | AnnoucementRequest                           | IE: object owns its data                                                                                      |
| Step 9	        | 	...removing the agent from the Request      | AnnouncementRequest                          | IE: object owns its data                                                                                      | 
| Step 10        | ...showing Success message                   | PublishAnnouncementUI                        |     IE: is responsible for user interactions                                                                                                          |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Announcement
 * AnnouncementRequest

Other software classes (i.e. Pure Fabrication) identified: 

 * PublishRequestUI
 * PublishRequestController
 * RequestRepository
 * AnnouncementRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![us008-sequence-diagram.svg](svg%2Fus008-sequence-diagram.svg)



## 3.3. Class Diagram (CD)

![us008-class-diagram.svg](svg%2Fus008-class-diagram.svg)