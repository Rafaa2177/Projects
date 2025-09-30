package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class ScheduleVisitResponseGUI extends Application implements Runnable {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us16.fxml").toURL());
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("US016");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void run() {

        launch();
    }
}



