using System;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.Staff;
using DDDSample1.Domain.User;

namespace DDDSample1.Controllers
{

    [Route("api/[controller]")]
    [ApiController]
    public class StaffsController : ControllerBase
    {
        private readonly StaffService _service;
        private readonly UserService _userService;
        private readonly EmailService _emailService;


        public StaffsController(StaffService service, UserService userService, EmailService emailService)
        {
            _service = service;
            _userService = userService;
            _emailService = _emailService;

        }

        // GET: api/Staffs
        [HttpGet]
        public async Task<ActionResult<IEnumerable<StaffDto>>> GetAll()
        {
            return await _service.GetAllAsync();
        }

        // GET: api/Staffs/5
        [HttpGet("active")]
        public async Task<ActionResult<IEnumerable<StaffDto>>> GetNonActiveAndActive()
        {
            return await _service.GetAllNonActiveAndActiveAsync();
        }
        
        [HttpGet("{id:guid}")]
        public async Task<ActionResult<StaffDto>> GetById(Guid id)
        {
            var staff = await _service.GetByIdAsync(new StaffId(id));

            if (staff == null)
            {
                return NotFound();
            }

            return staff;
        }

        [HttpGet("name/{firstName}/{lastName}")]
        public async Task<ActionResult<StaffDto>> GetByName(string firstName, string lastName)
        {
            var fullName = firstName + " " + lastName;
            var staff = await _service.GetByNameAsync(fullName);

            if (staff == null)
            {
                return NotFound(new { Message = "Staff not found with the name:", fullName });
            }

            return Ok(staff);
        }

        [HttpGet("email/{email}")]
        public async Task<ActionResult<StaffDto>> GetByEmail(string email)
        {
            var staff = await _service.GetByEmailAsync(email);

            if (staff == null)
            {
                return NotFound(new { Message = "Staff not found with the email:", email });
            }

            return Ok(staff);
        }

        [HttpGet("specialization/{specialization}")]
        public async Task<ActionResult<StaffDto>> GetBySpecialization(string specialization)
        {
            var staff = await _service.GetBySpecializationAsync(specialization);

            if (staff == null)
            {
                return NotFound(new { Message = "Staff not found with the specialization:", specialization });
            }

            return Ok(staff);
        }


        [HttpPost]
        public async Task<ActionResult<StaffDto>> Create(CreatingStaffDto dto)
        {
            try
            {
                var staff = await _service.AddAsync(dto);
               

                return CreatedAtAction(nameof(GetById), new { id = staff.LicenseNumber.AsGuid() }, staff);
            }
            catch (BusinessRuleValidationException ex)
            {
                return BadRequest(new { message = ex.Message });
            }
            
        }

        [HttpPut("email/{email}")]
        public async Task<ActionResult<StaffDto>> UpdateByEmail(string email, StaffDto dto)
        {
            if (dto == null)
            {
                return BadRequest(new { Message = "Invalid data. The StaffDto object cannot be null." });
            }

            var staff = await _service.UpdateAsync(email, dto.Specialization, dto.PhoneNumber, dto.TimeSlots,
                dto.FirstName, dto.LastName);

            if (staff == null)
            {
                return NotFound(new { Message = "Staff not found to update." });
            }

            await _service.SendEmailAsync(email);

            return Ok(new { Message = "Staff successfully updated." });
        }


        [HttpPut("inactivate/{email}")]
        public async Task<ActionResult<StaffDto>> Inactivate(string email)
        {
            var staff = await _service.InactivateAsync(email);

            if (staff == null)
            {
                return NotFound(new { Message = "Staff not found to deactivate." });
            }

            return Ok(new { Message = "Staff successfully inactivated." });
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(Guid id)
        {
            var staff = await _service.DeleteAsync(id);

            if (!staff)
            {
                return NotFound(new { Message = "Staff not found to delete." });
            }

            return Ok(new { Message = "Staff profile successfully deleted." });
        }

        [HttpGet("search")]
        public async Task<ActionResult> Search([FromQuery] string name, [FromQuery] string email,
            [FromQuery] string? specialization, [FromQuery] int page = 1, [FromQuery] int pageSize = 10)
        {

            Console.WriteLine($"Page: {page}, PageSize: {pageSize}");

            var (results, totalCount) = await _service.SearchAsync(name, email, specialization, page, pageSize);

            return Ok(new
            {
                Results = results,
                TotalCount = totalCount,
                Page = page,
                PageSize = pageSize,
                TotalPages = (int)Math.Ceiling(totalCount / (double)pageSize)
            });
        }

      /*  [HttpPost("register")]
        public async Task<IActionResult> Register(CreatingUserDto regDto)
        {
            try
            {
                var token = await _userService.GetManagementApiTokenAsync();
                var answer = await _userService.CreateUserAsAdmin(token, regDto);
                if (answer == HttpStatusCode.Created)
                {
                    var result = await _userService.SendPasswordResetEmail(regDto.email);
                
                    var staff = new CreatingStaffDto(
                        new StaffId(Guid.NewGuid()),
                        regDto.Staff.FirstName,
                        regDto.Staff.LastName,
                        regDto.Staff.Email,
                        regDto.Staff.PhoneNumber,
                        regDto.Staff.Specialization,
                        regDto.Staff.TimeSlots
                    );

                    await _service.AddAsync(staff);
                    
                    if (result == HttpStatusCode.OK) {
                        var role = await _userService.GetRoleIdByNameAsync(regDto.role, token);
                        var userId = await _userService.GetUserIdByEmailAsync(regDto.email, token);
                        await _userService.AddRoleToUserAsync(userId, role, token);
                        return Ok(new { Message = "User devidamente Registado, verifique o email registado" });
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
        }*/

    }
}