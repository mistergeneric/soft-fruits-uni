package model.fruit;

import model.BatchCode;

public abstract class Fruit {
    private String title;
    private BatchCode batchCode;
    public Fruit(){
        title = this.getClass().getSimpleName();
        batchCode = chooseBatchCode();
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

}
