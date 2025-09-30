import { Router } from 'express';
import { celebrate, Joi } from 'celebrate';
import { Container } from 'typedi';
import IAppointmentController from '../../controllers/IControllers/IAppointmentController';
import config from "../../../config";

const route = Router();

export default (app: Router) => {
  app.use('/appointments', route);

  const ctrl = Container.get(config.controllers.appointment.name) as IAppointmentController;

  route.post('',
    celebrate({
      body: Joi.object({
        doctorId: Joi.string().required(),
        pacientId: Joi.string().required(),
        startDate: Joi.date().required(),
        endDate: Joi.date().required(),
        room: Joi.string().required(),
        operationType: Joi.string().required(),
        status: Joi.string().valid('SCHEDULED', 'COMPLETED', 'CANCELLED', 'IN_PROGRESS').required()
      })
    }),
    (req, res, next) => ctrl.createAppointment(req, res, next));

  route.put('',
    celebrate({
      body: Joi.object({
        id: Joi.string().required(),
        doctorId: Joi.string().required(),
        pacientId: Joi.string().required(),
        startDate: Joi.date().required(),
        endDate: Joi.date().required(),
        room: Joi.string().required(),
        operationType: Joi.string().required(),
        status: Joi.string().valid('SCHEDULED', 'COMPLETED', 'CANCELLED', 'IN_PROGRESS').required()
      })
    }),
    (req, res, next) => ctrl.updateAppointment(req, res, next));

  route.get('',
    (req, res, next) => ctrl.getAllAppointments(req, res, next));

  route.get('/:id',
    celebrate({
      params: Joi.object({
        id: Joi.string().required()
      })
    }),
    (req, res, next) => ctrl.getAppointmentById(req, res, next));

  route.get('/doctor/:doctorId',
    celebrate({
      params: Joi.object({
        doctorId: Joi.string().required()
      })
    }),
    (req, res, next) => ctrl.getAppointmentsByDoctorId(req, res, next));

  route.get('/pacient/:pacientId',
    celebrate({
      params: Joi.object({
        pacientId: Joi.string().required()
      })
    }),
    (req, res, next) => ctrl.getAppointmentsByPacientId(req, res, next));

  route.delete('/:id',
    celebrate({
      params: Joi.object({
        id: Joi.string().required()
      })
    }),
    (req, res, next) => ctrl.deleteAppointment(req, res, next));
};
