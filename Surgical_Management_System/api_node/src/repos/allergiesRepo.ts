import { Service, Inject } from 'typedi';
import { Document, FilterQuery, Model } from 'mongoose';
import IAllergiesRepo from "../services/IRepos/IAllergiesRepo";
import { Allergies } from "../domain/allergies";
import { IAllergiesPersistence } from "../dataschema/IAllergiesPersistence";
import { AllergiesMap } from "../mappers/AllergiesMap";
import { AllergiesId } from "../domain/allergiesId";
import {SpecializationMap} from "../mappers/SpecializationMap";

/**
 * Repository class for managing allergies.
 */
@Service()
export default class AllergiesRepo implements IAllergiesRepo {
  private models: any;

  /**
   * Constructs an instance of AllergiesRepo.
   * @param allergiesSchema - The Mongoose model for allergies.
   */
  constructor(
    @Inject('allergiesSchema') private allergiesSchema: Model<IAllergiesPersistence & Document>,
  ) {}

  /**
   * Creates a base query object.
   * @returns The base query object.
   */
  private createBaseQuery(): any {
    return {
      where: {},
    }
  }

  /**
   * Checks if an allergy exists in the database.
   * @param allergies - The allergy domain object.
   * @returns A promise that resolves to true if the allergy exists, false otherwise.
   */
  public async exists(allergies: Allergies): Promise<boolean> {
    const idX = allergies.id instanceof AllergiesId ? (<AllergiesId>allergies.id).toValue() : allergies.id;
    const query = { domainId: idX };
    const allergiesDocument = await this.allergiesSchema.findOne(query as FilterQuery<IAllergiesPersistence & Document>);
    return !!allergiesDocument === true;
  }

  /**
   * Saves an allergy to the database.
   * @param allergies - The allergy domain object.
   * @returns A promise that resolves to the saved allergy domain object.
   */
  public async save(allergies: Allergies): Promise<Allergies> {
    const query = { domainId: allergies.id.toString() };
    const allergiesDocument = await this.allergiesSchema.findOne(query);

    try {
      if (allergiesDocument === null) {
        const rawAllergies: any = AllergiesMap.toPersistence(allergies);
        const allergiesCreated = await this.allergiesSchema.create(rawAllergies);
        return AllergiesMap.toDomain(allergiesCreated);
      }

      const updateResult = await this.allergiesSchema.updateOne(
        query,
        {
          $set: {
            name: allergies.name,
            code: allergies.code,
            description: allergies.description,
          },
        }
      );

      if (updateResult.modifiedCount > 0) {
        const updatedDoc = await this.allergiesSchema.findOne(query);
        return updatedDoc ? AllergiesMap.toDomain(updatedDoc) : null;
      }

      return null;
    } catch (err) {
      throw err;
    }
  }

  /**
   * Finds an allergy by its domain ID.
   * @param allergiesId - The domain ID of the allergy.
   * @returns A promise that resolves to the found allergy domain object, or null if not found.
   */
  public async findByDomainId(allergiesId: AllergiesId | string): Promise<Allergies> {
    const query = { domainId: allergiesId };
    const allergiesRecord = await this.allergiesSchema.findOne(query as FilterQuery<IAllergiesPersistence & Document>);
    if (allergiesRecord != null) {
      return AllergiesMap.toDomain(allergiesRecord);
    } else {
      return null;
    }
  }

  /**
   * Finds all allergies in the database.
   * @returns A promise that resolves to an array of allergy domain objects.
   */
  public async findAll(): Promise<Allergies[]> {
    const allergiesRecord = await this.allergiesSchema.find({});
    return allergiesRecord.map(allergiesRecord => AllergiesMap.toDomain(allergiesRecord));
  }
}
