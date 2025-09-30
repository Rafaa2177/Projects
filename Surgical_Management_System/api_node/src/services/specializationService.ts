import { Service, Inject } from 'typedi';
import config from "../../config";
import { Result } from "../core/logic/Result";
import ISpecializationService from "./IServices/ISpecializationService";
import ISpecializationRepo from "./IRepos/ISpecializationRepo";
import ISpecializationDTO from "../dto/ISpecializationDTO";
import {SpecializationMap} from "../mappers/SpecializationMap";
import {Specialization} from "../domain/specialization";
import {SpecializationId} from "../domain/specializationId";

/**
 * Service class for managing specializations.
 * Implements the `ISpecializationService` interface to provide business logic for specializations.
 */
@Service()
export default class SpecializationService implements ISpecializationService {

  /**
   * Constructor to initialize the specialization service.
   * @param specializationRepo The repository for specialization data access.
   */
  constructor(
    @Inject(config.repos.specialization.name) private specializationRepo : ISpecializationRepo
  ) {}

  /**
   * Retrieves all specializations.
   * @returns A Promise that resolves to a Result containing an array of specialization DTOs.
   */
  public async getSpecialization(): Promise<Result<ISpecializationDTO[]>> {
    try {
      const specialization = await this.specializationRepo.findAll();

      if (specialization.length === 0) {
        return Result.fail<ISpecializationDTO[]>("No specialization found");
      } else {
        const specializationDTOResult = specialization.map(specialization => SpecializationMap.toDTO(specialization) as ISpecializationDTO);
        return Result.ok<ISpecializationDTO[]>(specializationDTOResult);
      }
    } catch (e) {
      throw e;
    }
  }

  /**
   * Creates a new specialization.
   * @param specializationDTO The specialization DTO to create.
   * @returns A Promise that resolves to a Result containing the created specialization DTO.
   */
  public async createSpecialization(specializationDTO: ISpecializationDTO): Promise<Result<ISpecializationDTO>> {

    try {

      const specializationOrError = await Specialization.create( specializationDTO );

      if (specializationOrError.isFailure) {
        return Result.fail<ISpecializationDTO>(specializationOrError.errorValue());
      }

      const specializationResult = specializationOrError.getValue();

      await this.specializationRepo.save(specializationResult);

      const specializationDTOResult = SpecializationMap.toDTO( specializationResult ) as ISpecializationDTO;
      return Result.ok<ISpecializationDTO>( specializationDTOResult )
    } catch (e) {
      throw e;
    }
  }

  /**
   * Updates an existing specialization.
   * @param specializationDTO The specialization DTO to update.
   * @returns A Promise that resolves to a Result containing the updated specialization DTO.
   */
  public async updateSpecialization(specializationDTO: ISpecializationDTO): Promise<Result<ISpecializationDTO>> {
    try {
      console.log('Service - Updating specialization:', specializationDTO);

      const specialization = await this.specializationRepo.findByDomainId(specializationDTO.id);
      console.log('Service - Found existing specialization:', specialization);

      if (specialization === null) {
        console.log('Service - Specialization not found');
        return Result.fail<ISpecializationDTO>("Specialization not found");
      }
      else {
        specialization.name = specializationDTO.name;
        specialization.code = specializationDTO.code;
        specialization.description = specializationDTO.description;

        console.log('Service - Saving updated specialization:', specialization);
        const savedSpecialization = await this.specializationRepo.save(specialization);
        console.log('Service - Save result:', savedSpecialization);

        const specializationDTOResult = SpecializationMap.toDTO(savedSpecialization) as ISpecializationDTO;
        return Result.ok<ISpecializationDTO>(specializationDTOResult)
      }
    } catch (e) {
      console.error('Service - Update error:', e);
      throw e;
    }
  }

  /**
   * Retrieves a specialization by its ID.
   * @param specializationId The domain ID of the specialization.
   * @returns A Promise that resolves to a Result containing the specialization DTO.
   */
    public async getSpecializationById(specializationId: SpecializationId): Promise<Result<ISpecializationDTO>> {
        try {
        const specialization = await this.specializationRepo.findByDomainId(specializationId);

        if (specialization === null) {
            return Result.fail<ISpecializationDTO>("Specialization not found");
        } else {
            const specializationDTOResult = SpecializationMap.toDTO( specialization ) as ISpecializationDTO;
            return Result.ok<ISpecializationDTO>(specializationDTOResult);
        }
        } catch (e) {
        throw e;
        }
    }

}
