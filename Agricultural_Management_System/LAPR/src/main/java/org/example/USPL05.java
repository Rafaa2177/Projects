package org.example;

import application.repositories.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class USPL05 {

    public void registarOperacaoDeMonda(){

        Scanner Ler = new Scanner(System.in);
        System.out.println("\nRegistar Operação de Monda\n");
        System.out.println("Insira a data da operação de monda (dd-mm-yyyy): ");
        String dataOp = Ler.nextLine();
        System.out.println("Insira a parcela: ");
        String parcela = Ler.nextLine();
        System.out.println("Insira a cultura da operação de monda: ");
        String cultura = Ler.nextLine();
        System.out.println("Insira a area utilizada: ");
        double areaUtilizada = Ler.nextDouble();


        mondaRegister(dataOp, parcela, cultura, areaUtilizada);
    }

    public void mondaRegister(String dataOp, String parcela, String cultura, double areaUtilizada){
        CallableStatement callStmt = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();

            callStmt = connection.prepareCall("{ call insert_monda(?,?,?,?) }");

            callStmt.setString(1, parcela);
            callStmt.setString(2, dataOp);
            callStmt.setString(3, cultura);
            callStmt.setDouble(4, areaUtilizada);

            callStmt.execute();
            connection.commit();
        }
        catch (SQLException e) {
            // Lidar com exceções SQL
            e.printStackTrace();
        }
    }
}
