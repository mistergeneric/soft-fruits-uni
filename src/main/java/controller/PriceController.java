package controller;

import Utils.DateUtils;
import model.Batch;
import model.Grade;
import model.Price;
import model.fruit.Fruit;
import service.BatchService;
import service.FruitService;
import service.JsonService;
import service.PriceService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceController {

    public void priceMenu() {
        PriceService priceService = new PriceService();
        FruitService fruitService = new FruitService();
        Scanner scanner = new Scanner(System.in);


        Fruit fruit;
        Price price;


        Set<Price> prices = priceService.findPrices();



        String currentUserChoice = "Y";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("System date:   " + sdf.format(new Date()));



        while (true) {
            boolean isUserFinished = !currentUserChoice.equals("Y");
            if (isUserFinished) break;

            System.out.println("Choose a fruit to price:");
            System.out.println("1. Strawberry");
            System.out.println("2. Raspberry");
            System.out.println("3. Blackberry");
            System.out.println("4. Gooseberry");

            try {
                fruit = fruitService.pickFruit(Integer.parseInt(scanner.nextLine()));
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again");
                continue;
            }
            Map<Grade, Double> priceGrades = priceService.buildPrices(scanner);

            System.out.println("Are prices correct? (Y/N)");
            if(!scanner.nextLine().equals("Y")) {
                System.out.println("Please try again");
                continue;
            }


            price = new Price(fruit, priceGrades, new Date());
            prices = priceService.checkForDuplicatePrices(price, prices);

            prices.add(price);
            if(prices.size() < 4) {
                System.out.println("You must add prices for every fruit type");
                System.out.println();
                System.out.println();
                continue;
            }
            System.out.println("Edit fruit prices again?");
            System.out.println(("Y/N"));

            currentUserChoice = scanner.nextLine();

        }

        priceService.savePrice(prices);

    }

    public void transactionReport() {
        System.out.println("TRANSACTION REPORT");
        System.out.println("Please enter the date for this day: ");
        Scanner scanner = new Scanner(System.in);
        try {
            String inputDate = scanner.nextLine();
            boolean isNotValidDate = DateUtils.isNotValidDate(inputDate);
            if(isNotValidDate) {
                System.out.println("This is not a valid date or date format, please try again by pressing any key");
                scanner.nextLine();
                transactionReport();
            }
            else {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = formatter.parse(inputDate);
                JsonService jsonService = new JsonService();
                ArrayList<Batch> allBatches = jsonService.jsonToBatches();
                System.out.println("List of all batches and price sold for entered date: ");
                allBatches.stream().filter(batch -> DateUtils.isSameDay(batch.getRecievedDate(), date)).forEach(batch -> System.out.println(batch.displayBatchCost()));
                System.out.println("Return to menu with any key + enter");
                scanner.nextLine();
            }
        }
        catch (Exception e) {
            System.out.println("Invalid input. Please try again");
            transactionReport();
        }
    }

}
