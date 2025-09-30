export interface IAppointmentPersistence {
  _id: string;
  doctorId: string;
  pacientId: string;
  startDate: Date;
  endDate: Date;
  room: string;
  operationType: string;
  status: string;
}
