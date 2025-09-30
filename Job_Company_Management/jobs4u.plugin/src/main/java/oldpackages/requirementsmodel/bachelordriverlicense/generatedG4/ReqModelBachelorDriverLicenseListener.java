// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4.plugin/src/main/java/requirementsmodel/bachelordriverlicense/ReqModelBachelorDriverLicense.g4 by ANTLR 4.13.1
package oldpackages.requirementsmodel.bachelordriverlicense.generatedG4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ReqModelBachelorDriverLicenseParser}.
 */
public interface ReqModelBachelorDriverLicenseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ReqModelBachelorDriverLicenseParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ReqModelBachelorDriverLicenseParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#specification}.
	 * @param ctx the parse tree
	 */
	void enterSpecification(ReqModelBachelorDriverLicenseParser.SpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#specification}.
	 * @param ctx the parse tree
	 */
	void exitSpecification(ReqModelBachelorDriverLicenseParser.SpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#educationRequirement}.
	 * @param ctx the parse tree
	 */
	void enterEducationRequirement(ReqModelBachelorDriverLicenseParser.EducationRequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#educationRequirement}.
	 * @param ctx the parse tree
	 */
	void exitEducationRequirement(ReqModelBachelorDriverLicenseParser.EducationRequirementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#driverLicenseRequirement}.
	 * @param ctx the parse tree
	 */
	void enterDriverLicenseRequirement(ReqModelBachelorDriverLicenseParser.DriverLicenseRequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReqModelBachelorDriverLicenseParser#driverLicenseRequirement}.
	 * @param ctx the parse tree
	 */
	void exitDriverLicenseRequirement(ReqModelBachelorDriverLicenseParser.DriverLicenseRequirementContext ctx);
}