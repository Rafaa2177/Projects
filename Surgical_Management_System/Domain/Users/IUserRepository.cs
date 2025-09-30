using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.User;

namespace DDDSample1.Domain.User;

public interface IUserRepository : IRepository<User, UserId>
{
    Task<List<User>> GetUsersByRole(string role);
    
    Task <bool> DoctorExists(UserId id);
}