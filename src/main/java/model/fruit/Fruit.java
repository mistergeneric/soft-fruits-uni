package model.fruit;

import model.BatchCode;

import java.util.Map;
import java.util.Objects;

//discovered in this iteration, that I cannot rebuild a POJO from a json file if Fruit remains abstract
public class Fruit {
    private String title;
    private BatchCode batchCode;
    public Fruit(){
        title = this.getClass().getSimpleName();
        batchCode = chooseBatchCode();
    }
    public Fruit(String title, BatchCode batchCode) {
        this.title = title;
        this.batchCode = batchCode;

    }
    public String getTitle() {
        return title;
    }

    public BatchCode getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(BatchCode batchCode) {
        this.batchCode = batchCode;
    }

    private BatchCode chooseBatchCode() {
        switch(this.getClass().getSimpleName()) {
            case "Strawberry":
                return BatchCode.ST;
            case "Raspberry":
                return BatchCode.RA;
            case "Blackberry":
                return BatchCode.BL;
            case "Gooseberry":
                return BatchCode.GO;
            default:
                return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Fruit otherFruit = (Fruit) obj;
        return otherFruit.getTitle().equals(this.getTitle());
    }

}
