package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.List;

public class AnalyzeDealsController {
    Repositories repositories = Repositories.getInstance();
    OrderRepository orderRepository = repositories.getOrderRepository();
    AnnouncementRepository announcementRepository = repositories.getAnnouncementRepository();
    ListDealsController listDealsController = new ListDealsController();
    private List<PurchaseOrder> purchaseOrderList = listDealsController.getDealList();
    private double Prediction;

    public List<PurchaseOrder> getPurchaseOrderList() {
        return purchaseOrderList;
    }

    public void linearRegression(double area) {
        System.out.println(area);

        SimpleRegression simpleRegression = new SimpleRegression();
        for (Announcement ann : announcementRepository.getAnnouncements()) {
            if (!ann.getRequest().getProperty().getPropertyType().equals(PropertyType.LAND)) {
                for (PurchaseOrder orders : orderRepository.getOrdersByAnnouncement(ann)) {
                    if (orders.getStatus() == Status.ACCEPT) {
                        simpleRegression.addData(ann.getRequest().getProperty().getAreaM2(), orders.getAmountOfered());
                    }
                }
            }
        }
        double intercept = simpleRegression.getIntercept();
        double slope = simpleRegression.getSlope();
        double prediction = simpleRegression.predict(area);

        intercept = Math.round(intercept * 1000.0) / 1000.0;
        slope = Math.round(slope * 1000.0) / 1000.0;
        prediction = Math.round(prediction * 1000.0) / 1000.0;

        System.out.println("Choosen: " + area);
        System.out.println("Intercept: " + intercept);
        System.out.println("Slope: " + slope);
        System.out.println("Prediction: " + prediction);
        Prediction = prediction;
    }

    public void linearRegressionD(double distanceCityCentre) {

        SimpleRegression simpleRegression = new SimpleRegression();

        for (Announcement ann : announcementRepository.getAnnouncements()) {
            if (!ann.getRequest().getProperty().getPropertyType().equals(PropertyType.LAND)) {

                for (PurchaseOrder orders : orderRepository.getOrdersByAnnouncement(ann)) {
                    if (orders.getStatus() == Status.ACCEPT) {
                        simpleRegression.addData(ann.getRequest().getProperty().getCityCentreDistance(), orders.getAmountOfered());
                    }
                }
            }
        }
        double intercept = simpleRegression.getIntercept();
        double slope = simpleRegression.getSlope();
        double prediction = simpleRegression.predict(distanceCityCentre);

        intercept = Math.round(intercept * 1000.0) / 1000.0;
        slope = Math.round(slope * 1000.0) / 1000.0;
        prediction = Math.round(prediction * 1000.0) / 1000.0;

        System.out.println("Choosen: " + distanceCityCentre);
        System.out.println("Intercept: " + intercept);
        System.out.println("Slope: " + slope);
        System.out.println("Prediction: " + prediction);
        Prediction = prediction;
    }

    public void linearRegressionB(double numerOfBedrooms) {

        SimpleRegression simpleRegression = new SimpleRegression();
        for (Announcement ann : announcementRepository.getAnnouncements()) {

            if (!ann.getRequest().getProperty().getPropertyType().equals(PropertyType.LAND)) {
                for (PurchaseOrder orders : orderRepository.getOrdersByAnnouncement(ann)) {
                    if (orders.getStatus() == Status.ACCEPT) {
                        simpleRegression.addData(ann.getRequest().getProperty().getCityCentreDistance(), orders.getAmountOfered());
                    }
                }
            }
        }
        double intercept = simpleRegression.getIntercept();
        double slope = simpleRegression.getSlope();
        double prediction = simpleRegression.predict(numerOfBedrooms);

        intercept = Math.round(intercept * 1000.0) / 1000.0;
        slope = Math.round(slope * 1000.0) / 1000.0;
        prediction = Math.round(prediction * 1000.0) / 1000.0;

        System.out.println("Choosen: " + numerOfBedrooms);
        System.out.println("Intercept: " + intercept);
        System.out.println("Slope: " + slope);
        System.out.println("Prediction: " + prediction);
        Prediction = prediction;
    }

