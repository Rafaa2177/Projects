package pt.ipp.isep.dei.esoft.project.domain;
/*
import java.io.Serializable;

public class Task implements Serializable {
    private final String reference;
    private final String description;
    private final String informalDescription;
    private final String technicalDescription;
    private final Integer duration;
    private final Double cost;

    private final TaskCategory taskCategory;
    private final Person employee = null;

    //private final Employee employee;

    public Task(String reference, String description, String informalDescription, String technicalDescription,
                Integer duration, Double cost, TaskCategory taskCategory, Person employee) {

        validateReference(reference);
        this.reference = reference;
        this.description = description;
        this.informalDescription = informalDescription;
        this.technicalDescription = technicalDescription;
        this.duration = duration;
        this.cost = cost;
        this.taskCategory = taskCategory;

    }

    private void validateReference(String reference) {
        //TODO: missing from the diagrams
        if (reference == null || reference.isEmpty()) {
            throw new IllegalArgumentException("Reference cannot be null or empty.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return reference.equals(task.reference);
    }
    public Task clone() {
        return new Task(this.reference, this.description, this.informalDescription, this.technicalDescription,
                this.duration, this.cost, this.taskCategory, this.employee);
    }
}

 */

 