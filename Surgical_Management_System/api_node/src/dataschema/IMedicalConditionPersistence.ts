/**
 * Interface para a persistência de uma condição médica.
 * Representa os dados de uma condição médica a serem armazenados ou recuperados de um repositório de dados.
 */
export interface IMedicalConditionPersistence {

  /**
   * ID único da condição médica.
   *
   * @type {string}
   * @description Identificador único da condição médica, geralmente utilizado para consultar ou manipular o registro na base de dados.
   */
  domainId: string;

  /**
   * Nome da condição médica.
   *
   * @type {string}
   * @description Nome da condição médica, como "Hipertensão", "Diabetes", etc.
   */
  name: string;

  /**
   * Código único associado à condição médica.
   *
   * @type {string}
   * @description Código de identificação, como um código ICD (International Classification of Diseases), ou outro código único para a condição médica.
   */
  code: string;
}
