import { IMedicalConditionPersistence } from '../../dataschema/IMedicalConditionPersistence';
import mongoose from 'mongoose';

/**
 * Definição do Mongoose Schema para a entidade `MedicalCondition`.
 *
 * Este arquivo define o esquema de dados para armazenar informações sobre uma condição médica
 * no banco de dados, utilizando o Mongoose, uma biblioteca de modelagem de objetos para MongoDB.
 * O esquema é utilizado para criar um modelo que pode ser usado para interagir com a coleção no MongoDB.
 *
 * @module MedicalConditionSchema
 */

// Definição do Mongoose Schema para `MedicalCondition`
const MedicalConditionSchema = new mongoose.Schema(
  {
    /**
     * Identificador único da condição médica.
     *
     * @type {String}
     * @description Este campo é único e representa o ID da condição médica no banco de dados.
     * Ele deve ser gerado e fornecido pela camada de aplicação. O valor é armazenado como uma string.
     */
    domainId: { type: String, unique: true },

    /**
     * Nome da condição médica.
     *
     * @type {String}
     * @description O nome descreve a condição médica, como "Hipertensão", "Diabetes", etc.
     * Este campo é único, ou seja, não pode haver duas condições médicas com o mesmo nome.
     */
    name: { type: String, unique: true },

    /**
     * Código associado à condição médica.
     *
     * @type {String}
     * @description O código pode ser um código internacional (como ICD-10) ou outro identificador único
     * para a condição médica. Este campo também é único no banco de dados.
     */
    code: { type: String, unique: true },
  },
  {
    /**
     * Opções de configuração do schema:
     *
     * - `timestamps: true`: cria automaticamente os campos `createdAt` e `updatedAt` para rastrear
     * a criação e a atualização dos documentos.
     */
    timestamps: true
  }
);

// Modelo Mongoose para `MedicalCondition` com persistência de dados e validação.
export default mongoose.model<IMedicalConditionPersistence & mongoose.Document>(
  'Medical Condition', // Nome do modelo
  MedicalConditionSchema // Esquema definido
);
