package com.example;

import java.util.Scanner;

public class Main {
    private static ShowManager showManager;
    public static void main(String[] args) {
        showManager = new ShowManager();
        Scanner scanner = new Scanner(System.in);

        boolean isQuit = false;
        while (!isQuit) {
            User user = getLoginUser(scanner);
            if (user != null) {
                UtilityHelper.clearConsole();
                boolean isLogout = false;
                while (!isLogout) {
                    String[] actionMenu = showMenu(user.getMenu());
                    String action = getValidChoice(actionMenu, scanner);
                    isLogout = user.invokeAction(action);
                }
            } else {
                isQuit = true;
            }
        }
    }

    private static User getLoginUser(Scanner scanner) {
        User user = null;
        String[] loginMenu = showMenu(getLoginMenu());
        String loginType = getValidChoice(loginMenu, scanner);

        switch (loginType) {
            case "1":
                user = loginAsAdmin();
                break;
            case "2":
                user = loginAsBuyer();
                break;
            case "3":
                System.out.println("Bye!");
                return null;
            default : return null;
        }
        return user;
    }

    private static User loginAsBuyer() {
        System.out.println("Logging as Buyer");
        User user = new BuyerUser(showManager);
        return login(user);
    }

    private static User loginAsAdmin() {
        System.out.println("Logging as Admin");
        User user = new AdminUser(showManager);
        return login(user);
    }

    private static User login(User user) {
        UtilityHelper.clearConsole();
        while (true) {
            if (user.login() != null) {
                return user;
            }
        }
    }

    private static String[] getLoginMenu() {
        System.out.println("================================================================");
        System.out.println("Welcome to the Booking Application");
        System.out.println("================================================================");
        String[] loginMenu = { "Log in as Admin", "Log in as Buyer", "Quit" };
        return loginMenu;
    }

    private static String[] showMenu(String[] menu) {
        for (int i = 0; i < menu.length; i++) {
            System.out.println(String.valueOf(i + 1).concat(". ").concat(menu[i]));
        }
        System.out.print("Please select an option: ");
        return menu;
    }


    private static String getValidChoice(String[] menu, Scanner scanner) {
        String choice = scanner.nextLine();
        while (!UtilityHelper.isChoiceValid(menu, choice)) {
            showMenu(menu);
            choice = scanner.nextLine();
        }
        return choice;
    }

}