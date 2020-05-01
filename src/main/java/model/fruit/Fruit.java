package model.fruit;

public abstract class Fruit {
    private String title;
    public Fruit(){
        title = this.getClass().getSimpleName();
    }
    public String getTitle() {
        return title;
    }
}
