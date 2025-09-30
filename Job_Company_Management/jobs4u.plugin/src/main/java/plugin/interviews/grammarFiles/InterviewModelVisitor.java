// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4u.plugin/src/main/java/plugin/interviews/grammarFiles/InterviewModel.g4 by ANTLR 4.13.1
package plugin.interviews.grammarFiles;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link InterviewModelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface InterviewModelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(InterviewModelParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#interview_model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterview_model(InterviewModelParser.Interview_modelContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#specification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecification(InterviewModelParser.SpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#boolean_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_spec(InterviewModelParser.Boolean_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#short_text_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort_text_spec(InterviewModelParser.Short_text_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#option_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_spec(InterviewModelParser.Option_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#multi_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_spec(InterviewModelParser.Multi_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#digit_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit_spec(InterviewModelParser.Digit_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#decimal_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal_spec(InterviewModelParser.Decimal_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#date_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_spec(InterviewModelParser.Date_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#time_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_spec(InterviewModelParser.Time_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#range_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange_spec(InterviewModelParser.Range_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#multi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti(InterviewModelParser.MultiContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(InterviewModelParser.OptionContext ctx);
}