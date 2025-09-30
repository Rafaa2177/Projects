namespace DDDNetCore.Domain.Users;

public class RoleDto
{
    public string Role { get; set; }
    public RoleDto(string role)
    {
        this.Role = role;
    }
    
}