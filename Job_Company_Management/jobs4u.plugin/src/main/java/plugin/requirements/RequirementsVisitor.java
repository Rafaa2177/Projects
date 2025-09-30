package plugin.requirements;

import org.antlr.v4.runtime.tree.TerminalNode;
import plugin.requirements.grammarFiles.RequirementsModelBaseVisitor;
import plugin.requirements.grammarFiles.RequirementsModelParser.*;

import java.util.List;

public class RequirementsVisitor extends RequirementsModelBaseVisitor<Boolean> {

    private int requirementId = 0;

    private boolean result = true;

    public boolean getResult() {
        return result;
    }

    @Override
    public Boolean visitStart(StartContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitRequirement_model(Requirement_modelContext ctx) {
        requirementId = Integer.parseInt(ctx.DIGIT().getText());
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitDigit_spec(Digit_specContext ctx) {
        int answer = Integer.parseInt(ctx.DIGIT().getText());
        if (requirementId == 1) {
            if(answer < 5) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Boolean visitProgramming_languages_spec(Programming_languages_specContext ctx) {
        List<TerminalNode> answer = ctx.multi_planguages().PROGRAMMING_LANGUAGES();
        if (requirementId == 1) {
            boolean exists = answer.stream().anyMatch(x -> x.getText().equalsIgnoreCase("java"));
            if (!exists) result = false;
        }
        return result;
    }
}
