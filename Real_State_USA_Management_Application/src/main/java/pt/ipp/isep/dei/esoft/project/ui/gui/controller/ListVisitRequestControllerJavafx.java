package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListVisitRequestController;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListVisitRequestControllerJavafx implements Initializable {
private static List<String> content=new ArrayList<>();
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private FXMLLoader fxmlLoader;
    @FXML
    private Button cancel;
    @FXML
    private DatePicker dateBegin;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private Button continueButton;
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> lView;
    private LocalDate begin;
    private LocalDate end;

    private ListVisitRequestController controller = new ListVisitRequestController();

    public void switchToScene1(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us15.fxml").toURL());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        content=new ArrayList<>();
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us15.2.fxml").toURL());
        List<VisitRequest> list;

        if (begin == null || end == null){
            lView.getItems().add("You must choose the dates before");
        } else {
            list = controller.getSortedVisitRequestList(controller.getAgentList(),begin, end);
            for (int i = 0; i < list.size(); i++){
                content.add(list.get(i).toString());
            }

        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static List<String> getContent() {return content;}

    @FXML
    void onReadBeginDate(ActionEvent event) {
        this.begin = dateBegin.getValue();
    }

    @FXML
    void onReadEndDate(ActionEvent event) {this.end = dateEnd.getValue();}

    @FXML
    public void onCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (lView==null) {
            lView=new ListView<>();
        }
        lView.getItems().addAll(content);
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us16.fxml").toURL());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


}
