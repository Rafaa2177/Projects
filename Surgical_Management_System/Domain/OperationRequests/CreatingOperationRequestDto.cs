using System;
using System.Collections.Generic;

namespace DDDSample1.Domain.OperationRequests;

public class CreatingOperationRequestDto
{
    public string PatientId { get; set; }
    public string DoctorId { get; set; }
    public string OperationType { get; set; }
    public DateTime OperationDate { get; set; }
    public string Priority { get; set; }
    
    public CreatingOperationRequestDto(string patientId, string doctorId, string operationType, DateTime operationDate, string priority)
    {
        this.PatientId = patientId;
        this.DoctorId = doctorId;
        this.OperationType = operationType;
        this.OperationDate = operationDate;
        this.Priority = priority;
    }

    public CreatingOperationRequestDto()
    {
    }
}