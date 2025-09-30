package jobs4u.candidate.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.Role;
import jobs4u.candidate.domain.Candidate;
import jobs4u.candidate.domain.PasswordGenerator;
import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import jobs4u.jobs4uUser.domain.Jobs4uUserBuilder;
import jobs4u.jobs4uUser.repositories.Jobs4uUserRepository;
import jobs4u.usermanagement.domain.BaseRoles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@UseCaseController
public class RegisterAtmCandidateController {

    private final String candidatesReportPath = "jobs4u.applicationUtilities/ApplicationFileBot/reports";
    private final Jobs4uUserRepository jobs4uUserRepository = PersistenceContext.repositories().jobs4uUsers();
    private final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();
    private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobapplications();

    final String password = PasswordGenerator.generatePassword();

    public void importAllCandidates() {

        // Get all files in the folder
        File folder = new File(candidatesReportPath);
        File[] files = folder.listFiles();

        // Loop through each file and read the content
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    readFromFile(file);
                }
            }
        }
    }

    private Candidate createCandidate(final String username, final String password, final String firstName,
                                      final String lastName, final String email, final Set<Role> roles,final String phone) {
        final Jobs4uUserBuilder jobs4uUserBuilder = new Jobs4uUserBuilder();
        jobs4uUserBuilder.withInfo(username,password,firstName,lastName,email,roles).withPhone(phone);
        Jobs4uUser u = this.jobs4uUserRepository.save(jobs4uUserBuilder.build());
        return this.candidateRepository.save(new Candidate(u));
    }

    private Candidate findCandidateByEmail(final String email) {
        if (this.candidateRepository.exists(email)) {
            return this.candidateRepository.findByEmail(email);
        }
        return null;
    }

    private void createJobApplication(Candidate c, JobOpening jo, String filesPath){
        this.jobApplicationRepository.save(new JobApplication(filesPath, c, jo));
    }

    public void readFromFile(File file){
        System.out.println("\n\n-----------------------------------------");
        System.out.println("Importing candidates - " + file.getName());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            boolean create;
            String line = null;
            do {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                create = false;
                String filesPath ="jobs4u.applicationUtilities/" +  line;
                String jobReference = reader.readLine();
                String username = reader.readLine();
                String email = username;
                String firstName = reader.readLine();
                String lastName = reader.readLine();
                String phoneNumber = reader.readLine();
                create = true;
                if(create){
                    Candidate c = this.findCandidateByEmail(email);
                    if(c == null){
                        final Set<Role> roles = new HashSet<>();
                        roles.add(BaseRoles.CANDIDATE);
                        c = this.createCandidate(username, password, firstName, lastName, email, roles, phoneNumber);
                    }
                    JobOpening jo = PersistenceContext.repositories().jobopenings().findByReference(jobReference);
                    this.createJobApplication(c, jo, filesPath);
                }
                line = reader.readLine();
            } while (line != null);
        }
        catch (IOException e) {
            System.out.println("Error importing candidate - " + file.getName());
        }
    }
}
