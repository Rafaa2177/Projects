package jobs4u.candidate.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.jobs4uUser.repositories.Jobs4uUserRepository;
import jobs4u.usermanagement.domain.BaseRoles;
import org.antlr.v4.runtime.RecognitionException;
import plugin.Plugin;

import java.io.File;
import java.io.IOException;

@UseCaseController
public class UploadCandidateDataController {
    private RepositoryFactory repositoryFactory = PersistenceContext.repositories();
    private JobOpeningRepository jobOpeningRepository = repositoryFactory.jobopenings();
    private JobApplicationRepository jobApplicationRepository = repositoryFactory.jobapplications();
    private Jobs4uUserRepository jobs4uUserRepository = repositoryFactory.jobs4uUsers();
    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();

    public Iterable<JobOpening> listJobOpenings(){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);

        return jobOpeningRepository.findAll();
    }

    public Iterable<JobApplication> listJobApplications(JobOpening jobOpening) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);

        return jobApplicationRepository.findByJobOpening(jobOpening);
    }

    public boolean upload(String src, String dst, int interviewModelId, int requirementModelId){
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);

        SystemUser su = authorizationService.session().get().authenticatedUser();
        if(su.hasAny(BaseRoles.OPERATOR)){
            return uploadRequirementData(src,dst,interviewModelId);
        }
        else{
            return uploadInterviewData(src,dst,requirementModelId);
        }
    }

    private boolean uploadInterviewData(String src, String dst, int interviewModelId){
        try{
            Plugin plugin = new Plugin();
            plugin.getInterviewModel(interviewModelId).evaluateInterview(src).toStringTree();

            moveFile(src,dst,"interviews.txt");
            return true;
        }catch (IOException e){
            System.out.println("Error uploading interview responses, try again!");
            return false;
        }catch (RuntimeException re){
            System.out.println("One of the fields isn't in the correct format, try again!");
            return false;
        }
    }

    private boolean uploadRequirementData(String src, String dst, int requirementModelId){
        try{

            Plugin plugin = new Plugin();
            plugin.getRequirementsModel(requirementModelId).evaluateRequirements(src);

            moveFile(src,dst,"requirements.txt");
            return true;
        }catch (IOException e){
            System.out.println("Error uploading requirements, try again!");
            return false;
        }catch (RuntimeException re){
            System.out.println("One of the fields isn't in the correct format, try again!");
            return false;
        }
    }

    private void moveFile(String src, String dst, String fileName) {
        File file = new File(src);
        file.renameTo(new File(dst+"/"+fileName));
        
    }


}
