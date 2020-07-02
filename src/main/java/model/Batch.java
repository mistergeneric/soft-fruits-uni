package model;

import Utils.DateUtils;
import model.fruit.Fruit;
import service.PriceService;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Batch {
    private String batchNumber;
    private Fruit fruit;
    private int weight;
    private int farmNumber;
    private Date recievedDate;
    private Map<String, Integer> grade;
    private double totalCost;

    public Batch(Fruit fruit, int weight, int farmNumber) {
        this.fruit = fruit;
        this.weight = weight;
        this.farmNumber = farmNumber;
        this.recievedDate = new Date();
        this.batchNumber = buildBatchNumber();
        this.grade = new HashMap<>();
        totalCost = 0;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFarmNumber() {
        return farmNumber;
    }

    public void setFarmNumber(int farmNumber) {
        this.farmNumber = farmNumber;
    }

    public Date getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(Date recievedDate) {
        this.recievedDate = recievedDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Map<String, Integer> getGrade() {
        return grade;
    }

    public void setGrade(Map<String, Integer> grade) {
        this.grade = new HashMap<>();
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Batch contains: \n" +
                "Fruit type: " + fruit.getTitle() + "\n" +
                "From farm number: " + farmNumber + "\n" +
                "recieved on: " + recievedDate + "\n" +
                "Batch number: " + batchNumber + "\n";
    }

    private String buildBatchNumber() {
        String batchNumber = "";
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy");
        batchNumber += df1.format(this.recievedDate) + "-";
        batchNumber = batchNumber.replaceAll("/", "");
        batchNumber += this.fruit.getBatchCode() + "-";
        batchNumber += String.format("%03d", farmNumber);
        return batchNumber;
    }

    public String printGrades() {
        String printGrades = "";
        if(grade == null || this.grade.size() < 1) {
            return "No graded fruit";
        }
        for (Map.Entry mapElement : grade.entrySet()) {
            printGrades += (mapElement.getKey() + " " + ((int)mapElement.getValue())) + "%";
            printGrades += ":  " + ((this.weight * (int)mapElement.getValue()) /100)  + "KG \n";
        }
        return printGrades;
    }

    public void generateTotalCost() {
        HashSet<Price> prices = new PriceService().findPrices();

        Price foundPrice = prices
                .stream()
                .filter(price -> DateUtils.isSameDay(price.getDatePriced(), this.getRecievedDate()))
                .filter(p -> p.getFruitType().equals(fruit)).findFirst().orElse(null);
        if(foundPrice == null) {
            this.totalCost = 0;
        }
        else {
            this.totalCost = foundPrice.batchCost(this);
        }
    }

    public String displayBatchCost() {
        generateTotalCost();
        StringBuilder batchCost = new StringBuilder("");
        batchCost.append("BatchNumber: ").append(this.batchNumber).append("\n");
        batchCost.append("Fruit: ").append(this.fruit.toString()).append("\n");
        batchCost.append("Weight: ").append(this.weight).append("\n");
        batchCost.append("Date: ").append(this.recievedDate).append("\n");
        batchCost.append("Cost: ").append(this.totalCost).append("\n");
        return batchCost.toString();

    }

    public String formatTotalCost() {
        generateTotalCost();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.totalCost);
    }


}
