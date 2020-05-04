package service;

import com.google.gson.Gson;
import model.Batch;
import model.Price;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class JsonService {


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

    public Object findData(String filename, Object object) {
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("src/main/resources/" + filename + ".json");
            return gson.fromJson(reader, (Type) object);
        } catch (FileNotFoundException e) {
            System.out.println("Data not found");
            return null;
        }
    }

    public void saveFile(String fileName, Object object) {
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter("src/main/resources/" + fileName + ".json");
            gson.toJson(object, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("There has been an error printing to json file format");
        }
    }
}
