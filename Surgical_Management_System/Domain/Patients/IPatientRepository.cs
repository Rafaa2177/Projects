using System.Collections.Generic;
using System.Threading.Tasks;
using DDDSample1.Domain.Shared;

namespace DDDSample1.Domain.Patients
{
    public interface IPatientRepository : IRepository<Patient, PatientId>
    
    
    
    {
        Task<Patient> FindByEmailAsync(string email);
        Task<Patient> FindByEmailAndExcludedIdAsync(string email, PatientId excludedId);
        
        Task<List<Patient>> FindByNameAsync(string firstName, string lastName);
        Task<Patient> FindByMedicalRecordNumberAsync(PatientId medicalRecordNumber);
        Task<Patient> FindByIDAsync(PatientId id);
        Task<bool> PatientExists(PatientId id);
        
        Task<List<Patient>> FindByAllergyAsync(string allergy);
        
        Task<List<Patient>> FindByMedicalConditionAsync(string medicalCondition);
        Task<List<Patient>> FindPatientByAllergyAsync(string allergy);
        Task<List<Patient>> FindPatientByMedicalConditionAsync(string medicalCondition);
        

    }
}