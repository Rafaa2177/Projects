// src/domain/OperationRoom/OperationRoom.ts

import { AggregateRoot } from "../../core/domain/AggregateRoot";
import { UniqueEntityID } from "../../core/domain/UniqueEntityID";
import { Result } from "../../core/logic/Result";
import { Guard } from "../../core/logic/Guard";

interface OperationRoomProps {
  room: string;
  roomType: string;
}

export class OperationRoom extends AggregateRoot<OperationRoomProps> {
  get id(): UniqueEntityID {
    return this._id;
  }

  get room(): string {
    return this.props.room;
  }

  get roomType(): string {
    return this.props.roomType;
  }
  set roomType(value: string) {
    this.props.roomType = value;
  }
  private constructor(props: OperationRoomProps, id?: UniqueEntityID) {
    super(props, id);
  }

  public static create(props: OperationRoomProps, id?: UniqueEntityID): Result<OperationRoom> {
    const guardResult = Guard.againstNullOrUndefinedBulk([
      { argument: props.room, argumentName: 'room' },
      { argument: props.roomType, argumentName: 'roomType' }
    ]);

    if (!guardResult.succeeded) {
      return Result.fail<OperationRoom>(guardResult.message);
    }

    const operationRoom = new OperationRoom(props, id);
    return Result.ok<OperationRoom>(operationRoom);
  }
}
