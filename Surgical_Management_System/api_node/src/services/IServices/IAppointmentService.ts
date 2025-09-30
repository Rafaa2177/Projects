import { Result } from "../../core/logic/Result";
import IAppointmentDTO from "../../dto/IAppointmentDTO";

export default interface IAppointmentService {
  createAppointment(appointmentDTO: IAppointmentDTO): Promise<Result<IAppointmentDTO>>;
  updateAppointment(appointmentDTO: IAppointmentDTO): Promise<Result<IAppointmentDTO>>;
  getAllAppointments(): Promise<Result<IAppointmentDTO[]>>;
  getAppointmentById(appointmentId: string): Promise<Result<IAppointmentDTO>>;
  getAppointmentsByDoctorId(doctorId: string): Promise<Result<IAppointmentDTO[]>>;
  getAppointmentsByPacientId(pacientId: string): Promise<Result<IAppointmentDTO[]>>;
  deleteAppointment(appointmentId: string): Promise<Result<void>>;
}
