package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Photographs.
 */
public class Photographs implements Serializable {
    /**
     * The constant readPhotos.
     */
    public static Scanner readPhotos = new Scanner(System.in);
    private int noOfPhotos;
    /**
     * The Photos.
     */
    public static List<String> photos ;

    /**
     * Instantiates a new Photographs.
     *
     * @param photographs the photographs
     */
    public Photographs(String photographs) {
        validatePhotos(noOfPhotos);
        this.photos = new ArrayList<>(List.of(uploadPhotos(noOfPhotos)));

    }

    /**
     * Upload photos string [ ].
     *
     * @param noOfPhotos the no of photos
     * @return the string [ ]
     */
    public static String[]  uploadPhotos(int noOfPhotos) {
        String[] photos = new String[noOfPhotos];
        for (int i = 0; i < noOfPhotos; i++){
            System.out.println("Insert "+ (i+1) + "º " + "photo URI:");
            photos[i] = readPhotos.nextLine();
        }
        return photos;
    }

    /**
     * Validate photos.
     *
     * @param noOfPhotos the no of photos
     */
    public static void validatePhotos(int noOfPhotos) {
        if (noOfPhotos <= 0 || noOfPhotos > 30) {
            throw new IllegalArgumentException("Number of photos can't be equal to or lower than 0 or exceed 30.");
        }
    }

    /**
     * Gets no of photos.
     *
     * @return the no of photos
     */
    public int getNoOfPhotos() {
        return noOfPhotos;
    }

    /**
     * Gets photos.
     *
     * @return the photos
     */
    public static List<String> getPhotos() {
        return photos;
    }

    /**
     * Create photograph list.
     *
     * @return the list
     */
    public List<String> createPhotographList() {
        return null;
    }
}
