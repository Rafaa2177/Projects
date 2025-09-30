import { Service, Inject } from 'typedi';
import { Document, FilterQuery, Model } from 'mongoose';
import { IAppointmentPersistence } from '../dataschema/IAppointmentPersistence';
import { Appointment } from '../domain/appointment/appointment';
import { AppointmentId } from '../domain/appointment/appointmentId';
import { AppointmentMap } from '../mappers/AppointmentMap';
import { IAppointmentRepo } from '../services/IRepos/IAppointmentRepo';
import { UniqueEntityID } from '../core/domain/UniqueEntityID';

@Service()
export default class AppointmentRepo implements IAppointmentRepo {
    private models: any;

    constructor(
        @Inject('appointmentSchema') private appointmentSchema: Model<IAppointmentPersistence & Document>
    ) {}

    private createBaseQuery(): any {
        return {
            where: {},
        };
    }

    public async exists(appointment: Appointment): Promise<boolean> {
        const idX = appointment.id instanceof UniqueEntityID
            ? (<UniqueEntityID>appointment.id).toValue()
            : appointment.id;

        const query = { domainId: idX };
        const appointmentDocument = await this.appointmentSchema.findOne(query);

        return !!appointmentDocument;
    }

    public async save(appointment: Appointment): Promise<Appointment> {
        const query = { domainId: appointment.id.toString() };

        const appointmentDocument = await this.appointmentSchema.findOne(query);

        try {
            if (appointmentDocument === null) {
                const rawAppointment: any = AppointmentMap.toPersistence(appointment);
                const appointmentCreated = await this.appointmentSchema.create(rawAppointment);
                return AppointmentMap.toDomain(appointmentCreated);
            } else {
                appointmentDocument.doctorId = appointment.doctorId;
                appointmentDocument.pacientId = appointment.pacientId;
                appointmentDocument.startDate = appointment.operationStartDate;
                appointmentDocument.endDate = appointment.operationEndDate;
                appointmentDocument.room = appointment.operationRoom;
                appointmentDocument.operationType = appointment.operationType;
                appointmentDocument.status = appointment.status;
                await appointmentDocument.save();

                return appointment;
            }
        } catch (err) {
            throw err;
        }
    }

    public async findByDomainId(appointmentId: AppointmentId | string): Promise<Appointment> {
        const query = { domainId: appointmentId.toString() };
        const appointmentRecord = await this.appointmentSchema.findOne(query);

        if (appointmentRecord != null) {
            return AppointmentMap.toDomain(appointmentRecord);
        } else
            return null;
    }

    public async findAll(): Promise<Appointment[]> {
        const appointmentRecords = await this.appointmentSchema.find();
        return appointmentRecords.map((record) => AppointmentMap.toDomain(record));
    }

    public async findByDoctorId(doctorId: string): Promise<Appointment[]> {
        const query = { doctorId: doctorId };
        const appointmentRecords = await this.appointmentSchema.find(query);
        return appointmentRecords.map((record) => AppointmentMap.toDomain(record));
    }

    public async findByPacientId(pacientId: string): Promise<Appointment[]> {
        const query = { pacientId: pacientId };
        const appointmentRecords = await this.appointmentSchema.find(query);
        return appointmentRecords.map((record) => AppointmentMap.toDomain(record));
    }

    public async delete(appointmentId: AppointmentId | string): Promise<void> {
        const query = { domainId: appointmentId.toString() };
        await this.appointmentSchema.deleteOne(query);
    }
}
