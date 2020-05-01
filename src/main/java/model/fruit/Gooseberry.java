package model.fruit;

public class Gooseberry implements Fruit {
    private String title;
    public Gooseberry(){
        title = this.getClass().getSimpleName();
    }
    public String getTitle() {
        return title;
    }}
