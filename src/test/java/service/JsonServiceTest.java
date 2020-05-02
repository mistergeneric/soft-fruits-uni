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

public class JsonServiceTest {
    private JsonService jsonService;
    @Before
    public void setUp()
    {
        jsonService = new JsonService();
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
}