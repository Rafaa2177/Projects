namespace DDDSample1.Domain.Users;

public class LoginUserDto
{
    public string Email { get; set; }
    public string Password { get; set; }
    public LoginUserDto(string email, string password)
    {
        this.Email = email;
        this.Password = password;
    }
}