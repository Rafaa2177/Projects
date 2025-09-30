import { AggregateRoot } from "../core/domain/AggregateRoot";
import { UniqueEntityID } from "../core/domain/UniqueEntityID";
import { Result } from "../core/logic/Result";
import { AllergiesId } from "./allergiesId";
import IAllergiesDTO from "../dto/IAllergiesDTO";

/**
 * Interface that defines the properties of an allergy.
 */
interface AllergiesProps {
  name: string;
  code: string;
  description?: string;
}

/**
 * Class representing an allergy.
 */
export class Allergies extends AggregateRoot<AllergiesProps> {

  /**
   * Gets the unique entity ID.
   * @returns The unique entity ID.
   */
  get id(): UniqueEntityID {
    return this._id;
  }

  /**
   * Gets the allergies ID.
   * @returns The allergies ID.
   */
  get allergiesId(): AllergiesId {
    return new AllergiesId(this.allergiesId.toValue());
  }

  /**
   * Gets the name of the allergy.
   * @returns The name of the allergy.
   */
  get name(): string {
    return this.props.name;
  }

  /**
   * Sets the name of the allergy.
   * @param value - The name of the allergy.
   */
  set name(value: string) {
    this.props.name = value;
  }

  /**
   * Gets the code of the allergy.
   * @returns The code of the allergy.
   */
  get code(): string {
    return this.props.code;
  }

  /**
   * Sets the code of the allergy.
   * @param value - The code of the allergy.
   */
  set code(value: string) {
    this.props.code = value;
  }

  /**
   * Gets the description of the allergy.
   * @returns The description of the allergy.
   */
  get description(): string | undefined {
    return this.props.description;
  }

  /**
   * Sets the description of the allergy.
   * @param value - The description of the allergy.
   */
  set description(value: string | undefined) {
    this.props.description = value;
  }

  /**
   * Constructs an instance of Allergies.
   * @param props - The properties of the allergy.
   * @param id - The unique entity ID.
   */
  public constructor(props: AllergiesProps, id?: UniqueEntityID) {
    super(props, id);
  }

  /**
   * Creates an instance of Allergies.
   * @param allergiesDTO - The allergies data transfer object.
   * @param id - The unique entity ID.
   * @returns A result containing the created allergy.
   */
  public static create(allergiesDTO: IAllergiesDTO, id?: UniqueEntityID): Result<Allergies> {
    const { code, name, description } = allergiesDTO;

    if (!code || !name) {
      return Result.fail<Allergies>('Must provide code and a name for the allergy');
    }

    const codeRegex = /^[0-9]{5,20}$/;
    if (!codeRegex.test(code)) {
      return Result.fail<Allergies>('Code must be a number with 5 to 20 digits');
    }

    const allergies = new Allergies({ code, name, description }, id);
    return Result.ok<Allergies>(allergies);
  }
}
