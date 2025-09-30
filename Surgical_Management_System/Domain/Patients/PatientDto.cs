using System;
using System.Collections.Generic;

namespace DDDSample1.Domain.Patients
{

    public class PatientDto
    {
        public PatientId RecordNumber { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string FullName { get; set; }
        public DateTime DateOfBirth { get; set; }
        public string DateOfBirthFormatted => DateOfBirth.ToString("yyyy-MM-dd");
        public Gender Gender { get; set; }
        public string Email { get; set; }
        public long PhoneNumber { get; set; }
        public List<string> MedicalConditions { get; set; }
        public long EmergencyContact { get; set; }
        public string Appointments { get; set; }
        
        public List<string> Allergies { get; set; }

        
        public PatientDto(PatientId recordNumber, string firstName, string lastName, string fullName, DateTime dateOfBirth, Gender gender, string email, long phoneNumber, List<string> medicalConditions, long emergencyContact, string appointments, List<string> allergies)
        {
            RecordNumber = recordNumber; 
            FirstName = firstName;
            LastName = lastName;
            FullName = fullName;
            DateOfBirth = dateOfBirth;
            Gender = gender;
            Email = email;
            PhoneNumber = phoneNumber;
            MedicalConditions = medicalConditions;
            EmergencyContact = emergencyContact;
            Appointments = appointments;
            Allergies = allergies;
        }

        public PatientDto()
        {
        }
        
    }
}