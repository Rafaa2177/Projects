import { Service, Inject } from "typedi";
import config from "../../config";
import { Result } from "../core/logic/Result";
import IRoomTypeDTO from "../dto/IRoomTypeDTO";
import { RoomTypeMap } from "../mappers/RoomTypeMap";
import IRoomTypeRepo from "./IRepos/IRoomTypeRepo";
import IRoomTypeService from "./IServices/IRoomTypeService";
import { RoomType } from "../domain/OperationTypeRoom/roomType";

@Service()
export default class RoomTypeService implements IRoomTypeService {
  constructor(
      @Inject(config.repos.roomType.name) private roomTypeRepo: IRoomTypeRepo
  ) {}
  public async getAllRoomTypes(): Promise<Result<Array<IRoomTypeDTO>> > {
    try {
      const roomTypes = await this.roomTypeRepo.findAll();

      const roomTypeDTOs = roomTypes.map((roomType) => RoomTypeMap.toDTO(roomType));

      return Result.ok<Array<IRoomTypeDTO>>(roomTypeDTOs);
    } catch (e) {
      return Result.fail<Array<IRoomTypeDTO>>(e.toString
      ());
    }
  }
  public async createRoomType(roomTypeDTO: IRoomTypeDTO): Promise<Result<IRoomTypeDTO>> {
    try {
      const roomTypeOrError = RoomType.create(roomTypeDTO);

      if (roomTypeOrError.isFailure) {
        return Result.fail<IRoomTypeDTO>(roomTypeOrError.error.toString());
      }

      const roomType = roomTypeOrError.getValue();

      await this.roomTypeRepo.save(roomType);

      const roomTypeDTOResult = RoomTypeMap.toDTO(roomType);

      return Result.ok<IRoomTypeDTO>(roomTypeDTOResult);
    } catch (e) {
      return Result.fail<IRoomTypeDTO>(e.toString());
    }
  }

  public async updateRoomType(roomTypeDTO: IRoomTypeDTO): Promise<Result<IRoomTypeDTO>> {
    try {
      const roomType = await this.roomTypeRepo.findByDomainId(roomTypeDTO.id);

      if (!roomType) {
        return Result.fail<IRoomTypeDTO>("RoomType not found");
      }

      roomType.name = roomTypeDTO.name;
      roomType.description = roomTypeDTO.description;

      await this.roomTypeRepo.save(roomType);

      const roomTypeDTOResult = RoomTypeMap.toDTO(roomType);

      return Result.ok<IRoomTypeDTO>(roomTypeDTOResult);
    } catch (e) {
      return Result.fail<IRoomTypeDTO>(e.toString());
    }
  }

  public async getRoomType(roomTypeId: string): Promise<Result<IRoomTypeDTO>> {
    try {
      const roomType = await this.roomTypeRepo.findByDomainId(roomTypeId);

      if (!roomType) {
        return Result.fail<IRoomTypeDTO>("RoomType not found");
      }

      const roomTypeDTOResult = RoomTypeMap.toDTO(roomType);

      return Result.ok<IRoomTypeDTO>(roomTypeDTOResult);
    } catch (e) {
      return Result.fail<IRoomTypeDTO>(e.toString());
    }
  }
}
