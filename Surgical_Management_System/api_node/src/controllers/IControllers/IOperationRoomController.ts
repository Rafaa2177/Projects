

import { Request, Response, NextFunction } from 'express';

export default interface IOperationRoomController {
  createOperationRoom(req: Request, res: Response, next: NextFunction): Promise<Response>;
  getOperationRoomByRoom(req: Request, res: Response, next: NextFunction): Promise<Response>;
  getAllOperationRooms(req: Request, res: Response, next: NextFunction): Promise<Response>;
}
