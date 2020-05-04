package service;


import model.Batch;
import model.fruit.Strawberry;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class JsonServiceTest {
    private JsonService jsonService;
    private BatchService batchService;
    @Before
    public void setUp()
    {
        jsonService = new JsonService();
        batchService = new BatchService();
    }

    @Test
    public void printBatch() {
        Batch batch = new Batch(new Strawberry(), 12, 12);
        assertEquals(batch.toString(), "Batch contains: \n" +
                "Fruit type: Strawberry\n" +
                "From farm number: 12\n" +
                "recieved on: " + batch.getRecievedDate() + "\n");
        batchService.saveBatch(batch);
        File file = new File("src/main/resources/" + batch.getBatchNumber() + ".json");
        assertTrue(file.length() > 0);
    }
}