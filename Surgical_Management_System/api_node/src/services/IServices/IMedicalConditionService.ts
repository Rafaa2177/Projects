import { Result } from "../../core/logic/Result";
import IMedicalConditionDTO from "../../dto/IMedicalConditionDto";
import { MedicalConditionId } from "../../domain/medicalConditionId";

/**
 * Interface que define os serviços disponíveis para manipulação de condições médicas.
 */
export default interface IMedicalConditionService {

  /**
   * Cria uma nova condição médica.
   *
   * @param roleDTO O DTO contendo os dados da nova condição médica a ser criada.
   * @returns Um `Promise` com o resultado da criação, que pode ser um DTO com a nova condição médica ou uma falha com a descrição do erro.
   */
  createMedicalCondition(roleDTO: IMedicalConditionDTO): Promise<Result<IMedicalConditionDTO>>;

  /**
   * Atualiza uma condição médica existente.
   *
   * @param roleDTO O DTO contendo os dados da condição médica a ser atualizada.
   * @returns Um `Promise` com o resultado da atualização, que pode ser um DTO com a condição médica atualizada ou uma falha com a descrição do erro.
   */
  updateMedicalCondition(roleDTO: IMedicalConditionDTO): Promise<Result<IMedicalConditionDTO>>;

  /**
   * Recupera uma condição médica específica pelo ID.
   *
   * @param roleId O ID da condição médica a ser recuperada.
   * @returns Um `Promise` com o resultado da busca, que pode ser um DTO com a condição médica encontrada ou uma falha caso a condição não seja encontrada.
   */
  getMedicalCondition(roleId: string): Promise<Result<IMedicalConditionDTO>>;

  /**
   * Recupera todas as condições médicas cadastradas.
   *
   * @returns Um `Promise` com o resultado da busca, que pode ser um array de DTOs das condições médicas ou uma falha caso não existam condições.
   */
  getAllMedicalConditions(): Promise<Result<IMedicalConditionDTO[]>>;

  /**
   * Recupera uma condição médica específica pelo seu ID de domínio.
   *
   * @param medicalConditionId O ID de domínio da condição médica a ser recuperada.
   * @returns Um `Promise` com o resultado da busca, que pode ser um DTO com a condição médica encontrada ou uma falha caso a condição não seja encontrada.
   */
  getMedicalConditionById(medicalConditionId: MedicalConditionId): Promise<Result<IMedicalConditionDTO>>;

}
