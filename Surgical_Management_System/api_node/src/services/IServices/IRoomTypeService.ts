import { Result } from "../../core/logic/Result";
import IRoomTypeDTO from "../../dto/IRoomTypeDTO";

export default interface IRoomTypeService {
  createRoomType(roomTypeDTO: IRoomTypeDTO): Promise<Result<IRoomTypeDTO>>;
  updateRoomType(roomTypeDTO: IRoomTypeDTO): Promise<Result<IRoomTypeDTO>>;
  getRoomType(roomTypeId: string): Promise<Result<IRoomTypeDTO>>;
  getAllRoomTypes(): Promise<Result<Array<IRoomTypeDTO>>>;
}
