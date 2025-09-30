package org.example;

import application.repositories.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class USPL07 {

        public void registarOperacaoDeFP(){

            //fpRegister("06/10/2023", "Campo Novo", "Fertimax Extrume de Cavalo", "Solo", 0.5, "ha", 100, "kg");

            Scanner Ler = new Scanner(System.in);
            System.out.println("\nRegistar Operação de Aplicação de Fator de Produção:\n");
            System.out.println("Insira a data da operação de aplicação de fator de produção (dd-mm-yyyy): ");
            String dataOp = Ler.nextLine();
            System.out.println("Insira a parcela: ");
            String parcela = Ler.nextLine();
            System.out.println("Insira o fator de produção: ");
            String fatorProducao = Ler.nextLine();
            System.out.println("Insira o tipo de fator de produção(solo, foliar): ");
            String tipoFatorProducao = Ler.nextLine();
            System.out.println("Insira a area utilizada: ");
            double areaUtilizada = Ler.nextDouble();
            Ler.nextLine();
            System.out.println("Insira a unidade da area utilizada: ");
            String unidadeArea = Ler.nextLine();
            System.out.println("Insira a quantidade de fator de produção aplicada: ");
            double quantidadeFatorProducao = Ler.nextDouble();
            Ler.nextLine();
            System.out.println("Insira a unidade da quantidade de fator de produção aplicada: ");
            String unidadeFatorProducao = Ler.nextLine();

            fpRegister(dataOp, parcela, fatorProducao, tipoFatorProducao, areaUtilizada, unidadeArea, quantidadeFatorProducao, unidadeFatorProducao);

        }

        public void fpRegister(String dataOp, String parcela, String fatorProducao, String tipoFatorProducao, double areaUtilizada, String unidadeArea, double quantidadeFatorProducao, String unidadeFatorProducao){
            CallableStatement callStmt = null;
            try {
                Connection connection = DatabaseConnection.getInstance().getConnection();

                callStmt = connection.prepareCall("{ call insert_fertilizacao(?,?,?,?,?,?,?,?,?) }");

                callStmt.setString(1, parcela);
                callStmt.setString(2, dataOp);
                callStmt.setString(3, fatorProducao);
                callStmt.setString(4, tipoFatorProducao);
                callStmt.setDouble(5, areaUtilizada);
                callStmt.setString(6, unidadeArea);
                callStmt.setDouble(7, quantidadeFatorProducao);
                callStmt.setString(8, unidadeFatorProducao);
                callStmt.setString(9, null);

                callStmt.execute();
                connection.commit();
            }
            catch (SQLException e) {
                // Lidar com exceções SQL
                e.printStackTrace();
            }
        }
}
