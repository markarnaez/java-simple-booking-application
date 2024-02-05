package com.example;

import java.io.Console;
import java.util.Map;
import java.util.Scanner;

public class AdminUser extends User {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123";
    private static final boolean IS_ADMIN = true;
    private static final String[] ADMIN_MENU = { 
        "List shows", 
        "Setup show", 
        "View show", 
        "Book Show for a Buyer",
        "Logout" };

    public AdminUser(ShowManager showManager) {
        super(showManager, ADMIN_MENU, IS_ADMIN);
        this.setUsername(ADMIN_USERNAME);
        this.setPassword(ADMIN_PASSWORD);
    }

    @Override
    public String[] getMenu() {
        System.out.println("================================================================");
        System.out.println("Admin Main Menu");
        System.out.println("================================================================");
        return super.getMenu();
    }

    @Override
    public boolean invokeAction(String action) {
        Scanner scanner = new Scanner(System.in);
        boolean logout = false;
        switch (action) {
            case "1":
                System.out.println("List of Shows");
                showManager.displayShowList();
                break;
            case "2":
                System.out.println("Setup Show");
                setupShow(scanner);
                break;
            case "3":
                System.out.println("View Show");
                viewShow(scanner);
                break;
            case "4":
                System.out.println("Book Show for a Buyer");
                System.out.print("Enter Username: ");
                String bookingUsername = scanner.nextLine();
                bookShow(scanner, bookingUsername);
            case "5":
                System.out.println("Bye!");
                logout = true;
                break;
        }

        return logout;
    }

    @Override
    public User login() {
        Console console = System.console();
        System.out.print("Enter Admin username: ");
        String usernameInput = console.readLine();

        if (!username.equals(usernameInput)) {
            System.out.println("Invalid username for Admin. Please try again.");
            return null;
        }

        char[] passwordChars = console.readPassword("Enter Admin password: ");
        String passwordInput = new String(passwordChars);
        if (!password.equals(passwordInput)) {
            System.out.println("Invalid password. Please try again.");
            return null;
        }

        System.out.println("Login successful!");
        clearPasswordFromMemory(passwordChars);
        return this;

    }

    @Override
    public void displayShow(Show selectedShow) {
        selectedShow.printShowInfo();
        Map<String, Map<String, BookingInfo>> allBookings = selectedShow.getBookings().getBookingsByShow();
        if (allBookings != null) {
            Map<String, BookingInfo> bookings = allBookings.get(selectedShow.getId());
            if (bookings != null) {
                for (BookingInfo booking : bookings.values()) {
                    System.out.println(booking.toString());
                }
            }
        }
    }
}
