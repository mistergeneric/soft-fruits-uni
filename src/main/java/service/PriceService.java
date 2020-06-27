package service;

import com.google.gson.reflect.TypeToken;
import model.Batch;
import model.Grade;
import model.Price;
import model.fruit.Fruit;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public HashSet<Price> findPrices() {
        Type setType = new TypeToken<HashSet<Price>>(){}.getType();
        return (HashSet<Price>) jsonService.findData("Pricing", setType);

    }

    public Price costOfFruit(Date date, Fruit fruit) {
        HashSet<Price> prices = findPrices();
        Price price = new Price(fruit, new HashMap<>(), date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(Price p : prices) {
            String format1 = sdf.format(p.getDatePriced());
            String format2 = sdf.format(date);
            boolean isDay = sdf.format(p.getDatePriced()).equals(sdf.format(date));
            boolean isFruit = p.getFruitType().equals(fruit);
            if(isDay && isFruit) {
                return p;
            }
        }
        return price;
    }

    public Set<Price> checkForDuplicatePrices(Price price, Set<Price> prices) {
        if (prices == null) {
            prices = new HashSet<>();
        }
        prices.removeIf(p -> p.getFruitType().equals(price.getFruitType()));
        return prices;
    }

    public Date findLatestPricedDate(Set<Price> prices) {
        Date date = null;
        for(Price p : prices) {
            if (date == null) {
                date = p.getDatePriced();
            }
            if (date.before(p.getDatePriced())) {
                date = p.getDatePriced();
            }
        }
        return date;
    }
}
