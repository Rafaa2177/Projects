package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListVisitRequestController;
import pt.ipp.isep.dei.esoft.project.application.controller.ScheduleVisitResponseController;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.ListVisitRequestControllerJavafx;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScheduleVisitResponseControllerjavaFX {
    public TextField choosenID;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private FXMLLoader fxmlLoader;
    @FXML
    private Button cancel;
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> lView;

    private Button button1;
    private Button button2;

    private ListVisitRequestController controller = new ListVisitRequestController();
    ScheduleVisitResponseController controller1 = new ScheduleVisitResponseController();
    private List<VisitRequest> visitRequestList = controller1.getVisitRequestList();
    private  int id;
    private VisitRequest visitRequest;

    ListVisitRequestControllerJavafx listVisitRequestControllerJavafx = new ListVisitRequestControllerJavafx();
    public void switchToScene1(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us15.2.fxml").toURL());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("US0015");
        stage.setScene(scene);
        stage.show();
    }



    public void confirmYes(ActionEvent actionEvent) {

    }

    @FXML
    public void onCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }


    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        Button successButton = new Button("Operação Concluída");
        successButton.setOnAction(event -> showSuccessMessage());

        StackPane root = new StackPane(successButton);
        Scene scene = new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.setTitle("Success Message Example");
        stage.show();
    }

    public void showSuccessMessage() {
        controller1.emailOption(visitRequest);
        String message = "The visit request that you selected was sucessfully accepted!You will receive an email with all the informations";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Accepted");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();

    }

    public void showDeclineMessage() {
        controller1.emailOption(visitRequest);
        String message = "The visit request that you selected was sucessfully declined!You will receive an email with all the informations";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Declined");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();


    }


    public void switchToScene3(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us16.fxml").toURL());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public VisitRequest chooseId(ActionEvent event){
        lView.getItems().clear();
        String idS = choosenID.getText();
        try {
            id = Integer.parseInt(idS);
        } catch (NumberFormatException e){

        } for (int i=0; i < visitRequestList.size(); i++) {
            if (id == visitRequestList.get(i).getIdVisit()){
                visitRequest = visitRequestList.get(i);
                lView.getItems().add(visitRequest.toString());

            }

        }
        return visitRequest;

    }



}
