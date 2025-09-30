import { OperationRoom } from "../domain/OperationRoom/operationRoom";
import IOperationRoomDTO from "../dto/IOperationRoomDTO";
import { IOperationRoomPersistence } from "../dataschema/IOperationRoomPersistence";
import { UniqueEntityID } from "../core/domain/UniqueEntityID";

export class OperationRoomMap {
  public static toDTO(operationRoom: OperationRoom): IOperationRoomDTO {
    return {
      id: operationRoom.id.toString(),
      room: operationRoom.room,
      roomType: operationRoom.roomType,
    };
  }

  public static toDomain(raw: any): OperationRoom {
    const operationRoomOrError = OperationRoom.create(
      {
        room: raw.room,
        roomType: raw.roomType,
      },
      new UniqueEntityID(raw.domainId)
    );

    operationRoomOrError.isFailure ? console.log(operationRoomOrError.error) : '';

    return operationRoomOrError.isSuccess ? operationRoomOrError.getValue() : null;
  }

  public static toPersistence(operationRoom: OperationRoom): IOperationRoomPersistence {
    return {
      domainId: operationRoom.id.toString(),
      room: operationRoom.room,
      roomType: operationRoom.roomType,
    };
  }
}
