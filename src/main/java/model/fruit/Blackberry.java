package model.fruit;

public class Blackberry implements Fruit {
    private String title;
    public Blackberry(){
        title = this.getClass().getSimpleName();
    }
    public String getTitle() {
        return title;
    }
}
