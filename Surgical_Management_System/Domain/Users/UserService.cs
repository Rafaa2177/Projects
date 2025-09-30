using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using DDDSample1.Domain.Shared;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Patients;
using DDDSample1.Domain.Users;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;
using SendGrid;
using SendGrid.Helpers.Mail;

namespace DDDSample1.Domain.User;

public class UserService
{
    private readonly IUnitOfWork _unitOfWork;
    private readonly IUserRepository _repo;
    private readonly HttpClient _httpClient;
    private readonly EmailService _emailService;
    public UserService(IUnitOfWork unitOfWork, IUserRepository repo, HttpClient httpClient, EmailService emailService)
    {
        this._unitOfWork = unitOfWork;
        this._repo = repo;
        _httpClient = httpClient;
        _emailService = emailService;

    }
    
    public async Task<List<UserDto>> GetAllAsync()
    {
        var list = await this._repo.GetAllAsync();
        List<UserDto> listDto = list.ConvertAll<UserDto>(pat => new UserDto
        (
            pat.role, pat.email, pat.Id
        ));
        return listDto;
    }

    public async Task SendEmailToAdminsAsync(string email)
    {
        string role = "Admin";
        var admins = await _repo.GetUsersByRole(role);

        Console.WriteLine("Sending email to admins");
        Console.WriteLine("Admins: " + admins.Count);

        foreach (var admin in admins)
        {
            Console.WriteLine("Sending email to admin.");
            Console.WriteLine("Admin email: " + admin.email);
            var fromEmail = new EmailAddress("franciscasteixeira07@gmail.com", "Francisca Teixeira");
            var toEmail = new EmailAddress(admin.email);
            var subject = "Conta Bloqueada";
            var body = "O utilizador " + email +
                       " excedeu o número de tentativas de login. Por favor, verifique a conta.";
            await _emailService.SendEmail(fromEmail, toEmail, subject, body);
            Console.WriteLine("Email sent to admin.");
        }

    }

    public async Task<UserDto> AddUserAsync(CreatingUserDto dto)
    {
        var user = new User(dto.role, dto.email);
        
        await this._repo.AddAsync(user);
        await this._unitOfWork.CommitAsync();

        return new UserDto(user.role, user.email, user.Id);    
    }
    
    
    public async Task<(String accessToken, List<String> roles)> GetUserToken(LoginUserDto request) {
        var tokenEndpoint = $"https://{Environment.GetEnvironmentVariable("_domain")}/oauth/token";
 
        var tokenRequestBody = new Dictionary<string, string> {
            { "grant_type", "password" },
            { "username", request.Email },
            { "password", request.Password },
            { "client_id", Environment.GetEnvironmentVariable("_client_id") },
            { "client_secret", Environment.GetEnvironmentVariable("_client_secret") },
            { "audience", Environment.GetEnvironmentVariable("_audience") },
            { "connection", Environment.GetEnvironmentVariable("_database") },
        };
 
        var requestContent = new FormUrlEncodedContent(tokenRequestBody);
        var tokenResponse = await _httpClient.PostAsync(tokenEndpoint, requestContent);
 
        if (tokenResponse.IsSuccessStatusCode) {
            var tokenResponseBody = await tokenResponse.Content.ReadAsStringAsync();
            var tokenResult = JsonSerializer.Deserialize<JsonElement>(tokenResponseBody);
            string accessToken = tokenResult.GetProperty("access_token").GetString();
 
            // Obter roles a partir das claims do token
            var handler = new JwtSecurityTokenHandler();
            var jwtToken = handler.ReadJwtToken(accessToken);
 
             // Display all claims for debugging
             //foreach (var claim in jwtToken.Claims) {
             //    Console.WriteLine($"{claim.Type}: {claim.Value}");
             //}
 
            var roles = jwtToken.Claims
                .Where(c => c.Type == "https://management-system.com/roles")
                .Select(c => c.Value)
                .ToList();
 
            return (accessToken, roles);
        } else {
            var errorContent = await tokenResponse.Content.ReadAsStringAsync();
            throw new Exception($"Error retrieving access token: {errorContent}");
        }
    }
    public async Task<HttpStatusCode> RegisterPatient(LoginUserDto regDto)
    {
        
        var tokenEndpoint = $"https://{Environment.GetEnvironmentVariable("_domain")}/dbconnections/signup";
 
        var tokenRequestBody = new Dictionary<string, string> {
            { "client_id", Environment.GetEnvironmentVariable("_client_id") },
            { "email", regDto.Email },
            { "password", regDto.Password },
            { "connection", Environment.GetEnvironmentVariable("_database") },
        };
 
        var requestContent = new FormUrlEncodedContent(tokenRequestBody);
        var tokenResponse = await _httpClient.PostAsync(tokenEndpoint, requestContent);
        
        if (!tokenResponse.IsSuccessStatusCode) {
            var errorContent = await tokenResponse.Content.ReadAsStringAsync();
            throw new Exception($"Error registering user: {errorContent}");
        }
        
        return tokenResponse.StatusCode;

    }
    public async Task<string> GetUserIdByEmailAsync(string email, string accessToken)
    {
        // Validar o token de acesso
        if (string.IsNullOrEmpty(accessToken))
        {
            throw new Exception("O token de acesso está vazio ou nulo.");
        }

        // Adicionar o token de autenticação no cabeçalho
        _httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", accessToken);

        // Construir URL para buscar o usuário
        var requestUrl = $"https://{Environment.GetEnvironmentVariable("_domain")}/api/v2/users-by-email?email={Uri.EscapeDataString(email)}";

        // Fazer a requisição HTTP GET
        var response = await _httpClient.GetAsync(requestUrl);

        // Em caso de falha, ler e lançar o erro
        if (!response.IsSuccessStatusCode)
        {
            var errorContent = await response.Content.ReadAsStringAsync();
            throw new Exception($"Erro ao buscar usuário: {response.ReasonPhrase}. Detalhes: {errorContent}");
        }

        // Parsear o conteúdo JSON da resposta
        var content = await response.Content.ReadAsStringAsync();
        using var jsonDoc = JsonDocument.Parse(content);

        if (jsonDoc.RootElement.GetArrayLength() > 0)
        {
            // Retornar o ID do primeiro usuário encontrado
            return jsonDoc.RootElement[0].GetProperty("user_id").GetString();
        }
        else
        {
            throw new Exception($"Usuário com e-mail '{email}' não encontrado.");
        } 
    }

