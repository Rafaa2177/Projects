using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Events.Handler;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.User;
using SendGrid;
using SendGrid.Helpers.Mail;

namespace DDDSample1.Domain.Patients
{
    public class PatientService
    {
        private readonly IUnitOfWork _unitOfWork;
        private readonly IPatientRepository _repo;
        private readonly UserService _userService;
        private readonly EmailService _emailService;
        private readonly string _apiKey = "YOUR_SENDGRID_API_KEY";
        public PatientService(IUnitOfWork unitOfWork, IPatientRepository repo, UserService userService, EmailService emailService)
        {
            this._unitOfWork = unitOfWork;
            this._repo = repo;
            this._userService = userService;
            this._emailService = emailService;
        }
        

        public async Task<List<PatientDto>> GetAllAsync()
        {
            var list = await this._repo.GetAllAsync();
            return list.ConvertAll(pat => new PatientDto
            (
                pat.RecordNumber, pat.FirstName, pat.LastName,
                pat.FullName, pat.DateOfBirth, pat.Gender, pat.Email,
                pat.PhoneNumber, pat.MedicalConditions,
                pat.EmergencyContact, pat.Appointments, pat.Allergies
            ));
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

        public async Task<PatientDto> GetByIdAsync(string recordNumber)
        {
            // Cria uma instância de PatientId a partir da string recordNumber
            var patientId = new PatientId(recordNumber);
    
            
            var profile = await this._repo.FindByMedicalRecordNumberAsync(patientId);
            if (profile == null)
                return null;

            return new PatientDto
            {
                FirstName = profile.FirstName,
                LastName = profile.LastName,
                FullName = profile.FullName,
                DateOfBirth = profile.DateOfBirth,
                Email = profile.Email,
                PhoneNumber = profile.PhoneNumber,
                Gender = profile.Gender,
                EmergencyContact = profile.EmergencyContact,
                RecordNumber = profile.RecordNumber,
                MedicalConditions = profile.MedicalConditions,
                Appointments = profile.Appointments,
                Allergies = profile.Allergies
            };
        }

        public async Task<List<string>> GetPatientByAllergyAsync(string allergy)
        {
            var profiles = await this._repo.FindPatientByAllergyAsync(allergy);
            if (profiles == null)
                return null;

            return profiles.ConvertAll(profile => profile.Email);
        }
        
        public async Task<List<string>> GetPatientByMedicalConditionAsync(string medicalCondition)
        {
            var profiles = await this._repo.FindPatientByMedicalConditionAsync(medicalCondition);
            if (profiles == null)
                return null;

            return profiles.ConvertAll(profile => profile.Email);
        }
        
        public async Task<List<string>> GetAllergiesByEmailAsync(string email)
        {
            var profile = await this._repo.FindByEmailAsync(email);
            if (profile == null)
            {
                throw new KeyNotFoundException("Patient profile not found.");
            }
            
            return profile.Allergies;
        }

        
        public async Task<List<string>> GetAllMedicalConditionsByEmailAsync(string email)
        {
            var profile = await this._repo.FindByEmailAsync(email);
            if (profile == null)
            {
                throw new KeyNotFoundException("Patient profile not found.");
            }
            
            return profile.MedicalConditions;
        }

        public async Task<List<PatientDto>> GetByNameAsync(string firstName, string lastName)
        {
            var profiles = await this._repo.FindByNameAsync(firstName, lastName);
            if (profiles == null)
                return null;

            return profiles.ConvertAll(profile => new PatientDto
            {
                FirstName = profile.FirstName,
                LastName = profile.LastName,
                FullName = profile.FullName,
                DateOfBirth = profile.DateOfBirth,
                Email = profile.Email,
                PhoneNumber = profile.PhoneNumber,
                Gender = profile.Gender,
                EmergencyContact = profile.EmergencyContact,
                RecordNumber = profile.RecordNumber,
                MedicalConditions = profile.MedicalConditions,
                Appointments = profile.Appointments,
                Allergies = profile.Allergies
            });
        }

        public async Task<PatientDto> GetByEmailAsync(string email)
        {
            var profile = await this._repo.FindByEmailAsync(email);
            if (profile == null)
                return null;

            return new PatientDto
            {
                FirstName = profile.FirstName,
                LastName = profile.LastName,
                FullName = profile.FullName,
                DateOfBirth = profile.DateOfBirth,
                Email = profile.Email,
                PhoneNumber = profile.PhoneNumber,
                Gender = profile.Gender,
                EmergencyContact = profile.EmergencyContact,
                RecordNumber = profile.RecordNumber,
                MedicalConditions = profile.MedicalConditions,
                Appointments = profile.Appointments,
                Allergies = profile.Allergies
            };
        }
        

        public async Task<PatientDto> AddAsync(CreatingPatientDto dto)
        {
            var profile = new Patient(dto.FirstName, dto.LastName, dto.FullName, dto.DateOfBirth, dto.Gender,
                dto.Email, dto.PhoneNumber, dto.MedicalConditions, dto.EmergencyContact, dto.AppointmentHistory, dto.Allergies);

            await this._repo.AddAsync(profile);
            await this._unitOfWork.CommitAsync();

            return new PatientDto
            {
                FirstName = profile.FirstName,
                LastName = profile.LastName,
                FullName = profile.FullName,
                DateOfBirth = profile.DateOfBirth,
                Email = profile.Email,
                PhoneNumber = profile.PhoneNumber,
                Gender = profile.Gender,
                EmergencyContact = profile.EmergencyContact,
                RecordNumber = profile.RecordNumber,
                MedicalConditions = profile.MedicalConditions,
                Appointments = profile.Appointments,
                Allergies = profile.Allergies
            };
        }

        public async Task<PatientDto> UpdateAllergyAsync(string email, string newAllergy)
        {
            var profile = await this._repo.FindByEmailAsync(email);

            if (profile == null)
            {
                throw new KeyNotFoundException("Patient profile not found.");
            }

            // Adiciona a nova alergia à lista de alergias do paciente
            if (!profile.Allergies.Contains(newAllergy))
            {
                profile.Allergies.Add(newAllergy);
            }

            await this._unitOfWork.CommitAsync();

            return new PatientDto
            {
                FirstName = profile.FirstName,
                LastName = profile.LastName,
                FullName = profile.FullName,
                DateOfBirth = profile.DateOfBirth,
                Email = profile.Email,
                PhoneNumber = profile.PhoneNumber,
                Gender = profile.Gender,
                EmergencyContact = profile.EmergencyContact,
                RecordNumber = profile.RecordNumber,
                MedicalConditions = profile.MedicalConditions,
                Appointments = profile.Appointments,
                Allergies = profile.Allergies
            };
        }
        public async Task<PatientDto> UpdateAsync(string email, CreatingPatientDto dto)
        {
            var profile = await this._repo.FindByEmailAsync(email);

            if (profile == null)
            {
                throw new KeyNotFoundException("Patient profile not found.");
            }

            // Chama o método Update com os parâmetros do DTO
            profile.Update(dto.FirstName, dto.LastName, dto.FullName, dto.DateOfBirth, dto.Email, dto.PhoneNumber,
                dto.Gender, dto.EmergencyContact,  dto.AppointmentHistory);

            await this._unitOfWork.CommitAsync();

            return new PatientDto
            {
                FirstName = profile.FirstName,
                LastName = profile.LastName,
                FullName = profile.FullName,
                DateOfBirth = profile.DateOfBirth,
                Email = profile.Email,
                PhoneNumber = profile.PhoneNumber,
                Gender = profile.Gender,
                EmergencyContact = profile.EmergencyContact,
                RecordNumber = profile.RecordNumber,
                MedicalConditions = profile.MedicalConditions,
                Appointments = profile.Appointments,
                Allergies = profile.Allergies
            };
        }


    

        public async Task<bool> DeleteAsync(string email)
        {
            var profile = await this._repo.FindByEmailAsync(email);  // Usamos o email para buscar o paciente.
            if (profile == null)
            {
                return false;
            }

            this._repo.Remove(profile);
            await this._unitOfWork.CommitAsync();

            return true;
        }


        private bool IsValidEmail(string email)
        {
            return email.Contains("@") && email.Contains(".");
        }

        private bool IsValidPhoneNumber(long phoneNumber)
        {
            var phoneString = phoneNumber.ToString();
            return phoneString.Length == 9 && phoneString.StartsWith("9");
        }

        public async Task<PatientDto> isReallyPatient(string authToken)
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


            if (roles.Contains("Patient"))
            {
                PatientDto dto =await  GetByEmailAsync(email);
                if (dto != null)
                {
                    return dto;
                }
            }

            return null;
        }
        public async Task<HttpStatusCode> DeletePatient(String authToken)
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

