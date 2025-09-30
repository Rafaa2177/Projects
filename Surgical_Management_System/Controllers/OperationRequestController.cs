using System.Collections.Generic;
using System.Net;
using System.Threading.Tasks;
using DDDSample1.Domain.OperationRequests;
using Microsoft.AspNetCore.Mvc;

namespace DDDSample1.Controllers;

[Route("api/[controller]")]
[ApiController]
public class OperationRequestController : ControllerBase
{
    public readonly OperationRequestService _service;
    
    public OperationRequestController(OperationRequestService service)
    {
        _service = service;
    }

    [HttpPost]
    public async Task<IActionResult> AddOperationRequest(CreatingOperationRequestDto dto)
    {
        var createdOperationRequest = await _service.AddAsync(dto);

        var result = new OperationRequestDto
        (
            createdOperationRequest.DoctorId,
            createdOperationRequest.PatientId,
            createdOperationRequest.OperationType,
            createdOperationRequest.OperationDate,
            createdOperationRequest.Priority
        );

        return Ok(result);
    }
    [HttpGet]
    public async Task<ActionResult<IEnumerable<OperationRequestDto>>> GetAll()
    {
        return await _service.GetAllAsync();
    }
    
    [HttpGet("{id:guid}")]
    public async Task<ActionResult<OperationRequestDto>> GetById(OperationRequestId id)
    {
        return await _service.GetByIdAsync(id);
    }
    
    
    [HttpGet("doctorId/{id}")]
    public async Task<ActionResult<IEnumerable<OperationRequestDto>>> GetByDoctorId(string id)
    {
        return await _service.GetByDoctorIdAsync(id);
    }
    
    [HttpGet("patientId/{id}")]
    public async Task<ActionResult<IEnumerable<OperationRequestDto>>> GetByPatientId(string id)
    {
        var operations = await _service.GetByPatientIdAsync(id);

        if (operations == null)
        {
            return NotFound();
        }

        return Ok(operations);
    }

    [HttpGet("priority/{priority}")]
    public async Task<ActionResult<IEnumerable<OperationRequestDto>>> GetByPriority(string priority)
    {
        var operations = await _service.GetByPriorityAsync(priority);

        if (operations == null)
        {
            return NotFound();
        }

        return Ok(operations);
    }

    [HttpGet("operationType/{type}")]
    public async Task<ActionResult<IEnumerable<OperationRequestDto>>> GetByOperationType(string type)
    {
        var operations = await _service.GetByOperationTypeAsync(type);

        if (operations == null)
        {
            return NotFound();
        }

        return Ok(operations);
    }
    
    [HttpPut("{authtoken}/{docId}")]
    public async Task<IActionResult> UpdateOperationRequest(string docId,string authtoken, UpdatingOperationRequestDto dto)
    {
        var updatedOperationRequest = await _service.UpdateAsync(docId,authtoken,dto);
        if (updatedOperationRequest == HttpStatusCode.Forbidden)
        {
            return Unauthorized("Não tem permissões para apagar este pedido de operação!");
        }
        if (updatedOperationRequest == HttpStatusCode.NotFound)
        {
            return NotFound("Pedido de operação não encontrado!");
        }
        return Ok("Registo de Operação editado com sucesso!");
        
        
    }

    [HttpDelete("{docId}/{authtoken}/{opid}")]
    public async Task<IActionResult> DeleteOperationRequest(string docId,string authtoken,string opid)
    {
        var deletedOperationRequest = await _service.RemoveAsync(docId,authtoken,new OperationRequestId(opid));

        if (deletedOperationRequest == HttpStatusCode.Forbidden)
        {
            return Unauthorized("Não tem permissões para apagar este pedido de operação!");
        }
        if (deletedOperationRequest == HttpStatusCode.NotFound)
        {
            return NotFound("Pedido de operação não encontrado!");
        }
        return Ok("Registo de Operação apagado com sucesso!");
    }

}