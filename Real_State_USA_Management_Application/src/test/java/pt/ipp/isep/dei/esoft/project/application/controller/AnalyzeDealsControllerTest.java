package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalyzeDealsControllerTest {
    private AnalyzeDealsController analyzeDealsController;

    @BeforeEach
    public void setUp() {
        analyzeDealsController = new AnalyzeDealsController();
    }

    @Test
    public void testLinearRegression() {
        // Create sample data
        double area = 100.0;

        // Perform linear regression
        analyzeDealsController.linearRegression(area);

        // Assert the prediction value is not zero
        assertTrue(analyzeDealsController.getPrediction() != 0.0);
    }

    @Test
    public void testLinearRegressionD() {
        // Create sample data
        double distanceCityCentre = 5.0;

        // Perform linear regression
        analyzeDealsController.linearRegressionD(distanceCityCentre);

        // Assert the prediction value is not zero
        assertTrue(analyzeDealsController.getPrediction() != 0.0);
    }

    @Test
    public void testLinearRegressionB() {
        // Create sample data
        double numberOfBedrooms = 2.0;

        // Perform linear regression
        analyzeDealsController.linearRegressionB(numberOfBedrooms);

        // Assert the prediction value is not zero
        assertTrue(analyzeDealsController.getPrediction() != 0.0);
    }

    @Test
    public void testLinearRegressionBA() {
        // Create sample data
        double numberOfBathrooms = 2.0;

        // Perform linear regression
        analyzeDealsController.linearRegressionBA(numberOfBathrooms);

        // Assert the prediction value is not zero
        assertTrue(analyzeDealsController.getPrediction() != 0.0);
    }

    @Test
    public void testLinearRegressionP() {
        // Create sample data
        double numberOfParkingSpaces = 1.0;

        // Perform linear regression
        analyzeDealsController.linearRegressionP(numberOfParkingSpaces);

        // Assert the prediction value is not zero
        assertTrue(analyzeDealsController.getPrediction() != 0.0);
    }

    @Test
    public void testMultiLinearRegression() {
        // Create sample data
        double area = 100.0;
        double distanceFromCenter = 5.0;
        double nBedrooms = 2.0;
        double nBathrooms = 2.0;
        double nParking = 1.0;

        // Perform multi-linear regression
        analyzeDealsController.multiLinearRegression(area, distanceFromCenter, nBedrooms, nBathrooms, nParking);

        // Assert the prediction value is not zero
        assertTrue(analyzeDealsController.getPrediction() != 0.0);
    }
}
