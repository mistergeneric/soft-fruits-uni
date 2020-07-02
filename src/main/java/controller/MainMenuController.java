package controller;

import Utils.DateUtils;
import service.PriceService;

import java.util.Scanner;

public class MainMenuController {
    public void startApp() {
        PriceService priceService = new PriceService();
        PriceController priceController = new PriceController();

        System.out.println("Welcome to the Renfrewshire Soft Fruits Co_operative");
        System.out.println();
        boolean isNoPriceForToday = DateUtils.isNoPriceForToday(priceService);

        if(isNoPriceForToday || priceService.findPrices() == null) {
            System.out.println("THERE IS NO PRICE IN THE SYSTEM FOR THIS DATE. MOVING TO PRICE CHECK MENU");
            priceController.priceMenu();
        }


        System.out.println(listMenuChoices());
        Scanner scanner = new Scanner(System.in);


        String choice = scanner.nextLine();
        boolean isNotValidMenuChoice = !(choice.equals("2") || choice.equals("1") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6"));


        if (isNotValidMenuChoice) System.exit(1);


        mainMenuChoice(Integer.parseInt(choice));

    }



    public String listMenuChoices() {
        return "Choose an option below:\n" +
                "\n" +
                "1. Create new batch\n" +
                "2. List all batches\n" +
                "3. Show detailed batch information\n" +
                "4. Grade/sort batch\n" +
                "5. Pricing \n" +
                "6. Transaction Report \n" +
                "7. Quit \n";
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
            case 6:
                priceController.transactionReport();
            default:
                break;
        }
    }
}
