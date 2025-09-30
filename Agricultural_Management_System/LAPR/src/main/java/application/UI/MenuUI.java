package application.UI;

import org.example.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuUI implements Runnable{

    public MenuUI(){

    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        Map<Integer,String> menu = new HashMap<>();
        menu.put(1, "Verify if the irrigation program is running at the moment");
        menu.put(2, "Verify if the irrigation program is running at a specified moment");
        menu.put(3, "Generate thirty days irrigation program in a csv file ");
        menu.put(4, "USPL04 - Inserir operacao de semeadura");
        menu.put(5, "USPL05 - Inserir operacao de monda");
        menu.put(6, "USPL06 - Inserir operacao de colheita");
        menu.put(7, "USPL07 - Inserir operacao de fertilizacao");
        menu.put(8, "USPL08 - Inserir operacao de poda");

        while (true) {
            System.out.printf("\n\n\n\n");
            System.out.println("=======================================================================");
            System.out.println("                       Irrigation Programmer                           ");
            System.out.println("=======================================================================");

            for (Map.Entry<Integer, String> entry : menu.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            System.out.println("=======================================================================");
            System.out.print("Enter Option: \n");

            String op = scanner.nextLine();

            switch (op) {
                case "1":
                    new IrrigProgRunningATMUI().run();
                    break;
                case "2":
                    new IrrigationProgramTimeSpecifiedUI().run();
                    break;
                case "3":
                    new thirtyDaysUI().run();
                    break;
                case "4":
                    new USPL04().registarOperacaoDeSemeadura();
                    break;
                case "5":
                    new USPL05().registarOperacaoDeMonda();
                    break;
                case "6":
                    new USPL06().registarOperacaoDeColheita();
                    break;
                case "7":
                    new USPL07().registarOperacaoDeFP();
                    break;
                case "8":
                    new USPL08().registarOperacaoDePoda();
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.\n");

            }
        }
    }
    }

