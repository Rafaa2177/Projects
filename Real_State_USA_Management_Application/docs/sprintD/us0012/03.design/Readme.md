# US 0012 

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...                                                         | Answer               | Justification (with patterns)                                                                                                          |
|:---------------|:----------------------------------------------------------------------------------------------------|:---------------------|:---------------------------------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	...interacting with the actor?                                                                     | LegacyFileUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                          |
| 			  		        | 	... coordinating the US?	                                                                          | LegacyFileController | Controller                                                                                                                             |
| 			  		        | ... has all the repositorys	                                                                        | Repositories	        | IE                                                                                                                                     |
| 	Step 2	       | ... show the list requested by the user?                                                            | LegacyFileUI         | Pure Fabrication                                                                                                                       |
| 	 		           | ... get the list of Files?                                                                          | LegacyFileController | IE:Repositories usage                                                                                                                  |
| Step 3	  		    | ...saving the temporary data                                                                        | LegacyFileUI         | Pure Fabrication                                                                                                                       |
| Step 4       	 | ...interacting with the actor (ask data filter again)?	                                             | LegacyFileUI         | Pure Fabrication                                                                                                                       |
| 		             | ... create the announcements?	                                                                      | LegacyFileController | Controller                                                                                                                             |
| 		             | ... saves the list of announcements?	                                                               | organization         | Repositories usage                                                                                                                     | 	                                                                                                     |                      |                                                                                                                                        | 
LegacyFileRepository
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Organization
 * Task

Other software classes (i.e. Pure Fabrication) identified: 

 * CreateTaskUI  
 * CreateTaskController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us0012-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)