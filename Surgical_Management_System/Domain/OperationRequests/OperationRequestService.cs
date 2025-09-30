using System.Threading.Tasks;
using DDDSample1.Domain.Patients;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.Staff;
using System;
using System.Collections.Generic;
using System.Linq;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Net;

namespace DDDSample1.Domain.OperationRequests;

public class OperationRequestService
{
    private readonly IOperationRequestRepository _operationRequestRepository;
    private readonly IUnitOfWork _unitOfWork;
    public OperationRequestService(IOperationRequestRepository operationRequestRepository, IUnitOfWork unitOfWork)
    {
        _operationRequestRepository = operationRequestRepository;
        _unitOfWork = unitOfWork;
    }
    
    public async Task<OperationRequestDto> AddAsync(CreatingOperationRequestDto dto)
    {
        var profile = new OperationRequest(dto.PatientId, dto.DoctorId, dto.OperationType, dto.OperationDate, dto.Priority);

        await this._operationRequestRepository.AddAsync(profile);
        await this._unitOfWork.CommitAsync();

        return new OperationRequestDto(
            profile.DoctorId,
            profile.PatientId,
            profile.OperationType,
            profile.OperationDate,
            profile.Priority
        );
        {
        };
    }
    public async Task<List<OperationRequestDto>> GetAllAsync()
    {
        var list = await this._operationRequestRepository.GetAllActiveAsync();
        
        List<OperationRequestDto> listDto = list.ConvertAll<OperationRequestDto>(pat => new OperationRequestDto
        (
            pat.Id.AsString(),pat.PatientId,pat.DoctorId,pat.OperationType,pat.OperationDate,pat.Priority
        ));
        return listDto;
    }
    
    public async Task<OperationRequestDto> GetByIdAsync(OperationRequestId id )
    {  var operation = await this._operationRequestRepository.FindByIdAsync(id);
        if (operation == null)
            return null;

        return new OperationRequestDto(
            operation.DoctorId,
            operation.PatientId,
            operation.OperationType,
            operation.OperationDate,
            operation.Priority
        );
    }
    

    public async Task<List<OperationRequestDto>> GetByDoctorIdAsync(string id)
    {
        var list = await this._operationRequestRepository.FindByDoctorIdAsync(id);
        
        List<OperationRequestDto> listDto = list.ConvertAll<OperationRequestDto>(pat => new OperationRequestDto
        (
            pat.PatientId,pat.DoctorId,pat.OperationType,pat.OperationDate,pat.Priority
        ));
        return listDto;    }
    
    public async Task<List<OperationRequestDto>> GetByPatientIdAsync(string id)
    {
        var list = await this._operationRequestRepository.FindByPatientIdAsync(id);
        
        List<OperationRequestDto> listDto = list.ConvertAll<OperationRequestDto>(pat => new OperationRequestDto
        (
            pat.PatientId,pat.DoctorId,pat.OperationType,pat.OperationDate,pat.Priority
        ));
        return listDto;    }
    
    public async Task<List<OperationRequestDto>> GetByPriorityAsync(string priority)
    {
        var list = await this._operationRequestRepository.FindByPriorityAsync(priority);
        
        List<OperationRequestDto> listDto = list.ConvertAll<OperationRequestDto>(pat => new OperationRequestDto
        (
            pat.PatientId,pat.DoctorId,pat.OperationType,pat.OperationDate,pat.Priority
        ));
        return listDto;    }
    
    public async Task<List<OperationRequestDto>> GetByOperationTypeAsync(string type)
    {
        var list = await this._operationRequestRepository.FindByOperationTypeAsync(type);
        
        List<OperationRequestDto> listDto = list.ConvertAll<OperationRequestDto>(pat => new OperationRequestDto
        (
            pat.PatientId,pat.DoctorId,pat.OperationType,pat.OperationDate,pat.Priority
        ));
        return listDto;    }
    
     public async  Task<HttpStatusCode> UpdateAsync(string docId,string authToken, UpdatingOperationRequestDto dto)
    {
        
        
        var handler = new JwtSecurityTokenHandler();
        var jwtToken = handler.ReadJwtToken(authToken);
        var email = jwtToken?.Claims
            .FirstOrDefault(c => c.Type == "https://management-system.com/email")?.Value;
        var roles = jwtToken.Claims
            .Where(c => c.Type == "https://management-system.com/roles")
            .Select(c => c.Value)
            .ToList();
        var userId = jwtToken?.Claims
            .FirstOrDefault(c => c.Type == "sub")?.Value;

        if (roles.Contains("Doctor"))
        {
            var profile = await this._operationRequestRepository.GetByIdAsync(new OperationRequestId(dto.Id));

            if (profile == null)
                return HttpStatusCode.NotFound;
            if (profile.DoctorId != docId)
                return HttpStatusCode.Forbidden;
            if (dto.PatientId != null)
                profile.UpdatePatientId(dto.PatientId);
            if (dto.DoctorId != null)
                profile.UpdateDoctorId(dto.DoctorId);
            if (dto.OperationType != null)
                profile.UpdateOperationType(dto.OperationType);
            if (dto.OperationDate != null)
                profile.UpdateOperationDate(dto.OperationDate);
            if (dto.Priority != null)
                profile.UpdatePriority(dto.Priority);

            await this._unitOfWork.CommitAsync();
            return HttpStatusCode.OK;
        }

        return HttpStatusCode.Forbidden;

    }
    public async Task<HttpStatusCode> RemoveAsync(string docId, string authToken, OperationRequestId id)
    {
        var handler = new JwtSecurityTokenHandler();
        var jwtToken = handler.ReadJwtToken(authToken);
        var email = jwtToken?.Claims
            .FirstOrDefault(c => c.Type == "https://management-system.com/email")?.Value;
        var roles = jwtToken.Claims
            .Where(c => c.Type == "https://management-system.com/roles")
            .Select(c => c.Value)
            .ToList();
        var userId = jwtToken?.Claims
            .FirstOrDefault(c => c.Type == "sub")?.Value;
        if (roles.Contains("Doctor"))
        {
            
            var profile = await this._operationRequestRepository.GetByIdAsync(id);
            
            if (profile == null)
                return HttpStatusCode.NotFound;
            if (profile.DoctorId != docId)
                return HttpStatusCode.Forbidden;
            this._operationRequestRepository.Remove(profile);
            await this._unitOfWork.CommitAsync();

            return HttpStatusCode.OK;
        }

        return HttpStatusCode.Forbidden;
    }
}