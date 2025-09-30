import { Request, Response, NextFunction } from 'express';
import { Service, Inject } from 'typedi';
import IOperationRoomService from '../services/IServices/IOperationRoomService';
import { OperationRoomMap } from '../mappers/OperationRoomMap';
import IOperationRoomController from './IControllers/IOperationRoomController';
import { Result } from '../core/logic/Result';
import IOperationRoomDTO from "../dto/IOperationRoomDTO";
import config from "../../config";

@Service()
export default class OperationRoomController implements IOperationRoomController {
  constructor(
    @Inject(config.services.operationRoom.name) private operationRoomService: IOperationRoomService,
  ) {}

  public async createOperationRoom(req: Request, res: Response, next: NextFunction): Promise<Response> {
    try {
      const operationRoomDTO = req.body;
      const result: Result<IOperationRoomDTO> = await this.operationRoomService.createOperationRoom(operationRoomDTO);

      if (result.isFailure) {
        return res.status(400).json(result.error);
      }

      return res.status(201).json(result.getValue());
    } catch (err) {
      next(err);
      return res.status(500).json({ message: 'Internal server error' });
    }
  }

  public async getOperationRoomByRoom(req: Request, res: Response, next: NextFunction): Promise<Response> {
    try {
      const room = req.params.room;
      const result: Result<IOperationRoomDTO> = await this.operationRoomService.getOperationRoomByRoom(room);

      if (result.isFailure) {
        return res.status(404).json(result.error);
      }

      return res.status(200).json(result.getValue());
    } catch (err) {
      next(err);
      return res.status(500).json({ message: 'Internal server error' });
    }
  }

  public async getAllOperationRooms(req: Request, res: Response, next: NextFunction): Promise<Response> {
    try {
      const result: Result<IOperationRoomDTO[]> = await this.operationRoomService.getAllOperationRooms();

      if (result.isFailure) {
        return res.status(400).json(result.error);
      }

      return res.status(200).json(result.getValue());
    } catch (err) {
      next(err);
      return res.status(500).json({ message: 'Internal server error' });
    }
  }

  public async editOperationRoom(req: Request, res: Response, next: NextFunction): Promise<Response> {
    try {
      const room = req.params.room;
      const operationRoomDTO = req.body;
      const result: Result<IOperationRoomDTO> = await this.operationRoomService.editOperationRoom(room, operationRoomDTO);

      if (result.isFailure) {
        return res.status(400).json(result.error);
      }

      return res.status(200).json(result.getValue());
    } catch (err) {
      next(err);
      return res.status(500).json({ message: 'Internal server error' });
    }
  }
}
