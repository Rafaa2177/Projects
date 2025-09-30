import { IDomainEvent } from '../../core/domain/events/IDomainEvent';
import { UniqueEntityID } from '../../core/domain/UniqueEntityID';

export class AppointmentCancelledEvent implements IDomainEvent {
  public readonly appointmentId: UniqueEntityID;
  public readonly dateTimeOccurred: Date;

  constructor(appointmentId: UniqueEntityID) {
    this.appointmentId = appointmentId;
    this.dateTimeOccurred = new Date();
  }

  getAggregateId(): UniqueEntityID {
    return this.appointmentId;
  }
}
