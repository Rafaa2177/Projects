import { Request, Response, NextFunction } from 'express';
import { Inject, Service } from 'typedi';
import config from "../../config";
import { Result } from "../core/logic/Result";
import IAllergiesController from "./IControllers/IAllergiesController";
import IAllergiesService from "../services/IServices/IAllergiesService";
import IAllergiesDTO from "../dto/IAllergiesDTO";
import ISpecializationDTO from "../dto/ISpecializationDTO";
import {SpecializationId} from "../domain/specializationId";
import {AllergiesId} from "../domain/allergiesId";


/**
 * Controller for managing allergies.
 */
@Service()
export default class AllergiesController implements IAllergiesController {
  /**
   * Constructs an instance of AllergiesController.
   * @param allergiesServiceInstance - The allergies service instance.
   */
  constructor(
    @Inject(config.services.allergies.name) private allergiesServiceInstance : IAllergiesService
  ) {}

  /**
   * Creates a new allergy.
   * @param req - The request object.
   * @param res - The response object.
   * @param next - The next middleware function.
   * @returns A promise that resolves to the created allergy.
   */
  public async createAllergies(req: Request, res: Response, next: NextFunction) {
    try {
      const { code, name, description } = req.body;
      const allergiesOrError = await this.allergiesServiceInstance.createAllergies({ code, name, description,} as IAllergiesDTO);

      if (allergiesOrError.isFailure) {
        return res.status(402).json({ error: allergiesOrError.error });
      }

      return res.status(201).json(allergiesOrError.getValue());
    } catch (e) {
      return next(e);
    }
  };

  /**
   * Updates an existing allergy.
   * @param req - The request object.
   * @param res - The response object.
   * @param next - The next middleware function.
   * @returns A promise that resolves to the updated allergy.
   */
  public async updateAllergies(req: Request, res: Response, next: NextFunction) {
    try {
      const { id } = req.params;
      const { name, code, description } = req.body;
      const allergyData = { id, name, code, description };

      console.log('Update Request - ID:', id);
      console.log('Update Request - Body:', req.body);
      console.log('Update Request - Combined Data:', allergyData);

      const allergiesOrError = await this.allergiesServiceInstance.updateAllergies(allergyData as IAllergiesDTO) as Result<IAllergiesDTO>;

      if (allergiesOrError.isFailure) {
        console.log('Update failed:', allergiesOrError.error);
        return res.status(404).send();
      }

      const allergiesDto = allergiesOrError.getValue();
      console.log('Update successful - Response:', allergiesDto);
      return res.status(201).json(allergiesDto);
    } catch (e) {
      console.error('Update error:', e);
      return next(e);
    }
  };

  /**
   * Retrieves an allergy by its ID.
   * @param req - The request object.
   * @param res - The response object.
   * @param next - The next middleware function.
   * @returns A promise that resolves to the retrieved allergy.
   */
  public async getAllergyById(req: Request, res: Response, next: NextFunction) {
    try {
      const id: AllergiesId = req.params.id as unknown as AllergiesId; // Convert to unknown first
      const result = await this.allergiesServiceInstance.getAllergiesById(id);
      if (result.isFailure) {
        return res.status(404).send(result.error);
      }
      return res.json(result.getValue());
    } catch (e) {
      return next(e);
    }
  }

  /**
   * Retrieves all allergies.
   * @param req - The request object.
   * @param res - The response object.
   * @param next - The next middleware function.
   * @returns A promise that resolves to the list of all allergies.
   */
  public async getAllAllergies(req: Request, res: Response, next: NextFunction) {
    try {
      const result = await this.allergiesServiceInstance.getAllAllergies();
      if (result.isFailure) {
        return res.status(404).send(result.error);
      }
      return res.json(result.getValue());
    } catch (e) {
      return next(e);
    }
  }
}
