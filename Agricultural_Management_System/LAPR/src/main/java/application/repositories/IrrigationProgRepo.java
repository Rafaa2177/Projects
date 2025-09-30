package application.repositories;

import application.domain.Ciclo;
import application.domain.CicloRega;
import application.domain.Intervalo;
import application.domain.IrrigationProg;
import application.utils.ExecuteTask;
import application.utils.ReadFile;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

import static application.utils.ReadFile.lerPlanoDeRega;

public class IrrigationProgRepo {

    private final List<IrrigationProg> irrigationProg;

    public IrrigationProgRepo() {
        irrigationProg = new ArrayList<>();
    }

    public void addIrrigationProg (IrrigationProg irrigationProg1){

        irrigationProg.add(irrigationProg1);
    }

    public List<IrrigationProg> getIrrigationProg(){
        return irrigationProg;
    }

    public void consumirPlanoDeRega(){
        //Ficheiro a ser consumido
        String ficheiroPlano = "src/main/java/application/file.csv";

        try {
            int i= 0;
            for (String line: lerPlanoDeRega(ficheiroPlano)){
                new ExecuteTask().scheduleTask(line, i);
                i++;
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void irrigationRegister(int setorId, String dataOp, int duracao, String horaInicio, int mixId) throws SQLException, InterruptedException {

        System.out.printf("\nInformações da rega: \nData: %s\nSetor id: %d\nDuracao: %d\nHora de ínício: %s\nReceita: %d\n",dataOp,setorId,duracao, horaInicio, mixId );

        inserirOperacaoRega(setorId, dataOp, duracao, horaInicio, mixId);
    }




    public void inserirOperacaoRega(int setorId, String dataOp, int duracao, String horaInicio, int mixId) {

        CallableStatement callStmt = null;

        int operacaoInseridaId = -1;
        try {
            System.out.println("A inserir operação de rega...");
            Connection connection = DatabaseConnection.getInstance().getConnection();

            callStmt = connection.prepareCall("{ call inserir_Rega(?,?,?,?,?) }");

            callStmt.setString(1, dataOp);
            callStmt.setInt(2, setorId);
            callStmt.setInt(3, duracao);
            callStmt.setString(4, horaInicio);
            callStmt.setInt(5, mixId);


            // Execute a atualização
            int affectedRows = callStmt.executeUpdate();
            if (affectedRows > 0) {
                // Obter o último ID inserido
                try (ResultSet generatedKeys = callStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        operacaoInseridaId = generatedKeys.getInt(1);
                        System.out.println("Operação inserida com sucesso com o id " + operacaoInseridaId);
                        System.out.println("\n");
                    }
                }
            }

            connection.commit();
        }
        catch (SQLException e) {
            // Lidar com exceções SQL
            e.printStackTrace();
        }

    }
}
