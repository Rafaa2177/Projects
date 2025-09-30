import { AggregateRoot } from "../../core/domain/AggregateRoot";
import { UniqueEntityID } from "../../core/domain/UniqueEntityID";
import { Guard } from "../../core/logic/Guard";
import { Result } from "../../core/logic/Result";
import IAppointmentDTO from "../../dto/IAppointmentDTO";
import { OperationType, isValidOperationTypeIndex } from "./operationType";
import { AppointmentCreatedEvent } from "./appointmentCreatedEvent";
import { AppointmentCancelledEvent } from "./appointmentCancelledEvent";

/**
 * Interface representing the properties of an appointment.
 */
interface appointmentProps {
  doctorId: string,
  pacientId: string,
  startDate: Date,
  endDate: Date,
  room: string,
  operationType: string,
  status: string
}

/**
 * Class representing an appointment.
 * Extends the AggregateRoot class.
 */
export class Appointment extends AggregateRoot<appointmentProps> {
  /**
   * Gets the unique identifier of the appointment.
   * @returns {UniqueEntityID} The unique identifier of the appointment.
   */
  get id(): UniqueEntityID {
    return this._id;
  }

  /**
   * Gets the doctor ID associated with the appointment.
   * @returns {string} The doctor ID.
   */
  get doctorId(): string {
    return this.props.doctorId;
  }

  /**
   * Gets the patient ID associated with the appointment.
   * @returns {string} The patient ID.
   */
  get pacientId(): string {
    return this.props.pacientId;
  }

  /**
   * Gets the start date of the appointment.
   * @returns {Date} The start date.
   */
  get operationStartDate(): Date {
    return this.props.startDate;
  }

  /**
   * Gets the end date of the appointment.
   * @returns {Date} The end date.
   */
  get operationEndDate(): Date {
    return this.props.endDate;
  }

  /**
   * Gets the room where the appointment will take place.
   * @returns {string} The room.
   */
  get operationRoom(): string {
    return this.props.room;
  }

  /**
   * Gets the type of operation for the appointment.
   * @returns {string} The operation type.
   */
  get operationType(): string {
    return this.props.operationType;
  }

  /**
   * Gets the status of the appointment.
   * @returns {string} The status.
   */
  get status(): string {
    return this.props.status;
  }

  /**
   * Sets the end date of the appointment.
   * Throws an error if the end date is before the start date.
   * @param {Date} value - The new end date.
   */
  set endDate(value: Date) {
    if (value < this.props.startDate) {
      throw new Error('End date must be after start date');
    }
    this.props.endDate = value;
  }

  /**
   * Sets the status of the appointment.
   * Throws an error if the status is invalid.
   * Registers a cancellation event if the status is set to 'CANCELLED'.
   * @param {string} value - The new status.
   */
  set status(value: string) {
    if (!['SCHEDULED', 'COMPLETED', 'CANCELLED', 'IN_PROGRESS'].includes(value)) {
      throw new Error('Invalid status');
    }

    if (this.props.status !== value) {
      this.props.status = value;

      if (value === 'CANCELLED') {
        // Register cancellation event
        this.clearEvents();
        this.addDomainEvent(new AppointmentCancelledEvent(this.id));
      }
    }
  }

  /**
   * Private constructor for the Appointment class.
   * @param {appointmentProps} props - The properties of the appointment.
   * @param {UniqueEntityID} [id] - The unique identifier of the appointment.
   */
  private constructor(props: appointmentProps, id?: UniqueEntityID) {
    super(props, id);
  }

  /**
   * Static method to create a new appointment.
   * Validates the input data and returns a Result object.
   * @param {IAppointmentDTO} appointmentDTO - The data transfer object for the appointment.
   * @param {UniqueEntityID} [id] - The unique identifier of the appointment.
   * @returns {Result<Appointment>} The result of the creation operation.
   */
  public static create(appointmentDTO: IAppointmentDTO, id?: UniqueEntityID): Result<Appointment> {
    const guardResult = Guard.againstNullOrUndefinedBulk([
      { argument: appointmentDTO.doctorId, argumentName: 'doctorId' },
      { argument: appointmentDTO.pacientId, argumentName: 'pacientId' },
      { argument: appointmentDTO.startDate, argumentName: 'startDate' },
      { argument: appointmentDTO.endDate, argumentName: 'endDate' },
      { argument: appointmentDTO.room, argumentName: 'room' },
      { argument: appointmentDTO.operationType, argumentName: 'operationType' },
      { argument: appointmentDTO.status, argumentName: 'status' }
    ]);

    if (!guardResult.succeeded) {
      return Result.fail<Appointment>(guardResult.message);
    }

    if (!id) {
      // Validate operationType
      if (!isValidOperationTypeIndex(appointmentDTO.operationType)) {
        return Result.fail<Appointment>(`Tipo de operação inválido: ${appointmentDTO.operationType}`);
      }
    }

    // Validate dates
    const appointmentStartDate = new Date(appointmentDTO.startDate);
    const now = new Date();
    const appointmentEndDate = new Date(appointmentDTO.endDate);

    if (isNaN(appointmentStartDate.getTime())) {
      return Result.fail<Appointment>('Data inválida');
    }

    if (appointmentStartDate < now && id == null) {
      return Result.fail<Appointment>('A data da consulta não pode ser no passado');
    }
    if (appointmentStartDate > appointmentEndDate) {
      return Result.fail<Appointment>('A data de início da consulta não pode ser depois da data de término');
    }
    const diffInMinutes = (appointmentEndDate.getTime() - appointmentStartDate.getTime()) / (1000 * 60);
    if (diffInMinutes < 15) {
      return Result.fail<Appointment>('A duração da consulta não deve de ser inferior a 15 minutos');
    }

    // Validate status
    const validStatus = ['SCHEDULED', 'COMPLETED', 'CANCELLED', 'IN_PROGRESS'];
    if (!validStatus.includes(appointmentDTO.status.toUpperCase())) {
      return Result.fail<Appointment>(`Status inválido: ${appointmentDTO.status}`);
    }

    const appointment = new Appointment({
      doctorId: appointmentDTO.doctorId,
      pacientId: appointmentDTO.pacientId,
      startDate: appointmentDTO.startDate,
      endDate: appointmentDTO.endDate,
      room: appointmentDTO.room,
      operationType: OperationType[appointmentDTO.operationType],
      status: appointmentDTO.status.toUpperCase()
    }, id);
    appointment.addDomainEvent(new AppointmentCreatedEvent(appointment.id));
    return Result.ok<Appointment>(appointment);
  }
}
