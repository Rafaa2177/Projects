using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using DDDSample1.Domain.Patients;
using DDDSample1.Domain.Shared;

namespace DDDSample1.Domain.Staff;


public interface IStaffRepository : IRepository<Staff, StaffId>
{
    Task<Staff> FindByNameAsync(string fullName);
    Task<Staff> FindBySpecializationAsync(string specialization);
    Task<Staff> FindByEmailAsync(string email);
    Task<Staff> FindByLicenseNumberAsync(StaffId licenseNumber);
    Task<List<Staff>> GetAllActiveAsync();
    Task<List<Staff>> SearchAsync(string name = null, string email = null, string? specialization = null, int page = 1, int pageSize = 10);
    Task<int> CountAsync(string name = null, string email = null, string? specialization = null);
    Task<bool> IsEmailInUseAsync(string email, Guid? excludeId = null);
    Task<bool> IsPhoneNumberInUseAsync(string phoneNumber, Guid? excludeId = null);
    Task<Staff> FindByPhoneNumber(string phoneNumber);
    Task<List<Staff>> GetAllNonActiveAndActiveAsync();
}