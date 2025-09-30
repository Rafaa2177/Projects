package Sheets;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Sheet_Reader {
    public Sheet_Reader(int indexOfSheet, int numColunas) throws IOException {
        try {
            FileInputStream arquivoXLSX = new FileInputStream("BaseDados/Legacy_Data_v4.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(arquivoXLSX);

            XSSFSheet folha = workbook.getSheetAt(indexOfSheet); // Escolha a folha que deseja ler

            List<List<String>> dadosFolha = new ArrayList<>();

            for (int rowNum = 1; rowNum <= folha.getLastRowNum(); rowNum++) {
                XSSFRow row = folha.getRow(rowNum);

                if (row != null) {
                    List<String> rowData = new ArrayList<>();

                    for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                        XSSFCell cell = row.getCell(colNum);
                        String cellValue = "";

                        if (cell != null) {
                            if (cell.getCellType() == CellType.STRING) {
                                cellValue = cell.getStringCellValue();
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    // Se a célula for uma data, obtenha o valor da data
                                    cellValue = cell.getLocalDateTimeCellValue().toString();
                                } else {
                                    // Se não for uma data, obtenha o valor numérico
                                    cellValue = Double.toString(cell.getNumericCellValue());
                                }
                            }
                        }
                        if (cellValue.isEmpty()) {
                            cellValue = "null";
                        }
                        String cellValue2 = cellValue.replaceAll("'", "");
                        rowData.add(cellValue2);
                    }
                    dadosFolha.add(rowData);
                }
            }
            arquivoXLSX.close();
            // Exemplo: Imprime o conteúdo das células, incluindo "vazio" para células vazias
            for (List<String> row : dadosFolha) {

                    for (int i = 0; i < numColunas; i++) {
                        if (i >= row.size()) {
                            row.add("null");
                        }
                    }

            }
            int j = 0;
            for (List<String> row : dadosFolha) {
                j++;
                if (indexOfSheet == 0) {
                    //System.out.println(row);
                    Plantas planta = new Plantas(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
                    Plantas.addPlanta(planta);
                }
                if (indexOfSheet == 1) {
                    //System.out.println(row);
                    SetoresRega setoresRega = new SetoresRega(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5));
                    SetoresRega.addSetorRega(setoresRega);
                }
                if (indexOfSheet == 2) {
                    //System.out.println(row);
                    FatorProducao fatorProducao = new FatorProducao(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7), row.get(8), row.get(9), row.get(10), row.get(11), row.get(12), row.get(13), row.get(14), row.get(15), row.get(16), row.get(17), row.get(18), row.get(19), row.get(20));
                    FatorProducao.addFatorProducao(fatorProducao);
                }
                if (indexOfSheet == 3) {
                    //System.out.println(row);
                    ExploracaoAgricola exploracaoAgricola = new ExploracaoAgricola(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
                    ExploracaoAgricola.addExploracaoAgricola(exploracaoAgricola);
                }
                if (indexOfSheet == 4) {
                    //System.out.println(row);
                    Culturas culturas = new Culturas(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6),row.get(7));
                    Culturas.addCulturas(culturas);
                }
                if (indexOfSheet == 5) {
                    //System.out.println(row);
                    CicloRega cicloRega = new CicloRega(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5));
                    CicloRega.addCicloRega(cicloRega);
                }
                if (indexOfSheet == 6) {
                    //System.out.println(row);
                    Operacoes operacoes = new Operacoes(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7), row.get(8), row.get(9), row.get(10), row.get(11), row.get(12), row.get(13), row.get(14), row.get(15), row.get(16));
                    Operacoes.addOperacao(operacoes);
                }
                if (indexOfSheet == 7) {
                    //System.out.println(row);
                    ReceitasFertirrega rega = new ReceitasFertirrega(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
                    ReceitasFertirrega.addReceitasFertirrega(rega);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }



