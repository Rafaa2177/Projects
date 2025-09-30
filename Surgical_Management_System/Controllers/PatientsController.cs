using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Net;
    using System.Runtime.InteropServices.ComTypes;
    using System.Threading.Tasks;
    using DDDNetCore.Domain.Users;
    using DDDSample1.Domain.OperationRequests;
    using DDDSample1.Domain.Patients;
    using Microsoft.AspNetCore.Authorization;
    using Microsoft.AspNetCore.Mvc;
    using SendGrid;
    using SendGrid.Helpers.Mail;
    namespace DDDSample1.Controllers;
    [Route("api/[controller]")]
    [ApiController]
    public class PatientsController : ControllerBase
    {
        private readonly PatientService _service;
        private readonly EmailService _emailService;
        private readonly OperationRequestService _operationRequestService;
        
        public PatientsController(PatientService service, OperationRequestService opService)
        {
            _service = service;
            _operationRequestService=opService;
        }

        // GET: api/Patients
        
        [HttpGet]
        public async Task<ActionResult<IEnumerable<PatientDto>>> GetAll()
        {
            return await _service.GetAllAsync();
        }

      
        [HttpPost]
        public async Task<IActionResult> AddPatientProfile(CreatingPatientDto dto)
        {
            var createdPatientProfile = await _service.AddAsync(dto);
            
            
            var result = new PatientDto
            {
                FirstName = createdPatientProfile.FirstName,
                LastName = createdPatientProfile.LastName,
                FullName = createdPatientProfile.FullName,
                DateOfBirth = createdPatientProfile.DateOfBirth,
                Email = createdPatientProfile.Email,
                PhoneNumber = createdPatientProfile.PhoneNumber,
                Gender = createdPatientProfile.Gender,
                EmergencyContact = createdPatientProfile.EmergencyContact,
                RecordNumber = createdPatientProfile.RecordNumber,
                MedicalConditions = createdPatientProfile.MedicalConditions,
                Appointments = createdPatientProfile.Appointments
            };

            // Use AsGuid() method to return a Guid for RecordNumber
            return CreatedAtAction(nameof(GetByRecordNumber), new { recordNumber = result.RecordNumber.AsGuid() }, result);
        }

        
        [HttpPut("email/{email}")]
        public async Task<IActionResult> UpdatePatientProfile( string email, [FromBody] CreatingPatientDto patientDto)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var result = await _service.UpdateAsync(email, patientDto);
            if (result == null)
            {
                return Conflict(new { Message = "Patient profile with this email not found or conflicting information." });
            }

            await _service.SendEmailAsync(email);

            return Ok(result);
        }

        
        
        [HttpDelete("email/{email}")]
        public async Task<IActionResult> DeletePatientProfile(string email)
        {
            var isDeleted = await _service.DeleteAsync(email);
            if (!isDeleted) {
                return NotFound(new { Message = "Patient profile not found." });
            }

            return Ok(new { Message = "Patient profile successfully deleted." });
        }


       
        [HttpGet("recordNumber/{recordNumber}")]
        public async Task<ActionResult<PatientDto>> GetByRecordNumber(string recordNumber)
        {
            var patientProfile = await _service.GetByIdAsync(recordNumber);
            if (patientProfile == null)
            {
                return NotFound();
            }
            return Ok(patientProfile);
        }
    
        
        [HttpGet("name/{firstName}/{lastName}")]
        public async Task<ActionResult<IEnumerable<PatientDto>>> GetByName(string firstName, string lastName)
        {
            var patientProfiles = await _service.GetByNameAsync(firstName, lastName);
            if (patientProfiles == null || patientProfiles.Count == 0)
            {
                return NotFound(new { Message = "No patient profiles found with the given name." });
            }
            return Ok(patientProfiles);
        }

             
            [HttpGet("medicalRecordNumber/{medicalRecordNumber}")]
            public async Task<ActionResult<PatientDto>> GetByMedicalRecordNumber(string medicalRecordNumber) {
                var patientProfile = await _service.GetByIdAsync(medicalRecordNumber);
                if (patientProfile == null) {
                    return NotFound(new { Message = "Patient profile not found with the given medical record number." });
                }
                return Ok(patientProfile);
            }
            
           
            [HttpDelete("deletePatientAccount/{authToken}")]
            public async Task<IActionResult> DeletePatientAccount(string authToken)
            {
                try
                {
                    var dto = await _service.isReallyPatient(authToken);
                    if (dto != null)
                    {
                        var res = _operationRequestService.GetByPatientIdAsync(dto.RecordNumber.AsString());
                        Console.WriteLine("TESTE " + res.Result.Count);
                        if (res.Result.Count == 0)
                        {
                            var statusCode = await _service.DeletePatient(authToken);

                            if (statusCode == HttpStatusCode.Accepted)
                            {
                                return Ok($"User deleted successfully.");
                            }
                            return BadRequest($"Error: {statusCode}");
                        }

                        return BadRequest($"Error: Patient has pending Operation Requests");
                    }

                    return BadRequest($"Patient Not found");

                }
                catch (Exception ex)
                {
                    return BadRequest($"Error: {ex.Message}");
                }
            }

       
        [HttpGet("email/{email}")]
        public async Task<ActionResult<PatientDto>> GetByEmail(string email)
        {
            var patientProfile = await _service.GetByEmailAsync(email);
            if (patientProfile == null)
            {
                return NotFound(new { Message = "Patient profile not found with the given email." });
            }
            return Ok(patientProfile);
        } 
        
        [HttpGet("allergy/{email}")]
        public async Task<ActionResult<PatientDto>> GetByAllergy(string email)
        {
            var patientProfiles = await _service.GetAllergiesByEmailAsync(email);
            if (patientProfiles == null)
            {
                return NotFound(new { Message = "No patient profiles found with the given allergy." });
            }
            return Ok(patientProfiles);
        }

        [HttpGet("patientAllergy/{allergy}")]
        public async Task<ActionResult<IEnumerable<PatientDto>>> GetPatientByAllergy(string allergy)
        {
            var patientProfiles = await _service.GetPatientByAllergyAsync(allergy);
            if (patientProfiles == null || patientProfiles.Count == 0)
            {
                return NotFound(new { Message = "No patient profiles found with the given allergy." });
            }
            return Ok(patientProfiles);
        }

        [HttpPut("allergy/{email}/{allergy}")]
        public async Task<IActionResult> UpdateAllergy(string email, string allergy)
        {
            var result = await _service.UpdateAllergyAsync(email, allergy);
            if (result == null)
            {
                return NotFound(new { Message = "Patient profile not found with the given email." });
            }
            return Ok(result);
        }
        
        [HttpGet("patientMedicalCondition/{medicalCondition}")]
        public async Task<ActionResult<IEnumerable<PatientDto>>> GetPatientByMedicalCondition(string medicalCondition)
        {
            var patientProfiles = await _service.GetPatientByMedicalConditionAsync(medicalCondition);
            if (patientProfiles == null || patientProfiles.Count == 0)
            {
                return NotFound(new { Message = "No patient profiles found with the given allergy." });
            }
            return Ok(patientProfiles);
        }
        
        [HttpGet("medicalCondition/{email}")]
        public async Task<ActionResult<PatientDto>> GetByMedicalCondition(string email)
        {
            var patientProfiles = await _service.GetAllMedicalConditionsByEmailAsync(email);
            if (patientProfiles == null)
            {
                return NotFound(new { Message = "No patient profiles found with the given medical condition." });
            }
            return Ok(patientProfiles);
        }
        
        
        [HttpPut("medicalCondition/{email}/{medicalCondition}")]
        public async Task<IActionResult> AddMedicalCondition(string email, string medicalCondition)
        {
            var result = await _service.UpdateMedicalConditionAsync(email, medicalCondition);
            if (result == null)
            {
                return NotFound(new { Message = "Patient profile not found with the given email." });
            }
            return Ok(result);
        }
        
    }