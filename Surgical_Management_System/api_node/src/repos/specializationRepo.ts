import { Service, Inject } from 'typedi';
import { Document, FilterQuery, Model } from 'mongoose';
import ISpecializationRepo from "../services/IRepos/ISpecializationRepo";
import { Specialization } from "../domain/specialization";
import { ISpecializationPersistence } from "../dataschema/ISpecializationPersistence";
import { SpecializationMap } from "../mappers/SpecializationMap";
import { SpecializationId } from "../domain/specializationId";
import ISpecializationDTO from "../dto/ISpecializationDTO";

/**
 * Repository for managing specializations in the database.
 * Implements the `ISpecializationRepo` interface to provide data access for specializations.
 */
@Service()
export default class SpecializationRepo implements ISpecializationRepo {
  private models: any;

  /**
   * Constructor to initialize the specialization repository.
   * @param specializationSchema The Mongoose schema for specialization persistence.
   */
  constructor(
    @Inject('specializationSchema') private specializationSchema: Model<ISpecializationPersistence & Document>,
  ) {}

  /**
   * Creates a base query object for specializations.
   * @returns An object representing the base query.
   */
  private createBaseQuery(): any {
    return {
      where: {},
    };
  }

  /**
   * Checks if a specialization exists in the database.
   * @param specialization The specialization to check.
   * @returns A Promise that resolves to `true` if the specialization exists, or `false` otherwise.
   */
  public async exists(specialization: Specialization): Promise<boolean> {
    const idX = specialization.id instanceof SpecializationId
      ? (<SpecializationId>specialization.id).toValue()
      : specialization.id;

    const query = { domainId: idX };
    const specializationDocument = await this.specializationSchema.findOne(
      query as FilterQuery<ISpecializationPersistence & Document>
    );

    return !!specializationDocument === true;
  }

  /**
   * Saves or updates a specialization in the database.
   * @param specialization The specialization to save or update.
   * @returns A Promise that resolves to the saved or updated specialization.
   */
  public async save(specialization: Specialization): Promise<Specialization> {
    const query = { domainId: specialization.id.toString() };

    try {
      const specializationDocument = await this.specializationSchema.findOne(query);

      if (specializationDocument === null) {
        const rawSpecialization = SpecializationMap.toPersistence(specialization);
        const specializationCreated = await this.specializationSchema.create(rawSpecialization);
        return SpecializationMap.toDomain(specializationCreated);
      }

      // Update the existing document
      const updateResult = await this.specializationSchema.updateOne(
        query,
        {
          $set: {
            name: specialization.name,
            code: specialization.code,
            description: specialization.description,
          },
        }
      );

      if (updateResult.modifiedCount > 0) {
        const updatedDoc = await this.specializationSchema.findOne(query);
        return updatedDoc ? SpecializationMap.toDomain(updatedDoc) : null;
      }

      return null;
    } catch (err) {
      throw err;
    }
  }

  /**
   * Finds a specialization by its domain ID.
   * @param specializationId The domain ID of the specialization.
   * @returns A Promise that resolves to the found specialization, or `null` if not found.
   */
  public async findByDomainId(specializationId: SpecializationId | string): Promise<Specialization> {
    const query = { domainId: specializationId };
    const specializationRecord = await this.specializationSchema.findOne(
      query as FilterQuery<ISpecializationPersistence & Document>
    );

    if (specializationRecord != null) {
      return SpecializationMap.toDomain(specializationRecord);
    } else {
      return null;
    }
  }

  /**
   * Retrieves all specializations from the database.
   * @returns A Promise that resolves to an array of specializations.
   */
  public async findAll(): Promise<Specialization[]> {
    const specializationRecord = await this.specializationSchema.find({});
    return specializationRecord.map((specializationRecord) =>
      SpecializationMap.toDomain(specializationRecord)
    );
  }
}
