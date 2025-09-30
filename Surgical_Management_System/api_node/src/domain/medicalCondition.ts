import { AggregateRoot } from "../core/domain/AggregateRoot";
import { UniqueEntityID } from "../core/domain/UniqueEntityID";
import { Result } from "../core/logic/Result";
import { MedicalConditionId } from "./medicalConditionId";
import IMedicalConditionDTO from "../dto/IMedicalConditionDto";

/**
 * Interface para as propriedades da condição médica.
 * Representa os dados básicos necessários para criar ou atualizar uma condição médica.
 */
interface MedicalConditionProps {
  name: string;  // Nome da condição médica (ex: "Hipertensão", "Asma").
  code: string;  // Código identificador da condição médica (ex: código ICD ou outro código único).
}

/**
 * Classe que representa uma Condição Médica.
 * A classe herda de `AggregateRoot`, o que implica que ela é uma entidade de domínio com identidade própria e regras de negócio.
 */
export class MedicalCondition extends AggregateRoot<MedicalConditionProps> {

  /**
   * Retorna o ID único da condição médica.
   *
   * @returns {UniqueEntityID} O identificador único da condição médica.
   */
  get id (): UniqueEntityID {
    return this._id;
  }

  /**
   * Retorna o ID da condição médica como uma instância de `MedicalConditionId`.
   *
   * @returns {MedicalConditionId} O ID da condição médica.
   */
  get roleId (): MedicalConditionId {
    return new MedicalConditionId(this.roleId.toValue());
  }

  /**
   * Retorna o nome da condição médica.
   *
   * @returns {string} O nome da condição médica (ex: "Hipertensão", "Asma").
   */
  get name (): string {
    return this.props.name;
  }

  /**
   * Retorna o código da condição médica.
   *
   * @returns {string} O código associado à condição médica (ex: código ICD).
   */
  get code(): string {
    return this.props.code;
  }

  /**
   * Define o nome da condição médica.
   *
   * @param {string} value - O novo nome para a condição médica.
   */
  set name(value: string) {
    this.props.name = value;
  }

  /**
   * Define o código da condição médica.
   *
   * @param {string} value - O novo código para a condição médica.
   */
  set code(value: string) {
    this.props.code = value;
  }

  /**
   * Construtor da classe `MedicalCondition`.
   *
   * @param {MedicalConditionProps} props - As propriedades que definem a condição médica.
   * @param {UniqueEntityID} [id] - O ID único da condição médica. Se não fornecido, será gerado automaticamente.
   */
  public constructor(props: MedicalConditionProps, id?: UniqueEntityID) {
    super(props, id); // Chama o construtor da classe base `AggregateRoot`.
  }

  /**
   * Método estático para criar uma nova instância de `MedicalCondition`.
   * Este método valida os dados fornecidos e cria uma instância da condição médica.
   *
   * @param {IMedicalConditionDTO} allergiesDTO - Dados da condição médica fornecidos no DTO (Data Transfer Object).
   * @param {UniqueEntityID} [id] - O ID único da condição médica. Se não fornecido, será gerado automaticamente.
   *
   * @returns {Result<MedicalCondition>} Resultado que indica se a criação foi bem-sucedida ou falhou.
   */
  public static create(allergiesDTO: IMedicalConditionDTO, id?: UniqueEntityID): Result<MedicalCondition> {
    const { code, name } = allergiesDTO;

    // Validação: Verifica se o nome e o código foram fornecidos
    if (!code || !name) {
      return Result.fail<MedicalCondition>('Must provide code and a name for the allergy');
    }

    // Validação: Verifica se o código está no formato correto (deve ser um número com 5 a 20 dígitos)
    const codeRegex = /^[0-9]{5,20}$/;
    if (!codeRegex.test(code)) {
      return Result.fail<MedicalCondition>('Code must be a number with 5 to 20 digits');
    }

    // Criação da condição médica
    const allergies = new MedicalCondition({ code, name }, id);

    // Retorna o resultado da criação com sucesso
    return Result.ok<MedicalCondition>(allergies);
  }
}
