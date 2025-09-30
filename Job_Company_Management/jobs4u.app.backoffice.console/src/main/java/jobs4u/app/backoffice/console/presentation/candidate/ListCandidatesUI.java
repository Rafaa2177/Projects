package jobs4u.app.backoffice.console.presentation.candidate;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.candidate.application.ListCandidatesController;
import jobs4u.candidate.domain.Candidate;

public class ListCandidatesUI extends AbstractUI {

    ListCandidatesController listCandidatesController = new ListCandidatesController();

    @Override
    protected boolean doShow() {
        showCandidates();
        return true;
    }

    private void showCandidates(){
        Iterable<Candidate> candidates = listCandidatesController.getAllCandidates();
        if (candidates == null) {
            System.out.println("No candidates found.");
        }
    }


    @Override
    public String headline() {
        return "List of Candidates";
    }


}
