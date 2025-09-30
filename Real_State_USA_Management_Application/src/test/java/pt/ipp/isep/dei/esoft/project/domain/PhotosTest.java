package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class PhotosTest {
    private Photographs photographs;


    public void setup() {
        // Prepare input for test
        String input = "photo1\nphoto2\nphoto3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int noOfPhotos = 3;
        photographs = new Photographs(String.valueOf(noOfPhotos));
    }


    public void testConstructor_ValidArguments_ObjectCreated() {
        // Arrange
        int noOfPhotos = 3;

        // Act
        Photographs photographs = new Photographs(String.valueOf(noOfPhotos));

        // Assert
        Assertions.assertNotNull(photographs);
        Assertions.assertEquals(noOfPhotos, photographs.getNoOfPhotos());
    }


    public void testUploadPhotos_ValidNumberOfPhotos_ReturnsArrayWithPhotos() {
        // Arrange
        int noOfPhotos = 3;
        String[] expectedPhotos = {"photo1", "photo2", "photo3"};

        // Act
        String[] photos = Photographs.uploadPhotos(noOfPhotos);

        // Assert
        Assertions.assertArrayEquals(expectedPhotos, photos);
    }


    public void testValidatePhotos_ValidNumberOfPhotos_NoExceptionThrown() {
        // Arrange
        int noOfPhotos = 3;

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> Photographs.validatePhotos(noOfPhotos));
    }


    public void testValidatePhotos_ZeroNumberOfPhotos_ExceptionThrown() {
        // Arrange
        int noOfPhotos = 0;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Photographs.validatePhotos(noOfPhotos));
    }


    public void testGetNoOfPhotos_ReturnsCorrectNumberOfPhotos() {
        // Arrange
        int expectedNoOfPhotos = 3;

        // Act
        int noOfPhotos = photographs.getNoOfPhotos();

        // Assert
        Assertions.assertEquals(expectedNoOfPhotos, noOfPhotos);
    }


    public void testGetPhotos_ReturnsCorrectListOfPhotos() {
        // Arrange
        List<String> expectedPhotos = List.of("photo1", "photo2", "photo3");

        // Act
        List<String> photos = Photographs.getPhotos();

        // Assert
        Assertions.assertEquals(expectedPhotos, photos);
    }


    public void testCreatePhotographList_ReturnsEmptyList() {
        // Act
        List<String> photographList = photographs.createPhotographList();

        // Assert
        Assertions.assertTrue(photographList.isEmpty());
    }
}

