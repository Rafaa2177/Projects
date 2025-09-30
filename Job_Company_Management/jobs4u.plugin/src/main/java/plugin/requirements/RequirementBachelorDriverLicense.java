package plugin.requirements;


import java.io.*;

public class RequirementBachelorDriverLicense implements RequirementsModelInterface {

    @Override
    public String generateTemplateRequirements() {
        File outputFile = new File("requirements_bachelor_driver_license.txt");
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            // Write to the file here
            writer.println("1. What's your education level? ( None, High School, Bachelor, Master, PhD )");
            writer.println("2. Do you have a driver's license? ( Yes, No )");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outputFile.getAbsolutePath();
    }

}
