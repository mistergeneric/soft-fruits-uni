package service;

import com.google.gson.Gson;
import model.Batch;
import model.fruit.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BatchService {

    public String presentFinalBatch(Batch batch) {
        String batchMessage = "\n\n" + batch.toString();
        batchMessage += "Is this correct? (Y/N)";
        return batchMessage;
    }

    public String presentWeightChoice() {
        return ("\nEnter batch weight: (KG)\n");
    }

    public String presentFruitChoice() {
        return "\nSelect a fruit type (1. Strawberries,2. Raspberries,3. Blackberries,4. Gooseberries)\n";
    }

    public String presentFarmChoice() {
        String farmChoice = "";
        farmChoice += "CREATE NEW BATCH\n";
        farmChoice += new Date() + "\n\n";
        farmChoice += "Enter Farm Number (001 to 999) \n";
        return farmChoice;
    }

    public Fruit pickFruit(int fruitChoice) {
        switch (fruitChoice) {
            case 1:
                return new Strawberry();
            case 2:
                return new Raspberry();
            case 3:
                return new Blackberry();
            case 4:
                return new Gooseberry();
            default:
                return null;
        }
    }

    public int generateFarmNumber(String userInput) {
        Scanner scanner = new Scanner(System.in);
        int farmNumber = Integer.parseInt(userInput);
        while (farmNumber < 0 || farmNumber > 999) {
            System.out.println("Please enter a number between 001 and 999");
            userInput = scanner.nextLine();
            farmNumber = Integer.parseInt(userInput);
        }
        return farmNumber;
    }

    public int generateFruitChoice(String userInput) {
        Scanner scanner = new Scanner(System.in);
        int fruitChoice = Integer.parseInt(userInput);
        while (fruitChoice < 1 || fruitChoice > 4) {
            System.out.println("Please choose valid fruit");
            userInput = scanner.nextLine();
            fruitChoice = Integer.parseInt(userInput);
        }
        return fruitChoice;
    }

    public int generateWeightChoice(String userInput) {
        Scanner scanner = new Scanner(System.in);
        int weight = Integer.parseInt(userInput);
        while (weight < 0 || weight > 100) {
            System.out.println("Please choose valid weight");
            userInput = scanner.nextLine();
            weight = Integer.parseInt(userInput);
        }
        return weight;
    }

}
