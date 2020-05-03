package controller;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MenuController {
    public void startApp() {
        System.out.println("Welcome to the Renfrewshire Soft Fruits Co_operative");
        System.out.println();
        System.out.println("Choose an option below:");
        System.out.println();
        System.out.println("1. Create new batch");
        System.out.println("2. List all batches");
        System.out.println("3. Show detailed batch information");
        System.out.println("4. Grade/sort batch");
        System.out.println("5. Quit");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (!(choice.equals("2") || choice.equals("1") || choice.equals("3") || choice.equals("4"))) System.exit(1);
        mainMenuChoice(Integer.parseInt(choice));

    }
    public void mainMenuChoice(int choice) {
        BatchController batchController = new BatchController();
        switch(choice) {
            case 1:
                batchController.makeBatch();
                break;
            case 2:
                batchController.listAllBatches();
                break;
            case 3:
                batchController.showBatchDetail();
                break;
            case 4:
                batchController.gradeFruit();
                break;
            default:
                break;
        }
    }
}