                bool res = await DeleteAsync(email: email);
                if (res)
                {
                    var managementApiToken = await _userService.GetManagementApiTokenAsync();
                    var ai = await _userService.DeleteUser(userId,managementApiToken);
                    if (ai == HttpStatusCode.NoContent)
                    {
                        var client = new SendGridClient(_apiKey);
                        // Configure o e-mail de remetente (necessário que seja um e-mail verificado no SendGrid)
                        var from = new EmailAddress("franciscasteixeira07@gmail.com", "Francisca Teixeira");
                        var toEmail = new EmailAddress(email);
                        var subject = "Informação";
                        var body = "A sua conta foi apagada com sucesso do HealthCare System";
                        var msg = MailHelper.CreateSingleEmail(from, toEmail, subject, body, body);
                        var response = await client.SendEmailAsync(msg);
                        return response.StatusCode;
                    }

                    return HttpStatusCode.BadRequest;
                
            
                return HttpStatusCode.NotFound;
            }
            return HttpStatusCode.Forbidden;
        }
        
        public async Task<PatientDto> UpdateMedicalConditionAsync(string email, string newCondition)
        {
            var profile = await this._repo.FindByEmailAsync(email);
            if (profile == null)
            {
                throw new KeyNotFoundException("Patient profile not found.");
            }

            // Initialize the list if null
            if (profile.MedicalConditions == null)
            {
                profile.MedicalConditions = new List<string>();
            }

            // Add the new condition if it doesn't exist
            if (!profile.MedicalConditions.Contains(newCondition))
            {
                profile.MedicalConditions.Add(newCondition);
            }

            await this._unitOfWork.CommitAsync();

            // Return updated DTO
            return new PatientDto
            {
                FirstName = profile.FirstName,
                LastName = profile.LastName,
                FullName = profile.FullName,
                DateOfBirth = profile.DateOfBirth,
                Email = profile.Email,
                PhoneNumber = profile.PhoneNumber,
                Gender = profile.Gender,
                EmergencyContact = profile.EmergencyContact,
                RecordNumber = profile.RecordNumber,
                MedicalConditions = profile.MedicalConditions,
                Appointments = profile.Appointments,
                Allergies = profile.Allergies
            };
        }
    }
}