    public async Task<HttpStatusCode> AddRoleToUserAsync(string userId, string roleId, string authToken)
    {   
        if (string.IsNullOrEmpty(authToken))
        {
            throw new Exception("O token de acesso está vazio ou nulo.");
        }

        // Adicionar o token de autenticação no cabeçalho
        _httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", authToken);

        // Endpoint para adicionar papéis ao usuário
        var tokenEndpoint = $"https://{Environment.GetEnvironmentVariable("_domain")}/api/v2/users/{userId}/roles";

        // Criar o corpo da requisição como um JSON com um array de roles
        var tokenRequestBody = new
        {
            roles = new[] { roleId }
        };

        // Serializar o corpo para JSON
        var requestBodyJson = JsonSerializer.Serialize(tokenRequestBody);
        var requestContent = new StringContent(requestBodyJson, Encoding.UTF8, "application/json");

        // Fazer a requisição POST
        var tokenResponse = await _httpClient.PostAsync(tokenEndpoint, requestContent);
    
        if (!tokenResponse.IsSuccessStatusCode) 
        {
            var errorContent = await tokenResponse.Content.ReadAsStringAsync();
            throw new Exception($"Error adding role to user: {errorContent}");
        }

        return tokenResponse.StatusCode;
    }
    public async Task<HttpStatusCode> RegisterUser(CreatingUserDto regDto)
    {
        var tokenEndpoint = $"https://{Environment.GetEnvironmentVariable("_domain")}/dbconnections/signup";
 
        var tokenRequestBody = new Dictionary<string, string> {
            { "client_id", Environment.GetEnvironmentVariable("_client_id") },
            { "email", regDto.email },
            { "password", regDto.username },
            { "connection", Environment.GetEnvironmentVariable("_database") },
        };
 
        var requestContent = new FormUrlEncodedContent(tokenRequestBody);
        var tokenResponse = await _httpClient.PostAsync(tokenEndpoint, requestContent);
        
        if (!tokenResponse.IsSuccessStatusCode) {
            var errorContent = await tokenResponse.Content.ReadAsStringAsync();
            throw new Exception($"Error registering user: {errorContent}");
        }
        
        return tokenResponse.StatusCode;

    }
    // Método para Obter o Token da Management API
    public async Task<string> GetManagementApiTokenAsync()
    {
        var clientId = Environment.GetEnvironmentVariable("_client_id");
        var clientSecret = Environment.GetEnvironmentVariable("_client_secret");
        var domain = Environment.GetEnvironmentVariable("_domain");
        var audience = $"https://{domain}/api/v2/";

        if (string.IsNullOrEmpty(clientId) || string.IsNullOrEmpty(clientSecret) || string.IsNullOrEmpty(domain))
        {
            throw new Exception("Um ou mais valores de ambiente estão ausentes.");
        }

        var tokenEndpoint = $"https://{domain}/oauth/token";
        var tokenRequestBody = new Dictionary<string, string>
        {
            { "client_id", clientId },
            { "client_secret", clientSecret },
            { "audience", audience },
            { "grant_type", "client_credentials" }
        };

        var requestContent = new FormUrlEncodedContent(tokenRequestBody);
        var tokenResponse = await _httpClient.PostAsync(tokenEndpoint, requestContent);

        if (!tokenResponse.IsSuccessStatusCode)
        {
            var errorContent = await tokenResponse.Content.ReadAsStringAsync();
            throw new Exception($"Erro ao obter token de acesso: {tokenResponse.ReasonPhrase}. Detalhes: {errorContent}");
        }

        var content = await tokenResponse.Content.ReadAsStringAsync();
        using var jsonDoc = JsonDocument.Parse(content);
        return jsonDoc.RootElement.GetProperty("access_token").GetString();
    }

