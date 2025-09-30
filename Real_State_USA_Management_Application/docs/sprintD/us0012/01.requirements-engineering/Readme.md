#US012 - Import information from a legacy system

## 1. Requirements Engineering


### 1.1. User Story Description


As a system administrator, I want to import information from a legacy system that has been in use in several agencies



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> An unregistered user should be able to register in the system to buy, rent or sell properties.



**From the client clarifications:**



> **Question:** US012 - Clarification of the measuring system ( Friday, 2 de June de 2023 às 09:34)
>In the project description and the previous US's it is stated that the area (and distance from the city center) of a property must be in meters (or m^2), however in the legacy file the measuring system used in the area is feet (the distance is also in miles) instead.
As such, do we need to convert the data into meters when we are importing the file information or do we just import the data and pretend it is in meters for the purposes of the application?

> **Answer:** There is no need to convert the area and distances metrics. Both in the legacy system and in the system that we are developing now, the area should always be measured in feet and the distance in miles

> **Question:** US012 - Clarification about the client's email (Thursday, 1 de June de 2023 às 16:07)
>In my last question, how should we proceed when facing with an email that has an aphostrofe, should we ignore the property, you said that you could not identify the problem and asked for an exemple.
When reading the .csv files, both of them, have these three emails that have an aphostrofe in them:
FreyaO'SULLIVAN2155@gmail.com (associated with sid 79/75 depending on the file)
ElizaO'SULLIVAN2155@hotmail.com (associated with sid 383/379 depending on the file)
LaylaO'CONNELL2155@gmail.com (associated with sid 412/408 depending on the file)
My question is once again, how should we proceed when facing these emails.

> **Answer:** I just updated the files. Thank you for your help!

>**Question:** Can the System Administrator, when wanting to import information from a legacy system, send more than one file at once?

>**Answer:** Only one file at a time.


### 1.3. Acceptance Criteria


* **AC1:** The system administrator must be able to choose a file to import.
* **AC2:** The system should only accept CSV files.
* **AC3:** The file content must be validated, showing a message to the system administrator if the file is empty or its content is not in the requested format.
* **AC4:** The import operation, when successful, should trigger a success message to the system administrator.

### 1.4. Found out Dependencies
* There is no dependency with other user-stories.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* There is no typed data.
	
* Selected data:
	* CSV file.


**Output Data:**

* Operation Success.
* Creation of announcements , imported from the csc file.

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us0012-system-sequence-diagram-System_Sequence_Diagram__SSD____Alternative_One.svg)



### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.