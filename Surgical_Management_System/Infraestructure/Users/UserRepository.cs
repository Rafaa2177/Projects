using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Staff;
using DDDSample1.Domain.User;
using DDDSample1.Infrastructure.Shared;
using Microsoft.EntityFrameworkCore;

namespace DDDSample1.Infrastructure.User;

public class UserRepository :  BaseRepository<Domain.User.User, UserId>, IUserRepository
{
    private readonly DDDSample1DbContext _context;

    public UserRepository(DDDSample1DbContext context) : base(context.Users)
    {
        _context = context;
   
    }
    
    public async Task<List<Domain.User.User>> GetUsersByRole(string role)
    {
        {
            return await _context.Users
                .Where(u => u.role.Equals(role)) // Ensure correct comparison
                .ToListAsync(); // Ensure correct method is used
        }
    }

    public Task<bool> DoctorExists(UserId id)
    {
        return _context.Users.AnyAsync(e => e.Id.Equals(id) && e.role.Equals("Doctor"));
    }
    
    
}