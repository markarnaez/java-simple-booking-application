package com.example.user;

import java.io.Console;
import java.util.Arrays;

import com.example.manager.ShowManager;
import com.example.model.User;

public class AdminUser extends User {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123";
    private static final String[] ADMIN_MENU = {
            "List shows",
            "Setup show",
            "View show",
            "Logout" };

    public AdminUser(ShowManager showManager) {
        super(showManager);
        this.menu = ADMIN_MENU;
    }

    @Override
    public String[] getMenu() {
        System.out.println("================================================================");
        System.out.println("Admin Main Menu");
        System.out.println("================================================================");
        return super.getMenu();
    }

    @Override
    public boolean login() {
        Console console = System.console();
        System.out.print("Enter Admin username: ");
        String inputUsername = (console.readLine());

        if (!inputUsername.equals(ADMIN_USERNAME)) {
            System.out.println("Invalid username for Admin. Please try again.");
            return false;
        }
        
        char[] passwordChars = console.readPassword("Enter Admin password: ");
        String inputPassword = new String(passwordChars);
        if (!inputPassword.equals(ADMIN_PASSWORD)) {
            System.out.println("Invalid password. Please try again.");
            return false;
        }

        setUsername(inputUsername);
        setPassword(inputPassword);
        clearPasswordFromMemory(passwordChars);
        
        System.out.println("Login successful!");
        return true;

    }

    protected void clearPasswordFromMemory(char[] passwordChars) {
        Arrays.fill(passwordChars, ' ');
    }
}
