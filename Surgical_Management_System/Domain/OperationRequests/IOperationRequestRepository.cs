using System.Collections.Generic;
using System.Threading.Tasks;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.Staff;

namespace DDDSample1.Domain.OperationRequests;

public interface IOperationRequestRepository:IRepository<OperationRequest,OperationRequestId>
{
    Task<List<OperationRequest>> GetAllActiveAsync();
    
    Task<OperationRequest> FindByIdAsync(OperationRequestId id);
    Task<List<OperationRequest>> FindByDoctorIdAsync(string id);
    Task<List<OperationRequest>> FindByPatientIdAsync(string id);
    Task<List<OperationRequest>> FindByPriorityAsync(string priority);
    Task<List<OperationRequest>> FindByOperationTypeAsync(string type);
    
}