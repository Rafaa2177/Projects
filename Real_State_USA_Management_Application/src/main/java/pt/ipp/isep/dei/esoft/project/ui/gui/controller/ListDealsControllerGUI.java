package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;

import javafx.scene.input.MouseEvent;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListDealsControllerGUI implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private FXMLLoader fxmlLoader;
    @FXML
    public Button cancel;
    public Button continueButton    ;
    @FXML
    private ComboBox<String> sortAlgBox;

    @FXML
    private ComboBox<String> sortOrderBox;

    @FXML
    private Button btnNext;


    @FXML
    private ListView<String> lView;

    private ListDealsController listDealsController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listDealsController = new ListDealsController();
        if(sortAlgBox == null){
            return;
        }
        if(sortOrderBox == null){
            return;
        }

        // Initialize combo boxes
        sortAlgBox.setItems(FXCollections.observableArrayList(listDealsController.getAlgOptions()));
        sortOrderBox.setItems(FXCollections.observableArrayList(listDealsController.getOrderList()));
    }
    @FXML
    private void sort(MouseEvent event) {
        lView.getItems().clear();
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        String sortingAlg = sortAlgBox.getValue();
        String sortingOrder = sortOrderBox.getValue();

        if (sortingAlg == null || sortingOrder == null) {
            // Display an error message if sorting algorithm or sorting order is not selected
        } else {
            listDealsController.sortProperties(sortingAlg, sortingOrder);
            List<PurchaseOrder> deals = listDealsController.getDealList();
            for (PurchaseOrder order: deals ) {
                //System.out.println(order);
                lView.getItems().addAll(order.toString());
            }
        }
    }

    @FXML
    public void onCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void switchToSceneUS18(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us18.fxml").toURL());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
