package plugin.interviews;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class InterviewJavaProgrammer implements InterviewModelInterface {
    @Override
    public String generateTemplateQuestions() {
        File outputFile = new File("java_programmer_template_questions.txt");
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            // Write to the file here
            writer.println("1-QUESTION: Is Java a statically typed language? - BOOLEAN");

            writer.println("2-QUESTION: What is the maximum length of a Java class name? SHORT TEXT");


            writer.format("3-QUESTION: Which of the following is not a primitive data type in Java? - ONE CHOICE \n" +
                    "a) int \n" +
                    "b) float \n" +
                    "c) String \n" +
                    "d) boolean \n");

            writer.format(" 4-QUESTION: Which of the following are access modifiers in Java? - MULTIPLE CHOICE"
                    + "\n a) public"
                    + "\n b) private "
                    + "\n c) protected"
                    + "\n d) final\n"
            );

            writer.println("5-QUESTION: How many bits are used to represent an int in Java? - NUMERIC");
            writer.println("6-QUESTION: What is the value of pi in Java? - DECIMAL");
            writer.println("7-QUESTION: When was Java invented? - DATE");
            writer.println("8-QUESTION: What is the current time? - TIME");
            writer.println("9-QUESTION: On a scale of 1 to 5, how comfortable are you with multithreading in Java? - SCALE");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return outputFile.getAbsolutePath();
    }

}
