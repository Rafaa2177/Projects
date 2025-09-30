package plugin.interviews;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import plugin.interviews.grammarFiles.InterviewModelLexer;
import plugin.interviews.grammarFiles.InterviewModelParser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface InterviewModelInterface {

    // This method is used to generate the questions for the template
    String generateTemplateQuestions();

    // This method is used to evaluate the interview syntax
    default ParseTree evaluateInterview(String textFile) throws IOException, RuntimeException{
        InputStream is = new FileInputStream(textFile);

        BufferedInputStream bis = new BufferedInputStream(is);

        // Create a CharStream that reads from standard input
        CharStream input = CharStreams.fromStream(bis);

        // Create a lexer that feeds off of input CharStream
        InterviewModelLexer lexer = new InterviewModelLexer(input);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        InterviewModelParser parser = new InterviewModelParser(tokens);

        parser.removeErrorListeners(); // remove default ConsoleErrorListener
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg,
                                    RecognitionException e) {
                System.out.println("line " + line + ":" + charPositionInLine + " " + msg);
                throw new RuntimeException();
            }
        });

        // Start parsing at the start rule
        ParseTree tree = parser.start();

        return tree;
    };

    // This method is used to grade the interview
    default int gradeInterview(String textFile) {

        try {
            ParseTree tree = evaluateInterview(textFile);
            InterviewVisitor visitor = new InterviewVisitor();
            visitor.visit(tree);

            return visitor.getGrade();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }
}
