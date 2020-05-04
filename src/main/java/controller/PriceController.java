package controller;

import model.Batch;
import model.Grade;
import model.Price;
import model.fruit.Fruit;
import model.fruit.Strawberry;
import service.FruitService;
import service.PriceService;

import java.text.SimpleDateFormat;
import java.util.*;

public class PriceController {

    public void priceMenu() {
        PriceService priceService = new PriceService();
        FruitService fruitService = new FruitService();
        Scanner scanner = new Scanner(System.in);
        Fruit fruit;
        Price price;
        Set<Price> prices = new HashSet<>();
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

            System.out.println("Are prices correct? (Y/N)");
            if(!scanner.nextLine().equals("Y")) {
                System.out.println("Please try again");
                continue;
            }

            Map<Grade, Double> priceGrades = priceService.buildPrices(scanner);

            price = new Price(fruit, priceGrades, new Date());
            prices = priceService.checkForDuplicatePrices(price, prices);
            prices.add(price);
            if(prices.size() < 4) {
                System.out.println("You must add prices for every fruit type");
                continue;
            }
            System.out.println("Edit fruit prices again?");
            System.out.println(("Y/N"));

            currentUserChoice = scanner.nextLine();

        }

        priceService.savePrice(prices);

    }

}
