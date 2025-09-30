package org.example;

import application.repositories.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class USPL06 {

    public void registarOperacaoDeColheita(){

        Scanner Ler = new Scanner(System.in);
        System.out.println("\nRegistar Operação de Colheita\n");
        System.out.println("Insira a data da operação de colheita (dd-mm-yyyy): ");
        String dataOp = Ler.nextLine();
        System.out.println("Insira a parcela: ");
        String parcela = Ler.nextLine();
        System.out.println("Insira a fruta resultante da operação de colheita: ");
        String fruta = Ler.nextLine();
        System.out.println("Insira a quantidade de fruta colhida: ");
        double quantidadeFruta = Ler.nextDouble();
        Ler.nextLine();
        System.out.println("Insira a unidade da quantidade de fruta colhida: ");
        String unidadeFruta = Ler.nextLine();

        colheitaRegister(dataOp, parcela, fruta, quantidadeFruta, unidadeFruta);
    }

    public void colheitaRegister(String dataOp, String parcela, String fruta, double quantidade, String unidadeArea){
        CallableStatement callStmt = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();

            callStmt = connection.prepareCall("{ call insert_colheita(?,?,?,?,?) }");

            callStmt.setString(1, parcela);
            callStmt.setString(2, dataOp);
            callStmt.setString(3, fruta);
            callStmt.setDouble(4, quantidade);
            callStmt.setString(5, unidadeArea);

            callStmt.execute();
            connection.commit();
        }
        catch (SQLException e) {
            // Lidar com exceções SQL
            e.printStackTrace();
        }
    }
}
