import { Request, Response, NextFunction } from 'express';
import { Container } from 'typedi';
import AllergiesController from '../../../api_node/src/controllers/allergiesController';
import IAllergiesService from '../../../api_node/src/services/IServices/IAllergiesService';
import { Result } from '../../src/core/logic/Result';
import IAllergiesDTO from '../../../api_node/src/dto/IAllergiesDTO';
import { expect } from '@jest/globals';
// Mock do serviço
const mockAllergiesService = {
  createAllergies: jest.fn(),
  updateAllergies: jest.fn(),
  getAllergiesById: jest.fn(),
  getAllAllergies: jest.fn(),
};

// Injeta o mock no container do typedi
Container.set('AllergiesService', mockAllergiesService as IAllergiesService);

describe('AllergiesController', () => {
  let allergiesController: AllergiesController;
  let mockRequest: Partial<Request>;
  let mockResponse: Partial<Response>;
  let mockNextFunction: NextFunction;

  beforeEach(() => {
    allergiesController = new AllergiesController(
      mockAllergiesService as unknown as IAllergiesService
    );

    mockRequest = {
      body: {},
      params: {},
    };

    mockResponse = {
      status: jest.fn().mockReturnThis(),
      json: jest.fn(),
      send: jest.fn(),
    };

    mockNextFunction = jest.fn();
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  describe('createAllergies', () => {
    it('should return 201 and the created allergy on success', async () => {
      const mockAllergy: IAllergiesDTO = { code: 'A001', name: 'Peanuts', description: 'Peanut allergy' };
      mockAllergiesService.createAllergies.mockResolvedValue(Result.ok<IAllergiesDTO>(mockAllergy));

      mockRequest.body = { code: 'A001', name: 'Peanuts', description: 'Peanut allergy' };

      await allergiesController.createAllergies(
        mockRequest as Request,
        mockResponse as Response,
        mockNextFunction
      );

      expect(mockAllergiesService.createAllergies).toHaveBeenCalledWith(mockRequest.body);
      expect(mockResponse.status).toHaveBeenCalledWith(201);
      expect(mockResponse.json).toHaveBeenCalledWith(mockAllergy);
    });

    it('should return 402 on failure', async () => {
      mockAllergiesService.createAllergies.mockResolvedValue(Result.fail('Error creating allergy'));

      mockRequest.body = { code: 'A001', name: 'Peanuts', description: 'Peanut allergy' };

      await allergiesController.createAllergies(
        mockRequest as Request,
        mockResponse as Response,
        mockNextFunction
      );

      expect(mockAllergiesService.createAllergies).toHaveBeenCalledWith(mockRequest.body);
      expect(mockResponse.status).toHaveBeenCalledWith(402);
      expect(mockResponse.json).toHaveBeenCalledWith({ error: 'Error creating allergy' });
    });
  });

  describe('updateAllergies', () => {
    it('should return 201 and the updated allergy on success', async () => {
      const mockAllergy: IAllergiesDTO = { id: '1', code: 'A002', name: 'Dust', description: 'Dust allergy' };
      mockAllergiesService.updateAllergies.mockResolvedValue(Result.ok<IAllergiesDTO>(mockAllergy));

      mockRequest.params = { id: '1' };
      mockRequest.body = { name: 'Dust', code: 'A002', description: 'Dust allergy' };

      await allergiesController.updateAllergies(
        mockRequest as Request,
        mockResponse as Response,
        mockNextFunction
      );

      expect(mockAllergiesService.updateAllergies).toHaveBeenCalledWith({ id: '1', ...mockRequest.body });
      expect(mockResponse.status).toHaveBeenCalledWith(201);
      expect(mockResponse.json).toHaveBeenCalledWith(mockAllergy);
    });

    it('should return 404 on failure', async () => {
      mockAllergiesService.updateAllergies.mockResolvedValue(Result.fail('Allergy not found'));

      mockRequest.params = { id: '1' };
      mockRequest.body = { name: 'Dust', code: 'A002', description: 'Dust allergy' };

      await allergiesController.updateAllergies(
        mockRequest as Request,
        mockResponse as Response,
        mockNextFunction
      );

      expect(mockAllergiesService.updateAllergies).toHaveBeenCalledWith({ id: '1', ...mockRequest.body });
      expect(mockResponse.status).toHaveBeenCalledWith(404);
      expect(mockResponse.send).toHaveBeenCalled();
    });
  });

  describe('getAllergyById', () => {
    it('should return the allergy when found', async () => {
      const mockAllergy: IAllergiesDTO = { id: '1', code: 'A003', name: 'Shellfish', description: 'Shellfish allergy' };
      mockAllergiesService.getAllergiesById.mockResolvedValue(Result.ok<IAllergiesDTO>(mockAllergy));

      mockRequest.params = { id: '1' };

      await allergiesController.getAllergyById(
        mockRequest as Request,
        mockResponse as Response,
        mockNextFunction
      );

      expect(mockAllergiesService.getAllergiesById).toHaveBeenCalledWith('1');
      expect(mockResponse.json).toHaveBeenCalledWith(mockAllergy);
    });

    it('should return 404 if not found', async () => {
      mockAllergiesService.getAllergiesById.mockResolvedValue(Result.fail('Allergy not found'));

      mockRequest.params = { id: '1' };

      await allergiesController.getAllergyById(
        mockRequest as Request,
        mockResponse as Response,
        mockNextFunction
      );

      expect(mockAllergiesService.getAllergiesById).toHaveBeenCalledWith('1');
      expect(mockResponse.status).toHaveBeenCalledWith(404);
      expect(mockResponse.send).toHaveBeenCalledWith('Allergy not found');
    });
  });

  describe('getAllAllergies', () => {
    it('should return all allergies', async () => {
      const mockAllergies: IAllergiesDTO[] = [
        { id: '1', code: 'A001', name: 'Peanuts', description: 'Peanut allergy' },
        { id: '2', code: 'A002', name: 'Dust', description: 'Dust allergy' },
      ];
      mockAllergiesService.getAllAllergies.mockResolvedValue(Result.ok<IAllergiesDTO[]>(mockAllergies));

      await allergiesController.getAllAllergies(
        mockRequest as Request,
        mockResponse as Response,
        mockNextFunction
      );

      expect(mockAllergiesService.getAllAllergies).toHaveBeenCalled();
      expect(mockResponse.json).toHaveBeenCalledWith(mockAllergies);
    });

    it('should return 404 if no allergies are found', async () => {
      mockAllergiesService.getAllAllergies.mockResolvedValue(Result.fail('No allergies found'));

      await allergiesController.getAllAllergies(
        mockRequest as Request,
        mockResponse as Response,
        mockNextFunction
      );

      expect(mockAllergiesService.getAllAllergies).toHaveBeenCalled();
      expect(mockResponse.status).toHaveBeenCalledWith(404);
      expect(mockResponse.send).toHaveBeenCalledWith('No allergies found');
    });
  });
});
