using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace DDDSample1.Domain.Staff;

public class CreatingStaffDto
{
    public string FirstName { get; private set; }
    public string LastName { get; private set; }
    public string FullName { get; private set; }
    public string Specialization { get; private set; }
    public string Email { get; private set; }
    public string PhoneNumber { get; private set; }
    public List<TimeSlots> TimeSlots { get; private set; }
    public Roles Role { get; private set; }
    public bool Active { get; private set; } = true;
    
    
    public CreatingStaffDto(string firstName, string lastName, 
        string specialization, string email, string phoneNumber, List<TimeSlots> timeSlots, Roles role, bool active)
    {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.FullName = firstName + " " + lastName;
        this.Specialization = specialization;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.TimeSlots = timeSlots;
        this.Role = role;
        this.Active = active;
    }
    
}