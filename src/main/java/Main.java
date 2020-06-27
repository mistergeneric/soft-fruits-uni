import controller.MainMenuController;

public class Main {
    public static void main(String[] args) {
        MainMenuController menu = new MainMenuController();
        while(true) {
            menu.startApp();
        }
    }
}
