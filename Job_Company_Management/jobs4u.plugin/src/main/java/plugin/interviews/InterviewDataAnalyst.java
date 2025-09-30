package plugin.interviews;

import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class InterviewDataAnalyst  implements InterviewModelInterface {
    @Override
    public String generateTemplateQuestions() {
        // Implement a file that creates the template questions to fill
        File outputFile = new File("data_analyst_template_questions.txt");
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            // Write to the file here
            writer.println("1-QUESTION: True or False: Data analysis involves only statistical techniques. - BOOLEAN");

            writer.println("2-QUESTION: When was SQL invented? - DATE");

            writer.println("3-QUESTION: On a scale of 1 to 5, how comfortable are you with SQL queries? - RANGE");

            writer.format("4-QUESTION: Which of the following is not a type of data visualization? - SINGLE CHOICE \n" +
                    "a) Scatter Plot \n" +
                    "b) Box Plot \n" +
                    "c) Histogram \n" +
                    "d) String \n");

            writer.format(" 5-QUESTION: Which of the following Python libraries is commonly used for data manipulation and analysis? - MULTIPLE CHOICE"
                    + "\n a) NumPy"
                    + "\n b) Pandas "
                    + "\n c) Matplotlib"
                    + "\n d) Seaborn\n"
            );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return outputFile.getAbsolutePath();
    }

}
