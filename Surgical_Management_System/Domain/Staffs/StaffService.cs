using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Patients;
using DDDSample1.Domain.Products;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.User;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Identity;
using SendGrid.Helpers.Mail;

namespace DDDSample1.Domain.Staff;

public class StaffService
{
    private readonly IUnitOfWork _unitOfWork;
    private readonly IStaffRepository _repo;
    private readonly UserService _userService;
    private readonly HttpClient _httpClient;
    private readonly EmailService _emailService;
    public StaffService(IUnitOfWork unitOfWork, IStaffRepository repo, UserService service, HttpClient httpClient, EmailService emailService)
    {
        this._unitOfWork = unitOfWork;
        this._repo = repo;
        this._userService = service;
        this._httpClient = httpClient;
        this._emailService = emailService;
    }
    
    public async Task<List<StaffDto>> GetAllNonActiveAndActiveAsync()
    {
        var list = await this._repo.GetAllNonActiveAndActiveAsync();
        
        List<StaffDto> listDto = list.ConvertAll<StaffDto>(pat => new StaffDto
        (
            pat.FirstName, pat.LastName,
            pat.FullName,pat.LicenseNumber,
            pat.Specialization ,pat.Email,
            pat.PhoneNumber, pat.TimeSlots,pat.Role, pat.Active
        ));
        return listDto;
    }
    
    public async Task<List<StaffDto>> GetAllAsync()
    {
        var list = await this._repo.GetAllActiveAsync();
        
        List<StaffDto> listDto = list.ConvertAll<StaffDto>(pat => new StaffDto
        (
            pat.FirstName, pat.LastName,
            pat.FullName,pat.LicenseNumber,
            pat.Specialization, pat.Email,
            pat.PhoneNumber, pat.TimeSlots,pat.Role, pat.Active
        ));
        return listDto;
    }
    
    public async Task SendEmailAsync(string email)
    {
        var fromEmail = new EmailAddress("franciscasteixeira07@gmail.com", "Francisca Teixeira");
        var toEmail = new EmailAddress(email);
        var subject = "Conta atualizada";
        var body = "O utilizador " + email +
                   " teve a conta atualizada.";
        await _emailService.SendEmail(fromEmail, toEmail, subject, body);
    }
    
    public async Task<StaffDto> GetByIdAsync(StaffId LicenseNumber)
    {
        var staff = await this._repo.FindByLicenseNumberAsync(LicenseNumber);
        
        if(staff == null)
            return null;
        
        return new StaffDto(staff.FirstName, staff.LastName, staff.FullName, 
            staff.LicenseNumber, staff.Specialization, 
            staff.Email, staff.PhoneNumber, staff.TimeSlots,staff.Role, staff.Active);
    }

    public async Task<StaffDto> GetByNameAsync(string fullName)
    {
        var staff = await this._repo.FindByNameAsync(fullName);
       
        if (staff == null)
        {
            return null;
        }
        
        return new StaffDto(staff.FirstName,staff.LastName,staff.FullName,staff.LicenseNumber, staff.Specialization, staff.Email, staff.PhoneNumber, staff.TimeSlots,staff.Role, staff.Active);
    }
   
    public async Task<StaffDto> GetByEmailAsync(string email)
    {
        var staff = await this._repo.FindByEmailAsync(email);
        
        if (staff == null)
        {
            return null;
        }
        
        return new StaffDto(staff.FirstName,staff.LastName,staff.FullName,staff.LicenseNumber, staff.Specialization, staff.Email, staff.PhoneNumber, staff.TimeSlots,staff.Role, staff.Active);
    }
    
    public async Task<StaffDto> GetBySpecializationAsync(string specialization)
    {
        var staff = await this._repo.FindBySpecializationAsync(specialization);
        if (staff == null)
        {
            return null;
        }
        
        return new StaffDto(staff.FirstName,staff.LastName,staff.FullName,staff.LicenseNumber, staff.Specialization, staff.Email, staff.PhoneNumber, staff.TimeSlots,staff.Role, staff.Active);
    }
   
