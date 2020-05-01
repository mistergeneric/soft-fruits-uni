
package model.fruit;

public class Strawberry implements Fruit {
    private String title;
    public Strawberry(){
        title = this.getClass().getSimpleName();
    }
    public String getTitle() {
        return title;
    }
}
