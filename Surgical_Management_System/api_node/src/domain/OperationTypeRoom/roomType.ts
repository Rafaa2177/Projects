import {AggregateRoot} from "../../core/domain/AggregateRoot";
import {UniqueEntityID} from "../../core/domain/UniqueEntityID";
import IRoomTypeDTO from "../../dto/IRoomTypeDTO";
import { Guard } from "../../core/logic/Guard";
import {RoomTypeId} from "./roomTypeId";
import {Result} from "../../core/logic/Result";

interface roomTypeProps{
  name: string,
  description: string
}

export class RoomType extends AggregateRoot<roomTypeProps>{
  get id(): UniqueEntityID{
    return this._id;
  }

  get name(): string{
    return this.props.name;
  }
  get description(): string{
    return this.props.description;
  }
  set name(value:string|undefined) {
    this.props.name=value;
  }
  set description(value:string|undefined) {
    this.props.description=value;
  }
  private constructor(props: roomTypeProps, id?: UniqueEntityID){
    super(props, id);
  }

  public static create(roomTypeDTO: IRoomTypeDTO, id?: UniqueEntityID){
    const guardResult = Guard.againstNullOrUndefinedBulk([
      { argument: roomTypeDTO.name, argumentName: 'name' },
      { argument: roomTypeDTO.description, argumentName: 'description' }
    ]);

    if (!guardResult.succeeded){
      return Result.fail<RoomType>(guardResult.message);
    }

    const roomType = new RoomType({
      name: roomTypeDTO.name,
      description: roomTypeDTO.description
    }, id);

    return Result.ok<RoomType>(roomType);
  }
}
