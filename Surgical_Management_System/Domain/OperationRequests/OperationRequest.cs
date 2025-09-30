using System;
using System.ComponentModel.DataAnnotations;
using DDDSample1.Domain.Shared;

namespace DDDSample1.Domain.OperationRequests;

public class OperationRequest : Entity<OperationRequestId>
{
    
    public string PatientId { get; private set; }
    public string DoctorId { get; private set; }
    
    public string OperationType { get; private set; }
    public OperationRequestId Id { get; private set; }
    public DateTime OperationDate { get; private set; }
    public string Priority { get; private set; }
    
    private OperationRequest()
    {
    }
    
    public OperationRequest(string patientId, string doctorId, string operationType, DateTime operationDate, string priority)
    {
        if (patientId == null)
        {
            throw new ArgumentException("PatientId cannot be null", nameof(patientId));
        }

        if (doctorId == null)
        {
            throw new ArgumentException("DoctorId cannot be null", nameof(doctorId));
        }
        this.PatientId = patientId;
        this.DoctorId = doctorId;
        this.OperationType = operationType;
        this.OperationDate = operationDate;
        this.Priority = priority;
        this.Id = new OperationRequestId(Guid.NewGuid());
    }
    public void UpdatePatientId(string patientId)
    {
        this.PatientId = patientId;
    }

    public void UpdateDoctorId(string doctorId)
    {
        this.DoctorId = doctorId;
    }

    public void UpdateOperationType(string operationType)
    {
        this.OperationType = operationType;
    }

    public void UpdateOperationDate(DateTime operationDate)
    {
        this.OperationDate = operationDate;
    }

    public void UpdatePriority(string priority)
    {
        this.Priority = priority;
    }
    
}