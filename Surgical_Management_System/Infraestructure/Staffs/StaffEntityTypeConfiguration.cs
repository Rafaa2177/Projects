using DDDSample1.Domain.Shared;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
namespace DDDSample1.Infrastructure.Staff
{

    internal class StaffEntityTypeConfiguration : IEntityTypeConfiguration<Domain.Staff.Staff>, IAggregateRoot
    {
        public void Configure(EntityTypeBuilder<Domain.Staff.Staff> builder)
        {
            builder.HasKey(s => s.LicenseNumber);
        }
    }
}