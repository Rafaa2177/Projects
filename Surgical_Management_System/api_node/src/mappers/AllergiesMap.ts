import { Mapper } from "../core/infra/Mapper";
import { Document, Model } from 'mongoose';
import { IRolePersistence } from '../dataschema/IRolePersistence';
import IRoleDTO from "../dto/IRoleDTO";
import { Role } from "../domain/role";
import { UniqueEntityID } from "../core/domain/UniqueEntityID";
import { Allergies } from "../domain/allergies";
import IAllergiesDTO from "../dto/IAllergiesDTO";
import { IAllergiesPersistence } from "../dataschema/IAllergiesPersistence";

/**
 * Class for mapping allergies between different layers.
 */
export class AllergiesMap extends Mapper<Allergies> {

  /**
   * Maps an allergy domain object to a data transfer object.
   * @param allergies - The allergy domain object.
   * @returns The allergy data transfer object.
   */
  public static toDTO(allergies: Allergies): IAllergiesDTO {
    return {
      id: allergies.id.toString(),
      name: allergies.name,
      code: allergies.code,
      description: allergies.description
    } as IAllergiesDTO;
  }

  /**
   * Maps a persistence object to a domain object.
   * @param allergies - The persistence object.
   * @returns The allergy domain object.
   */
  public static toDomain(allergies: any | Model<IAllergiesPersistence & Document>): Allergies {
    const allergiesOrError = Allergies.create(
      allergies,
      new UniqueEntityID(allergies.domainId)
    );

    allergiesOrError.isFailure ? console.log(allergiesOrError.error) : '';

    return allergiesOrError.isSuccess ? allergiesOrError.getValue() : null;
  }

  /**
   * Maps a domain object to a persistence object.
   * @param allergies - The allergy domain object.
   * @returns The persistence object.
   */
  public static toPersistence(allergies: Allergies): any {
    return {
      domainId: allergies.id.toString(),
      name: allergies.name,
      code: allergies.code,
      description: allergies.description
    }
  }
}
