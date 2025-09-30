package plugin;

import plugin.interviews.InterviewDataAnalyst;
import plugin.interviews.InterviewJavaProgrammer;
import plugin.interviews.InterviewModelInterface;
import plugin.requirements.RequirementBachelorDriverLicense;
import plugin.requirements.RequirementsModelInterface;
import plugin.requirements.Requirement5YearsJava;

import java.util.List;

public class Plugin {

    public List<String> getInterviewModels() {
        return List.of("Data Analyst", "Java Programmer");
    }

    public List<String> getRequirementsModels() {
        return List.of("5 Years of experience in Java", "Bachelor with driving license");
    }

    public static InterviewModelInterface getInterviewModel(int id) {
        if(id == 1) {
            System.out.println("Interview Model ID: 1 - Data Analyst");
            return new InterviewDataAnalyst();
        } else if(id == 2) {
            System.out.println("Interview Model ID: 2 - Java Programmer");
            return new InterviewJavaProgrammer();
        }
        else{
            throw new IllegalArgumentException("Invalid Interview Model ID");
        }
    }

    public static RequirementsModelInterface getRequirementsModel(int id) {
        if(id == 1) {
            System.out.println("Requirements Model ID: 1 - 5 Years of experience in Java");
            return new Requirement5YearsJava();
        } else if(id == 2) {
            System.out.println("Requirements Model ID: 2 - Bachelor with driving license");
            return new RequirementBachelorDriverLicense();
        }
        else{
            throw new IllegalArgumentException("Invalid Requirements Model ID");
        }
    }


}