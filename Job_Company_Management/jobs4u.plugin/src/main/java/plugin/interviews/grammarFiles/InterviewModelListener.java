// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4u.plugin/src/main/java/plugin/interviews/grammarFiles/InterviewModel.g4 by ANTLR 4.13.1
package plugin.interviews.grammarFiles;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InterviewModelParser}.
 */
public interface InterviewModelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(InterviewModelParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(InterviewModelParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#interview_model}.
	 * @param ctx the parse tree
	 */
	void enterInterview_model(InterviewModelParser.Interview_modelContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#interview_model}.
	 * @param ctx the parse tree
	 */
	void exitInterview_model(InterviewModelParser.Interview_modelContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#specification}.
	 * @param ctx the parse tree
	 */
	void enterSpecification(InterviewModelParser.SpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#specification}.
	 * @param ctx the parse tree
	 */
	void exitSpecification(InterviewModelParser.SpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#boolean_spec}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_spec(InterviewModelParser.Boolean_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#boolean_spec}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_spec(InterviewModelParser.Boolean_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#short_text_spec}.
	 * @param ctx the parse tree
	 */
	void enterShort_text_spec(InterviewModelParser.Short_text_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#short_text_spec}.
	 * @param ctx the parse tree
	 */
	void exitShort_text_spec(InterviewModelParser.Short_text_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#option_spec}.
	 * @param ctx the parse tree
	 */
	void enterOption_spec(InterviewModelParser.Option_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#option_spec}.
	 * @param ctx the parse tree
	 */
	void exitOption_spec(InterviewModelParser.Option_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#multi_spec}.
	 * @param ctx the parse tree
	 */
	void enterMulti_spec(InterviewModelParser.Multi_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#multi_spec}.
	 * @param ctx the parse tree
	 */
	void exitMulti_spec(InterviewModelParser.Multi_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#digit_spec}.
	 * @param ctx the parse tree
	 */
	void enterDigit_spec(InterviewModelParser.Digit_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#digit_spec}.
	 * @param ctx the parse tree
	 */
	void exitDigit_spec(InterviewModelParser.Digit_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#decimal_spec}.
	 * @param ctx the parse tree
	 */
	void enterDecimal_spec(InterviewModelParser.Decimal_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#decimal_spec}.
	 * @param ctx the parse tree
	 */
	void exitDecimal_spec(InterviewModelParser.Decimal_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#date_spec}.
	 * @param ctx the parse tree
	 */
	void enterDate_spec(InterviewModelParser.Date_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#date_spec}.
	 * @param ctx the parse tree
	 */
	void exitDate_spec(InterviewModelParser.Date_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#time_spec}.
	 * @param ctx the parse tree
	 */
	void enterTime_spec(InterviewModelParser.Time_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#time_spec}.
	 * @param ctx the parse tree
	 */
	void exitTime_spec(InterviewModelParser.Time_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#range_spec}.
	 * @param ctx the parse tree
	 */
	void enterRange_spec(InterviewModelParser.Range_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#range_spec}.
	 * @param ctx the parse tree
	 */
	void exitRange_spec(InterviewModelParser.Range_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#multi}.
	 * @param ctx the parse tree
	 */
	void enterMulti(InterviewModelParser.MultiContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#multi}.
	 * @param ctx the parse tree
	 */
	void exitMulti(InterviewModelParser.MultiContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(InterviewModelParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(InterviewModelParser.OptionContext ctx);
}