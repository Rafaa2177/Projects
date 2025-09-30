import { IRolePersistence } from '../../dataschema/IRolePersistence';
import mongoose from 'mongoose';
import { IAllergiesPersistence } from "../../dataschema/IAllergiesPersistence";

/**
 * Mongoose schema for allergies.
 */
const AllergiesSchema = new mongoose.Schema(
  {
    /**
     * The domain ID of the allergy.
     */
    domainId: { type: String, unique: true },

    /**
     * The name of the allergy.
     */
    name: { type: String, unique: true },

    /**
     * The code of the allergy.
     */
    code: { type: String, unique: true },

    /**
     * The description of the allergy.
     */
    description: { type: String, unique: false }
  },
  {
    /**
     * Timestamps for the schema.
     */
    timestamps: true
  }
);

export default mongoose.model<IAllergiesPersistence & mongoose.Document>('Allergies', AllergiesSchema);
