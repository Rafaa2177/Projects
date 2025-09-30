import { Result } from "../../core/logic/Result";
import IRoleDTO from "../../dto/IRoleDTO";
import IAllergiesDTO from "../../dto/IAllergiesDTO";
import {AllergiesId} from "../../domain/allergiesId";

/**
 * Interface for allergies service.
 */
export default interface IAllergiesService {
  /**
   * Creates a new allergy.
   * @param allergiesDTO - The allergy data transfer object.
   * @returns A promise that resolves to a result containing the created allergy DTO.
   */
  createAllergies(allergiesDTO: IAllergiesDTO): Promise<Result<IAllergiesDTO>>;

  /**
   * Updates an existing allergy.
   * @param allergiesDTO - The allergy data transfer object.
   * @returns A promise that resolves to a result containing the updated allergy DTO.
   */
  updateAllergies(allergiesDTO: IAllergiesDTO): Promise<Result<IAllergiesDTO>>;

  /**
   * Retrieves all allergies.
   * @returns A promise that resolves to a result containing an array of allergy DTOs.
   */
  getAllAllergies(): Promise<Result<IAllergiesDTO[]>>;

  /**
   * Retrieves an allergy by its domain ID.
   * @param allergiesId - The domain ID of the allergy.
   * @returns A promise that resolves to a result containing the found allergy DTO.
   */
  getAllergiesById(allergiesId: AllergiesId): Promise<Result<IAllergiesDTO>>;
}
