package controller;

import com.google.gson.Gson;
import model.Batch;
import model.fruit.*;
import service.BatchService;
import service.JsonService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Scanner;

public class BatchController {

    private BatchService batchService;
    private JsonService jsonService;

    public BatchController()
    {
        this.batchService = new BatchService();
    }


    public void makeBatch() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        //TODO - refactor this method
        while (run) {
            System.out.println(batchService.presentFarmChoice());
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
            System.out.println(batchService.presentFruitChoice());
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
            Fruit fruit = batchService.pickFruit(fruitChoice);
            System.out.println(batchService.presentWeightChoice());
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
            System.out.println(batchService.presentFinalBatch(batch));
            String isSuccess = "";
            isSuccess = scanner.next();
            if (isSuccess.equals("Y") || isSuccess.equals("y")) {
                run = false;
                jsonService.printBatch(batch); }
        }
    }


}
