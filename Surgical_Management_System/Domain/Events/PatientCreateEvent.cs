using System;
using System.Threading.Tasks;
using DDDSample1.Domain.Patients;
using Microsoft.Extensions.Logging;

namespace DDDSample1.Domain.Events;


public class PatientCreateEvent
{
    public Guid PatientProfileId { get; set; }
    public string FirstName { get; set; }
    public string LastName { get; set; }

    public string FullName { get; set; }
    public DateTime DateOfBirth { get; set; }
    public string ContactInformation { get; set; }
    public Gender Gender { get; set; }
    public string EmergencyContact { get; set; }
    public string MedicalRecordNumber { get; set; }
    public DateTime CreatedAt { get; set; }


    public PatientCreateEvent(Guid patientProfileId, string firstName, string lastName, string fullName, DateTime dateOfBirth, string contactInformation, Gender gender, string emergencyContact, string medicalRecordNumber, DateTime createdAt)
    {
        PatientProfileId = patientProfileId;
        FirstName = firstName;
        LastName = lastName;
        FullName = fullName;
        DateOfBirth = dateOfBirth;
        ContactInformation = contactInformation;
        Gender = gender;
        EmergencyContact = emergencyContact;
        MedicalRecordNumber = medicalRecordNumber;
        CreatedAt = createdAt;
    }
}
