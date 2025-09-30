using System;

namespace DDDSample1.Domain.OperationRequests;

public class OperationRequestDto
{
    public string Id { get; private set; }
    public string PatientId { get; private set; }
    public string DoctorId { get; private set; }
    
    public string OperationType { get; private set; }
    
    public DateTime OperationDate { get; private set; }
    public string Priority { get; private set; }
    
    private OperationRequestDto()
    {
    }
    
    public OperationRequestDto(string patientId, string doctorId, string operationType, DateTime operationDate, string priority)
    {
        this.PatientId = patientId;
        this.DoctorId = doctorId;
        this.OperationType = operationType;
        this.OperationDate = operationDate;
        this.Priority = priority;
    }
    public OperationRequestDto(string id, string patientId, string doctorId, string operationType, DateTime operationDate, string priority)
    {
        this.Id = id;
        this.PatientId = patientId;
        this.DoctorId = doctorId;
        this.OperationType = operationType;
        this.OperationDate = operationDate;
        this.Priority = priority;
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