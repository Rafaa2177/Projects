import { Repo } from "../../core/infra/Repo";
import {Allergies} from "../../domain/allergies";
import {AllergiesId} from "../../domain/allergiesId";

/**
 * Interface for allergies repository.
 */
export default interface IAllergiesRepo extends Repo<Allergies> {
  /**
   * Saves an allergy to the database.
   * @param allergies - The allergy domain object.
   * @returns A promise that resolves to the saved allergy domain object.
   */
  save(allergies: Allergies): Promise<Allergies>;

  /**
   * Finds an allergy by its domain ID.
   * @param allergiesId - The domain ID of the allergy.
   * @returns A promise that resolves to the found allergy domain object, or null if not found.
   */
  findByDomainId (allergiesId: AllergiesId | string): Promise<Allergies>;

  /**
   * Finds all allergies in the database.
   * @returns A promise that resolves to an array of allergy domain objects.
   */
  findAll(): Promise<Allergies[]>;

  //findByIds (rolesIds: RoleId[]): Promise<Role[]>;
  //saveCollection (roles: Role[]): Promise<Role[]>;
  //removeByRoleIds (roles: RoleId[]): Promise<any>
}
