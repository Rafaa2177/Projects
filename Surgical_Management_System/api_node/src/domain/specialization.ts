import { AggregateRoot } from "../core/domain/AggregateRoot";
import { UniqueEntityID } from "../core/domain/UniqueEntityID";

import { Result } from "../core/logic/Result";
import {SpecializationId} from "./specializationId";
import ISpecializationDTO from "../dto/ISpecializationDTO";

interface SpecializationProps {
  name: string;
  code: string;
  description?: string;
}

/**
 * Domain class representing a Specialization.
 * Extends the `AggregateRoot` class to include business logic for specializations.
 */
export class Specialization extends AggregateRoot<SpecializationProps> {

  /**
   * Gets the unique entity ID of the specialization.
   * @returns The unique entity ID.
   */
  get id (): UniqueEntityID {
    return this._id;
  }

  /**
   * Gets the specialization ID.
   * @returns The specialization ID.
   */
  get specializationId (): SpecializationId {
    return new SpecializationId(this.specializationId.toValue());
  }

  /**
   * Gets the name of the specialization.
   * @returns The name of the specialization.
   */
  get name (): string {
    return this.props.name;
  }

  /**
   * Gets the code of the specialization.
   * @returns The code of the specialization.
   */
  get code (): string {
    return this.props.code;
  }

  /**
   * Gets the description of the specialization.
   * @returns The description of the specialization.
   */
  get description (): string | undefined {
    return this.props.description;
  }

  /**
   * Sets the name of the specialization.
   * @param value The new name of the specialization.
   */
  set name ( value: string) {
    this.props.name = value;
  }

  /**
   * Sets the code of the specialization.
   * @param value The new code of the specialization.
   */
  set code ( value: string) {
    this.props.code = value;
  }

  /**
   * Sets the description of the specialization.
   * @param value The new description of the specialization.
   */
  set description ( value: string | undefined) {
    this.props.description = value;
  }

  /**
   * Private constructor to initialize the specialization.
   * @param props The properties of the specialization.
   * @param id The unique entity ID of the specialization (optional).
   */
  private constructor (props: SpecializationProps, id?: UniqueEntityID) {
    super(props, id);
  }

  /**
   * Static method to create a new specialization.
   * @param specializationDTO The specialization data transfer object.
   * @param id The unique entity ID of the specialization (optional).
   * @returns A Result object containing the created specialization.
   */
  public static create (specializationDTO: ISpecializationDTO, id?: UniqueEntityID): Result<Specialization> {
    const { name, code, description } = specializationDTO;


    const codeRegex = /^[0-9]{5,20}$/;
    if(!codeRegex.test(code)) {
      return Result.fail<Specialization>('Code must be a number with 5 to 20 digits');
    }

    const specialization = new Specialization({ code, name, description }, id);
    return Result.ok<Specialization>(specialization);
  }
}
