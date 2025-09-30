import { Request, Response, NextFunction } from 'express';

/**
 * Interface for Specialization Controller.
 */
export default interface ISpecializationController {

  /**
   * Creates a new specialization.
   * @param req The request object.
   * @param res The response object.
   * @param next The next middleware function.
   */
  createSpecialization(req: Request, res: Response, next: NextFunction);

  /**
   * Updates an existing specialization.
   * @param req The request object.
   * @param res The response object.
   * @param next The next middleware function.
   */
  updateSpecialization(req: Request, res: Response, next: NextFunction);

  /**
   * Retrieves all specializations.
   * @param req The request object.
   * @param res The response object.
   * @param next The next middleware function.
   */
  getSpecialization(req: Request, res: Response, next: NextFunction);

  /**
   * Retrieves a specialization by its ID.
   * @param req The request object.
   * @param res The response object.
   * @param next The next middleware function.
   */
  getSpecializationById(req: Request, res: Response, next: NextFunction);
}

