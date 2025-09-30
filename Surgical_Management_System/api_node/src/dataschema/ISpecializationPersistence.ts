/**
 * Interface for Specialization Persistence.
 * Represents the structure of a specialization entity in the persistence layer.
 */
export interface ISpecializationPersistence {
  //The domain ID of the specialization.
  domainId: string;
  //The name of the specialization.
  name: string;
  //The code of the specialization.
  code: string;
  //The description of the specialization (optional).
  description?: string;
}
