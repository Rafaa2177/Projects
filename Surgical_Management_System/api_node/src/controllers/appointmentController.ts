import { Request, Response, NextFunction } from 'express';
import { Inject, Service } from 'typedi';
import config from '../../config';
import { Result } from '../core/logic/Result';
import IAppointmentController from './IControllers/IAppointmentController';
import IAppointmentService from '../services/IServices/IAppointmentService';
import IAppointmentDTO from '../dto/IAppointmentDTO';

@Service()
export default class AppointmentController implements IAppointmentController {
    constructor(
        @Inject(config.services.appointment.name) private appointmentServiceInstance: IAppointmentService
    ) {}

    public async createAppointment(req: Request, res: Response, next: NextFunction) {
        try {
            const appointmentOrError = await this.appointmentServiceInstance.createAppointment(req.body as IAppointmentDTO);

            if (appointmentOrError.isFailure) {
                return res.status(402).send(appointmentOrError.error);
            }

            const appointmentDTO = appointmentOrError.getValue();
            return res.json(appointmentDTO).status(201);
        } catch (e) {
            return next(e);
        }
    }

    public async updateAppointment(req: Request, res: Response, next: NextFunction) {
        try {
            const appointmentOrError = await this.appointmentServiceInstance.updateAppointment(req.body as IAppointmentDTO);

            if (appointmentOrError.isFailure) {
                return res.status(404).send(appointmentOrError.error);
            }

            const appointmentDTO = appointmentOrError.getValue();
            return res.status(201).json(appointmentDTO);
        } catch (e) {
            return next(e);
        }
    }

    public async getAllAppointments(req: Request, res: Response, next: NextFunction) {
        try {
            const appointmentsOrError = await this.appointmentServiceInstance.getAllAppointments();

            if (appointmentsOrError.isFailure) {
                return res.status(404).send(appointmentsOrError.error);
            }

            const appointments = appointmentsOrError.getValue();
            return res.status(200).json(appointments);
        } catch (e) {
            return next(e);
        }
    }

    public async getAppointmentById(req: Request, res: Response, next: NextFunction) {
        try {
            const appointmentOrError = await this.appointmentServiceInstance.getAppointmentById(req.params.id);

            if (appointmentOrError.isFailure) {
                return res.status(404).send(appointmentOrError.error);
            }

            const appointment = appointmentOrError.getValue();
            return res.status(200).json(appointment);
        } catch (e) {
            return next(e);
        }
    }

    public async getAppointmentsByDoctorId(req: Request, res: Response, next: NextFunction) {
        try {
            const appointmentsOrError = await this.appointmentServiceInstance.getAppointmentsByDoctorId(req.params.doctorId);

            if (appointmentsOrError.isFailure) {
                return res.status(404).send(appointmentsOrError.error);
            }

            const appointments = appointmentsOrError.getValue();
            return res.status(200).json(appointments);
        } catch (e) {
            return next(e);
        }
    }

    public async getAppointmentsByPacientId(req: Request, res: Response, next: NextFunction) {
        try {
            const appointmentsOrError = await this.appointmentServiceInstance.getAppointmentsByPacientId(req.params.pacientId);

            if (appointmentsOrError.isFailure) {
                return res.status(404).send(appointmentsOrError.error);
            }

            const appointments = appointmentsOrError.getValue();
            return res.status(200).json(appointments);
        } catch (e) {
            return next(e);
        }
    }

    public async deleteAppointment(req: Request, res: Response, next: NextFunction) {
        try {
            const appointmentOrError = await this.appointmentServiceInstance.deleteAppointment(req.params.id);

            if (appointmentOrError.isFailure) {
                return res.status(404).send(appointmentOrError.error);
            }

            return res.status(200).json("Consulta eliminada com sucesso!");
        } catch (e) {
            return next(e);
        }
    }
} 