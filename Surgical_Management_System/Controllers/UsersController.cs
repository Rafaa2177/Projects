using System;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Net;
using System.Threading.Tasks;
using DDDSample1.Domain.Staff;
using DDDSample1.Domain.User;
using Microsoft.AspNetCore.Authorization;

namespace DDDSample1.Controllers;


[Route("api/[controller]")]
[ApiController]
public class UsersController : ControllerBase
{
    private readonly UserService _service;

    public UsersController(UserService service)
    {
        _service = service;
    }
    
    [HttpGet]
    public async Task<ActionResult<IEnumerable<UserDto>>> GetAll()
    {
        return await _service.GetAllAsync();
    } 
    
    
    [HttpPost]
    public async Task<IActionResult> Register(CreatingUserDto regDto)
    {
        try
        {
            var token = await _service.GetManagementApiTokenAsync();
            var answer = await _service.CreateUserAsAdmin(token,regDto);
            if (answer == HttpStatusCode.Created)
            {
                var result = await _service.SendPasswordResetEmail(regDto.email);
                if (result == HttpStatusCode.OK)
                {
                    var role = await _service.GetRoleIdByNameAsync(regDto.role, token);
                    var userId = await _service.GetUserIdByEmailAsync(regDto.email, token);
                    
                    var result1 = await _service.AddUserAsync(regDto);
                    Console.WriteLine(result1);
                    await _service.AddRoleToUserAsync(userId, role, token);
                    return Ok("User devidamente Registado, verifique o email registado");
                }

                return BadRequest("Algo correu mal ao enviar o email de reset :("); 
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
    
    
}