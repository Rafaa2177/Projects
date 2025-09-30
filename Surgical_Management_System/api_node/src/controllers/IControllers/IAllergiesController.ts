import { Request, Response, NextFunction } from 'express';

/**
 * Interface for managing allergies.
 */
export default interface IAllergiesController {
  /**
   * Creates a new allergy.
   * @param req - The request object.
   * @param res - The response object.
   * @param next - The next middleware function.
   */
  createAllergies(req: Request, res: Response, next: NextFunction);

  /**
   * Updates an existing allergy.
   * @param req - The request object.
   * @param res - The response object.
   * @param next - The next middleware function.
   */
  updateAllergies(req: Request, res: Response, next: NextFunction);

  /**
   * Retrieves all allergies.
   * @param req - The request object.
   * @param res - The response object.
   * @param next - The next middleware function.
   */
  getAllAllergies(req: Request, res: Response, next: NextFunction);

  /**
   * Retrieves an allergy by its ID.
   * @param req - The request object.
   * @param res - The response object.
   * @param next - The next middleware function.
   */
  getAllergyById(req: Request, res: Response, next: NextFunction);
}
