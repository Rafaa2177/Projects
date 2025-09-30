// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4.plugin/src/main/java/requirementsmodel/fiveyearsjava/ReqModels5YearsJava.g4 by ANTLR 4.13.1
package oldpackages.requirementsmodel.fiveyearsjava.generatedG4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ReqModels5YearsJavaParser}.
 */
public interface ReqModels5YearsJavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ReqModels5YearsJavaParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ReqModels5YearsJavaParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReqModels5YearsJavaParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ReqModels5YearsJavaParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReqModels5YearsJavaParser#specification}.
	 * @param ctx the parse tree
	 */
	void enterSpecification(ReqModels5YearsJavaParser.SpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReqModels5YearsJavaParser#specification}.
	 * @param ctx the parse tree
	 */
	void exitSpecification(ReqModels5YearsJavaParser.SpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReqModels5YearsJavaParser#experienceRequirement}.
	 * @param ctx the parse tree
	 */
	void enterExperienceRequirement(ReqModels5YearsJavaParser.ExperienceRequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReqModels5YearsJavaParser#experienceRequirement}.
	 * @param ctx the parse tree
	 */
	void exitExperienceRequirement(ReqModels5YearsJavaParser.ExperienceRequirementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReqModels5YearsJavaParser#languageRequirement}.
	 * @param ctx the parse tree
	 */
	void enterLanguageRequirement(ReqModels5YearsJavaParser.LanguageRequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReqModels5YearsJavaParser#languageRequirement}.
	 * @param ctx the parse tree
	 */
	void exitLanguageRequirement(ReqModels5YearsJavaParser.LanguageRequirementContext ctx);
}