import mongoose from 'mongoose';
import { IOperationRoomPersistence } from '../../dataschema/IOperationRoomPersistence';

const OperationRoomSchema = new mongoose.Schema({
  domainId: {
    type: String,
    required: true,
    unique: true
  },
  room: {
    type: String,
    required: true
  },
  roomType: {
    type: String,
    required: true
  }
}, { timestamps: true });

export default mongoose.model<IOperationRoomPersistence & mongoose.Document>('OperationRoom', OperationRoomSchema);
