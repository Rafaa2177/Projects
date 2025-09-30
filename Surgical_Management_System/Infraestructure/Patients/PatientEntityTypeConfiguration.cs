using DDDSample1.Domain.Patients;
using DDDSample1.Domain.Shared;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
namespace DDDSample1.Infrastructure.Patient
{

    internal class PatientEntityTypeConfiguration : IEntityTypeConfiguration<Domain.Patients.Patient>, IAggregateRoot
    {
        public void Configure(EntityTypeBuilder<Domain.Patients.Patient> builder)
        {
            builder.HasKey(b => b.RecordNumber);
            builder.Property(p => p.RecordNumber)
                .HasConversion(
                    id => id.AsGuid(),  // Converte de PatientId para Guid
                    guid => new PatientId(guid)  // Converte de Guid para PatientId
                )
                .IsRequired();
        }
    }
}