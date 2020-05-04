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
    private FruitService fruitService;
    @Before
    public void setUp()
    {
         batchService = new BatchService();
         fruitService = new FruitService();
    }
    @Test
    public void pickFruit() {
        assertTrue(fruitService.pickFruit(1) instanceof Strawberry);
        assertTrue(fruitService.pickFruit(2) instanceof Raspberry);
        assertTrue(fruitService.pickFruit(4) instanceof Gooseberry);

        assertNull(fruitService.pickFruit(5));


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