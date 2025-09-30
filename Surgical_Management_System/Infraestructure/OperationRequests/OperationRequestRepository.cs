using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DDDSample1.Domain.OperationRequests;
using DDDSample1.Infrastructure.Shared;
using Microsoft.EntityFrameworkCore;

namespace DDDSample1.Infrastructure.OperationRequests;

public class OperationRequestRepository : BaseRepository<OperationRequest,OperationRequestId> , IOperationRequestRepository
{
    private readonly DDDSample1DbContext _context;
    public OperationRequestRepository(DDDSample1DbContext context) : base(context.OperationRequests)
    {
        _context = context;

    }
    
    public async Task<List<OperationRequest>> GetAllActiveAsync()
    {
        return await _context.OperationRequests.ToListAsync();
    }

    public async Task<OperationRequest> FindByIdAsync(OperationRequestId id)
    {
        return await _context.OperationRequests.FirstOrDefaultAsync(o => o.Id == id);
    }

    public async Task<List<OperationRequest>> FindByDoctorIdAsync(string id)
    {
        return await _context.OperationRequests
            .Where(o => o.DoctorId == id)
            .ToListAsync();
    }

    public async Task<List<OperationRequest>> FindByPatientIdAsync(string id)
    {
        return await _context.OperationRequests
            .Where(o => o.PatientId == id)
            .ToListAsync();    }

    public async Task<List<OperationRequest>> FindByPriorityAsync(string priority)
    {
        return await _context.OperationRequests
            .Where(o => o.Priority == priority)
            .ToListAsync();    }

    public async Task<List<OperationRequest>> FindByOperationTypeAsync(string type)
    {
        return await _context.OperationRequests
            .Where(o => o.OperationType == type)
            .ToListAsync();    }
}