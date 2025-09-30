import { Mapper } from "../core/infra/Mapper";
import { Document, Model } from 'mongoose';
import { IMedicalConditionPersistence } from '../dataschema/IMedicalConditionPersistence';
import { MedicalCondition } from "../domain/medicalCondition";
import { UniqueEntityID } from "../core/domain/UniqueEntityID";
import IMedicalConditionDTO from "../dto/IMedicalConditionDto";

/**
 * Classe de mapeamento para a entidade `MedicalCondition`.
 *
 * A classe `MedicalConditionMap` é responsável por transformar a entidade `MedicalCondition`
 * em diferentes representações: um objeto DTO para transferência de dados, um objeto persistente
 * para armazenamento no banco de dados e vice-versa.
 */
export class MedicalConditionMap extends Mapper<MedicalCondition> {

  /**
   * Converte uma instância de `MedicalCondition` para um DTO (Data Transfer Object).
   *
   * O DTO é uma estrutura simplificada dos dados de `MedicalCondition` que pode ser usada para transferir
   * informações entre camadas do sistema ou expor os dados por meio de APIs.
   *
   * @param {MedicalCondition} role - A instância da entidade `MedicalCondition` a ser convertida.
   *
   * @returns {IMedicalConditionDTO} O DTO com os dados da condição médica.
   */
  public static toDTO(role: MedicalCondition): IMedicalConditionDTO {
    return {
      id: role.id.toString(),  // Converte o ID para string.
      name: role.name,         // Nome da condição médica.
      code: role.code          // Código associado à condição médica.
    } as IMedicalConditionDTO;
  }

  /**
   * Converte um objeto de dados (geralmente um documento de banco de dados) para a entidade `MedicalCondition`.
   *
   * Este método mapeia um objeto de persistência para a instância de domínio `MedicalCondition`,
   * validando os dados e criando a entidade com os valores apropriados.
   *
   * @param {any | Model<IMedicalConditionPersistence & Document>} role - O objeto de persistência ou documento
   * a ser convertido para a entidade `MedicalCondition`.
   *
   * @returns {MedicalCondition | null} A instância da entidade `MedicalCondition`, ou `null` se houver erro na criação.
   */
  public static toDomain(role: any | Model<IMedicalConditionPersistence & Document>): MedicalCondition {
    const roleOrError = MedicalCondition.create(
      role,
      new UniqueEntityID(role.domainId)  // Converte o ID persistente para `UniqueEntityID`.
    );

    // Se a criação falhar, loga o erro no console.
    roleOrError.isFailure ? console.log(roleOrError.error) : '';

    // Retorna a entidade criada ou `null` se falhou.
    return roleOrError.isSuccess ? roleOrError.getValue() : null;
  }

  /**
   * Converte a entidade `MedicalCondition` para um formato adequado para persistência no banco de dados.
   *
   * Este método mapeia a entidade de domínio para um objeto de persistência, que pode ser armazenado em um banco
   * de dados (por exemplo, um documento MongoDB).
   *
   * @param {MedicalCondition} role - A instância da entidade `MedicalCondition` a ser convertida para persistência.
   *
   * @returns {any} O objeto de persistência pronto para ser armazenado no banco de dados.
   */
  public static toPersistence(role: MedicalCondition): any {
    return {
      domainId: role.id.toString(),  // Converte o ID para string para persistir.
      name: role.name,               // Nome da condição médica.
      code: role.code                // Código da condição médica.
    };
  }
}
