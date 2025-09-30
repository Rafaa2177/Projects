using System;

namespace DDDSample1.Domain.OperationRequests;

public class UpdatingOperationRequestDto
{
    public string Id { get; set; }
    public string PatientId { get; set; }
    public string DoctorId { get; set; }
    public string OperationType { get; set; }
    public DateTime OperationDate { get; set; }
    public string Priority { get; set; }
}