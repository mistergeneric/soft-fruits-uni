package controller;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MenuController {
    public void startApp() {
        System.out.println("Welcome to the Renfrewshire Soft Fruits Co_operative");
        System.out.println();
        System.out.println("Choose an option below:");
        System.out.println();
        System.out.println("1. Create new batch");
        System.out.println("2. Quit");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (!choice.equals("1")) System.exit(1);
    }
}
