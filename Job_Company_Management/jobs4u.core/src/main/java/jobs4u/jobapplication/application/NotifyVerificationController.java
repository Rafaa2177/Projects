package jobs4u.jobapplication.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import jobs4u.jobapplication.domain.Status;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.usermanagement.domain.BaseRoles;
import org.example.notification.EmailService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class NotifyVerificationController {

    private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobapplications();


    public void notification(JobOpening jobOpening){
        EmailService emailService = new EmailService();
        StringBuilder message = new StringBuilder();
        jobApplicationRepository.findByJobOpening(jobOpening).forEach(jobApplication -> {
            if (jobApplication.state() == Status.Rejected){
                message.append("Dear " + jobApplication.candidate().user().user().name() + " We hope this message finds you well.We would like to inform that the result of your verification process is: REJECTED. ");
            } else if (jobApplication.state() == Status.Pending) {
                message.append("Dear " + jobApplication.candidate().user().user().name() + " We hope this message finds you well.We would like to inform that the result of your verification process is: PENDING. ");

            } else if (jobApplication.state() == Status.Accepted){
                message.append("Dear " + jobApplication.candidate().user().user().name() + " We hope this message finds you well.We would like to inform that the result of your verification process is: ACCEPTED. ");
            }
            System.out.println("Sending email");
            emailService.sendEmail(jobApplication.candidate().user().user().email().toString(), "Job application for \"+jo.identity().toString()+\" results\"", message.toString());
        });
    }


}

