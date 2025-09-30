import IOperationRoomDTO from "../../dto/IOperationRoomDTO";
import { Result } from "../../core/logic/Result";

export default interface IOperationRoomService {
  createOperationRoom(operationRoomDTO: IOperationRoomDTO): Promise<Result<IOperationRoomDTO>>;
  getOperationRoomByRoom(room: string): Promise<Result<IOperationRoomDTO>>;
  getAllOperationRooms(): Promise<Result<IOperationRoomDTO[]>>;
  operationRoomExists(operationRoomDTO: IOperationRoomDTO): Promise<Result<boolean>>;
  editOperationRoom(room: string, operationRoomDTO: IOperationRoomDTO): Promise<Result<IOperationRoomDTO>>;
}
