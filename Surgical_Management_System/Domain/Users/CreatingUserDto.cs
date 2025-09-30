using System.Collections.Generic;
using DDDSample1.Domain.Staff;

namespace DDDSample1.Domain.User;

public class CreatingUserDto
{
    public CreatingStaffDto Staff { get; set; }
    public string username { get; set; }
    public string role { get; set; }
    public string email { get; set; }
    public string password { get; set; }
    public string PhoneNumber { get; set; }
    public List<string> Specialization { get; set; }
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public string FullName { get; set; }
    public List<TimeSlots> TimeSlots { get; set; }


    public CreatingUserDto()
    {
        
    }
    public CreatingUserDto(string email,string role)
    {
        this.role = role;
        this.email = email;
    }
    
    public CreatingUserDto(string username, string role, string email, string phoneNumber, List<string> specialization, string firstName, string lastName, List<TimeSlots> timeSlots)
    {
        this.username = username;
        this.role = role;
        this.email = email;
        this.PhoneNumber = phoneNumber;
        this.Specialization = specialization;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.FullName = firstName + " " + lastName;
        this.TimeSlots = timeSlots;
    }

}