    public async Task<StaffDto> AddAsync(CreatingStaffDto dto)
    {
       /* var auth0User = await CheckUserInAuth0(dto.Email);
        string auth0UserId = null;

        if (auth0User != null)
        {
            auth0UserId = auth0User.user_id;
            Console.WriteLine($"User already exists in Auth0 with ID: {auth0UserId}");
        }*/

        var existingStaffByEmail = await _repo.FindByEmailAsync(dto.Email);
        var existingStaffByPhone = await _repo.FindByPhoneNumber(dto.PhoneNumber);
        if (existingStaffByEmail != null && existingStaffByPhone != null)
        {
            throw new BusinessRuleValidationException("This email and phone number are already in use.");
        }
        
        if (existingStaffByEmail != null)
        {
            throw new BusinessRuleValidationException("This email is already in use.");
        }
        
       
        if (existingStaffByPhone != null)
        {
            throw new BusinessRuleValidationException("This phone number is already in use.");
        }
        
        var staff = new Staff(dto.FirstName, dto.LastName, 
            dto.Specialization, dto.Email, 
            dto.PhoneNumber, dto.TimeSlots, dto.Role, true);
        
        await this._repo.AddAsync(staff);
        await this._unitOfWork.CommitAsync();

        return new StaffDto(staff.FirstName, staff.LastName, staff.FullName, 
            staff.LicenseNumber, staff.Specialization, 
            staff.Email, staff.PhoneNumber, staff.TimeSlots,staff.Role, staff.Active);    
    }


    public async Task<StaffDto> UpdateAsync(string email, string specialization, string phoneNumber, List<TimeSlots> timeSlots, string firstName = null, string lastName = null)
    {
        if (string.IsNullOrWhiteSpace(email))
        {
            throw new ArgumentNullException(nameof(email), "Email cannot be null or empty.");
        }

        if (specialization == null && phoneNumber == null && timeSlots == null)
        {
            throw new ArgumentNullException("At least one field must be filled.");
        }

        // Busca pelo email
        var staff = await this._repo.FindByEmailAsync(email);

        if (staff == null)
        {
            return null; 
        }

        if ((firstName != null && firstName != staff.FirstName) || (lastName != null && lastName != staff.LastName))
        {
            throw new BusinessRuleValidationException("Editing the name is not allowed.");
        }

        if (specialization != null)
        {
            staff.ChangeSpecialization(specialization);
        }

        if (!string.IsNullOrWhiteSpace(phoneNumber) && phoneNumber != staff.PhoneNumber)
        {
            var phoneNumberInUse = await _repo.IsPhoneNumberInUseAsync(phoneNumber, staff.LicenseNumber.AsGuid());
            if (phoneNumberInUse)
            {
                throw new BusinessRuleValidationException("The phone number provided is already in use.");
            }
            staff.ChangePhoneNumber(phoneNumber);
        }

        if (timeSlots != null && timeSlots.Any())
        {
            staff.ChangeTimeSlots(timeSlots);
        }

        await this._unitOfWork.CommitAsync();

        return new StaffDto(staff.FirstName, staff.LastName, staff.FullName, 
            staff.LicenseNumber, staff.Specialization, staff.Email, staff.PhoneNumber, staff.TimeSlots, staff.Role, staff.Active);
    }

    public async Task<StaffDto> InactivateAsync(string email)
    {
        var staff = await this._repo.FindByEmailAsync(email);
        
        if (staff == null)
            return null;

        if (staff.Active)
        {
            staff.MarkAsInative();
        }
        else
        {
            staff.MarkAsActive();
        }
            
        await this._unitOfWork.CommitAsync();

        return new StaffDto(staff.FirstName, staff.LastName, staff.FullName, 
            staff.LicenseNumber, staff.Specialization, 
            staff.Email, staff.PhoneNumber, staff.TimeSlots,staff.Role, staff.Active);
    }

    public async Task<bool> DeleteAsync(Guid id)
    {
        Staff staff = null;

        if (id != null)
        {
            staff = await this._repo.FindByLicenseNumberAsync(new StaffId(id)); 
        } 
        
        if (staff == null)
            return false;   
        
        if (staff.Active)
            throw new BusinessRuleValidationException("It is not possible to delete an active staff.");

        this._repo.Remove(staff);
        await this._unitOfWork.CommitAsync();

        return true;
    }
    
    public async Task<(List<StaffDto> Results, int TotalCount)> SearchAsync(string name = null, string email = null, string? specialization = null, int page = 1, int pageSize = 10)
    {
        var staffList = await _repo.SearchAsync(name, email, specialization, page, pageSize);
        var totalCount = await _repo.CountAsync(name, email, specialization);

        var staffDtos = staffList.Select(s => new StaffDto(
            s.FirstName, s.LastName, s.FullName, s.LicenseNumber, 
            s.Specialization, s.Email, s.PhoneNumber, s.TimeSlots,s.Role, s.Active)).ToList();

        return (staffDtos, totalCount);
    }

}

