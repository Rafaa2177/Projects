using DDDSample1.Domain.Shared;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
namespace DDDSample1.Infrastructure.User
{

    internal class UserEntityTypeConfiguration : IEntityTypeConfiguration<Domain.User.User>, IAggregateRoot
    {
        public void Configure(EntityTypeBuilder<Domain.User.User> builder)
        {
            builder.HasKey(b => b.Id);
            builder.HasIndex(b => new { b.email }).IsUnique();
        }
    }
}