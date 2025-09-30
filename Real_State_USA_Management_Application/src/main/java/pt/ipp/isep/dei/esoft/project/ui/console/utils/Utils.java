package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class Utils {

    private static final String SERIALIZATION_FILENAME ="repositories.ser";

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }

    static public Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    public static void serialize(Repositories repo) throws IOException {

        FileOutputStream file = new FileOutputStream(SERIALIZATION_FILENAME);
        ObjectOutputStream out = new ObjectOutputStream(file);
        // Method for serialization of object
        out.writeObject(repo);
        out.close();
        file.close();

        System.out.println("Object has been serialized");
    }

    public static Repositories deserialize() throws IOException, ClassNotFoundException,EOFException {
        // Reading the object from a file
        FileInputStream file = null;
        try {
            file = new FileInputStream(SERIALIZATION_FILENAME);
        } catch (FileNotFoundException e) {
            new File(SERIALIZATION_FILENAME).createNewFile();
            file=new FileInputStream(SERIALIZATION_FILENAME);
        }
        ObjectInputStream in = new ObjectInputStream(file);

        // Method for deserialization of object
        Repositories repo = (Repositories)in.readObject();
        repo.getAuthenticationRepository().sync();
        in.close();
        file.close();

        return repo;
    }

    static public boolean confirmEng(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

        return input.equalsIgnoreCase("yes");
    }
}
