package model.fruit;

public class Raspberry implements Fruit {
    private String title;
    public Raspberry(){
        title = this.getClass().getSimpleName();
    }
    public String getTitle() {
        return title;
    }
}
