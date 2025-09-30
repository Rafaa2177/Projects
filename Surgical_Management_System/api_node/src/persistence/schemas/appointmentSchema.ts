import { IAppointmentPersistence } from '../../dataschema/IAppointmentPersistence';
import mongoose from 'mongoose';

const AppointmentSchema = new mongoose.Schema(
  {
    domainId: {
      type: String,
      unique: true
    },

    doctorId: {
      type: String,
      required: [true, 'Por favor insira o ID do médico']
    },

    pacientId: {
      type: String,
      required: [true, 'Por favor insira o ID do paciente']
    },

    startDate: {
      type: Date,
      required: [true, 'Por favor insira a data da consulta']
    },
    endDate: {
      type: Date,
      required: [true, 'Por favor insira a data da consulta']
    },

    room: {
      type: String,
      required: [true, 'Por favor insira a sala'],
    },

    operationType: {
      type: String,
      required: [true, 'Por favor insira o tipo de operação']
    },

    status: {
      type: String,
      required: [true, 'Por favor insira o status'],
      enum: ['SCHEDULED', 'COMPLETED', 'CANCELLED', 'IN_PROGRESS'],
      uppercase: true
    }
  },
  {
    timestamps: true
  }
);

export default mongoose.model<IAppointmentPersistence & mongoose.Document>('Appointment', AppointmentSchema);
