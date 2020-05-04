package controller;

import model.Batch;
import model.fruit.Fruit;
import service.BatchService;
import service.FruitService;
import service.JsonService;

import java.util.HashMap;
import java.util.Scanner;

public class BatchController {

    private BatchService batchService;
    private JsonService jsonService;
    private FruitService fruitService;

    public BatchController() {
        this.batchService = new BatchService();
        this.jsonService = new JsonService();
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
                farmNumber = batchService.generateFarmNumber(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 001 and 999");
                continue;
            }
            System.out.println(batchService.presentFruitChoice());
            int fruitChoice;
            userInput = scanner.nextLine();
            try {
                fruitChoice = batchService.generateFruitChoice(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Please choose valid fruit");
                continue;
            }
            Fruit fruit = fruitService.pickFruit(fruitChoice);
            System.out.println(batchService.presentWeightChoice());
            int weight;
            userInput = scanner.nextLine();
            try {
                weight = batchService.generateWeightChoice(userInput);

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
                batchService.saveBatch(batch);
            }
        }
    }

    public void listAllBatches() {
        Scanner scanner = new Scanner(System.in);
        String currentUserChoice = "Y";
        while(true) {
            boolean isUserFinished = !currentUserChoice.equals("Y");
            if (isUserFinished) break;
            jsonService.jsonToBatches().forEach(System.out::println);
            System.out.println("Would you like to reprint?");
            System.out.println(("Y/N"));
            currentUserChoice = scanner.nextLine();
        }

    }
    public void gradeFruit() {
        Scanner scanner = new Scanner(System.in);
        String currentUserChoice = "Y";
        while(true) {
            boolean isUserFinished = !currentUserChoice.equals("Y");
            if (isUserFinished) break;



            System.out.println("Please enter a batch number:");
            Batch batch = batchService.findBatch(scanner.nextLine());
            if(batch == null) {
                System.out.println("Press any key to return to main menu");
                break;
            }
            HashMap<String, Integer> grades = new HashMap<>();
            System.out.println("Please enter fruit grades as a percentage: ");
            System.out.println("GRADE A FRUIT:");
            try {
                int gradeA = Integer.parseInt(scanner.nextLine());
                grades.put("A", gradeA);
                System.out.println("GRADE B FRUIT:");
                int gradeB = Integer.parseInt(scanner.nextLine());
                grades.put("B", gradeB);
                System.out.println("GRADE C FRUIT:");
                int gradeC = Integer.parseInt(scanner.nextLine());
                grades.put("C", gradeC);
                System.out.println("REJECTED FRUIT:");
                int rejected = Integer.parseInt(scanner.nextLine());
                grades.put("Rejected", rejected);
                if (rejected + gradeA + gradeB + gradeC != 100) {
                    System.out.println("I'm sorry, but the gradings do not add up to 100%. Please re-enter");
                    continue;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Incorrect number format used. Please start again");
                continue;
            }
            batch.setGrade(grades);
            System.out.println(batchService.presentFinalBatch(batch));
            System.out.println(batch.printGrades());
            currentUserChoice = scanner.nextLine();
            if(currentUserChoice.equals("N") || currentUserChoice.equals("n")) {
                System.out.println("Okay, re-enter information");
                continue;
            }
            batchService.saveBatch(batch);
            System.out.println("Would you like to edit another batch?");
            System.out.println(("Y/N"));
            currentUserChoice = scanner.nextLine();

        }

    }
    public void showBatchDetail() {
        Scanner scanner = new Scanner(System.in);
        String currentUserChoice = "Y";
        while(true) {
            boolean isUserFinished = !currentUserChoice.equals("Y");
            if (isUserFinished) break;
            System.out.println("Enter batch number:");
            String batchNumber = scanner.nextLine();
            Batch batch = batchService.findBatch(batchNumber);
            if(batch == null) {
                System.out.println("Press any key to return to main menu");
                break;
            }
            System.out.println(batch);
            System.out.println(batch.printGrades());
            System.out.println("Would you like to find another batch?");
            System.out.println(("Y/N"));
            currentUserChoice = scanner.nextLine();

        }

    }


}
