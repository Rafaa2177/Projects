import { Request, Response, NextFunction } from 'express';

export default interface IRoomTypeController {
  createRoomType(req: Request, res: Response, next: NextFunction);
  updateRoomType(req: Request, res: Response, next: NextFunction);
  getRoomType(req: Request, res: Response, next: NextFunction);
  getAllRoomTypes(req: Request, res: Response, next: NextFunction);
}
