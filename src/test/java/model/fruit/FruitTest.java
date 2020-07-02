package model.fruit;

import model.BatchCode;
import org.junit.Test;

import static org.junit.Assert.*;

public class FruitTest {
    @Test
    public void testChooseBatchCode() {
        Fruit strawberry = new Strawberry();
        BatchCode b = strawberry.chooseBatchCode();
        assertEquals(BatchCode.ST, b);
        Fruit raspberry = new Raspberry();
        b = raspberry.chooseBatchCode();
        assertEquals(BatchCode.RA, b);
        Fruit gooseberry = new Gooseberry();
        b = gooseberry.chooseBatchCode();
        assertEquals(BatchCode.GO, b);
    }

    @Test
    public void testTestEquals() {
        Fruit testStrawberry = new Strawberry();
        Fruit comparisonStrawberry = new Strawberry();
        Fruit testRaspberry = new Raspberry();
        Fruit comparisonRaspberry = new Raspberry();
        assertEquals(testRaspberry, comparisonRaspberry);
        assertEquals(testStrawberry, comparisonStrawberry);
        assertNotEquals(testRaspberry, comparisonStrawberry);
        assertNotEquals(testStrawberry, comparisonRaspberry);
    }
}