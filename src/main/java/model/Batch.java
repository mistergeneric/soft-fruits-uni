package model;

import model.fruit.Fruit;
import model.fruit.Strawberry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Batch {
    private String batchNumber;
    private Fruit fruit;
    private int weight;
    private int farmNumber;
    private Date recievedDate;
    public Batch(Fruit fruit, int weight, int farmNumber) {
        this.fruit = fruit;
        this.weight = weight;
        this.farmNumber = farmNumber;
        this.recievedDate = new Date();
        batchNumber = buildBatchNumber();

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

    @Override
    public String toString() {
        return "Batch contains: \n" +
                "Fruit type: " + fruit.getClass().getSimpleName() + "\n" +
                "From farm number: " + farmNumber + "\n" +
                "recieved on: " + recievedDate + "\n";
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


}
