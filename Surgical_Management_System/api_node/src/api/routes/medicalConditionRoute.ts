import { Router } from 'express';
import { celebrate, Joi } from 'celebrate';

import { Container } from 'typedi';
import IMedicalConditionController from '../../controllers/IControllers/IMedicalConditionController';

import config from "../../../config";

const route = Router();

export default (app: Router) => {
  app.use('/medicalCondition', route);

  const ctrl = Container.get(config.controllers.medicalCondition.name) as IMedicalConditionController;

  route.post('',
    celebrate({
      body: Joi.object({
        name: Joi.string().required(),
        code: Joi.string().required(),
      })
    }),
    (req, res, next) => ctrl.createMedicalCondition(req, res, next) );

  route.put('/:id',
    celebrate({
      body: Joi.object({
        name: Joi.string().required(),
        code: Joi.string().required(),
      }),
    }),
    (req, res, next) => ctrl.updateMedicalCondition(req, res, next) );


  route.get('/',
    (req, res, next) => ctrl.getAllMedicalConditions(req, res, next)  // Chama o método getAllMedicalConditions do controlador
  );
  route.get('/:id', (req, res, next) => ctrl.getMedicalConditionById(req, res, next));
};
