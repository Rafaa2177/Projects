// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4.plugin/src/main/java/requirementsmodel/bachelordriverlicense/ReqModelBachelorDriverLicense.g4 by ANTLR 4.13.1
package oldpackages.requirementsmodel.bachelordriverlicense.generatedG4;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link ReqModelBachelorDriverLicenseVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
@SuppressWarnings("CheckReturnValue")
public class ReqModelBachelorDriverLicenseBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements ReqModelBachelorDriverLicenseVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitStart(ReqModelBachelorDriverLicenseParser.StartContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitSpecification(ReqModelBachelorDriverLicenseParser.SpecificationContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitEducationRequirement(ReqModelBachelorDriverLicenseParser.EducationRequirementContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitDriverLicenseRequirement(ReqModelBachelorDriverLicenseParser.DriverLicenseRequirementContext ctx) { return visitChildren(ctx); }
}