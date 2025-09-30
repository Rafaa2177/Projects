package org.example;

import application.UI.MenuUI;
import application.utils.ReadFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        loadProperties();
        String filePath= "src/main/resources/newInstructions.txt";
        ReadFile.lerInstrucoes(filePath);

        MenuUI menu = new MenuUI();
         menu.run();
    }

    private static void loadProperties() throws IOException {
        Properties properties = new Properties(System.getProperties());

        InputStream inputStream = new Main().getClass().getClassLoader().getResourceAsStream("application.properties");
        properties.load(inputStream);
        inputStream.close();

        System.setProperties(properties);
    }
}