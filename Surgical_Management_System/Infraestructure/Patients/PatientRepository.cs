using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DDDSample1.Domain.Patients;
using DDDSample1.Infrastructure.Shared;
using Microsoft.EntityFrameworkCore;

namespace DDDSample1.Infrastructure.Patients

{
    public class PatientRepository : BaseRepository<Domain.Patients.Patient, PatientId>, IPatientRepository
    {
        private readonly DDDSample1DbContext _context;
        public PatientRepository(DDDSample1DbContext context) : base(context.Patients)
        {
            _context = context;
        }
        
        
        
        public async Task<Domain.Patients.Patient> FindByEmailAndExcludedIdAsync(string email, PatientId excludedId) {
            return await _context.Patients
                .FirstOrDefaultAsync(op => op.Email == email && op.Id != excludedId);
        }

        public async Task<Domain.Patients.Patient> FindByEmailAsync(string email) {
            return await _context.Patients
                .FirstOrDefaultAsync(p => p.Email == email);
            
        }

        public async Task<Domain.Patients.Patient> FindByMedicalRecordNumberAsync(PatientId medicalRecordNumber)
        {
            return await _context.Patients
                .FirstOrDefaultAsync(p => p.RecordNumber == medicalRecordNumber);
        }

        
        public async Task<Domain.Patients.Patient> FindByIDAsync(PatientId id)
        {
            return await _context.Patients
                .FirstOrDefaultAsync(p => p.Id == id);
        }

        public async Task<List<Domain.Patients.Patient>> FindByNameAsync(string firstName, string lastName)
        {
            return await _context.Patients
                .Where(p => p.FirstName == firstName && p.LastName == lastName)
                .ToListAsync();        
        }
        
        public Task<bool> PatientExists(PatientId id)
        {
            return _context.Users.AnyAsync(e => e.Id.Equals(id));
        }
        
        public async Task<List<Domain.Patients.Patient>> FindByAllergyAsync(string allergy)
        {
            return await _context.Patients
                .Where(p => p.Allergies.Contains(allergy))
                .ToListAsync();
        }

        public async Task<List<Domain.Patients.Patient>> FindByMedicalConditionAsync(string medicalCondition)
        {
            return await _context.Patients
                .Where(p => p.MedicalConditions.Contains(medicalCondition))
                .ToListAsync();
        }

        public async Task<List<Domain.Patients.Patient>> FindPatientByAllergyAsync(string allergy)
        {
            return _context.Patients
                .AsEnumerable()  
                .Where(p => p.Allergies != null && p.Allergies.Contains(allergy, StringComparer.OrdinalIgnoreCase))
                .ToList();
        }
        
        public async Task<List<Domain.Patients.Patient>> FindPatientByMedicalConditionAsync(string medicalCondition)
        {
            return _context.Patients
                .AsEnumerable()  
                .Where(p => p.MedicalConditions != null && p.MedicalConditions.Contains(medicalCondition, StringComparer.OrdinalIgnoreCase))
                .ToList();
        }

    }
}
