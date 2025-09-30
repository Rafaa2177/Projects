# US010 - Place an order to purchase the property, submitting the order amount


## 1. Requirements Engineering


### 1.1. User Story Description

> As a client, I place an order to purchase the property, submitting the order amount.

### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> There's no specification on the document about the US010.



**From the client clarifications:**

> **Question:** US1/US10 - Sort By Ascending/Descending Price (Friday, 19 de May de 2023 às 21:59)
>  Let's suppose the client don´t want to filter the properties. That means there will be shown both "sale" and "rent"
announcements to him. If he later wants to sort them by price (ascending or descending), how does the comparison between
a property for sale with a 200 000$ price (e.g.) and a property for rent with 800$ monthly price (e.g.) and a 12 months
contract duration (e.g.) should be?
> **Answer:** If the actor does not chooses to filter properties by type of business (sale or rent), the property sale 
price and the property rent monthly price should be used for sorting the properties.


> **Question:** US010 - Show the other order on the screen (Friday, 5 de May de 2023 às 15:58)
> "If the order amount submitted by the client has already been posted for the property (by another request from this
client or any other client), the system must state that on the screen and the order placed previously should be 
considered first when selling the property." When showing the other order on the screen, what data should be shown 
(eg client name, published date, order status)?


> **Answer:** If the order amount submitted by the client has already been posted for the property 
(by another request from this client or any other client), the system must state that on the screen. 
The system should show the message "The order amount submitted has already been posted for this property. 
Please contact the agent that is responsible for this property.".


> **Question:** US010- Selected Data (Friday, 5 de May de 2023 às 11:43)
>To order a purchase of a property, should the client be able to filter the properties by type of property, city, district...
so that it's easier to find the wanted property, or should the system show the entire list of properties to sale?
> **Answer:** The system should show a list of properties to the client. Filtering is a useful feature of the system,
please prepare a user friendly and effective filtering to show the properties to the client.


> **Question:** US010 (Friday, 5 de May de 2023 às 11:35)
>Should this User Story be implemented as an addition to US001 (Users can display properties, and select one to make an order),
or should it be completely separate, with a separate section of the app dedicated to it?
> **Answer:** To place an order the actor should be registered in the system.


> **Question:** US10 (Friday, 5 de May de 2023 às 10:13)
>  In US10, can the client remove an offer they made at any point, in order to replace it with a different one?
> **Answer:** No.


> **Question:** US010 (Friday, 5 de May de 2023 às 11:35)
> If a client makes an order of equal or lower value to a previous one, the older order will be considered first.
The system should state this on the screen, but should this information be shown to the client, the agent, or both?
> **Answer:** Please check who is the actor of this Us and check again the acceptance criteria of this US.


> **Question:** US010 - Location of the order (Friday, 5 de May de 2023 às 10:08)
>Regarding the us010 I had a question about the order of the client. After the client sent an order to purchase the 
property where does the request go to? To the agency, to the owner, to the store?
> **Answer:** I do not understand your question and I think you are confusing things. Please discuss this question with 
your teammates and class teachers. Remember that I am your client and not your ESOFT teacher.



### 1.3. Acceptance Criteria

* **AC1:** The order amount submitted by the client must be equal to or lower than the price set by the owner for the property.
* **AC2:** If the order amount submitted by the client has already been posted for the property (by another request from
this client or any other client), the system must state that on the screen and the order placed previously should be 
considered first when selling the property.
* **AC3:** A client can only submit a new order to purchase the same property after the previous one is declined.



### 1.4. Found out Dependencies

* Dependency on US002 - because we need sale announcements to purchase other properties
* Dependency on US004 - The agent is responsible to take care of the order sent by the client



### 1.5 Input and Output Data

**Input Data:**

* Typed data:
	* Order Amount
	
* Selected data:
	* Property that was chosen to be purchased

**Output Data:**
* Creation of the Order



### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One  (falta)

![System Sequence Diagram - Alternative One](svg/us010-system-sequence-diagram-alternative-one-US010___Placing_an_order_to_purchase_a_property.svg)

### 1.7 Other Relevant Remarks
