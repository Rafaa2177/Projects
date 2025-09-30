using System.Threading.Tasks;
using Microsoft.Extensions.Logging;

namespace DDDSample1.Domain.Events.Handler;

public class PatientDeleteEventHandler

{
    private readonly ILogger<PatientDeleteEventHandler> _logger;

    public PatientDeleteEventHandler(ILogger<PatientDeleteEventHandler> logger)
    {
        _logger = logger;
    }

    public async Task Handle(PatientDeleteEvent eventData)
    {
        await Task.Run(() =>
        {
            _logger.LogInformation(
                $"Patient Profile Deleted: (ID: {eventData.PatientProfileId}) at {eventData.Delete}");
        });
    }
}