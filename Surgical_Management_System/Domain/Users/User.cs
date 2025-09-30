using System;
using System.Text.RegularExpressions;
using DDDSample1.Domain.Shared;


namespace DDDSample1.Domain.User;

public class User : Entity<UserId>
{
    public string role { get; private set; }
    public string email { get; private set; }

    private User()
    {
    }

    public User(string role, string email)
    {
        if (!IsValidEmail(email))
        {
            throw new ArgumentException("Invalid email format", nameof(email));
        }

        if (!IsValidRole(role))
        {
            throw new ArgumentException("Invalid role", nameof(role));
        }
        
        this.role = role;
        this.email = email;
        this.Id = new UserId(Guid.NewGuid());
    }
    
    
    public void UpdateRole(string role)
    {
        
        if (role != null && !IsValidRole(role))
        {
            throw new ArgumentException("Invalid role", nameof(role));
        }
        
        if (role == null)
        {
            this.role = this.role;
        }

        if (role != null)
        {
            this.role = role;

        }    }   
    
    public void UpdateEmail(string email)
    {
        if (email != null && !IsValidEmail(email))
        {
            throw new ArgumentException("Invalid email format", nameof(email));
        }
        
        if (email == null)
        { 
            this.email = this.email;
        }

        if (email != null)
        {
            this.email = email;

        }
    }   
    
    public void Update(string role, string email)
    {
        
        if (email != null && !IsValidEmail(email))
        {
            throw new ArgumentException("Invalid email format", nameof(email));
        }
        
        if (role != null && !IsValidRole(role))
        {
            throw new ArgumentException("Invalid role", nameof(role));
        }

        if (email == null)
        {
            this.email = this.email;
        }

        if (email != null)
        {
            this.email = email;

        }

        if (role == null)
        {
            this.role = this.role;
        }
        
        if (role != null)
        {
            this.role = role;
        }
        
    }   
    
    
    private bool IsValidEmail(string email)
    {
        var emailRegex = new Regex(@"^[^@\s]+@[^@\s]+\.[^@\s]+$");
        return emailRegex.IsMatch(email);
    }
    
    private bool IsValidRole(string role)
    {
        if (role == "Doctor" || role == "Nurse" || role == "Admin" || role == "Technician")
        {
            return true;
        }
        return false;
    }
    
   
}