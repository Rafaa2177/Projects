import mongoose from 'mongoose';
import {ISpecializationPersistence} from "../../dataschema/ISpecializationPersistence";

/**
 * Mongoose schema for Specialization.
 * Defines the structure of the specialization documents in the MongoDB collection.
 */
const SpecializationSchema = new mongoose.Schema(
  {

    //The unique identifier of the specialization.
    domainId: { type: String, unique: true },

    //The name of the specialization.
    name: { type: String, unique: true },

    //The code of the specialization.
    code: { type: String, unique: true },

    //The description of the specialization
    description: { type: String, unique: false }
  },
  {
    // Automatically adds `createdAt` and `updatedAt` timestamps to the schema.
    timestamps: true
  }
);

/**
 * Mongoose model for Specialization.
 * Represents the Specialization collection in the MongoDB database.
 */
export default mongoose.model<ISpecializationPersistence & mongoose.Document>('Specialization', SpecializationSchema);
