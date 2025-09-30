using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace DDDSample1.Domain.Staff;

public class StaffDto
{

    public string FirstName { get; set; }
    public string LastName { get;   set; }
    public string FullName { get;  set; }
    public StaffId LicenseNumber { get; set; }
    public string Specialization { get;  set; }
    public string Email { get;  set; }
    public string PhoneNumber { get;   set; }
    public List<TimeSlots> TimeSlots { get;  set; }
    public Roles Role { get;  set; }
    public bool Active { get;  set; }

    
    public StaffDto(string firstName, string lastName, string fullName, StaffId licenseNumber, string specialization,
        string email, string phoneNumber, List<TimeSlots> timeSlots, Roles role, bool active)
    {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.FullName = fullName;
        this.LicenseNumber = licenseNumber;
        this.Specialization = specialization;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.TimeSlots = timeSlots;
        this.Role = role;
        this.Active = active;
    }

    public StaffDto(string fullName, string specialization, string email)
    {
        this.FullName = fullName;
        this.Specialization = specialization;
        this.Email = email;
    }
    public StaffDto()
    {
    }
}