import { Router } from 'express';
import { Container } from 'typedi';
import IRoomTypeController from '../../controllers/IControllers/IRoomTypeController';
import config from "../../../config";
const router = Router();
export default (app: Router) => {
  app.use('/roomTypes', router);
  const roomTypeController = Container.get(config.controllers.roomType.name) as IRoomTypeController;

  router.post('', (req, res, next) => roomTypeController.createRoomType(req, res, next));
  router.put('/:id', (req, res, next) => roomTypeController.updateRoomType(req, res, next));
  router.get('/:id', (req, res, next) => roomTypeController.getRoomType(req, res, next));
  router.get('', (req, res, next) => roomTypeController.getAllRoomTypes(req, res, next));
}

