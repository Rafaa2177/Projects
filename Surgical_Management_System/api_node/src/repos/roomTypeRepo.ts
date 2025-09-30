import { Inject, Service } from "typedi";
import { Document, FilterQuery, Model } from 'mongoose';
import { IRoomTypePersistence } from "../dataschema/IRoomTypePersistence";
import { RoomType } from "../domain/OperationTypeRoom/roomType";
import { RoomTypeId } from "../domain/OperationTypeRoom/roomTypeId";
import { RoomTypeMap } from "../mappers/RoomTypeMap";
import IRoomTypeRepo from "../services/IRepos/IRoomTypeRepo";

@Service()
export default class RoomTypeRepo implements IRoomTypeRepo {
  private models: any;

  constructor(
    @Inject('roomTypeSchema') private roomTypeSchema: Model<IRoomTypePersistence & Document>,
  ) {}

  private createBaseQuery(): any {
    return {
      where: {},
    }
  }

  public async exists(roomType: RoomType): Promise<boolean> {
    const idX = roomType.id instanceof RoomTypeId ? (<RoomTypeId>roomType.id).toValue() : roomType.id;
    const query = { domainId: idX };
    const roomTypeDocument = await this.roomTypeSchema.findOne(query as FilterQuery<IRoomTypePersistence & Document>);
    return !!roomTypeDocument === true;
  }

  public async save(roomType: RoomType): Promise<RoomType> {
    const query = { domainId: roomType.id.toString() };

    const roomTypeDocument = await this.roomTypeSchema.findOne(query);

    try {
      if (roomTypeDocument === null) {
        const rawRoomType: any = RoomTypeMap.toPersistence(roomType);

        const roomTypeCreated = await this.roomTypeSchema.create(rawRoomType);

        return RoomTypeMap.toDomain(roomTypeCreated);
      } else {
        roomTypeDocument.name = roomType.name;
        roomTypeDocument.description = roomType.description;
        await roomTypeDocument.save();

        return roomType;
      }
    } catch (err) {
      throw err;
    }
  }

  public async findByDomainId(roomTypeId: RoomTypeId | string): Promise<RoomType> {
    const query = { domainId: roomTypeId.toString() };
    const roomTypeDocument = await this.roomTypeSchema.findOne(query as FilterQuery<IRoomTypePersistence & Document>);

    if (roomTypeDocument != null) {
      return RoomTypeMap.toDomain(roomTypeDocument);
    } else {
      return null;
    }
  }

  public async findAll(): Promise<RoomType[]> {
    const roomTypeDocuments = await this.roomTypeSchema.find();
    return roomTypeDocuments.map(doc => RoomTypeMap.toDomain(doc));
  }
}
