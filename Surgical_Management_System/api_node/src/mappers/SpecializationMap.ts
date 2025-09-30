import { Mapper } from "../core/infra/Mapper";
import { Document, Model } from 'mongoose';
import { UniqueEntityID } from "../core/domain/UniqueEntityID";
import {Specialization} from "../domain/specialization";
import ISpecializationDTO from "../dto/ISpecializationDTO";
import {ISpecializationPersistence} from "../dataschema/ISpecializationPersistence";

/**
 * Mapper class for Specialization.
 * Provides methods to map between domain objects, DTOs, and persistence models.
 */
export class SpecializationMap extends Mapper<Specialization> {

  /**
   * Maps a Specialization domain object to a DTO.
   * @param specialization The Specialization domain object.
   * @returns The Specialization DTO.
   */
  public static toDTO( specialization: Specialization): ISpecializationDTO {
    return {
      id: specialization.id.toString(),
      name: specialization.name,
      code: specialization.code,
      description: specialization.description
    } as ISpecializationDTO;
  }

  /**
   * Maps a persistence model to a Specialization domain object.
   * @param specialization The persistence model.
   * @returns The Specialization domain object.
   */
  public static toDomain (specialization: any | Model<ISpecializationPersistence & Document> ): Specialization {
    const specializationOrError = Specialization.create(
      specialization,
      new UniqueEntityID(specialization.domainId)
    );

    specializationOrError.isFailure ? console.log(specializationOrError.error) : '';

    return specializationOrError.isSuccess ? specializationOrError.getValue() : null;
  }

  /**
   * Maps a Specialization domain object to a persistence model.
   * @param specialization The Specialization domain object.
   * @returns The persistence model.
   */
  public static toPersistence (specialization: Specialization): any {
    return {
      domainId: specialization.id.toString(),
      name: specialization.name,
      code: specialization.code,
      description: specialization.description
    }
  }
}
