// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4u.plugin/src/main/java/plugin/requirements/grammarFiles/RequirementsModel.g4 by ANTLR 4.13.1
package plugin.requirements.grammarFiles;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RequirementsModelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RequirementsModelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(RequirementsModelParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#requirement_model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement_model(RequirementsModelParser.Requirement_modelContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#specification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecification(RequirementsModelParser.SpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#boolean_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_spec(RequirementsModelParser.Boolean_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#short_text_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort_text_spec(RequirementsModelParser.Short_text_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#long_text_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLong_text_spec(RequirementsModelParser.Long_text_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#education_level_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEducation_level_spec(RequirementsModelParser.Education_level_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#languages_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguages_spec(RequirementsModelParser.Languages_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#programming_languages_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramming_languages_spec(RequirementsModelParser.Programming_languages_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#digit_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit_spec(RequirementsModelParser.Digit_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#decimal_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal_spec(RequirementsModelParser.Decimal_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#date_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_spec(RequirementsModelParser.Date_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#range_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange_spec(RequirementsModelParser.Range_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#multi_planguages}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_planguages(RequirementsModelParser.Multi_planguagesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsModelParser#multi_languages}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_languages(RequirementsModelParser.Multi_languagesContext ctx);
}