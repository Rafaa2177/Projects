using System.Collections.Generic;
using DDDNetCore.Domain.Users;

namespace DDDSample1.Domain.User;

public class UserDto
{

    
    public string role { get; private set; }
    public string email { get; private set; }
    public UserId id { get; private set; }

    
    public UserDto( string role, string email, UserId id)
    {
        this.role = role;
        this.email = email;
        this.id = id;
    }
   
}