import { Mapper } from "../core/infra/Mapper";
import { Document, Model } from 'mongoose';
import { IRoomTypePersistence } from '../dataschema/IRoomTypePersistence';
import IRoomTypeDTO from "../dto/IRoomTypeDTO";
import { RoomType } from "../domain/OperationTypeRoom/roomType";
import { UniqueEntityID } from "../core/domain/UniqueEntityID";

export class RoomTypeMap extends Mapper<RoomType> {

  public static toDTO(roomType: RoomType): IRoomTypeDTO {
    return {
      id: roomType.id.toString(),
      name: roomType.name,
      description: roomType.description
    } as IRoomTypeDTO;
  }

  public static toDomain(roomType: any | Model<IRoomTypePersistence & Document>): RoomType {
    const roomTypeOrError = RoomType.create(
      roomType,
      new UniqueEntityID(roomType.domainId)
    );

    roomTypeOrError.isFailure ? console.log(roomTypeOrError.error) : '';

    return roomTypeOrError.isSuccess ? roomTypeOrError.getValue() : null;
  }

  public static toPersistence(roomType: RoomType): any {
    return {
      domainId: roomType.id.toString(),
      name: roomType.name,
      description: roomType.description
    }
  }
}
