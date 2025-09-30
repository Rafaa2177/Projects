using System;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Patients;
using DDDSample1.Domain.User;
using DDDSample1.Domain.Users;
using Microsoft.AspNetCore.Mvc;
using SendGrid;
using SendGrid.Helpers.Mail;

namespace DDDSample1.Controllers;
[ApiController]
public class LoginController:ControllerBase
{
    private readonly UserService _service;
    private readonly PatientService _patientService;
    public LoginController(UserService service,PatientService patientService)
    {
        _service = service ?? throw new ArgumentNullException(nameof(service));
        _patientService = patientService ?? throw new ArgumentNullException(nameof(patientService));
    }
    [HttpPost("login")]
    public async Task<IActionResult> Login(LoginUserDto loginDto) {
        
        try {
            var (accessToken,roles) = await _service.GetUserToken(loginDto);
            return Ok(new {
                AccessToken = accessToken,
                Roles = roles
            });
        } catch (Exception ex) {
            
            
            if (ex.Message.Contains("too_many_attempts")) {
                
                
                await _service.SendEmailToAdminsAsync(loginDto.Email);
                return Ok("Conta bloqueada, verifique o email registado");
                

            }
            return Unauthorized(new {
                Message = "Login failed.",
                Details = ex.Message
            });
        }
    }
    [HttpPost("registerPatient")]
    public async Task<IActionResult> RegisterPatient(LoginUserDto regDto)
    {
        try
        {
            PatientDto patient = await _patientService.GetByEmailAsync(regDto.Email);
            if (patient == null)
            {
                return BadRequest("Patient not found");
            }
            var answer = await _service.RegisterPatient(regDto);
            if (answer == HttpStatusCode.OK)
            {
                var accessToken = await _service.GetManagementApiTokenAsync();
                var roleId = await _service.GetRoleIdByNameAsync("Patient",accessToken);
                var userId = await _service.GetUserIdByEmailAsync(regDto.Email, accessToken);
                await _service.AddRoleToUserAsync(userId, roleId, accessToken);
                
                
                return Ok("Patient Registered");
            }
            else
            {
                return BadRequest("Erro ao registar o utilizador");
            }
            
        }
        catch (Exception ex)
        {
           
            return Unauthorized(new
            {
                Message = "Login failed.",
                Details = ex.Message
            });
        }
    }

    
    
    [HttpGet("getrole")]
    public async Task<IActionResult> getRole(RoleDto role)
    {
        try
        {
            var accessToken = await _service.GetManagementApiTokenAsync();
            var answer = await _service.GetRoleIdByNameAsync(role.Role,accessToken);
            return Ok("Role ID: " + answer);
        }
        catch (Exception ex)
        {
            return Unauthorized(new
            {
                Message = "Login failed.",
                Details = ex.Message
            });
        }
    }
    
    
}