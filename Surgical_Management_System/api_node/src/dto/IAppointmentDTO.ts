import internal from "stream";

export default interface appointmentProps{
    id?: string,
    doctorId: string,
    pacientId: string,
    startDate: Date,
    endDate: Date,
    room: string,
    operationType: string,
    status: string
  }
