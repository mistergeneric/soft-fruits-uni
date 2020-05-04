package service;

import model.Batch;
import model.Grade;
import model.Price;
import model.fruit.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.DoubleBinaryOperator;

public class PriceService {
    private final JsonService jsonService = new JsonService();

    public void savePrice(Set<Price> price) {
        jsonService.saveFile("Pricing", price);
    }

    public HashMap<Grade, Double> buildPrices(Scanner scanner) {
        HashMap<Grade, Double> priceGrades = new HashMap<>();


        System.out.println("Enter price below (£ per KG)");
        System.out.print("Grade A:");

        double gradeA = Double.parseDouble(scanner.nextLine());
        priceGrades.put(Grade.A, gradeA);
        System.out.print("Grade B:");
        double gradeB = Double.parseDouble(scanner.nextLine());
        priceGrades.put(Grade.B, gradeB);

        System.out.print("Grade C:");
        double gradeC = Double.parseDouble(scanner.nextLine());
        priceGrades.put(Grade.C, gradeC);
        return priceGrades;
    }

    public Price findPrice() {
        return (Price) jsonService.findData("Pricing", Price.class);
    }

    public Set<Price> checkForDuplicatePrices(Price price, Set<Price> prices) {
        for(Price p : prices) {
            if(p.getFruitType().equals(price.getFruitType()))  {
                prices.remove(p);
            }
        }
        return prices;
    }
}
