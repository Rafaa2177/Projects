import { Request, Response, NextFunction } from 'express';
import { Inject, Service } from 'typedi';
import config from "../../config";
import { Result } from "../core/logic/Result";
import ISpecializationController from "./IControllers/ISpecializationController";
import ISpecializationService from "../services/IServices/ISpecializationService";
import ISpecializationDTO from "../dto/ISpecializationDTO";
import {SpecializationId} from "../domain/specializationId";

/**
 * Controller for managing specializations.
 * Implements the `ISpecializationController` interface to provide API endpoints for specializations.
 */
@Service()
export default class SpecializationController implements ISpecializationController  {

  /**
   * Constructor to initialize the specialization controller.
   * @param specializationsServiceInstance The service instance for specialization management.
   */
  constructor(
    @Inject(config.services.specialization.name) private specializationsServiceInstance : ISpecializationService
  ) {}

  /**
   * Creates a new specialization.
   * @param req The request object.
   * @param res The response object.
   * @param next The next middleware function.
   */
  public async createSpecialization(req: Request, res: Response, next: NextFunction) {
    try {
      const specializationsOrError = await this.specializationsServiceInstance.createSpecialization(req.body as ISpecializationDTO) as Result<ISpecializationDTO>;

      if (specializationsOrError.isFailure) {
        return res.status(500).send();
      }

      const specializationsDTO = specializationsOrError.getValue();
      return res.json( specializationsDTO ).status(201);
    }
    catch (e) {
      return next(e);
    }
  };

  /**
   * Updates an existing specialization.
   * @param req The request object.
   * @param res The response object.
   * @param next The next middleware function.
   */
  public async updateSpecialization(req: Request, res: Response, next: NextFunction) {
    try {
      const { id } = req.params;
      const { name, code, description } = req.body;
      const specializationData = { id, name, code, description };

      console.log('Update Request - ID:', id);
      console.log('Update Request - Body:', req.body);
      console.log('Update Request - Combined Data:', specializationData);

      const specializationsOrError = await this.specializationsServiceInstance.updateSpecialization(specializationData as ISpecializationDTO) as Result<ISpecializationDTO>;

      if (specializationsOrError.isFailure) {
        console.log('Update failed:', specializationsOrError.error);
        return res.status(404).send();
      }

      const specializationsDTO = specializationsOrError.getValue();
      console.log('Update successful - Response:', specializationsDTO);
      return res.status(200).json(specializationsDTO);
    } catch (e) {
      console.error('Update error:', e);
      return next(e);
    }
  };

  /**
   * Retrieves all specializations.
   * @param req The request object.
   * @param res The response object.
   * @param next The next middleware function.
   */
  public async getSpecialization(req: Request, res: Response, next: NextFunction) {
    try {
      const result = await this.specializationsServiceInstance.getSpecialization();
      if (result.isFailure) {
        return res.status(404).send(result.error);
      }
      return res.json(result.getValue());
    } catch (e) {
      return next(e);
    }
  }

  /**
   * Retrieves a specialization by its ID.
   * @param req The request object.
   * @param res The response object.
   * @param next The next middleware function.
   */
  public async getSpecializationById(req: Request, res: Response, next: NextFunction) {
    try {
      const id: SpecializationId = req.params.id as unknown as SpecializationId; // Convert to unknown first
      const result = await this.specializationsServiceInstance.getSpecializationById(id);
      if (result.isFailure) {
        return res.status(404).send(result.error);
      }
      return res.json(result.getValue());
    } catch (e) {
      return next(e);
    }
  }

}
