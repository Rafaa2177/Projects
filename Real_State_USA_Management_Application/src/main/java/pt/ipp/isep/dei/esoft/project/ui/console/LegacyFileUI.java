package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.LegacyFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Locale.filter;


public class LegacyFileUI implements Runnable {
    Scanner sc = new Scanner(System.in);

    public void importLegacyUI() {
    }

    public void run() {
        String directoryPath = "";
        ArrayList<String> files = new ArrayList<>();
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".csv"))
                    .forEach(path -> files.add(String.valueOf(path.getFileName())));

        } catch (IOException e) {
            //e.printStackTrace();
        }
        System.out.println("Files that are availables to import:");
        int counter = 0;
        for (String file : files) {
            counter++;
            System.out.println(counter + " - " + file);
        }
        try {
            int opt = veriFy("Please choose the path of the file you want to import: ");
            String path = directoryPath + files.get(opt - 1);
            LegacyFile.addOrganizationsFromFile(path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Legacy File uploaded");

    }

    public int veriFy(String print) {
        int number;
        while (true) {
            System.out.println(print);
            try {
                number = Integer.parseInt(sc.next());
                break;
            } catch (NumberFormatException ignore) {
                System.out.println("Not valid!");
            }
        }
        return number;
    }


}
