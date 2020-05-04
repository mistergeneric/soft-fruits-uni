package service;

import model.fruit.*;

public class FruitService {
    public Fruit pickFruit(int fruitChoice) {
        switch (fruitChoice) {
            case 1:
                return new Strawberry();
            case 2:
                return new Raspberry();
            case 3:
                return new Blackberry();
            case 4:
                return new Gooseberry();
            default:
                return null;
        }
    }
}
