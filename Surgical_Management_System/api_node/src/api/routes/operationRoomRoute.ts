import { Router } from 'express';
import { Container } from 'typedi';
import OperationRoomController from '../../controllers/operationRoomController';

const route = Router();

export default (app: Router) => {
  app.use('/operation-rooms', route);

  const operationRoomController = Container.get(OperationRoomController);

  route.post('/', (req, res, next) => operationRoomController.createOperationRoom(req, res, next));
  route.get('/:room', (req, res, next) => operationRoomController.getOperationRoomByRoom(req, res, next));
  route.get('/', (req, res, next) => operationRoomController.getAllOperationRooms(req, res, next));
  route.put('/:room', (req, res, next) => operationRoomController.editOperationRoom(req, res, next));
};
