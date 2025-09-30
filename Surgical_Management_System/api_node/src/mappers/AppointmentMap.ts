import { Mapper } from "../core/infra/Mapper";
import { Document, Model } from 'mongoose';
import { IAppointmentPersistence } from '../dataschema/IAppointmentPersistence';
import IAppointmentDTO from "../dto/IAppointmentDTO";
import { Appointment } from "../domain/appointment/appointment";
import { UniqueEntityID } from "../core/domain/UniqueEntityID";
import { OperationType } from "../domain/appointment/operationType";

export class AppointmentMap extends Mapper<Appointment> {

  public static toDTO(appointment: Appointment): IAppointmentDTO {
    return {

      id: appointment.id.toString(),
      doctorId: appointment.doctorId,
      pacientId: appointment.pacientId,
      startDate: appointment.operationStartDate,
      endDate: appointment.operationEndDate,
      room: appointment.operationRoom,
      operationType: appointment.operationType,
      status: appointment.status
    } as IAppointmentDTO;
  }

  public static toDomain(appointment: any | Model<IAppointmentPersistence & Document>): Appointment {

    const appointmentOrError = Appointment.create(
      appointment,
      new UniqueEntityID(appointment.domainId)
    );
    console.log(appointmentOrError);
    appointmentOrError.isFailure ? console.log(appointmentOrError.error) : '';

    return appointmentOrError.isSuccess ? appointmentOrError.getValue() : null;
  }

  public static toPersistence(appointment: Appointment): any {
    return {
      domainId: appointment.id.toString(),
      startDate: appointment.operationStartDate,
      endDate: appointment.operationEndDate,
      doctorId: appointment.doctorId,
      pacientId: appointment.pacientId,
      room: appointment.operationRoom,
      operationType: appointment.operationType,
      status: appointment.status
    }
  }
}
