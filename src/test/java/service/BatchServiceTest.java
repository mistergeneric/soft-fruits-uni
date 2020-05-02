package service;


import model.Batch;
import model.fruit.Gooseberry;
import model.fruit.Raspberry;
import model.fruit.Strawberry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.BatchService;
import service.JsonService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.*;

public class BatchServiceTest {
    private BatchService batchService;
    @Before
    public void setUp()
    {
         batchService = new BatchService();
    }
    @Test
    public void pickFruit() {
        assertTrue(batchService.pickFruit(1) instanceof Strawberry);
        assertTrue(batchService.pickFruit(2) instanceof Raspberry);
        assertTrue(batchService.pickFruit(4) instanceof Gooseberry);

        assertNull(batchService.pickFruit(5));


    }

    @Test
    public void printBatch() {
        Batch batch = new Batch(new Strawberry(), 12, 12);
        assertEquals(batch.toString(), "Batch contains: \n" +
                "Fruit type: Strawberry\n" +
                "From farm number: 12\n" +
                "recieved on: " + batch.getRecievedDate() + "\n");
        jsonService.printBatch(batch);
        File file = new File("src/main/resources/" + batch.getBatchNumber() + ".json");
        assertTrue(file.length() > 0);
    }

    @Test
    public void makeBatchMessages() {
        assertEquals(batchService.presentFarmChoice(), "CREATE NEW BATCH\n" +
                new Date() +
                "\n\n" +
                "Enter Farm Number (001 to 999) \n");
        assertEquals(batchService.presentFruitChoice(), "\nSelect a fruit type (1. Strawberries,2. Raspberries,3. Blackberries,4. Gooseberries)\n");
        assertEquals(batchService.presentFinalBatch(new Batch(new Strawberry(), 1, 1)), "\n\n" +
                "Batch contains: \n" +
                "Fruit type: Strawberry\n" +
                "From farm number: 1\n" +
                "recieved on: " +  new Date() + "\n" +
                "Is this correct? (Y/N)");
    }

}