using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text.Json.Serialization;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Shared;


namespace DDDSample1.Domain.Staff;

public class Staff : Entity<StaffId>
{
    public string FirstName { get; private set; }
    public string LastName { get; private set; }
    public string FullName { get; private set; }
    public StaffId LicenseNumber { get; private set; }
    
    public string Specialization { get; private set; }
    public string Email { get;  set; }
    public string PhoneNumber { get;  set; }
    public List<TimeSlots> TimeSlots { get;  set; }
    public Roles Role { get;  set; }
    public bool Active { get; private set; } = true;

    private Staff()
    {
        this.Active = true;
    }

    public Staff(string firstName, string lastName, string specialization,
        string email, string phoneNumber, List<TimeSlots> timeSlots, Roles role, bool active)
    {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.FullName = firstName + " " + lastName;
        this.LicenseNumber = new StaffId(Guid.NewGuid());
        this.Specialization = specialization;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.TimeSlots = timeSlots;
        this.Role = role;
        this.Active = active;
    }

    public Staff(StaffId firstName, string regDtoFirstName, string regDtoLastName, string regDtoEmail, string regDtoPhoneNumber, string regDtoSpecialization, List<TimeSlots> regDtoTimeSlots)
    {
        this.LicenseNumber = firstName;
        this.FirstName = regDtoFirstName;
        this.LastName = regDtoLastName;
        this.FullName = regDtoFirstName + " " + regDtoLastName;
        this.Email = regDtoEmail;
        this.PhoneNumber = regDtoPhoneNumber;
        this.Specialization = regDtoSpecialization;
        this.TimeSlots = regDtoTimeSlots;
    }
    

    public void Update(string firstName, string lastName, string specialization, string email,
        string phoneNumber, List<TimeSlots> timeSlots)
    {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.FullName = firstName + " " + lastName;
        this.Specialization = specialization;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.TimeSlots = timeSlots;
    }
    
    public void ChangeFirstName(string firstName)
    {
        this.FirstName = firstName;
        this.FullName = firstName + " " + this.LastName;
    }
    
    public void ChangeLastName(string lastName)
    {
        this.LastName = lastName;
        this.FullName = this.FirstName + " " + lastName;
    }
    
    public void ChangeSpecialization(string specialization)
    {
        this.Specialization = specialization;
    }
    
    public void ChangeEmail(string email)
    {
        this.Email = email;
    }
    
    public void ChangePhoneNumber(string phoneNumber)
    {
        this.PhoneNumber = phoneNumber;
    }
    
    public void ChangeTimeSlots(List<TimeSlots> timeSlots)
    {
        this.TimeSlots = timeSlots;
    }
    
    
    
    public void MarkAsInative()
    {
        this.Active = false;
    }

    public void MarkAsActive()
    {
        this.Active = true;
    }
    
     public override string ToString()
        {
            var timeSlotsStr = string.Join("; ", TimeSlots.Select((slot, index) => 
                $"slot {index + 1}: {slot.ToString()}"));
            return $"{FullName} ({LicenseNumber}): {timeSlotsStr}";
        }
}

