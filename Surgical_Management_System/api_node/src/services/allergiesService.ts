import { Service, Inject } from 'typedi';
import config from "../../config";
import { Result } from "../core/logic/Result";
import IAllergiesService from "./IServices/IAllergiesService";
import IAllergiesRepo from "./IRepos/IAllergiesRepo";
import IAllergiesDTO from "../dto/IAllergiesDTO";
import {AllergiesMap} from "../mappers/AllergiesMap";
import {Allergies} from "../domain/allergies";
import axios from 'axios';
import ISpecializationDTO from "../dto/ISpecializationDTO";
import {SpecializationMap} from "../mappers/SpecializationMap";
import {SpecializationId} from "../domain/specializationId";
import {AllergiesId} from "../domain/allergiesId";

/**
 * Service class for managing allergies.
 */
@Service()
export default class AllergiesService implements IAllergiesService {
  constructor(
    @Inject(config.repos.allergies.name) private allergiesRepo : IAllergiesRepo
  ) {}

  /**
   * Retrieves all allergies.
   * @returns A promise that resolves to a result containing an array of allergy DTOs.
   */
  public async getAllAllergies(): Promise<Result<IAllergiesDTO[]>> {
    try {
      const allergies = await this.allergiesRepo.findAll();

      if (allergies.length === 0) {
        return Result.fail<IAllergiesDTO[]>("No allergies found");
      } else {
        const allergiesDTOResult = allergies.map(allergy => AllergiesMap.toDTO(allergy) as IAllergiesDTO);
        return Result.ok<IAllergiesDTO[]>(allergiesDTOResult);
      }
    } catch (e) {
      throw e;
    }
  }

  /**
   * Creates a new allergy.
   * @param allergiesDTO - The allergy data transfer object.
   * @returns A promise that resolves to a result containing the created allergy DTO.
   */
  public async createAllergies(allergiesDTO: IAllergiesDTO): Promise<Result<IAllergiesDTO>> {
    try {
      const allergiesOrError = await Allergies.create(allergiesDTO);

      if (allergiesOrError.isFailure) {
        return Result.fail<IAllergiesDTO>(allergiesOrError.errorValue());
      }

      const allergiesResult = allergiesOrError.getValue();

      await this.allergiesRepo.save(allergiesResult);

      const allergiesDTOResult = AllergiesMap.toDTO(allergiesResult) as IAllergiesDTO;
      return Result.ok<IAllergiesDTO>(allergiesDTOResult);
    } catch (e) {
      throw e;
    }
  }

  /**
   * Updates an existing allergy.
   * @param allergiesDTO - The allergy data transfer object.
   * @returns A promise that resolves to a result containing the updated allergy DTO.
   */
  public async updateAllergies(allergiesDTO: IAllergiesDTO): Promise<Result<IAllergiesDTO>> {
    try {
      console.log('Service - Updating allergy:', allergiesDTO);

      const allergy = await this.allergiesRepo.findByDomainId(allergiesDTO.id);
      console.log('Service - Found existing allergy:', allergy);

      if (allergy === null) {
        console.log('Service - Allergy not found');
        return Result.fail<IAllergiesDTO>("Allergy not found");
      }
      else {
        allergy.name = allergiesDTO.name;
        allergy.code = allergiesDTO.code;
        allergy.description = allergiesDTO.description;

        console.log('Service - Saving updated allergy:', allergy);
        const savedAllergy = await this.allergiesRepo.save(allergy);
        console.log('Service - Save result:', savedAllergy);

        const allergyDTOResult = AllergiesMap.toDTO(savedAllergy) as IAllergiesDTO;
        return Result.ok<IAllergiesDTO>(allergyDTOResult)
      }
    } catch (e) {
      console.error('Service - Update error:', e);
      throw e;
    }
  }

  /**
   * Retrieves an allergy by its domain ID.
   * @param allergyId - The domain ID of the allergy.
   * @returns A promise that resolves to a result containing the found allergy DTO.
   */
  public async getAllergiesById(allergyId: AllergiesId): Promise<Result<IAllergiesDTO>> {
    try {
      const allergy = await this.allergiesRepo.findByDomainId(allergyId);

      if (allergy === null) {
        return Result.fail<IAllergiesDTO>("Allergy not found");
      } else {
        const allergyDTOResult = AllergiesMap.toDTO( allergy ) as IAllergiesDTO;
        return Result.ok<IAllergiesDTO>(allergyDTOResult);
      }
    } catch (e) {
      throw e;
    }
  }
}
