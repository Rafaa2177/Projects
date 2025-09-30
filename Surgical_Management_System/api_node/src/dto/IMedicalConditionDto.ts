/**
 * Data Transfer Object (DTO) para uma condição médica.
 *
 * Esta interface define a estrutura de dados que será transferida entre camadas do sistema
 * ao lidar com informações sobre uma condição médica, como seu nome e código. O objetivo do DTO é
 * transportar os dados de forma padronizada, sem lógica de negócios.
 */
export default interface IMedicalConditionDTO {

  /**
   * Identificador único da condição médica.
   *
   * @type {string}
   * @description O ID é opcional, e pode ser fornecido em operações de atualização ou leitura.
   * Caso não seja fornecido, o sistema geralmente gerará um ID automaticamente.
   */
  id?: string;

  /**
   * Nome da condição médica.
   *
   * @type {string}
   * @description O nome descreve a condição médica, como "Hipertensão", "Diabetes", etc.
   * Este campo é obrigatório para criação e atualização da condição médica.
   */
  name: string;

  /**
   * Código associado à condição médica.
   *
   * @type {string}
   * @description O código pode ser um código internacional (como ICD-10) ou outro identificador único
   * para a condição médica. Este campo também é obrigatório.
   */
  code: string;
}
