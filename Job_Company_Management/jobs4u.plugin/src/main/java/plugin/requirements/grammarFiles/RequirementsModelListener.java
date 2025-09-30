// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4u.plugin/src/main/java/plugin/requirements/grammarFiles/RequirementsModel.g4 by ANTLR 4.13.1
package plugin.requirements.grammarFiles;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RequirementsModelParser}.
 */
public interface RequirementsModelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(RequirementsModelParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(RequirementsModelParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#requirement_model}.
	 * @param ctx the parse tree
	 */
	void enterRequirement_model(RequirementsModelParser.Requirement_modelContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#requirement_model}.
	 * @param ctx the parse tree
	 */
	void exitRequirement_model(RequirementsModelParser.Requirement_modelContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#specification}.
	 * @param ctx the parse tree
	 */
	void enterSpecification(RequirementsModelParser.SpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#specification}.
	 * @param ctx the parse tree
	 */
	void exitSpecification(RequirementsModelParser.SpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#boolean_spec}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_spec(RequirementsModelParser.Boolean_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#boolean_spec}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_spec(RequirementsModelParser.Boolean_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#short_text_spec}.
	 * @param ctx the parse tree
	 */
	void enterShort_text_spec(RequirementsModelParser.Short_text_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#short_text_spec}.
	 * @param ctx the parse tree
	 */
	void exitShort_text_spec(RequirementsModelParser.Short_text_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#long_text_spec}.
	 * @param ctx the parse tree
	 */
	void enterLong_text_spec(RequirementsModelParser.Long_text_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#long_text_spec}.
	 * @param ctx the parse tree
	 */
	void exitLong_text_spec(RequirementsModelParser.Long_text_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#education_level_spec}.
	 * @param ctx the parse tree
	 */
	void enterEducation_level_spec(RequirementsModelParser.Education_level_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#education_level_spec}.
	 * @param ctx the parse tree
	 */
	void exitEducation_level_spec(RequirementsModelParser.Education_level_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#languages_spec}.
	 * @param ctx the parse tree
	 */
	void enterLanguages_spec(RequirementsModelParser.Languages_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#languages_spec}.
	 * @param ctx the parse tree
	 */
	void exitLanguages_spec(RequirementsModelParser.Languages_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#programming_languages_spec}.
	 * @param ctx the parse tree
	 */
	void enterProgramming_languages_spec(RequirementsModelParser.Programming_languages_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#programming_languages_spec}.
	 * @param ctx the parse tree
	 */
	void exitProgramming_languages_spec(RequirementsModelParser.Programming_languages_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#digit_spec}.
	 * @param ctx the parse tree
	 */
	void enterDigit_spec(RequirementsModelParser.Digit_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#digit_spec}.
	 * @param ctx the parse tree
	 */
	void exitDigit_spec(RequirementsModelParser.Digit_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#decimal_spec}.
	 * @param ctx the parse tree
	 */
	void enterDecimal_spec(RequirementsModelParser.Decimal_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#decimal_spec}.
	 * @param ctx the parse tree
	 */
	void exitDecimal_spec(RequirementsModelParser.Decimal_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#date_spec}.
	 * @param ctx the parse tree
	 */
	void enterDate_spec(RequirementsModelParser.Date_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#date_spec}.
	 * @param ctx the parse tree
	 */
	void exitDate_spec(RequirementsModelParser.Date_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#range_spec}.
	 * @param ctx the parse tree
	 */
	void enterRange_spec(RequirementsModelParser.Range_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#range_spec}.
	 * @param ctx the parse tree
	 */
	void exitRange_spec(RequirementsModelParser.Range_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#multi_planguages}.
	 * @param ctx the parse tree
	 */
	void enterMulti_planguages(RequirementsModelParser.Multi_planguagesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#multi_planguages}.
	 * @param ctx the parse tree
	 */
	void exitMulti_planguages(RequirementsModelParser.Multi_planguagesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsModelParser#multi_languages}.
	 * @param ctx the parse tree
	 */
	void enterMulti_languages(RequirementsModelParser.Multi_languagesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsModelParser#multi_languages}.
	 * @param ctx the parse tree
	 */
	void exitMulti_languages(RequirementsModelParser.Multi_languagesContext ctx);
}