package plugin.interviews;

import lombok.Getter;
import plugin.interviews.grammarFiles.InterviewModelBaseVisitor;
import plugin.interviews.grammarFiles.InterviewModelParser.*;

import java.util.List;

@Getter
public class InterviewVisitor extends InterviewModelBaseVisitor<Integer> {

    private int grade = 0;

    private int interviewId = 0;

    public int getGrade() {
        return grade;
    }

    @Override
    public Integer visitStart(StartContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitInterview_model(Interview_modelContext ctx) {
        interviewId = Integer.parseInt(ctx.DIGIT().getText());
        return visitChildren(ctx);
    }

    @Override
    public Integer visitBoolean_spec(Boolean_specContext ctx) {
        String answer = ctx.BOOLEAN().getText();
        if (interviewId == 1 && answer.equalsIgnoreCase("false")) {
            grade += 20;
        }
        return grade;
    }

    @Override
    public Integer visitDate_spec(Date_specContext ctx) {
        String answer = ctx.DATE().getText();
        if (interviewId == 1 && answer.equalsIgnoreCase("12-08-2012")) {
            grade += 20;
        }
        return grade;
    }

    @Override
    public Integer visitRange_spec(Range_specContext ctx) {
        String answer = ctx.DIGIT().getText();
        if (interviewId == 1 && answer.equalsIgnoreCase("5")) {
            grade += 20;
        }
        return grade;
    }

    @Override
    public Integer visitOption_spec(Option_specContext ctx) {
        String answer = ctx.option().getText();
        if (interviewId == 1 && answer.equalsIgnoreCase("a)")) {
            grade += 20;
        }
        return grade;
    }

    @Override
    public Integer visitMulti_spec(Multi_specContext ctx) {
        List<OptionContext> options = ctx.multi().option();
        if(interviewId == 1)
            for (OptionContext option : options) {
                String answer = option.getText().toLowerCase();
                if(answer.contains("a")){
                    grade += 7;
                }if(answer.contains("b")){
                    grade += 5;
                }
                if(answer.contains("c")){
                    grade += 3;
                }
                if(answer.contains("d")){
                    grade += 5;
                }
            }

        return grade;
    }

   
}
