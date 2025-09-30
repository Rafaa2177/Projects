// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4.plugin/src/main/java/requirementsmodel/fiveyearsjava/ReqModels5YearsJava.g4 by ANTLR 4.13.1
package oldpackages.requirementsmodel.fiveyearsjava.generatedG4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ReqModels5YearsJavaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ReqModels5YearsJavaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ReqModels5YearsJavaParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ReqModels5YearsJavaParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReqModels5YearsJavaParser#specification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecification(ReqModels5YearsJavaParser.SpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReqModels5YearsJavaParser#experienceRequirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExperienceRequirement(ReqModels5YearsJavaParser.ExperienceRequirementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReqModels5YearsJavaParser#languageRequirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageRequirement(ReqModels5YearsJavaParser.LanguageRequirementContext ctx);
}