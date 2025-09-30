package org.example;

import application.repositories.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class USPL08 {
    public void registarOperacaoDePoda(){

        Scanner Ler = new Scanner(System.in);
        System.out.println("\nRegistar Operação de Poda\n");
        System.out.println("Insira a data da operação de poda (dd-mm-yyyy): ");
        String dataOp = Ler.nextLine();
        System.out.println("Insira a parcela: ");
        String parcela = Ler.nextLine();
        System.out.println("Insira a cultura da operação de poda: ");
        String cultura = Ler.nextLine();
        System.out.println("Insira a quantidade utilizada: ");
        double quantidade = Ler.nextDouble();

        podaRegister(dataOp, parcela, cultura, quantidade);
    }

    public void podaRegister(String dataOp, String parcela, String cultura, double quantidade){
        CallableStatement callStmt = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();

            callStmt = connection.prepareCall("{ call insert_poda(?,?,?,?) }");

            callStmt.setString(1, parcela);
            callStmt.setString(2, dataOp);
            callStmt.setString(3, cultura);
            callStmt.setDouble(4, quantidade);

            callStmt.execute();
            connection.commit();
        }
        catch (SQLException e) {
            // Lidar com exceções SQL
            e.printStackTrace();
        }
    }
}
