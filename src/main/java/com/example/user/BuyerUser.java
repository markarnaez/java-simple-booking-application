package com.example.user;

import java.io.Console;

import com.example.manager.ShowManager;
import com.example.model.User;

public class BuyerUser extends User {
    private static final String[] BUYER_MENU = {
            "List shows",
            "Display show",
            "Book show",
            "Cancel booking",
            "List bookings",
            "Logout" };

    public BuyerUser(ShowManager showManager) {
        super(showManager);
        this.menu = BUYER_MENU;
    }

    @Override
    public String[] getMenu() {
        System.out.println("================================================================");
        System.out.println("Buyer Main Menu [" + username + "]");
        System.out.println("================================================================");
        return super.getMenu();
    }

    @Override
    public boolean login() {
        Console console = System.console();
        System.out.print("Enter username (5-digit phone number): ");
        String inputUsername = console.readLine();
    
        if (inputUsername != null && inputUsername.matches("\\d{5}")) {
            setUsername(inputUsername);
            return true;
        } else {
            System.out.println("Invalid username format. Username must be a 5-digit number.");
            return false;
        }
    }

}
