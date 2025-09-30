import { Service, Inject } from 'typedi';
import { Result } from "../core/logic/Result";
import IAppointmentDTO from '../dto/IAppointmentDTO';
import { Appointment } from '../domain/appointment/appointment';
import { AppointmentMap } from '../mappers/AppointmentMap';
import IAppointmentService from './IServices/IAppointmentService';
import { IAppointmentRepo } from './IRepos/IAppointmentRepo';
import { AppointmentId } from '../domain/appointment/appointmentId';

@Service()
export default class AppointmentService implements IAppointmentService {
    constructor(
        @Inject('AppointmentRepo') private appointmentRepo: IAppointmentRepo
    ) {}

    public async createAppointment(appointmentDTO: IAppointmentDTO): Promise<Result<IAppointmentDTO>> {
        try {

            const appointmentOrError = await Appointment.create(appointmentDTO);

            if (appointmentOrError.isFailure) {
                return Result.fail<IAppointmentDTO>(appointmentOrError.errorValue());
            }

            const appointmentResult = appointmentOrError.getValue();
            await this.appointmentRepo.save(appointmentResult);
            const appointmentDTOResult = AppointmentMap.toDTO(appointmentResult) as IAppointmentDTO;

            return Result.ok<IAppointmentDTO>(appointmentDTOResult);
        } catch (e) {
            throw e;
        }
    }

    public async updateAppointment(appointmentDTO: IAppointmentDTO): Promise<Result<IAppointmentDTO>> {
        try {
            const appointment = await this.appointmentRepo.findByDomainId(appointmentDTO.id);

            if (appointment === null) {
                return Result.fail<IAppointmentDTO>("Consulta não encontrada");
            }

            const appointmentOrError = await Appointment.create(appointmentDTO, new AppointmentId(appointmentDTO.id));

            if (appointmentOrError.isFailure) {
                return Result.fail<IAppointmentDTO>(appointmentOrError.errorValue());
            }

            const appointmentResult = appointmentOrError.getValue();
            await this.appointmentRepo.save(appointmentResult);
            const appointmentDTOResult = AppointmentMap.toDTO(appointmentResult) as IAppointmentDTO;

            return Result.ok<IAppointmentDTO>(appointmentDTOResult);
        } catch (e) {
            throw e;
        }
    }

    public async getAllAppointments(): Promise<Result<IAppointmentDTO[]>> {
        try {
            const appointments = await this.appointmentRepo.findAll();
            const appointmentDTOs = appointments.map(appointment =>
                AppointmentMap.toDTO(appointment) as IAppointmentDTO
            );

            return Result.ok<IAppointmentDTO[]>(appointmentDTOs);
        } catch (e) {
            throw e;
        }
    }

    public async getAppointmentById(appointmentId: string): Promise<Result<IAppointmentDTO>> {
        try {
            const appointment = await this.appointmentRepo.findByDomainId(appointmentId);

            if (appointment === null) {
                return Result.fail<IAppointmentDTO>("Consulta não encontrada");
            }

            const appointmentDTOResult = AppointmentMap.toDTO(appointment) as IAppointmentDTO;
            return Result.ok<IAppointmentDTO>(appointmentDTOResult);
        } catch (e) {
            throw e;
        }
    }

    public async getAppointmentsByDoctorId(doctorId: string): Promise<Result<IAppointmentDTO[]>> {
        try {
            const appointments = await this.appointmentRepo.findByDoctorId(doctorId);
            const appointmentDTOs = appointments.map(appointment =>
                AppointmentMap.toDTO(appointment) as IAppointmentDTO
            );

            return Result.ok<IAppointmentDTO[]>(appointmentDTOs);
        } catch (e) {
            throw e;
        }
    }

    public async getAppointmentsByPacientId(pacientId: string): Promise<Result<IAppointmentDTO[]>> {
        try {
            const appointments = await this.appointmentRepo.findByPacientId(pacientId);
            const appointmentDTOs = appointments.map(appointment =>
                AppointmentMap.toDTO(appointment) as IAppointmentDTO
            );

            return Result.ok<IAppointmentDTO[]>(appointmentDTOs);
        } catch (e) {
            throw e;
        }
    }

    public async deleteAppointment(appointmentId: string): Promise<Result<void>> {
        try {
            const appointment = await this.appointmentRepo.findByDomainId(appointmentId);

            if (appointment === null) {
                return Result.fail<void>("Consulta não encontrada");
            }

            await this.appointmentRepo.delete(appointmentId);
            return Result.ok<void>();
        } catch (e) {
            throw e;
        }
    }

}
