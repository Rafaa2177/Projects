import SQL_File.SQL;
import SQL_File.Tabelas;
import Sheets.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        readFile();
        Tabelas tabelasConstantes = new Tabelas();
        String tabelas = tabelasConstantes.getTabelas();
        System.out.println(tabelas);
        SQL sql = new SQL();
        sql.writeSQL(tabelas);
    }

    public static void readFile() throws IOException {
        try {
            FileInputStream arquivoXLSX = new FileInputStream("BaseDados/Legacy_Data_v4.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(arquivoXLSX);

            int numFolhas = workbook.getNumberOfSheets();

            System.out.println("Número de folhas no arquivo XLSX: " + numFolhas);

            for (int i = 0; i < numFolhas; i++) {
                XSSFSheet folha = workbook.getSheetAt(i);
                int numColunas = 0;

                for (int j = 0; j < folha.getPhysicalNumberOfRows(); j++) {
                    XSSFRow row = folha.getRow(j);
                    if (row != null) {
                        int lastCellNum = row.getLastCellNum();
                        if (lastCellNum > numColunas) {
                            numColunas = lastCellNum;
                        }
                    }
                }
            }

            new Sheet_Reader(0, 5 );
            new Sheet_Reader(1, 6 );
            new Sheet_Reader(2, 21 );
            new Sheet_Reader(3, 5 );
            new Sheet_Reader(4, 8 );
            new Sheet_Reader(5, 6 );
            new Sheet_Reader(6, 17 );
            new Sheet_Reader(7, 5 );
            arquivoXLSX.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}