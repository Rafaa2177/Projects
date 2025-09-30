using System.IO;
using Auth0.AspNetCore.Authentication;
using DDDNetCore.Domain.Users;
using DDDSample1.Domain.Categories;
using DDDSample1.Domain.Families;
using DDDSample1.Domain.OperationRequests;
using DDDSample1.Domain.Patients;
using DDDSample1.Domain.Products;
using DDDSample1.Domain.Shared;
using DDDSample1.Domain.Staff;
using DDDSample1.Domain.Users;
using DDDSample1.Domain.User;
using DDDSample1.Infrastructure;
using DDDSample1.Infrastructure.Categories;
using DDDSample1.Infrastructure.Families;
using DDDSample1.Infrastructure.OperationRequests;
using DDDSample1.Infrastructure.Patients;
using DDDSample1.Infrastructure.Products;
using DDDSample1.Infrastructure.Shared;
using DDDSample1.Infrastructure.User;
using DDDSample1.Infrastructure.Staff;
using DDDSample1.Support;
using Microsoft.AspNetCore.Builder;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

var root = Directory.GetCurrentDirectory();
var dotenv = Path.Combine(root, ".env");
var config = new ConfigurationBuilder().AddEnvironmentVariables().Build();
DotEnv.Load(dotenv);
var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
builder.Services.AddDbContext<DDDSample1DbContext>(opt =>
    opt.UseMySql(connectionString,ServerVersion.AutoDetect(connectionString))
        .ReplaceService<IValueConverterSelector, StronglyEntityIdValueConverterSelector>());
builder.Services.AddHttpClient<UserService>();
builder.Services.AddHttpClient<PatientService>();
builder.Services.AddHttpClient<StaffService>(); //tenta agr
builder.Services.AddHttpClient<EmailService>(); //deu a mesma coisa ve agora
builder.Services.AddHttpClient<OperationRequestService>();
builder.Services.AddAuth0WebAppAuthentication(options =>
{
    options.Domain = builder.Configuration["Auth0:Domain"];
    options.ClientId = builder.Configuration["Auth0:ClientId"];
});
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowLocalhost3000", builder =>
    {
        builder.WithOrigins("http://localhost:3000") // Permitir a origem do Next.js
            .AllowAnyMethod() // Permitir todos os métodos (GET, POST, etc.)
            .AllowAnyHeader() // Permitir todos os cabeçalhos
            .AllowCredentials(); // Permitir cookies e credenciais
    });
});

SameSiteServiceCollectionExtensions.ConfigureSameSiteNoneCookies(builder.Services);

builder.Services.AddControllers().AddNewtonsoftJson();

ConfigureMyServices(builder.Services);

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseDeveloperExceptionPage();
}
else
{
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.UseCookiePolicy();

app.UseCors("AllowLocalhost3000");
app.UseEndpoints(endpoints =>
{
    endpoints.MapControllers();
    endpoints.MapDefaultControllerRoute();
});

app.Run();

void ConfigureMyServices(IServiceCollection services)
{
    services.AddTransient<IUnitOfWork, UnitOfWork>();

    services.AddTransient<ICategoryRepository, CategoryRepository>();
    services.AddTransient<CategoryService>();

    services.AddTransient<IProductRepository, ProductRepository>();
    services.AddTransient<ProductService>();

    services.AddTransient<IFamilyRepository, FamilyRepository>();
    services.AddTransient<FamilyService>();

    services.AddTransient<IPatientRepository, PatientRepository>();
    services.AddTransient<PatientService>();
    
    services.AddTransient<IUserRepository, UserRepository>();
    services.AddTransient<UserService>();
    
    services.AddTransient<IStaffRepository, StaffRepository>();
    services.AddTransient<StaffService>();
    services.AddTransient<IOperationRequestRepository, OperationRequestRepository>();
    services.AddTransient<OperationRequestService>();
    
}