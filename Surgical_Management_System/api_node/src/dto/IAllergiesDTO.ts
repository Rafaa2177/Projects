/**
 * Data Transfer Object for allergies.
 */
export default interface IAllergiesDTO {
  /**
   * The ID of the allergy.
   */
  id?: string;

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
