import { Service, Inject } from "typedi";
import IOperationRoomRepo from "../services/IRepos/IOperationRoomRepo";
import IOperationRoomService from "./IServices/IOperationRoomService";
import IOperationRoomDTO from "../dto/IOperationRoomDTO";
import { OperationRoomMap } from "../mappers/OperationRoomMap";
import { Result } from "../core/logic/Result";
import config from "../../config";

@Service()
export default class OperationRoomService implements IOperationRoomService {
  constructor(
    @Inject(config.repos.operationRoom.name) private operationRoomRepo: IOperationRoomRepo,
  ) {}

  public async createOperationRoom(operationRoomDTO: IOperationRoomDTO): Promise<Result<IOperationRoomDTO>> {
    try {
      const operationRoom = OperationRoomMap.toDomain(operationRoomDTO);
      const createdOperationRoom = await this.operationRoomRepo.save(operationRoom);
      return Result.ok<IOperationRoomDTO>(OperationRoomMap.toDTO(createdOperationRoom));
    } catch (err) {
      return Result.fail<IOperationRoomDTO>(err);
    }
  }

  public async getOperationRoomByRoom(room: string): Promise<Result<IOperationRoomDTO>> {
    try {
      const operationRoom = await this.operationRoomRepo.findByRoom(room);
      if (operationRoom) {
        return Result.ok<IOperationRoomDTO>(OperationRoomMap.toDTO(operationRoom));
      } else {
        return Result.fail<IOperationRoomDTO>('Operation Room not found');
      }
    } catch (err) {
      return Result.fail<IOperationRoomDTO>(err);
    }
  }

  public async getAllOperationRooms(): Promise<Result<IOperationRoomDTO[]>> {
    try {
      const operationRooms = await this.operationRoomRepo.findAll();
      return Result.ok<IOperationRoomDTO[]>(operationRooms.map(OperationRoomMap.toDTO));
    } catch (err) {
      return Result.fail<IOperationRoomDTO[]>(err);
    }
  }

  public async operationRoomExists(operationRoomDTO: IOperationRoomDTO): Promise<Result<boolean>> {
    try {
      const operationRoom = OperationRoomMap.toDomain(operationRoomDTO);
      const exists = await this.operationRoomRepo.exists(operationRoom);
      return Result.ok<boolean>(exists);
    } catch (err) {
      return Result.fail<boolean>(err);
    }
  }
  public async editOperationRoom(room: string, operationRoomDTO: IOperationRoomDTO): Promise<Result<IOperationRoomDTO>> {
    try {
      const operationRoom = await this.operationRoomRepo.findByRoom(room);

      if (!operationRoom) {
        return Result.fail<IOperationRoomDTO>('Operation room not found');
      }

      operationRoom.roomType = operationRoomDTO.roomType;

      await this.operationRoomRepo.save(operationRoom);

      return Result.ok<IOperationRoomDTO>(OperationRoomMap.toDTO(operationRoom));
    } catch (err) {
      return Result.fail<IOperationRoomDTO>(err);
    }
  }
}
