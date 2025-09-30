/**
 * Interface for allergies persistence.
 */
export interface IAllergiesPersistence {
  /**
   * The domain ID of the allergy.
   */
  domainId: string;

  /**
   * The name of the allergy.
   */
  name: string;

  /**
   * The code of the allergy.
   */
  code: string;

  /**
   * The description of the allergy.
   */
  description?: string;
}
