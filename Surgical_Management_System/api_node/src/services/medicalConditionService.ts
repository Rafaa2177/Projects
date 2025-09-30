import { Service, Inject } from 'typedi';
import config from "../../config";
import IMedicalConditionRepo from '../services/IRepos/IMedicalConditionRepo';
import IMedicalConditionService from './IServices/IMedicalConditionService';
import { Result } from "../core/logic/Result";
import { MedicalConditionMap } from "../mappers/MedicalConditionMap";
import IMedicalConditionDTO from "../dto/IMedicalConditionDto";
import { MedicalCondition } from "../domain/medicalCondition";
import { MedicalConditionId } from "../domain/medicalConditionId";

/**
 * Serviço responsável por manipular as condições médicas.
 */
@Service()
export default class MedicalConditionService implements IMedicalConditionService {

  /**
   * Construtor que injeta o repositório de condições médicas.
   * @param medicalConditionRepo O repositório para interagir com a persistência de dados.
   */
  constructor(
    @Inject(config.repos.medicalCondition.name) private medicalConditionRepo : IMedicalConditionRepo
  ) {}

  /**
   * Recupera uma condição médica a partir do seu ID de domínio.
   *
   * @param roleId O ID da condição médica.
   * @returns Resultado com o DTO da condição médica, ou uma falha caso não seja encontrado.
   */
  public async getMedicalCondition(roleId: string): Promise<Result<IMedicalConditionDTO>> {
    try {
      const role = await this.medicalConditionRepo.findByDomainId(roleId);

      if (role === null) {
        return Result.fail<IMedicalConditionDTO>("Role not found");
      } else {
        const roleDTOResult = MedicalConditionMap.toDTO(role) as IMedicalConditionDTO;
        return Result.ok<IMedicalConditionDTO>(roleDTOResult);
      }
    } catch (e) {
      throw e;
    }
  }

  /**
   * Cria uma nova condição médica.
   *
   * @param roleDTO O DTO da nova condição médica a ser criada.
   * @returns Resultado com o DTO da condição médica criada, ou uma falha caso não seja possível.
   */
  public async createMedicalCondition(roleDTO: IMedicalConditionDTO): Promise<Result<IMedicalConditionDTO>> {
    try {
      const roleOrError = await MedicalCondition.create(roleDTO);

      if (roleOrError.isFailure) {
        return Result.fail<IMedicalConditionDTO>(roleOrError.errorValue());
      }

      const roleResult = roleOrError.getValue();
      await this.medicalConditionRepo.save(roleResult);

      const roleDTOResult = MedicalConditionMap.toDTO(roleResult) as IMedicalConditionDTO;
      return Result.ok<IMedicalConditionDTO>(roleDTOResult);
    } catch (e) {
      throw e;
    }
  }

  /**
   * Atualiza uma condição médica existente.
   *
   * @param roleDTO O DTO da condição médica a ser atualizada.
   * @returns Resultado com o DTO da condição médica atualizada, ou uma falha caso a condição não seja encontrada.
   */
  public async updateMedicalCondition(roleDTO: IMedicalConditionDTO): Promise<Result<IMedicalConditionDTO>> {
    try {
      const role = await this.medicalConditionRepo.findByDomainId(roleDTO.id);

      if (role === null) {
        return Result.fail<IMedicalConditionDTO>("Medical Condition not found");
      } else {
        role.name = roleDTO.name;
        role.code = roleDTO.code;
        const savedMedicalCondition = await this.medicalConditionRepo.save(role);

        const roleDTOResult = MedicalConditionMap.toDTO(savedMedicalCondition) as IMedicalConditionDTO;
        return Result.ok<IMedicalConditionDTO>(roleDTOResult);
      }
    } catch (e) {
      throw e;
    }
  }

  /**
   * Recupera todas as condições médicas cadastradas.
   *
   * @returns Resultado com um array de DTOs das condições médicas, ou uma falha caso nenhuma condição seja encontrada.
   */
  public async getAllMedicalConditions(): Promise<Result<IMedicalConditionDTO[]>> {
    try {
      const role = await this.medicalConditionRepo.findAll(); // Busca todas as condições médicas

      if (role.length === 0) {
        return Result.fail<IMedicalConditionDTO[]>("No medical conditions found");
      }

      const medicalConditionsDTO = role.map(condition => MedicalConditionMap.toDTO(condition)); // Mapeia as condições médicas para DTOs
      return Result.ok<IMedicalConditionDTO[]>(medicalConditionsDTO);
    } catch (e) {
      throw e;
    }
  }

  /**
   * Recupera uma condição médica específica pelo seu ID de domínio.
   *
   * @param medicalConditionId O ID de domínio da condição médica.
   * @returns Resultado com o DTO da condição médica encontrada, ou uma falha caso não seja encontrada.
   */
  public async getMedicalConditionById(medicalConditionId: MedicalConditionId): Promise<Result<IMedicalConditionDTO>> {
    try {
      const medicalCond = await this.medicalConditionRepo.findByDomainId(medicalConditionId);

      if (medicalCond === null) {
        return Result.fail<IMedicalConditionDTO>("Allergy not found");
      } else {
        const medicalCondDTOResult = MedicalConditionMap.toDTO(medicalCond) as IMedicalConditionDTO;
        return Result.ok<IMedicalConditionDTO>(medicalCondDTOResult);
      }
    } catch (e) {
      throw e;
    }
  }

}
