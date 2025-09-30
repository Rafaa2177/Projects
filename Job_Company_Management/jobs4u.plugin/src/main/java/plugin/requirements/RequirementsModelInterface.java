package plugin.requirements;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import plugin.requirements.grammarFiles.RequirementsModelLexer;
import plugin.requirements.grammarFiles.RequirementsModelParser;

import java.io.FileInputStream;
import java.io.IOException;

public interface RequirementsModelInterface {

    // This method is used to generate the requirements for the template
    String generateTemplateRequirements();

    // This method is used to evaluate the requirements syntax
    default ParseTree evaluateRequirements(String textFile) throws IOException{
        // Create a CharStream that reads from standard input
        CharStream input = CharStreams.fromStream(new FileInputStream(textFile));

        // Create a lexer that feeds off of input CharStream
        RequirementsModelLexer lexer = new RequirementsModelLexer(input);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        RequirementsModelParser parser = new RequirementsModelParser(tokens);

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

    default boolean resultRequirements(String textFile) {
        try {
            ParseTree tree = evaluateRequirements(textFile);
            RequirementsVisitor visitor = new RequirementsVisitor();
            visitor.visit(tree);
            return visitor.getResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
