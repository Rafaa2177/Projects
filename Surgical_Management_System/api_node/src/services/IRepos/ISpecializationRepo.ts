import { Repo } from "../../core/infra/Repo";
import {Specialization} from "../../domain/specialization";
import {SpecializationId} from "../../domain/specializationId";

export default interface ISpecializationRepo extends Repo<Specialization> {
  save(specialization: Specialization): Promise<Specialization>;
  findByDomainId (specializationId: SpecializationId | string): Promise<Specialization>;
  findAll(): Promise<Specialization[]>;

  //findByIds (rolesIds: RoleId[]): Promise<Role[]>;
  //saveCollection (roles: Role[]): Promise<Role[]>;
  //removeByRoleIds (roles: RoleId[]): Promise<any>
}
