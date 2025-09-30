# Supplementary Specification (FURPS+)

## Functionality

_System Management_

- "Each store in the network has a store manager and the set of stores is managed by a store network manager. The main functions of a store manager are to monitore and streamline the branch with the aim of getting to know better the business carried out and to analyse and evaluate the performance of employees."
- "The manager of the network intends to analyse the performance of each of the branches and the global behaviour of the network on a daily basis."
- "The company´s systems administrator will be responsible for register all employees(specifying the name, the citizen´s card number, the tax number, the adress, the email adress, the contact telephone number and the agency to which it is assigned) and branches of the network (specifying the designation, location and local manager) as well as preparing the system in order to facilitate the insertion of advertisements and facilitate the use of the application."

_Localization_ 
- "The application must support the English language"

_Reporting_
- "Real Estate USA needs an application that enables buyers who visit its stories/agencies to access the properties available for lease or sale, as well as the company´s employees to carry out a set of operations related to the real estate business.Among these operations are the publication of rental and sale advertisements, the registration of a business(lease or sale) and the scheduling and registration of visits to the property."
- "From time to time, property owners contact Real Estate USA with the aim of selling or renting their properties. Owners go to one of the company´s branches and meet with a real estate agent to sell or rent one or more properties, or they can use the company´s applicaton for the same purposes"

_Printing_
 - "... publishes the offer so that is visible to all clients who visit the agency and use the application."

_Workflow_
- "The agent receives the request. checks the availability and sends the response."
- "The costumer accepts the order, it is automatically scheduled in the system."

_Security_
 - " All registered information, except the agency commission, can be accessed by the client ..."
- "All those who wish to use the
  application must be authenticated with a password of seven alphanumeric characters, including three capital letters and two digits."
- "The rent value is per month."

## Usability 

- "All the images/figures produced during the software development process should be recorded in SVG format."
- "Use javadoc to generate useful documentation for java code."
- "Adopt recognized coding standards."
- "The application graphical interface is to be developed in JavaFX 11".
- "All those who wish to use the application must be authenticated with a password of seven alphanumeric characters, including three capital letters and two digits."
- "The application must be developed in Java language using the IntelliJ IDE or NetBeans." 
- "During the system development, the team must: (i) adopt best practices for identifying  requirements, and for OO software analysis and design; (ii) adopt recognized coding conventions and standards(e.g.; CamelCase);(iii) use Javadoc to generate useful documentation for Java code."




## Reliability
- "The application should use objects serialization to ensure data persistence between two runs of the application."
- "The development team must implement unit tests for all methods, except for methods that implement Input/Output operations"

(fill in here )

## Performance
- "The application should use objects serialization to ensure data persistence between two runs of the application."


## Supportability
- "The development team must implement unit tests for all methods, except for methods that implement Input/Output operations. The unit tests should be implemented using the JUnit 5 framework."
- "The application to be developed in this project will replace an application that was already in operation in some agencies and will be replaced in July 2023."




## +

### Design Constraints
- " The implementation must adopt recognized coding standards, in this case CamelCase."
- "The implementation must adopt OO standards."


### Implementation Constraints

- "The application must be developed in Java language using the IntelliJ IDEA or NetBeans."
- "Use Javadoc to generate useful documentation for Java code."
- " The unit tests must be implemented using the JUnit 5 framework."
- "During the system development, the team must: (i) adopt best practices for identifying  requirements, and for OO software analysis and design; (ii) adopt recognized coding conventions and standards(e.g.; CamelCase);(iii) use Javadoc to generate useful documentation for Java code."




### Interface Constraints
- The JaCoCo plugin should be used to generate the coverage report.
- The application graphical interface is to be developed in Java FX11.
- All the images/figures produced during the software development process should be recorded in SVG format.



### Physical Constraints

- The application should use object to ensure data persistence between two runs of the application."