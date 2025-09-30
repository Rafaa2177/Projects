using System;
using System.Net.Http;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.User;
using Microsoft.AspNetCore.Http.HttpResults;
using SendGrid;
using SendGrid.Helpers.Mail;

namespace DDDNetCore.Domain.Users;

public class EmailService
{
    private readonly IUnitOfWork _unitOfWork;
    private readonly IUserRepository _repo;
    private readonly HttpClient _httpClient;
    private readonly string _apiKey = "YOUR_SENDGRID_API_KEY";

    public EmailService(IUnitOfWork unitOfWork, IUserRepository repo, HttpClient httpClient)
    {
        this._unitOfWork = unitOfWork;
        this._repo = repo;
        _httpClient = httpClient;

    }
    
    public async Task SendEmail(EmailAddress from, EmailAddress to, string subject, string body)
    {
            var client = new SendGridClient(_apiKey);
            var msg = MailHelper.CreateSingleEmail(from, to, subject, body, body);
            var response = await client.SendEmailAsync(msg);
        
    }

    }
