package controller;

import com.google.gson.Gson;
import model.Batch;
import model.fruit.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Scanner;

public class BatchController {
    public void makeBatch() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        //TODO - refactor this method
        while (run) {
            System.out.println(presentFarmChoice());
            String userInput = "";
            int farmNumber;
            userInput = scanner.nextLine();
            try {
                farmNumber = Integer.parseInt(userInput);
                while (farmNumber < 0 || farmNumber > 999) {
                    System.out.println("Please enter a number between 001 and 999");
                    userInput = scanner.nextLine();
                    farmNumber = Integer.parseInt(userInput);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 001 and 999");
                continue;
            }
            System.out.println(presentFruitChoice());
            int fruitChoice;
            userInput = scanner.nextLine();
            try {
                fruitChoice = Integer.parseInt(userInput);
                while (fruitChoice < 1 || fruitChoice > 4) {
                    System.out.println("Please choose valid fruit");
                    userInput = scanner.nextLine();
                    fruitChoice = Integer.parseInt(userInput);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please choose valid fruit");
                continue;
            }
            Fruit fruit = pickFruit(fruitChoice);
            System.out.println(presentWeightChoice());
            int weight;
            userInput = scanner.nextLine();
            try {
                weight = Integer.parseInt(userInput);
                while (weight < 0 || weight > 100) {
                    System.out.println("Please choose valid weight");
                    userInput = scanner.nextLine();
                    weight = Integer.parseInt(userInput);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please choose valid weight");
                continue;
            }
            Batch batch = new Batch(fruit, weight, farmNumber);
            System.out.println(presentFinalBatch(batch));
            String isSuccess = "";
            isSuccess = scanner.next();
            if (isSuccess.equals("Y") || isSuccess.equals("y")) {
                run = false;
                printBatch(batch);

                break;
            }
        }
    }

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

    public void printBatch(Batch batch) {
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter("src/main/resources/" + batch.getBatchNumber() + ".json");
            gson.toJson(batch, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("There has been an error printing to json file format");
        }
    }
}
