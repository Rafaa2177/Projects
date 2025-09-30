import { Repo } from "../../core/infra/Repo";
import { RoomType } from "../../domain/OperationTypeRoom/roomType";
import { RoomTypeId } from "../../domain/OperationTypeRoom/roomTypeId";

export default interface IRoomTypeRepo extends Repo<RoomType> {
  save(roomType: RoomType): Promise<RoomType>;
  findByDomainId(roomTypeId: RoomTypeId | string): Promise<RoomType>;
  findAll(): Promise<RoomType[]>;
}
