package controller;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import service.PriceService;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.Scanner;

public class MenuController {
    public void startApp() {
        PriceService priceService = new PriceService();
        PriceController priceController = new PriceController();

        System.out.println("Welcome to the Renfrewshire Soft Fruits Co_operative");
        System.out.println();

        //is today
        if(priceService.findPrice() == null || priceService.findPrice().getDatePriced().compareTo(new Date()) < 0) {
            System.out.println("THERE IS NO PRICE IN THE SYSTEM FOR THIS DATE. MOVING TO PRICE CHECK MENU");
            priceController.priceMenu();
        }


        System.out.println(listMenuChoices());
        Scanner scanner = new Scanner(System.in);


        String choice = scanner.nextLine();
        if (!(choice.equals("2") || choice.equals("1") || choice.equals("3") || choice.equals("4") || choice.equals("5"))) System.exit(1);
        mainMenuChoice(Integer.parseInt(choice));

    }


    public String listMenuChoices() {
        return "Choose an option below:\n" +
                "\n" +
                "1. Create new batch\n" +
                "2. List all batches\n" +
                "3. Show detailed batch information\n" +
                "4. Grade/sort batch\n" +
                "5. Quit\n";
    }

    public void mainMenuChoice(int choice) {
        BatchController batchController = new BatchController();
        PriceController priceController = new PriceController();
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
            case 5:
                priceController.priceMenu();
                break;
            default:
                break;
        }
    }
}
