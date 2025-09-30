using System;

namespace DDDSample1.Domain.Events;


public class PatientDeleteEvent
{
    public Guid PatientProfileId
    {
        get;
    }

    public DateTime Delete
    {
        get;
        
    }

    public PatientDeleteEvent(Guid patientProfile, DateTime delete)
    {
        PatientProfileId = patientProfile;
        Delete = Delete;
    }
}
