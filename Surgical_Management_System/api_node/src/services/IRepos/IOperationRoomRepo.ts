import { Repo } from "../../core/infra/Repo";
import { OperationRoom } from "../../domain/OperationRoom/operationRoom";


export default interface IOperationRoomRepo extends Repo<OperationRoom> {
  save(operationRoom: OperationRoom): Promise<OperationRoom>;
  findByRoom(room: string): Promise<OperationRoom>;
  findAll(): Promise<OperationRoom[]>;
}
