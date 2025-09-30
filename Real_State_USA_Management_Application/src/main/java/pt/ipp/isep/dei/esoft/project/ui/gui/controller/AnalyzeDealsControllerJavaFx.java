package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AnalyzeDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AnalyzeDealsControllerJavaFx implements Initializable {
    /**
     * The warning label to display messages or warnings.
     */
    @FXML
    public Label LabelWarning;
    /**
     * The confirm button to confirm user actions.
     */
    @FXML
    public Button confirmButton;
    /**
     * The text field for choosing the value of parameter D.
     */
    @FXML
    public TextField chooseValueParameterD;
    /**
     * The text field for choosing the value of parameter B.
     */
    @FXML
    public TextField chooseValueParameterB;
    /**
     * The text field for choosing the value of parameter BA.
     */
    @FXML
    public TextField chooseValueParameterBA;
    /**
     * The text field for choosing the value of parameter P.
     */
    @FXML
    public TextField chooseValueParameterP;
    /**
     * The text field for choosing the value of parameter A.
     */
    @FXML
    public TextField chooseValueParameterA;
    /**
     * The button for navigating back to the list view.
     */
    public Button backToList;
    /**
     * The list view for displaying deal information.
     */
    public ListView lView;

    /**
     * The stage for displaying the scene.
     */
    @FXML
    private Stage stage;
    /**
     * The scene for displaying the JavaFX UI.
     */
    @FXML
    private Scene scene;
    /**
     * The FXMLLoader for loading FXML files.
     */
    @FXML
    private FXMLLoader fxmlLoader;
    /**
     * The list view for displaying the price of the deal.
     */
    @FXML
    public ListView priceOfDeal;
    /**
     * The list view for displaying the estimated price.
     */
    @FXML
    public ListView priceEstimated;
    /**
     * The button for navigating back.
     */
    @FXML
    public Button backButton;
    /**
     * The line chart for displaying the analysis graph.
     */
    @FXML
    public LineChart grafic;
    /**
     * The text field for choosing the ID of the deal.
     */
    @FXML
    public TextField chooseID;
    /**
     * The button for performing multiple linear regression.
     */
    @FXML
    public Button multiLRButton;
    /**
     * The button for performing linear regression.
     */

    @FXML
    public Button linearRButton;
    /**
     * The combo box for selecting parameters.
     */
    @FXML
    private ComboBox parameters;
    @FXML
    private TextField chooseValueParameter;
    int id;
    double price, distanceValue, areaValue, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces;
    double priceEs,distanceValue2, areaValue2, numberOfBedrooms2, numberOfBathrooms2, numberOfParkingSpaces2;
    /**
     * The content list for deal information.
     */
    private static List<String> content=new ArrayList<>();
    /**
     * The content list for estimated deal information.
     */
    private static List<String> content2=new ArrayList<>();
    /**
     * The controller for analyzing deals.
     */
    private AnalyzeDealsController controller = new AnalyzeDealsController();
    /**
     * The purchase order object.
     */
    private PurchaseOrder purchaseOrder;
    /**
     * The list of purchase orders.
     */
    private List<PurchaseOrder> listPurchaseOrder = controller.getPurchaseOrderList();

    /**
     * Handles the action of navigating back.
     *
     * @param event The action event.
     * @throws IOException if an I/O error occurs.
     */
    public void backButton(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us18.fxml").toURL());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Switches to scene 4.
     *
     * @param event The action event.
     * @throws IOException if an I/O error occurs.
     */
    public void switchToScene4(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us18.4.fxml").toURL());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Switches to scene 5.
     *
     * @param event The action event.
     * @throws IOException if an I/O error occurs.
     */
    public void switchToScene5(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us18.5.fxml").toURL());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Switches to scene US17.
     *
     * @param event The action event.
     * @throws IOException if an I/O error occurs.
     */
    public void switchToSceneUS17(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us17.fxml").toURL());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("US0017");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Performs linear regression analysis.
     *
     * @param event The action event.
     * @throws IOException if an I/O error occurs.
     */
    public void LinearRegression(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us18.2.fxml").toURL());
        String valueS = parameters.getValue().toString();
        content = new ArrayList<>();
        content2 = new ArrayList<>();

        switch (valueS) {
            case "AREA":
                this.areaValue = readValueParameter(event);
                controller.linearRegression(areaValue);
                content.add(String.valueOf(price)) ;
                content2.add(String.valueOf(controller.getPrediction()));
                break;

            case "DISTANCE":
                this.distanceValue = readValueParameter(event);
                controller.linearRegressionD(distanceValue);
                content.add(String.valueOf(price));
                content2.add(String.valueOf(controller.getPrediction()));
                break;

            case "BEDROOMS":
                this.numberOfBedrooms = readValueParameter(event);
                controller.linearRegressionB(numberOfBedrooms);
                content.add(String.valueOf(price));
                content2.add(String.valueOf(controller.getPrediction()));

                break;

            case "BATHROOMS":
                this.numberOfBathrooms = readValueParameter(event);
                controller.linearRegressionBA(numberOfBathrooms);
                content.add(String.valueOf(price));
                content2.add(String.valueOf(controller.getPrediction()));
                break;

            case "PARKING":
                this.numberOfParkingSpaces = readValueParameter(event);
                controller.linearRegressionP(numberOfParkingSpaces);
                content.add(String.valueOf(price));
                content2.add(String.valueOf(controller.getPrediction()));
                break;

            default:
                System.out.println("You have to choose a option");
                break;
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Performs multiple linear regression analysis.
     *
     * @param event The action event.
     * @throws IOException if an I/O error occurs.
     */

    public void MultiLRegression(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/main/resources/fxml/us18.3.fxml").toURL());
        content = new ArrayList<>();
        content2 = new ArrayList<>();

        readValueParameter2(event);
        controller.multiLinearRegression(areaValue2, distanceValue2,numberOfBedrooms2, numberOfBathrooms2, numberOfParkingSpaces2);
        content2.add(String.valueOf(price));
        content2.add(String.valueOf(controller.getPrediction()));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public PurchaseOrder chooseDealID(ActionEvent event) {
        lView.getItems().clear();
        String idS = chooseID.getText();
        try {
            id = Integer.parseInt(idS);
        } catch (NumberFormatException e) {
            LabelWarning.setText("Number ID is not in the right format!\n Enter only numbers please.");
        } catch (Exception e) {
            LabelWarning.setText(e.toString());
        }
            for (int i=0; i < listPurchaseOrder.size(); i++) {
                if (id == listPurchaseOrder.get(i).getIdOrder()) {
                    purchaseOrder = listPurchaseOrder.get(i);
                    lView.getItems().addAll(purchaseOrder.toString());
                    price = purchaseOrder.getAnnouncement().getRequest().getPrice();
                }
            }
        return purchaseOrder;
    }

    public void confirmText(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("You're about to confirm the ID!");
        alert.setContentText("Do you want to confirm the Deal ID you've chosen?");
        alert.showAndWait();
    }
    public double readValueParameter(ActionEvent event) {
        String value = chooseValueParameter.getText();
        return Double.parseDouble(value);
    }
    public void readValueParameter2(ActionEvent event) {
         areaValue2  = Double.parseDouble(chooseValueParameterA.getText());
         distanceValue2 = Double.parseDouble(chooseValueParameterD.getText());
         numberOfBedrooms2 = Double.parseDouble(chooseValueParameterB.getText());
         numberOfBathrooms2 = Double.parseDouble(chooseValueParameterBA.getText());
         numberOfParkingSpaces2 = Double.parseDouble(chooseValueParameterP.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(parameters  == null){
            return;
        }

        parameters.getItems().addAll("AREA", "DISTANCE", "BEDROOMS", "BATHROOMS", "PARKING");
        parameters.setValue("Select the Independent Variable");
        parameters.getValue();

        if (priceOfDeal==null) {
            priceOfDeal=new ListView<>();
        }
        priceOfDeal.getItems().addAll(content);

        if (priceEstimated==null) {
            priceEstimated=new ListView<>();
        }
        priceEstimated.getItems().addAll(content2);

    }
}
