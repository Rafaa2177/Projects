import { Router } from 'express';
import { celebrate, Joi } from 'celebrate';

import { Container } from 'typedi';


import config from "../../../config";
import IAllergiesController from "../../controllers/IControllers/IAllergiesController";

const route = Router();

export default (app: Router) => {
  app.use('/allergies', route);

  const ctrl = Container.get(config.controllers.allergies.name) as IAllergiesController;

  route.post('',
    celebrate({
      body: Joi.object({
        name: Joi.string().required(),
        code: Joi.string().required(),
        description: Joi.string().optional()
      })
    }),
    (req, res, next) => ctrl.createAllergies(req, res, next) );

  route.put('/:id',
    celebrate({
      body: Joi.object({
        name: Joi.string().required(),
        code: Joi.string().required(),
        description: Joi.string()
      }),
    }),
    (req, res, next) => ctrl.updateAllergies(req, res, next));

  route.get('',
    (req, res, next) => ctrl.getAllAllergies(req, res, next));

  route.get('/:id', (req, res, next) => ctrl.getAllergyById(req, res, next));


};
