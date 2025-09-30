package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.DivideStoresController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.sort_algorithm.BruteForceAlgorithm;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

public class DivideStoresControllerJavaFx {
    @FXML
    public Button runButton ;
    @FXML
    public TextField numberStores;
    @FXML
    public ListView lViewSub1;
    @FXML
    public ListView lViewSub2;
    @FXML
    public Button cancel;
    private int n;
    private DivideStoresController controller = new DivideStoresController();

    public int readNumberStores(ActionEvent event) {
        return n = Integer.parseInt(numberStores.getText());
    }
    public void runBruteForceAlgorithm(ActionEvent event) {
        controller.getOrganizations();
        controller.getNumberOfProperties();
        controller.executeBruteForce(readNumberStores(event));

        lViewSub1.getItems().add(controller.getSubset1());
        lViewSub2.getItems().add(controller.getSubset2());
        controller.getDiff();//imprimir diff
    }

    public void onCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}