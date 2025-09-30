# US 006 - To create a Task 

## 1. Requirements Engineering


### 1.1. User Story Description





### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> All registered information, except the agency commission, can be accessed by the client who intends to buy or rent the property.

> The client is responsible for being able to consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located.



**From the client clarifications:**


> **Question:** US001 Clarifications (Thursday, 30 de March de 2023 às 14:29)
In a previous question, you said that the address doesn't include parishes ("In the USA, the addresses will not include municipalities or parishes."). That being said, how should we sort the properties by location, specifically city, and state?

> **Answer:** The client should be able to sort properties by price, city and state.



> **Question:** US01 - Unregistered users. (Thursday, 16 de March de 2023 às 11:28)
> In the project's documentation it's mentioned that "All those who wish to use the application must be authenticated", but in the US1 it's said that an unregistered user can see a list of properties. Can users who aren't authenticated do this?

> **Answer:** Non-authenticated users can only list properties.
 


> **Question:** US001 - Sprint A (Friday, 17 de March de 2023 às 10:57)
>The properties can be in sale and lease at the same time? The properties have always a sale price, even if they are at lease? If not, how do we list them according to the ascending or descending price? do we seperate them as a PropertiesForSale and PropertiesForLeasing?

> **Answer:** No.



> **Question:** US001 (Sprint A) - Sorting criteria (Friday, 17 de March de 2023 às 17:38)
>In the project description it is stated that "the client is, then, responsible for being able to consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located.". Is the client able to sort properties by only these 4 criteria or is he able to sort properties by any of the properties' characteristics?

> **Answer:** The client should be able to select the type of business (renting or buying), the type of property and the number of rooms. Then, the client should be able to sort properties by price or by parish where the property is located.
If the client does not select the type of business, the type of property and the number of rooms, the application should allow the client to sort all properties that are on sale or on renting.



> **Question:** US001 (Sprint A) - User Registry(17 de March de 2023 às 20:40)
>An unregistered user can olny see sale announcements or he is able to contact the agency agents to make a purchase request ?

> **Answer:** From the project description: "As an unregistered user, I want to display listed properties". For now this is the only functionality of the system that the non-registered user can use.


> **Question:** US01 - Unregistered users - List - search criteria number of rooms (Tuesday, 28 de March de 2023 às 12:22) 
> The one of the search criteria is "number of rooms". Is "Number of Bedrooms" or "Number of Bathrooms" or both?

> **Answer:** Number of Bedrooms.



> **Question:** Default list sorting criteria (Tuesday, 28 de March de 2023 às 17:23)
> When a unregistered user wants to list properties, the list given by the program is sorted by default with which criteria? For example the list is shown with the properties sorted by most recently added?

> **Answer:** Thank you for your suggestion. By default, the list should be shown with the properties sorted by most recently added.



> **Question:** Default list sorting criteria (Tuesday, 28 de March de 2023 às 17:23)
> Can an user filter the properties list for example by a type but choosing multiple values? For example the users wants to see only properties with 3 or 4 rooms. If this is possible, after filtering the list to show only the values chosen, he can sort by ascending/descending?

> **Answer:** The user should select only one value for each feature of the property. By default, the list should be shown with the properties sorted by most recently added.


Thank you for your suggestion. By default, the list should be shown with the properties sorted by most recently added.

### 1.3. Acceptance Criteria


* **AC1:** The unregistered user can be filter the list of properties by type of business, type of property and number of rooms.


### 1.4. Found out Dependencies


* There is a dependency to "US003 Create a task category" since at least a task category must exist to classify the task being created.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* number of bedrooms
	
* Selected data:
	* order criteria;
    * type of business;
    * type of the property


**Output Data:**

* Display listed properties.

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us001-system-sequence-diagram-alternative-one-System_Sequence_Diagram__SSD____Alternative_One.svg)


### 1.7 Other Relevant Remarks

