import { UniqueEntityID } from "../core/domain/UniqueEntityID";

/**
 * Classe que representa o ID de uma condição médica.
 *
 * A classe `MedicalConditionId` herda de `UniqueEntityID` e é utilizada para garantir que o identificador
 * da condição médica seja único e validado de acordo com as regras da entidade.
 * Esta classe serve para dar um nome semântico ao identificador da condição médica, tornando o código mais legível.
 *
 * @extends {UniqueEntityID} A classe herda de `UniqueEntityID`, que é responsável pela geração e validação do ID único.
 */
export class MedicalConditionId extends UniqueEntityID {
  // Não há necessidade de métodos ou propriedades adicionais, pois herda tudo de `UniqueEntityID`.
}
