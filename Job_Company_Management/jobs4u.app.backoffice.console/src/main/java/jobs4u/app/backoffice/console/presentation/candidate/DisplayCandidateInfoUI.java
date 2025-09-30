package jobs4u.app.backoffice.console.presentation.candidate;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.candidate.application.DisplayCandidateInfoController;
import jobs4u.candidate.domain.Candidate;

public class DisplayCandidateInfoUI  extends AbstractUI {



        private DisplayCandidateInfoController displayCandidateInfoController = new DisplayCandidateInfoController();
        @Override
        protected boolean doShow() {
            final Iterable<Candidate> CandidatesList = displayCandidateInfoController.allCandidates();
            System.out.printf("%-6s%-30s%n", "ID", "Email" );


            Candidate cSave = null;
            for (Candidate c : CandidatesList) {
                System.out.printf("%-6s%-30s%n",c.getId(), c.getUser().getSystemUser().email());

                cSave = c;
            }



            final long option = Console.readInteger("\n Enter user ID to display the corresponding candidate data: ");
            if(option == 0){
                System.out.println("No candidates were selected!");

            }else{
                System.out.println(" \nUserName :" + cSave.user().getSystemUser().email() +"\nFirst Name :" + cSave.user().getSystemUser().name().firstName() + " \nLast Name: "+ cSave.user().getSystemUser().name().lastName()+ "\nAddress: " +  cSave.user().getAddress() + "\nPhone Number: " + cSave.user().getPhone());

                }


            return true;

        }

        @Override
        public String headline() {
            return "Candidate data";
        }
}
