package model;

import model.fruit.Fruit;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Price {
    private Fruit fruitType;
    private Map<Grade, Double> fruitPrices;
    private Date datePriced;



    public Price(Fruit fruitType, Map<Grade, Double> fruitPrices, Date datePriced) {
        this.fruitType = fruitType;
        this.fruitPrices = fruitPrices;
        this.datePriced = datePriced;
    }

    public Fruit getFruitType() {
        return fruitType;
    }

    public void setFruitType(Fruit fruitType) {
        this.fruitType = fruitType;
    }

    public Date getDatePriced() {
        return datePriced;
    }

    public void setDatePriced(Date datePriced) {
        this.datePriced = datePriced;
    }

    public Map<Grade, Double> getFruitPrices() {
        return fruitPrices;
    }

    public void setFruitPrices(Map<Grade, Double> fruitPrices) {
        this.fruitPrices = fruitPrices;
    }

    public Double batchCost(Batch batch) {
        double price = 0;
        if(batch.getGrade().size() == 0) {
            return price;
        }
        for (Map.Entry<Grade, Double> entry : this.fruitPrices.entrySet()) {
            price += (entry.getValue() * batch.getGrade().get(entry.getKey().toString()));
        }
        return price;
    }

    public String displayCost(Batch batch) {
        double price = 0;
        for (Map.Entry<Grade, Double> entry : this.fruitPrices.entrySet()) {
            price += (entry.getValue() * batch.getGrade().get(entry.getKey().toString()));

        }
        DecimalFormat df = new DecimalFormat("#.##");
        return price <= 0.0 ?  "No valid data" : "£" + df.format(price);
    }
}
