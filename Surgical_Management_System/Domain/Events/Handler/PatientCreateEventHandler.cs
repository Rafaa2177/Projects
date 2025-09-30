using System.Threading.Tasks;
using Microsoft.Extensions.Logging;

namespace DDDSample1.Domain.Events.Handler;

public class PatientCreateEventHandler

{
    private readonly ILogger<PatientCreateEventHandler> _logger;

    public PatientCreateEventHandler(ILogger<PatientCreateEventHandler> logger)
    {
        _logger = logger;
    }

    public async Task Handle(PatientCreateEvent eventData)
    {
        await Task.Run(() =>
        {
            _logger.LogInformation($"Patient Profile Created: {eventData.FirstName} {eventData.LastName} (ID: {eventData.PatientProfileId}) at {eventData.CreatedAt}");
        });
    }
}