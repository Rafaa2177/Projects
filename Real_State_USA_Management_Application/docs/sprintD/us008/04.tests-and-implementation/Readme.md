# US 006 - To create a Task 

# 4. Tests 

**Test 1:** Check that Advertisment Request is sorted by date - AC1.

    @Test void ensureAdvertismentRequestsAreSortedByDate(){
        //Arrange
        ...

        AnnouncementRequest announcementRequest1 = new AdvertisementRequest(desiredAgent, currentOwner, area, distance,photos, city,street1,postalCode, nrBedrooms,
                nrBathrooms, nrParkingSpaces, centralHeating, airConditioning, sunExposure, basement, inhabitableLoft,
                price, type);

       AnnouncementRequest announcementRequest2 = new AnnouncementRequest(desiredAgent, currentOwner, area, distance,photos, city,street2,postalCode, nrBedrooms,
                nrBathrooms, nrParkingSpaces, centralHeating, airConditioning, sunExposure, basement, inhabitableLoft,
                price, type);

        AnnouncementRequest announcementRequest3 = new AnnouncementRequest(desiredAgent, currentOwner, area, distance,photos, city,street3,postalCode, nrBedrooms,
                nrBathrooms, nrParkingSpaces, centralHeating, airConditioning, sunExposure, basement, inhabitableLoft,
                price, type);

        announcementRequest1.setDate(LocalDate.of(2020, 1, 1));
        announcementRequest2.setDate(LocalDate.of(2020, 2, 2));
        announcementRequest3.setDate(LocalDate.of(2020, 3, 3));

        RequestRepository requestRepository = new RequestRepository();
        RequestRepository.addRequest(advertisementRequest1);
        RequestRepository.addRequest(advertisementRequest2);
        RequestRepository.addRequest(advertisementRequest3);

        List <AnnouncementRequest> expected = new ArrayList<AnnouncementRequest>();
        expected.add(advertisementRequest3);
        expected.add(advertisementRequest2);
        expected.add(advertisementRequest1);

        //Act
        List<AnnouncementRequest> result = RequestRepository.getRequestForDiseredAgent(desiredAgent);
        //Assert
        assertEquals(expected,result);
    }


**Test 2:** Check that Announcement Request is removed from the list - AC2.

    @Test
    void EnsureRequestIsRemoved() {
        //Arrange
        ...

       AnnouncementRequest announcementRequest1 = new AdvertisementRequest(desiredAgent, currentOwner, area, distance,photos, city,street1,postalCode, nrBedrooms,
                nrBathrooms, nrParkingSpaces, centralHeating, airConditioning, sunExposure, basement, inhabitableLoft,
                price, type);

       AnnouncementRequest announcementRequest2 = new AnnouncementRequest(desiredAgent, currentOwner, area, distance,photos, city,street2,postalCode, nrBedrooms,
                nrBathrooms, nrParkingSpaces, centralHeating, airConditioning, sunExposure, basement, inhabitableLoft,
                price, type);

        AnnouncementRequest announcementRequest3 = new AnnouncementRequest(desiredAgent, currentOwner, area, distance,photos, city,street3,postalCode, nrBedrooms,
                nrBathrooms, nrParkingSpaces, centralHeating, airConditioning, sunExposure, basement, inhabitableLoft,
                price, type);
        RequestRepository.requestRepository = new RequestRepository();
        RequestRepository.addRequest(advertisementRequest1);
        RequestRepository.addRequest(advertisementRequest2);
        RequestRepository.addRequest(advertisementRequest3);

        Commission comission = new Commission("123");

        int expected = 2;

        //Act
        RequestRepository.removeRequest(advertisementRequest3);
        int result =RequestRepository.getRequest().size();
        //Assert
        assertEquals(expected,result);
    }


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class CreateTaskController 

```java
public Task createTask(String reference, String description, String informalDescription,
								 String technicalDescription, Integer duration, Double cost,
								 String taskCategoryDescription) {

	TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

	Employee employee = getEmployeeFromSession();
	Organization organization = getOrganizationRepository().getOrganizationByEmployee(employee);

	newTask = organization.createTask(reference, description, informalDescription, technicalDescription, 
			duration, cost,taskCategory, employee);
    
	return newTask;
}
```


## Class Organization

```java
public Optional<Task> createTask(String reference, String description, String informalDescription,
                                     String technicalDescription, Integer duration, Double cost,
                                     TaskCategory taskCategory, Employee employee) {
    
        Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                taskCategory, employee);

        addTask(task);
        
        return task;
    }
```

# 6. Integration and Demo 

* A new option on the Employee menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain. 

Is there any way to avoid this to happen?





