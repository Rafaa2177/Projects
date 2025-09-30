package plugin.requirements;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import plugin.requirements.grammarFiles.RequirementsModelLexer;
import plugin.requirements.grammarFiles.RequirementsModelParser;

import java.io.*;

public class Requirement5YearsJava implements RequirementsModelInterface {

    @Override
    public String generateTemplateRequirements() {
        File outputFile = new File("requirements_5_years_java.txt");
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            // Write to the file here
            writer.println("1. How many years of experience do you have? - DIGIT");
            writer.println("2. What programming languages do you know? - PROGRAMMING_LANGUAGES");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outputFile.getAbsolutePath();
    }

}
