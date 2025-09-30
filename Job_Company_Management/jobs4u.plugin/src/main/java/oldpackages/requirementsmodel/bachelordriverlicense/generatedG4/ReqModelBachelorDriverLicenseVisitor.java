// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4.plugin/src/main/java/requirementsmodel/bachelordriverlicense/ReqModelBachelorDriverLicense.g4 by ANTLR 4.13.1
package oldpackages.requirementsmodel.bachelordriverlicense.generatedG4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ReqModelBachelorDriverLicenseParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ReqModelBachelorDriverLicenseVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(ReqModelBachelorDriverLicenseParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#specification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecification(ReqModelBachelorDriverLicenseParser.SpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#educationRequirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEducationRequirement(ReqModelBachelorDriverLicenseParser.EducationRequirementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#driverLicenseRequirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDriverLicenseRequirement(ReqModelBachelorDriverLicenseParser.DriverLicenseRequirementContext ctx);
}