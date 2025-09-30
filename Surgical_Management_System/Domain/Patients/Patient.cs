using System;
using System.Collections.Generic;
using DDDSample1.Domain.Shared;

namespace DDDSample1.Domain.Patients
{
    public class Patient : Entity<PatientId>
    {
        public string FirstName { get; private set; }
        public string LastName { get; private set; }
        public string FullName { get; private set; }
        public DateTime DateOfBirth { get; private set; }
        public string DateOfBirthFormatted { get; private set; }
        public Gender Gender { get; private set; }
        public PatientId RecordNumber { get; private set; }
        public string Email { get; private set; }
        public long PhoneNumber { get; private set; }
        public List<string> MedicalConditions { get;  set; }
        
        public List<string> Allergies { get; private set; }
        public long EmergencyContact { get; private set; }
        public string Appointments { get; private set; }

        private Patient()
        {
        }

        public Patient(string firstName, string lastName, string fullName, DateTime dateOfBirth, Gender gender,
            string email, long phoneNumber, List<string> medicalConditions, long emergencyContact, string appointments, List<string> allergies)
        {
            if (string.IsNullOrWhiteSpace(firstName) || firstName.Length > 100)
                throw new ArgumentException("First name is required and must be between 1 and 100 characters.");

            if (string.IsNullOrWhiteSpace(lastName) || lastName.Length > 100)
                throw new ArgumentException("Last name is required and must be between 1 and 100 characters.");

            if (string.IsNullOrWhiteSpace(email) || !IsValidEmail(email))
                throw new ArgumentException("Email is required and must be a valid email address.");

            /*if (!IsValidPhoneNumber(phoneNumber))
                throw new ArgumentException(
                    "The contact information is mandatory and must start with 9 and have 9 digits.");
                    */
                    


            if (dateOfBirth > DateTime.Now)
                throw new ArgumentException("The date of birth must be in the past.");

            this.FirstName = firstName;
            this.LastName = lastName;
            this.FullName = fullName;
            this.DateOfBirth = dateOfBirth;
            this.DateOfBirthFormatted = dateOfBirth.ToString("yyyy-MM-dd");
            this.Gender = gender;
            this.RecordNumber = new PatientId(Guid.NewGuid());
            this.Email = email;
            this.PhoneNumber = phoneNumber;
            this.MedicalConditions = medicalConditions;
            this.EmergencyContact = emergencyContact;
            this.Appointments = appointments;
            this.Allergies = allergies;
        }
        
      
public void Update(string firstName = null, string lastName = null, string fullname = null, DateTime? dateOfBirth = null,
                   string email = null, long? contactInformation = null, Gender? gender = null,
                   long? emergencyContact = null,
                   string appointmentHistory = null)
{
    // Verificações para FirstName
    if (!string.IsNullOrWhiteSpace(firstName) && firstName.Length <= 100)
    {
        this.FirstName = firstName;
    }

    // Verificações para LastName
    if (!string.IsNullOrWhiteSpace(lastName) && lastName.Length <= 100)
    {
        this.LastName = lastName;
    }

    // Verificações para FullName
    if (!string.IsNullOrWhiteSpace(fullname))
    {
        this.FullName = fullname;
    }

    // Verificações para DateOfBirth
    if (dateOfBirth.HasValue && dateOfBirth.Value <= DateTime.Now)
    {
        this.DateOfBirth = dateOfBirth.Value;
        this.DateOfBirthFormatted = dateOfBirth.Value.ToString("yyyy-MM-dd");
    }

    // Verificações para Email
    if (!string.IsNullOrWhiteSpace(email) && IsValidEmail(email))
    {
        this.Email = email;
    }

    // Atualiza PhoneNumber somente se contactInformation tiver um valor válido
    if (contactInformation.HasValue && contactInformation.Value > 0)
    {
        this.PhoneNumber = contactInformation.Value;
    }

    // Verificações para Gender
    if (gender.HasValue)
    {
        this.Gender = gender.Value;
    }

    // Verificações para EmergencyContact
    if (emergencyContact.HasValue && emergencyContact.Value > 0)
    {
        this.EmergencyContact = emergencyContact.Value;
    }

    // Verificações para Appointments
    if (!string.IsNullOrWhiteSpace(appointmentHistory))
    {
        this.Appointments = appointmentHistory;
    }
    
}
// Método para validação de e-mail
private bool IsValidEmail(string email)
{
    return email.Contains("@") && email.Contains(".");
}




    }
}