import controller.BatchController;
import controller.MenuController;

public class Main {
    public static void main(String[] args) {
        MenuController menu = new MenuController();
        while(true) {
            menu.startApp();
        }
    }
}
