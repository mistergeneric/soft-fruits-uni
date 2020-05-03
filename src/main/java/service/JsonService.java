package service;

import com.google.gson.Gson;
import model.Batch;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

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

    public ArrayList<Batch> jsonToBatches() {
        Gson gson = new Gson();
        File folder = new File("src/main/resources");
        File[] listOfFiles = folder.listFiles();
        ArrayList<Batch> allBatches = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    Reader reader = new FileReader("src/main/resources/" + file.getName());
                    Batch batch = gson.fromJson(reader, Batch.class);
                    allBatches.add(batch);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error retrieving Json Data");
                }
            }
        }
        return allBatches;
    }

    public Batch findJsonBatch(String batchNumber) {
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("src/main/resources/" + batchNumber + ".json");
            return gson.fromJson(reader, Batch.class);
        } catch (FileNotFoundException e) {
            System.out.println("Batch not found");
            return null;
        }
    }
}
