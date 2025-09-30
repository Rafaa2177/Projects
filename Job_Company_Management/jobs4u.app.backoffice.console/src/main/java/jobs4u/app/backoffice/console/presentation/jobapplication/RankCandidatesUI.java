package jobs4u.app.backoffice.console.presentation.jobapplication;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.Application;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.ranking.application.RankCandidatesController;

public class RankCandidatesUI extends AbstractUI{

    private final RankCandidatesController controller = new RankCandidatesController();
    
    private JobOpening jo = null;
    private JobApplication ja = null;


     @Override
    protected boolean doShow() {

        try {
            do{
                jo = selectJobOpening();
                if (jo == null) {
                    return false;
                }
                System.out.println("\n-------------------------------\n");
                ja = selectJobApplication(jo);
                if (ja == null) {
                    return false;
                }
                System.out.println("\n-------------------------------\n");
                int numberOfVacancies =  jo.numberOfVacancies().value();
                selectJobApplicationRanking(numberOfVacancies, ja);
                System.out.println("\n-------------------------------\n");
                
            }
            while(!selectNewCandidateOption().equals("no"));
           
        } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
            System.out.println("Something went wrong. Please try again.");
        }
        return false;
    }


    private JobOpening selectJobOpening() {
        Iterable<JobOpening> jobOpenings = controller.listJobOpenings();
        if(!jobOpenings.iterator().hasNext()){
            System.out.println("There are no job openings at phase Analysis at the moment.");
            return null;
        }
        SelectWidget<JobOpening> joSelector = new SelectWidget<>("Job Openings - Select one:\n", jobOpenings);
        joSelector.show();

        System.out.println("Your selection: \n");
        System.out.println(joSelector.selectedElement().getDetails());


        return joSelector.selectedElement();
    }

    private JobApplication selectJobApplication(JobOpening jo) {
        Iterable<JobApplication> jobApplications = controller.listJobApplicationsByJO(jo);
        if (!jobApplications.iterator().hasNext()) {
            System.out.println("There are no job applications for this job opening at the moment.");
            return null;
        }
        SelectWidget<JobApplication> jaSelector = new SelectWidget<>("Job Applications - Select one:\n", jobApplications);
        jaSelector.show();
        return jaSelector.selectedElement();
    }

    private void selectJobApplicationRanking(int vacancies, JobApplication ja) {
        
        double magnitude = Application.settings().getRankingMagnitude();

        System.out.println("Ranking magnitude: " + magnitude + "\n");

        Iterable<Integer> list = IntStream.rangeClosed(1, ((int)(vacancies * magnitude))).boxed().collect(Collectors.toList());
        SelectWidget<Integer> rankingSelector = new SelectWidget<>("Ranking - Select one:\n", list);
        rankingSelector.show();

        controller.rankApplication(ja, rankingSelector.selectedElement(), jo, magnitude);
        System.out.println("Candidate ranked successfully!");
    }

    private String selectNewCandidateOption(){
        return Console.readLine("Do you want to rank another candidate? Yes/No - Select one:\n").toLowerCase();
    }

    @Override
    public String headline() {
        // TODO Auto-generated method stub
        return "Rank Candidates for a Job Opening";
    }
}