    public void linearRegressionBA(double numerOfBathrooms) {

        SimpleRegression simpleRegression = new SimpleRegression();

        for (Announcement ann : announcementRepository.getAnnouncements()) {
            if (!ann.getRequest().getProperty().getPropertyType().equals(PropertyType.LAND)) {
                for (PurchaseOrder orders : orderRepository.getOrdersByAnnouncement(ann)) {
                    if (orders.getStatus() == Status.ACCEPT) {
                        simpleRegression.addData(ann.getRequest().getProperty().getCityCentreDistance(), orders.getAmountOfered());
                    }
                }
            }
        }
        double intercept = simpleRegression.getIntercept();
        double slope = simpleRegression.getSlope();
        double prediction = simpleRegression.predict(numerOfBathrooms);

        intercept = Math.round(intercept * 1000.0) / 1000.0;
        slope = Math.round(slope * 1000.0) / 1000.0;
        prediction = Math.round(prediction * 1000.0) / 1000.0;

        System.out.println("Choosen: " + numerOfBathrooms);
        System.out.println("Intercept: " + intercept);
        System.out.println("Slope: " + slope);
        System.out.println("Prediction: " + prediction);
        Prediction = prediction;
    }

    public void linearRegressionP(double numerOfParkingSpaces) {

        SimpleRegression simpleRegression = new SimpleRegression();

        for (Announcement ann : announcementRepository.getAnnouncements()) {
            if (!ann.getRequest().getProperty().getPropertyType().equals(PropertyType.LAND)) {
                for (PurchaseOrder orders : orderRepository.getOrdersByAnnouncement(ann)) {
                    if (orders.getStatus() == Status.ACCEPT) {
                        simpleRegression.addData(ann.getRequest().getProperty().getCityCentreDistance(), orders.getAmountOfered());
                    }
                }
            }
        }
        double intercept = simpleRegression.getIntercept();
        double slope = simpleRegression.getSlope();
        double prediction = simpleRegression.predict(numerOfParkingSpaces);

        intercept = Math.round(intercept * 1000.0) / 1000.0;
        slope = Math.round(slope * 1000.0) / 1000.0;
        prediction = Math.round(prediction * 1000.0) / 1000.0;

        System.out.println("Choosen: " + numerOfParkingSpaces);
        System.out.println("Intercept: " + intercept);
        System.out.println("Slope: " + slope);
        System.out.println("Prediction: " + prediction);
        Prediction = prediction;
    }

    public void multiLinearRegression(double area, double distanceFromCenter, double nBedrooms, double nBathrooms, double nParking) {
        double[][] X = new double[announcementRepository.getAnnouncements().size()][5];
        double[] Y = new double[announcementRepository.getAnnouncements().size()];

        for (int i = 0; i < announcementRepository.getAnnouncements().size(); i++) {
            int j = 0;
            if (announcementRepository.getAnnouncements().get(i).getRequest().getProperty() instanceof Apartment) {
                Apartment apartment = (Apartment) announcementRepository.getAnnouncements().get(i).getRequest().getProperty();
                X[i][j] = apartment.getAreaM2();
                X[i][j + 1] = apartment.getCityCentreDistance();
                X[i][j + 2] = apartment.getNumberBedrooms();
                X[i][j + 3] = apartment.getNumberBathrooms();
                X[i][j + 4] = apartment.getNumberParkingSpaces();
            }
            if (announcementRepository.getAnnouncements().get(i).getRequest().getProperty() instanceof House) {
                House house = (House) announcementRepository.getAnnouncementList().get(i).getRequest().getProperty();
                X[i][j] = house.getAreaM2();
                X[i][j + 1] = house.getCityCentreDistance();
                X[i][j + 2] = house.getNumberBedrooms();
                X[i][j + 3] = house.getNumberBathrooms();
                X[i][j + 4] = house.getNumberParkingSpaces();
            }
        }
        int count = 0;
        for (Announcement ann : announcementRepository.getAnnouncements()) {
            for (PurchaseOrder orders : orderRepository.getOrdersByAnnouncement(ann)) {
                if (orders.getStatus() == Status.ACCEPT) {
                    Y[count] = orders.getAmountOfered();
                }
            }
            count++;
        }

        // Create regression model
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.setNoIntercept(true); // Exclude the intercept term if not needed
        regression.newSampleData(Y, X);

        // Compute regression coefficients
        double[] coefficients = regression.estimateRegressionParameters();

        // Print coefficients
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println("Coefficient " + i + ": " + coefficients[i]);
        }

        // Make predictions on new data
        double[][] newX = {{area, distanceFromCenter, nBedrooms, nBathrooms, nParking}};

        RealMatrix newXMatrix = new Array2DRowRealMatrix(newX);
        RealMatrix coefficientMatrix = new Array2DRowRealMatrix(new double[][]{coefficients});
        RealMatrix predictions = newXMatrix.multiply(coefficientMatrix.transpose());

        // Print predictions
        for (int i = 0; i < predictions.getRowDimension(); i++) {
            System.out.println();
            System.out.println("Prediction " + (i + 1) + ": " + predictions.getEntry(i, 0));
            Prediction = predictions.getEntry(i, 0);
        }

        System.out.println();
    }

    public double getPrediction() {
        return Prediction;
    }
}
