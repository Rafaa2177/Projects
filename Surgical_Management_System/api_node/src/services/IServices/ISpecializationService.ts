import { Result } from "../../core/logic/Result";
import ISpecializationDTO from "../../dto/ISpecializationDTO";
import {SpecializationId} from "../../domain/specializationId";

/**
 * Interface for Specialization Service.
 * Provides methods for managing specializations.
 */
export default interface ISpecializationService {

  /**
   * Creates a new specialization.
   * @param specializationDTO The specialization data transfer object.
   * @returns A Promise that resolves to a Result containing the created specialization DTO.
   */
  createSpecialization(specializationDTO: ISpecializationDTO): Promise<Result<ISpecializationDTO>>;

  /**
   * Updates a specialization.
   * @param specializationDTO The specialization data transfer object.
   * @returns A Promise that resolves to a Result containing the updated specialization DTO.
   */
  updateSpecialization(allergiesDTO: ISpecializationDTO): Promise<Result<ISpecializationDTO>>;

  /**
   * Retrieves all specializations.
   * @returns A Promise that resolves to a Result containing an array of specialization DTOs.
   */
  getSpecialization(): Promise<Result<ISpecializationDTO[]>>;

  /**
   * Retrieves a specialization by ID.
   * @param specializationId The ID of the specialization to retrieve.
   * @returns A Promise that resolves to a Result containing the specialization DTO.
   */
  getSpecializationById(specializationId: SpecializationId): Promise<Result<ISpecializationDTO>>;
}
