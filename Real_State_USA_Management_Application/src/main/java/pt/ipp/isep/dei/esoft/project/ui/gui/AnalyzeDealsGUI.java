package pt.ipp.isep.dei.esoft.project.ui.gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

/**
 * The type Analyze deals gui.
 */
public class AnalyzeDealsGUI extends Application implements Runnable {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us18.fxml").toURL());
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("US0018");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private static boolean javaFxlanched = false;

    static public boolean launchFxApp(boolean javaFxlanched, Class<? extends Application> app){

        if(!javaFxlanched){
            Platform.setImplicitExit(false);
            try{
                new Thread(() -> {
                    try{
                        Application.launch(app);
                    }catch (Exception e){
                        launchFxApp(true,app);
                    }
                }).start();
            }catch (Exception ignored){
            }
            javaFxlanched = true;

        }else {
            Platform.runLater(() -> {
                        try {
                            Application application = app.newInstance();
                            Stage primaryStage = new Stage();
                            application.start(primaryStage);
                        } catch (Exception ignored) {}
                    });
        }
        return  javaFxlanched;
        
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        javaFxlanched = launchFxApp(javaFxlanched, this.getClass());
    }

}
