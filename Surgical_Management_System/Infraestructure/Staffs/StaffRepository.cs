using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DDDSample1.Domain.Staff;
using DDDSample1.Infrastructure.Shared;
using Microsoft.EntityFrameworkCore;


namespace DDDSample1.Infrastructure.Staff;

public class StaffRepository :  BaseRepository<Domain.Staff.Staff, StaffId>, IStaffRepository
{
    private readonly DDDSample1DbContext _context;
    public StaffRepository(DDDSample1DbContext context) : base(context.Staffs)
    {
        _context = context;

    }
    
    public async Task<Domain.Staff.Staff> FindByNameAsync(string fullName) {
        return await _context.Staffs
            .FirstOrDefaultAsync(s => s.FullName == fullName);
    }
    
    public async Task<Domain.Staff.Staff> FindBySpecializationAsync(string specialization)
    {
        return await _context.Staffs
            .FirstOrDefaultAsync(s => s.Specialization == specialization);;        
    }

    public async Task<Domain.Staff.Staff> FindByEmailAsync(string email)
    {
        return await _context.Staffs
            .FirstOrDefaultAsync(s => s.Email == email);
    }

    public async Task<Domain.Staff.Staff> FindByLicenseNumberAsync(StaffId licenseNumber)
    {
       return await _context.Staffs
            .FirstOrDefaultAsync(s => s.LicenseNumber == licenseNumber);
    }
    
    public async Task<List<Domain.Staff.Staff>> GetAllActiveAsync()
    {
        return await _context.Staffs
            .Where(s => s.Active)
            .ToListAsync();
    }
    
    public async Task<List<Domain.Staff.Staff>> SearchAsync(string name = null, string email = null, string? specialization = null, int page = 1, int pageSize = 10)
    {
        var query = _context.Staffs.AsQueryable();

        if (!string.IsNullOrWhiteSpace(name))
            query = query.Where(s => s.FullName.Contains(name));

        if (!string.IsNullOrWhiteSpace(email))
            query = query.Where(s => s.Email.Contains(email));

        if (specialization != null && specialization.Any())
            query = query.Where(s => s.Specialization == specialization);
        
        query = query.Where(s => s.Active); 
        
        Console.WriteLine($"Skip: {(page - 1) * pageSize}, Take: {pageSize}");

        return await query.Skip((page - 1) * pageSize).Take(pageSize).ToListAsync();
    }


    public async Task<int> CountAsync(string name = null, string email = null, string? specialization = null)
    {
        var query = _context.Staffs.AsQueryable();

        if (!string.IsNullOrWhiteSpace(name))
            query = query.Where(s => s.FullName.Contains(name));

        if (!string.IsNullOrWhiteSpace(email))
            query = query.Where(s => s.Email.Contains(email));

        if (specialization != null && specialization.Any())
            query = query.Where(s => s.Specialization == specialization);

        query = query.Where(s => s.Active);

        return await query.CountAsync();
    }

    public async Task<bool> IsEmailInUseAsync(string email, Guid? excludeId = null)
    {
        // Traz todos os registros que possuem o email especificado
        var staffs = await _context.Staffs
            .Where(s => s.Email == email)
            .ToListAsync();

        // Verifica a unicidade após carregar os dados em memória
        return staffs.Any(s => excludeId == null || s.LicenseNumber.AsGuid() != excludeId);
    }

    public async Task<bool> IsPhoneNumberInUseAsync(string phoneNumber, Guid? excludeId = null)
    {
        var staffs = await _context.Staffs
            .Where(s => s.PhoneNumber == phoneNumber)
            .ToListAsync();

        return staffs.Any(s => excludeId == null || s.LicenseNumber.AsGuid() != excludeId);
    }

    public async Task<Domain.Staff.Staff> FindByPhoneNumber(string phoneNumber)
    {
        return await _context.Staffs
            .FirstOrDefaultAsync(s => s.PhoneNumber == phoneNumber);
    }

    public async Task<List<Domain.Staff.Staff>> GetAllNonActiveAndActiveAsync()
    {
        return await _context.Staffs.ToListAsync();
    }

}