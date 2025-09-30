/**
 * Data Transfer Object (DTO) interface for Specialization.
 * Represents the structure of a specialization data transfer object.
 */
export default interface ISpecializationDTO {

  // The unique identifier of the specialization (optional).
  id?: string;

  // The name of the specialization.
  name: string;

  // The code of the specialization.
  code: string;

  // The description of the specialization (optional).
  description?: string
}
