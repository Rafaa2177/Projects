using DDDSample1.Domain.OperationRequests;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace DDDSample1.Infrastructure.OperationRequests;

internal class OperationRequestEntityTypeConfiguration : IEntityTypeConfiguration<OperationRequest>
{
    public void Configure(EntityTypeBuilder<OperationRequest> builder)
    {
        // Define Guid Id as the primary key
        builder.HasKey(b => b.Id);

        // Define a unique index on PatientId, OperationType, and OperationDate
        builder.HasIndex(b => new { b.PatientId, b.OperationType, b.OperationDate })
            .IsUnique();
        
        
        
    }
    
}