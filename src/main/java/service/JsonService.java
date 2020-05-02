package service;

import com.google.gson.Gson;
import model.Batch;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonService {
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
