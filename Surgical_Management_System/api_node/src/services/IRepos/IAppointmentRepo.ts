import { Repo } from "../../core/infra/Repo";
import { Appointment } from "../../domain/appointment/appointment";
import { AppointmentId } from "../../domain/appointment/appointmentId";

export interface IAppointmentRepo extends Repo<Appointment> {
    save(appointment: Appointment): Promise<Appointment>;
    findByDomainId(appointmentId: AppointmentId | string): Promise<Appointment>;
    findAll(): Promise<Appointment[]>;
    findByDoctorId(doctorId: string): Promise<Appointment[]>;
    findByPacientId(pacientId: string): Promise<Appointment[]>;
    exists(appointment: Appointment): Promise<boolean>;
    delete(appointmentId: AppointmentId | string): Promise<void>;
}
