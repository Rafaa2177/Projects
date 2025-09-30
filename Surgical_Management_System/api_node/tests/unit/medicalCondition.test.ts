import { MedicalCondition } from '../../src/domain/medicalCondition';
import { UniqueEntityID } from '../../src/core/domain/UniqueEntityID';
import { Result } from '../../src/core/logic/Result';
import { expect } from '@jest/globals';

describe('MedicalCondition', () => {
  let validProps: { id: string, name: string, code: string };

  beforeEach(() => {
    validProps = {
      id: '1',
      name: 'Asthma',
      code: '12345'
    };
  });

  it('should create a MedicalCondition with valid properties', () => {
    const result = MedicalCondition.create(validProps, new UniqueEntityID('1'));
    expect(result.isSuccess).toBeTruthy();
    const medicalCondition = result.getValue();
    expect(medicalCondition.id.toValue()).toBe('1');
    expect(medicalCondition.name).toBe('Asthma');
    expect(medicalCondition.code).toBe('12345');
  });

  it('should fail creation if name is missing', () => {
    const invalidProps = { ...validProps, name: undefined };
    const result = MedicalCondition.create(invalidProps, new UniqueEntityID('1'));
    expect(result.isFailure).toBeTruthy();
    expect(result.error).toContain('Must provide code and a name for the allergy');
  });

  it('should fail creation if code is missing', () => {
    const invalidProps = { ...validProps, code: undefined };
    const result = MedicalCondition.create(invalidProps, new UniqueEntityID('1'));
    expect(result.isFailure).toBeTruthy();
    expect(result.error).toContain('Must provide code and a name for the allergy');
  });

  it('should fail creation if code is invalid', () => {
    const invalidProps = { ...validProps, code: 'abc' };
    const result = MedicalCondition.create(invalidProps, new UniqueEntityID('1'));
    expect(result.isFailure).toBeTruthy();
    expect(result.error).toContain('Code must be a number with 5 to 20 digits');
  });

  it('should update the name', () => {
    const result = MedicalCondition.create(validProps, new UniqueEntityID('1'));
    const medicalCondition = result.getValue();
    medicalCondition.name = 'Bronchitis';
    expect(medicalCondition.name).toBe('Bronchitis');
  });
});
