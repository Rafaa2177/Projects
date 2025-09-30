import { Service, Inject } from 'typedi';
import IMedicalConditionRepo from "../services/IRepos/IMedicalConditionRepo";
import { MedicalConditionMap } from "../mappers/MedicalConditionMap";
import { Document, FilterQuery, Model } from 'mongoose';
import { IMedicalConditionPersistence } from '../dataschema/IMedicalConditionPersistence';
import { MedicalCondition } from "../domain/medicalCondition";
import { MedicalConditionId } from "../domain/medicalConditionId";

/**
 * Implementação do repositório para a entidade `MedicalCondition`.
 *
 * O repositório `MedicalConditionRepo` é responsável por interagir com o banco de dados MongoDB
 * para realizar operações CRUD (Criar, Ler, Atualizar, Deletar) relacionadas às condições médicas.
 * Ele usa o Mongoose para gerenciar a persistência de dados e o mapeamento entre a entidade de domínio e os dados armazenados.
 *
 * @class MedicalConditionRepo
 * @implements {IMedicalConditionRepo}
 */
@Service()
export default class MedicalConditionRepo implements IMedicalConditionRepo {
  private models: any;

  /**
   * Construtor do repositório `MedicalConditionRepo`.
   *
   * @param {Model<IMedicalConditionPersistence & Document>} medicalConditionSchema - Modelo Mongoose que representa
   * a coleção de condições médicas no MongoDB. Este modelo é usado para interagir com o banco de dados.
   */
  constructor(
    @Inject('medicalConditionSchema') private medicalConditionSchema: Model<IMedicalConditionPersistence & Document>,
  ) {}

  /**
   * Cria a consulta base para buscas no banco de dados.
   *
   * Este método define um objeto de consulta inicial, que pode ser modificado conforme necessário
   * em métodos como `findByDomainId` ou `findAll`.
   *
   * @returns {any} Objeto de consulta com um campo `where` vazio.
   */
  private createBaseQuery(): any {
    return {
      where: {},
    };
  }

  /**
   * Verifica se uma condição médica já existe no banco de dados com base no ID da entidade.
   *
   * Este método consulta o banco de dados para verificar se uma condição médica com o mesmo `domainId`
   * já está registrada. A verificação é feita com base no ID da condição médica, que é um campo único.
   *
   * @param {MedicalCondition} role - A instância da entidade `MedicalCondition` que se deseja verificar a existência.
   * @returns {Promise<boolean>} Retorna `true` se a condição médica existir no banco de dados, caso contrário `false`.
   */
  public async exists(role: MedicalCondition): Promise<boolean> {
    const idX = role.id instanceof MedicalConditionId ? (<MedicalConditionId>role.id).toValue() : role.id;
    const query = { domainId: idX };

    // Realiza a consulta e retorna se o documento foi encontrado.
    const roleDocument = await this.medicalConditionSchema.findOne(query as FilterQuery<IMedicalConditionPersistence & Document>);
    return !!roleDocument === true;
  }

  /**
   * Salva uma nova condição médica ou atualiza uma existente no banco de dados.
   *
   * Este método verifica se a condição médica já existe no banco de dados. Se existir, ele a atualiza com
   * os novos valores de `name` e `code`. Caso contrário, ele cria um novo registro.
   * A persistência é realizada usando o método do Mongoose.
   *
   * @param {MedicalCondition} medicalCondition - A instância da entidade `MedicalCondition` a ser salva ou atualizada.
   * @returns {Promise<MedicalCondition>} Retorna a instância atualizada ou criada da entidade `MedicalCondition`.
   * @throws {Error} Lança um erro se ocorrer alguma falha durante a operação de salvamento ou atualização.
   */
  public async save(medicalCondition: MedicalCondition): Promise<MedicalCondition> {
    const query = { domainId: medicalCondition.id.toString() };
    const roleDocument = await this.medicalConditionSchema.findOne(query);

    try {
      if (roleDocument === null) {
        // Cria um novo registro se a condição médica não existir.
        const rawMedicalCondition: any = MedicalConditionMap.toPersistence(medicalCondition);
        const medicalConditionCreated = await this.medicalConditionSchema.create(rawMedicalCondition);
        return MedicalConditionMap.toDomain(medicalConditionCreated);
      }

      // Atualiza o registro existente com os novos valores.
      const updateResult = await this.medicalConditionSchema.updateOne(
        query,
        {
          $set: {
            name: medicalCondition.name,
            code: medicalCondition.code,
          },
        }
      );

      if (updateResult.modifiedCount > 0) {
        const updatedDoc = await this.medicalConditionSchema.findOne(query);
        return updatedDoc ? MedicalConditionMap.toDomain(updatedDoc) : null;
      }

      return null;
    } catch (err) {
      throw err;
    }
  }

  /**
   * Busca uma condição médica pelo seu ID único (`domainId`).
   *
   * Este método realiza uma consulta no banco de dados para localizar uma condição médica
   * utilizando o `domainId` fornecido. Se encontrada, a condição médica é retornada como uma instância de domínio.
   *
   * @param {MedicalConditionId | string} medicalConditionId - O ID da condição médica a ser buscada (pode ser uma instância
   * de `MedicalConditionId` ou uma string).
   * @returns {Promise<MedicalCondition>} A instância da condição médica encontrada ou `null` se não existir.
   */
  public async findByDomainId(medicalConditionId: MedicalConditionId | string): Promise<MedicalCondition> {
    const query = { domainId: medicalConditionId };
    const medicalConditionRecord = await this.medicalConditionSchema.findOne(query as FilterQuery<IMedicalConditionPersistence & Document>);

    if (medicalConditionRecord != null) {
      return MedicalConditionMap.toDomain(medicalConditionRecord);
    } else {
      return null;
    }
  }

  /**
   * Busca todas as condições médicas armazenadas no banco de dados.
   *
   * Este método consulta o banco de dados e retorna todas as condições médicas registradas.
   * A lista de condições médicas é convertida de volta para instâncias de domínio usando o mapeamento.
   *
   * @returns {Promise<MedicalCondition[]>} Lista de todas as condições médicas armazenadas no banco de dados.
   * @throws {Error} Lança um erro se ocorrer alguma falha durante a consulta.
   */
  public async findAll(): Promise<MedicalCondition[]> {
    try {
      const roleRecords = await this.medicalConditionSchema.find();

      // Mapeia todos os registros para instâncias de domínio `MedicalCondition`.
      return roleRecords.map(record => MedicalConditionMap.toDomain(record));
    } catch (error) {
      throw error;
    }
  }
}
