import { Router } from 'express';
import { celebrate, Joi } from 'celebrate';

import { Container } from 'typedi';


import config from "../../../config";
import ISpecializationController from "../../controllers/IControllers/ISpecializationController";

const route = Router();

export default (app: Router) => {
  app.use('/specialization', route);

  const ctrl = Container.get(config.controllers.specialization.name) as ISpecializationController;

  route.post('',
    celebrate({
      body: Joi.object({
        name: Joi.string().required(),
        code:Joi.string().required(),
        description: Joi.string()
      })
    }),
    (req, res, next) => ctrl.createSpecialization(req, res, next) );

    route.put('/:id',
        celebrate({
            body: Joi.object({
                name: Joi.string().required(),
                code: Joi.string().required(),
                description: Joi.string()
            }),
        }),
        (req, res, next) => ctrl.updateSpecialization(req, res, next));

  route.get('',
    (req, res, next) => ctrl.getSpecialization(req, res, next) );

    route.get('/:id', (req, res, next) => ctrl.getSpecializationById(req, res, next));
};
