using System;
using System.Collections.Generic;

namespace DDDSample1.Domain.Patients;


public class CreatingPatientDto
{
    
    
    public string FirstName { get; set; }
    public string LastName { get; set; } 
    public string FullName { get; set;}
    public DateTime DateOfBirth { get; set; }
    public string Email { get; set; }
    public long PhoneNumber { get; set; }
    public Gender Gender { get; set; }
    public long EmergencyContact { get; set; }
    public string AppointmentHistory { get; set; }
    public List<string> MedicalConditions { get; set; }
    public List<string> Allergies { get; set; }
}

