package SQL_File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SQL {
    public static ArrayList<String> SQL_FILE = new ArrayList<>();

    public static void addSQL(String command){
        SQL_FILE.add(command);

    }
    public void writeSQL(String tabelas) throws IOException {

            FileWriter fileWriter = new FileWriter("C:/Users/franc/IdeaProjects/sem3pi2023_24_g034/BaseDados/meuarquivo.txt");
            for (String command : SQL_FILE) {
                fileWriter.write(command + "\n");
            }
            fileWriter.write(tabelas);
            fileWriter.close();
            System.out.println("Conteúdo escrito com sucesso usando FileWriter.");

    }
}
