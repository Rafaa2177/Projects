import { Appointment } from '../../src/domain/appointment/appointment';
import { UniqueEntityID } from '../../src/core/domain/UniqueEntityID';
import IAppointmentDTO from '../../src/dto/IAppointmentDTO';
import { expect } from '@jest/globals';

/**
 * Unit tests for the Appointment domain entity.
 */
describe('Appointment', () => {
  let appointment: Appointment;
  let baseAppointmentDTO: IAppointmentDTO;

  /**
   * Setup the base appointment DTO and create an appointment instance before each test.
   */
  beforeEach(() => {
    baseAppointmentDTO = {
      doctorId: '1',
      pacientId: '2',
      startDate: new Date('2026-01-01T10:00:00Z'),
      endDate: new Date('2026-01-01T11:00:00Z'),
      room: 'or1',
      operationType: '0',
      status: 'SCHEDULED'
    };

    const result = Appointment.create(baseAppointmentDTO, new UniqueEntityID('1'));
    if (result.isFailure) {
      throw new Error(result.error as string);
    }
    appointment = result.getValue();
  });

  /**
   * Tests for the creation of an appointment.
   */
  describe('Creation', () => {
    /**
     * Test that an appointment is created with the correct properties.
     */
    it('should create an appointment with correct properties', () => {
      expect(appointment.id.toValue()).toBe('1');
      expect(appointment.doctorId).toBe('1');
      expect(appointment.pacientId).toBe('2');
      expect(appointment.operationStartDate).toEqual(new Date('2026-01-01T10:00:00Z'));
      expect(appointment.operationEndDate).toEqual(new Date('2026-01-01T11:00:00Z'));
      expect(appointment.operationRoom).toBe('or1');
      expect(appointment.operationType).toBe('Cardiology');
      expect(appointment.status).toBe('SCHEDULED');
    });

    /**
     * Test that creation fails when required fields are missing.
     */
    it('should fail creation with missing required fields', () => {
      const invalidDTO = { ...baseAppointmentDTO };
      delete invalidDTO.doctorId;

      const result = Appointment.create(invalidDTO, new UniqueEntityID('1'));
      expect(result.isFailure).toBeTruthy();
      expect(result.error).toContain('doctorId');
    });

    /**
     * Test that creation fails when dates are invalid.
     */
    it('should fail creation with invalid dates', () => {
      const invalidDTO = {
        ...baseAppointmentDTO,
        startDate: new Date('2026-01-01T11:00:00Z'),
        endDate: new Date('2026-01-01T10:00:00Z')
      };

      const result = Appointment.create(invalidDTO, new UniqueEntityID('1'));
      console.log(result);
      expect(Appointment.create(invalidDTO, new UniqueEntityID('1')).error).toContain('A data de início da consulta não pode ser depois da data de término');
    });
  });

  /**
   * Tests for updating an appointment.
   */
  describe('Updates', () => {
    /**
     * Test that the appointment details can be updated.
     */
    it('should update the appointment details', () => {
      appointment.props.pacientId = '3';
      appointment.props.doctorId = '4';
      appointment.props.startDate = new Date('2026-01-02T10:00:00Z');
      appointment.props.endDate = new Date('2026-01-02T11:00:00Z');
      appointment.props.room = 'or2';
      appointment.props.operationType = '1';
      appointment.props.status = 'COMPLETED';

      expect(appointment.pacientId).toBe('3');
      expect(appointment.doctorId).toBe('4');
      expect(appointment.operationStartDate).toEqual(new Date('2026-01-02T10:00:00Z'));
      expect(appointment.operationEndDate).toEqual(new Date('2026-01-02T11:00:00Z'));
      expect(appointment.operationRoom).toBe('or2');
      expect(appointment.operationType).toBe('1');
      expect(appointment.status).toBe('COMPLETED');
    });

    /**
     * Test that an error is thrown if the end date is before the start date.
     */
    it('should throw an error if endDate is before startDate', () => {
      expect(() => {
        appointment.endDate = new Date('2025-12-31T11:00:00Z');
      }).toThrow('End date must be after start date');
    });

    /**
     * Test that the status can be updated to valid values.
     */
    it('should allow updating status to valid values', () => {
      const validStatuses = ['SCHEDULED', 'COMPLETED', 'CANCELLED'];

      validStatuses.forEach(status => {
        appointment.props.status = status;
        expect(appointment.status).toBe(status);
      });
    });

    /**
     * Test that an error is thrown when updating to an invalid status.
     */
    it('should throw error when updating to invalid status', () => {
      expect(() => {
        appointment.status = 'INVALID_STATUS';
      }).toThrow('Invalid status');
    });
  });

  /**
   * Tests for business rules related to appointments.
   */
  describe('Business Rules', () => {
    /**
     * Test that appointments shorter than the minimum duration are not allowed.
     */
    it('should not allow appointments shorter than minimum duration', () => {
      const shortAppointment = {
        ...baseAppointmentDTO,
        startDate: new Date('2026-01-01T10:00:00Z'),
        endDate: new Date('2026-01-01T10:14:00Z')  // 15 minutes
      };

      const result = Appointment.create(shortAppointment, new UniqueEntityID('1'));
      expect(result.isFailure).toBeTruthy();
      expect(result.error).toContain('A duração da consulta não deve de ser inferior a 15 minutos');
    });
  });

  /**
   * Tests for domain events related to appointments.
   */
  describe('Domain Events', () => {
    /**
     * Test that an appointment created event is registered.
     */
    it('should register appointment created event', () => {
      const result = Appointment.create(baseAppointmentDTO, new UniqueEntityID('1'));
      const appointment = result.getValue();
      console.log(result);
      expect(appointment.domainEvents.length).toBe(1);
      expect(appointment.domainEvents[0].constructor.name).toBe('AppointmentCreatedEvent');
    });

    /**
     * Test that an appointment cancelled event is registered when the status changes to CANCELLED.
     */
    it('should register appointment cancelled event when status changes to CANCELLED', () => {
      appointment.status = 'CANCELLED';
      console.log(appointment);
      expect(appointment.domainEvents.length).toBe(1);
      expect(appointment.domainEvents[0].constructor.name).toBe('AppointmentCancelledEvent');
    });
  });
});