    // Método para Buscar o Role ID pelo Nome
    public async Task<string> GetRoleIdByNameAsync(string roleName, string accessToken)
    {
        // Adicionar o token de autenticação no cabeçalho
        _httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", accessToken);

        // Construir URL com filtro de nome
        var requestUrl = $"https://{Environment.GetEnvironmentVariable("_domain")}/api/v2/roles?name_filter={Uri.EscapeDataString(roleName)}";

        // Fazer a requisição HTTP GET
        var response = await _httpClient.GetAsync(requestUrl);

        // Em caso de falha, ler e lançar o erro
        if (!response.IsSuccessStatusCode)
        {
            var errorContent = await response.Content.ReadAsStringAsync();
            throw new Exception($"Erro ao buscar role: {response.ReasonPhrase}. Detalhes: {errorContent}");
        }

        // Parsear o conteúdo JSON da resposta
        var content = await response.Content.ReadAsStringAsync();
        using var jsonDoc = JsonDocument.Parse(content);

        if (jsonDoc.RootElement.GetArrayLength() > 0)
        {
            return jsonDoc.RootElement[0].GetProperty("id").GetString();
        }
        else
        {
            throw new Exception($"Role '{roleName}' não encontrado.");
        }
    }
    public async Task<HttpStatusCode> CreateUserAsAdmin(string token, CreatingUserDto dto)
    {
        

        var createUserEndpoint = $"https://{Environment.GetEnvironmentVariable("_domain")}/api/v2/users";
        var newUser = new
        {
            email = dto.email,
            password = "ASJDSOIADJi23112309120890disDSA@@@ASJODOIASJD1231", //fazer pass random
            connection = Environment.GetEnvironmentVariable("_database"),
            email_verified = true // Defina como true se o e-mail já tiver sido verificado.
        };

        var requestContent = new StringContent(JsonSerializer.Serialize(newUser), Encoding.UTF8, "application/json");
        _httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
    
        var response = await _httpClient.PostAsync(createUserEndpoint, requestContent);
    
        if (!response.IsSuccessStatusCode)
        {
            var errorContent = await response.Content.ReadAsStringAsync();
            throw new Exception($"Error creating user: {errorContent}");
        }

        return response.StatusCode;
    }
    
    
    
    public async Task<HttpStatusCode> SendPasswordResetEmail(string email)
    {
        var resetPasswordEndpoint = $"https://{Environment.GetEnvironmentVariable("_domain")}/dbconnections/change_password";
    
        var resetRequestBody = new
        {
            client_id = Environment.GetEnvironmentVariable("_client_id"),
            email = email,
            connection = Environment.GetEnvironmentVariable("_database")
        };

        var requestContent = new StringContent(JsonSerializer.Serialize(resetRequestBody), Encoding.UTF8, "application/json");
        var response = await _httpClient.PostAsync(resetPasswordEndpoint, requestContent);
    
        if (!response.IsSuccessStatusCode)
        {
            var errorContent = await response.Content.ReadAsStringAsync();
            throw new Exception($"Error sending password reset email: {errorContent}");
        }

        return response.StatusCode;
    }
    public async Task<HttpStatusCode> DeleteUser(string userId, string authToken)
    {
        // Endpoint da API para deletar usuário pelo user_id
        var deleteUserEndpoint = $"https://{Environment.GetEnvironmentVariable("_domain")}/api/v2/users/{userId}";
    
        // Configurar o cabeçalho de autorização com o token
        _httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", authToken);
    
        // Enviar a solicitação DELETE para a API
        var response = await _httpClient.DeleteAsync(deleteUserEndpoint);
    
        if (!response.IsSuccessStatusCode)
        {
            var errorContent = await response.Content.ReadAsStringAsync();
            throw new Exception($"Error deleting user: {errorContent}");
        }
    
        return response.StatusCode;
    }

    public async Task<string> GetUserIdFromToken(string accessToken)
    {
        var handler = new JwtSecurityTokenHandler();
        var jsonToken = handler.ReadToken(accessToken) as JwtSecurityToken;

        // A claim "sub" geralmente contém o user_id no Auth0
        var userId = jsonToken?.Claims.FirstOrDefault(c => c.Type == "sub")?.Value;

        return userId;
    }



}