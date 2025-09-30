import { Request, Response, NextFunction } from 'express';
import { Inject, Service } from 'typedi';
import IRoomTypeDTO from '../dto/IRoomTypeDTO';
import IRoomTypeService from '../services/IServices/IRoomTypeService';
import config from '../../config';


import IRoomTypeController from "./IControllers/IRoomTypeController";

@Service()
export default class RoomTypeController implements IRoomTypeController{
  constructor(
    @Inject(config.services.roomType.name) private roomTypeService: IRoomTypeService
  ) {}

  public async createRoomType(req: Request, res: Response, next: NextFunction) {
    try {
      const roomTypeDTO: IRoomTypeDTO = req.body;
      const result = await this.roomTypeService.createRoomType(roomTypeDTO);

      if (result.isFailure) {
        return res.status(400).json(result.error);
      }

      return res.status(201).json(result.getValue());
    } catch (e) {
      return next(e);
    }
  }

  public async updateRoomType(req: Request, res: Response, next: NextFunction) {
    try {
      const roomTypeDTO: IRoomTypeDTO = req.body;
      const result = await this.roomTypeService.updateRoomType(roomTypeDTO);

      if (result.isFailure) {
        return res.status(400).json(result.error);
      }

      return res.status(200).json(result.getValue());
    } catch (e) {
      return next(e);
    }
  }

  public async getRoomType(req: Request, res: Response, next: NextFunction) {
    try {
      const roomTypeId: string = req.params.id;
      const result = await this.roomTypeService.getRoomType(roomTypeId);

      if (result.isFailure) {
        return res.status(404).json(result.error);
      }

      return res.status(200).json(result.getValue());
    } catch (e) {
      return next(e);
    }
  }
  public async getAllRoomTypes(req: Request, res: Response, next: NextFunction) {
    try {
      const result = await this.roomTypeService.getAllRoomTypes();

      if (result.isFailure) {
        return res.status(404).json(result.error);
      }

      return res.status(200).json(result.getValue());
    } catch (e) {
      return next(e);
    }
  }
}
