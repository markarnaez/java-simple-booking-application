package com.example;

import java.util.Scanner;

public class BuyerUser extends User {
    private static final boolean IS_ADMIN = false;
    private static final String[] BUYER_MENU = { 
        "List shows", 
        "Display show", 
        "Book show", 
        "Cancel booking",
        "Logout" };

    public BuyerUser(ShowManager showManager) {
        super(showManager, BUYER_MENU, IS_ADMIN);
    }

    @Override
    public String[] getMenu() {
        System.out.println("================================================================");
        System.out.println("Buyer Main Menu [" + username + "]");
        System.out.println("================================================================");
        return super.getMenu();
    }

    @Override
    public boolean invokeAction(String action) {
        Scanner scanner = new Scanner(System.in);
        boolean logout = false;
        String selectedShowNumber = null;
        Show selectedShow = null;
        switch (action) {
            case "1":
                System.out.println("List of Shows");
                showManager.displayShowList();
                break;
            case "2":
                System.out.println("View Show");
                viewShow(scanner);
                break;
            case "3":
                System.out.println("Book Show");
                bookShow(scanner, this.username);
                break;
            case "4":
                System.out.println("Cancel Booking");
                cancelShow(scanner, this.username);
                break;
            case "5":
                System.out.println("Bye!");
                logout = true;
                break;
        }

        return logout;
    }

    @Override
    public void displayShow(Show selectedShow) {
        selectedShow.displaySeatingArrangement();
    }

}
