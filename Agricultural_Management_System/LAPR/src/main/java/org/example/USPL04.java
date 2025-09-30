package org.example;

import application.repositories.DatabaseConnection;

import java.sql.*;
import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

public class USPL04 {

    public void registarOperacaoDeSemeadura() {

        Scanner Ler = new Scanner(System.in);
        System.out.println("\nRegistar Operação de Semeadura\n");
        System.out.println("Insira a data da operação de semeadura (dd-mm-yyyy): ");
        String dataOp = Ler.nextLine();
        System.out.println("Insira a parcela: ");
        String parcela = Ler.nextLine();
        System.out.println("Insira a cultura da operação de semeadura: ");
        String cultura = Ler.nextLine();
        System.out.println("Insira a area utilizada: ");
        double areaUtilizada = Ler.nextDouble();
        Ler.nextLine();
        System.out.println("Insira a unidade da area utilizada: ");
        String unidadeArea = Ler.nextLine();
        System.out.println("Insira a quantidade de sementes utilizada: ");
        double quantidadeSementes = Ler.nextDouble();
        Ler.nextLine();
        System.out.println("Insira a unidade da quantidade de sementes utilizada: ");
        String unidadeSementes = Ler.nextLine();


        try {
            semeaduraRegister(dataOp, parcela, cultura, areaUtilizada, unidadeArea, quantidadeSementes, unidadeSementes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void semeaduraRegister(String dataOp, String parcela, String cultura, double areaUtilizada, String unidadeArea, double quantidadeSementes, String unidadeSementes) throws SQLException {

        CallableStatement callStmt = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();

            callStmt = connection.prepareCall("{ call insert_semeadura(?,?,?,?,?,?,?) }");
            callStmt.setString(1, parcela);
            callStmt.setString(2, dataOp);
            callStmt.setString(3, cultura);
            callStmt.setDouble(4, areaUtilizada);
            callStmt.setString(5, unidadeArea);
            callStmt.setDouble(6, quantidadeSementes);
            callStmt.setString(7, unidadeSementes);

            callStmt.execute();
            connection.commit();
        }
        catch (SQLException e) {
            // Lidar com exceções SQL
            e.printStackTrace();
        }
        finally {
            if(!Objects.isNull(callStmt)) {
                callStmt.close();
            }
        }
    }
}
