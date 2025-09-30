import { Repo } from "../../core/infra/Repo";
import { MedicalConditionId } from "../../domain/medicalConditionId";
import {MedicalCondition} from "../../domain/medicalCondition";

export default interface IMedicalConditionRepo extends Repo<MedicalCondition> {
  save(role: MedicalCondition): Promise<MedicalCondition>;
  findByDomainId (medicalConditionId: MedicalConditionId | string): Promise<MedicalCondition>;
  findAll(): Promise<MedicalCondition[]>;


  //findByIds (rolesIds: RoleId[]): Promise<Role[]>;
  //saveCollection (roles: Role[]): Promise<Role[]>;
  //removeByRoleIds (roles: RoleId[]): Promise<any>
}
