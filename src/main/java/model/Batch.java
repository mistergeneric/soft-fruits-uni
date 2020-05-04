package model;

import model.fruit.Fruit;
import model.fruit.Strawberry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Batch {
    private String batchNumber;
    private Fruit fruit;
    private int weight;
    private int farmNumber;
    private Date recievedDate;
    private Map<String, Integer> grade;

    public Batch(Fruit fruit, int weight, int farmNumber) {
        this.fruit = fruit;
        this.weight = weight;
        this.farmNumber = farmNumber;
        this.recievedDate = new Date();
        this.batchNumber = buildBatchNumber();
        this.grade = new HashMap<>();

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
            printGrades += ":  " + this.weight * (int)mapElement.getValue() + "KG \n";
        }
        return printGrades;
    }


}
