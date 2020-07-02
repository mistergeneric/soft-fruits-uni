package model;

import model.fruit.Strawberry;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BatchTest {

    @Test
    public void testPrintGrades() {
        Batch batch = new Batch(new Strawberry(), 100, 90);
        assertEquals("No graded fruit", batch.printGrades());
        HashMap<String, Integer> grade = new HashMap<>();
        grade.put("A", 100);
        batch.setGrade(grade);
        assertEquals("A 100%:  100KG", batch.printGrades());
    }

    public void testGenerateTotalCost() {
    }

    public void testDisplayBatchCost() {
    }

    public void testFormatTotalCost() {
    }
}