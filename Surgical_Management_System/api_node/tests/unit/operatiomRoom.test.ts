import { OperationRoom } from '../../src/domain/OperationRoom/operationRoom';
import { UniqueEntityID } from '../../src/core/domain/UniqueEntityID';
import { Result } from '../../src/core/logic/Result';
import { expect } from '@jest/globals';
describe('OperationRoom', () => {
  let validProps: { room: string, roomType: string };

  beforeEach(() => {
    validProps = {
      room: 'OR1',
      roomType: 'Surgery'
    };
  });

  it('should create an OperationRoom with valid properties', () => {
    const result = OperationRoom.create(validProps, new UniqueEntityID('1'));
    expect(result.isSuccess).toBeTruthy();
    const operationRoom = result.getValue();
    expect(operationRoom.id.toValue()).toBe('1');
    expect(operationRoom.room).toBe('OR1');
    expect(operationRoom.roomType).toBe('Surgery');
  });

  it('should fail creation if room is missing', () => {
    const invalidProps = { ...validProps, room: undefined };
    const result = OperationRoom.create(invalidProps, new UniqueEntityID('1'));
    expect(result.isFailure).toBeTruthy();
    expect(result.error).toContain('room');
  });

  it('should fail creation if roomType is missing', () => {
    const invalidProps = { ...validProps, roomType: undefined };
    const result = OperationRoom.create(invalidProps, new UniqueEntityID('1'));
    expect(result.isFailure).toBeTruthy();
    expect(result.error).toContain('roomType');
  });

  it('should update the roomType', () => {
    const result = OperationRoom.create(validProps, new UniqueEntityID('1'));
    const operationRoom = result.getValue();
    operationRoom.roomType = 'Recovery';
    expect(operationRoom.roomType).toBe('Recovery');
  });
});
