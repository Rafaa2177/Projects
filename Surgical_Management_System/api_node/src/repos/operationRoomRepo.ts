import { Inject, Service } from "typedi";
import { Document, FilterQuery, Model } from 'mongoose';
import { IOperationRoomPersistence } from "../dataschema/IOperationRoomPersistence";
import { OperationRoom } from "../domain/OperationRoom/operationRoom";
import { OperationRoomMap } from "../mappers/OperationRoomMap";
import IOperationRoomRepo from "../services/IRepos/IOperationRoomRepo";

@Service()
export default class OperationRoomRepo implements IOperationRoomRepo {
  private models: any;
  constructor(
    @Inject('operationRoomSchema') private operationRoomSchema: Model<IOperationRoomPersistence & Document>,
  ) {}
  private createBaseQuery (): any {
    return {
      where: {},
    }
  }

  public async save(operationRoom: OperationRoom): Promise<OperationRoom> {
    const query = { domainId: operationRoom.id.toString() };

    const operationRoomDocument = await this.operationRoomSchema.findOne(query);

    try {
      if (operationRoomDocument === null) {
        const rawOperationRoom: any = OperationRoomMap.toPersistence(operationRoom);

        const operationRoomCreated = await this.operationRoomSchema.create(rawOperationRoom);

        return OperationRoomMap.toDomain(operationRoomCreated);
      } else {
        operationRoomDocument.room = operationRoom.room;
        operationRoomDocument.roomType = operationRoom.roomType;
        await operationRoomDocument.save();

        return operationRoom;
      }
    } catch (err) {
      throw err;
    }
  }

  public async findByRoom(room: string): Promise<OperationRoom> {
    const query = { room: room };
    const operationRoomDocument = await this.operationRoomSchema.findOne(query as FilterQuery<IOperationRoomPersistence & Document>);

    if (operationRoomDocument != null) {
      return OperationRoomMap.toDomain(operationRoomDocument);
    } else {
      return null;
    }
  }

  public async findAll(): Promise<OperationRoom[]> {
    const operationRoomDocuments = await this.operationRoomSchema.find();
    return operationRoomDocuments.map(doc => OperationRoomMap.toDomain(doc));
  }
  public async exists(operationRoom: OperationRoom): Promise<boolean> {
    const query = { domainId: operationRoom.id.toString() };
    const operationRoomDocument = await this.operationRoomSchema.findOne(query as FilterQuery<IOperationRoomPersistence & Document>);
    return operationRoomDocument != null;
  }
}
