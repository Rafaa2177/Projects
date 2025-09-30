import { Request, Response, NextFunction } from 'express';

export default interface IAppointmentController {
  createAppointment(req: Request, res: Response, next: NextFunction);
  updateAppointment(req: Request, res: Response, next: NextFunction);
  getAllAppointments(req: Request, res: Response, next: NextFunction);
  getAppointmentById(req: Request, res: Response, next: NextFunction);
  getAppointmentsByDoctorId(req: Request, res: Response, next: NextFunction);
  getAppointmentsByPacientId(req: Request, res: Response, next: NextFunction);
  deleteAppointment(req: Request, res: Response, next: NextFunction);
}
