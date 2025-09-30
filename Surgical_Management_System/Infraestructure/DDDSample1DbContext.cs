using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using DDDSample1.Domain.Categories;
using DDDSample1.Domain.Products;
using DDDSample1.Domain.Families;
using DDDSample1.Domain.Staff;
using DDDSample1.Infrastructure.Categories;
using DDDSample1.Infrastructure.OperationRequests;
using DDDSample1.Infrastructure.Patient;
using DDDSample1.Infrastructure.Products;
using DDDSample1.Infrastructure.Staff;
using DDDSample1.Infrastructure.User;

namespace DDDSample1.Infrastructure
{
    public class DDDSample1DbContext : DbContext
    {
        public DbSet<Category> Categories { get; set; }

        public DbSet<Product> Products { get; set; }

        public DbSet<Family> Families { get; set; }
        
        public DbSet<Domain.Patients.Patient> Patients { get; set; }
        
        public DbSet<Domain.Staff.Staff> Staffs { get; set; }
        public DbSet<Domain.OperationRequests.OperationRequest> OperationRequests { get; set; }
        public DbSet<Domain.User.User> Users { get; set; }

        public DDDSample1DbContext(DbContextOptions options) : base(options)
        {
        
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new CategoryEntityTypeConfiguration());
            modelBuilder.ApplyConfiguration(new ProductEntityTypeConfiguration());
            modelBuilder.ApplyConfiguration(new FamilyEntityTypeConfiguration());
            modelBuilder.ApplyConfiguration(new PatientEntityTypeConfiguration());
            modelBuilder.ApplyConfiguration(new StaffEntityTypeConfiguration());

            modelBuilder.Entity<Domain.Staff.Staff>(entity =>
            {
                entity.OwnsMany(e => e.TimeSlots);
            });
            
            
            //modelBuilder.ApplyConfiguration(new UserEntityTypeConfiguration());

        }
    }
}