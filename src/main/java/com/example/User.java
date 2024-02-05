package com.example;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class User implements UserActions {
    protected String username;
    protected String password;
    protected String[] menu;
    protected boolean isAdmin;
    protected ShowManager showManager;

    public User() {
    }

    public User(ShowManager showManager, String[] menu, boolean isAdmin) {
        this.menu = menu;
        this.isAdmin = isAdmin;
        this.showManager = showManager;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public User login() {
        Console console = System.console();
        System.out.print("Enter username: ");
        String usernameInput = console.readLine();

        if (usernameInput != null && usernameInput.length() > 0) {
            this.username = usernameInput;
            return this;
        }
        return null;
    }

    protected void clearPasswordFromMemory(char[] passwordChars) {
        Arrays.fill(passwordChars, ' ');
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    public String[] getMenu() {
        return menu;
    }

    @Override
    public boolean invokeAction(String action) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'invokeAction'");
    }

    public void setupShow(Scanner scanner) {
        if (isAdmin) {
            System.out.print("Enter Show Number: ");
            String showNumber = scanner.nextLine();

            System.out.print("Enter Show Title: ");
            String showTitle = scanner.nextLine();

            System.out.print("Enter Row Number: ");
            int rowNumber = scanner.nextInt();

            System.out.print("Enter Number of Seat per Row: ");
            int seatsPerRow = scanner.nextInt();

            System.out.print("Enter Cancellation Window (minutes): ");
            int cancellationWindow = scanner.nextInt();

            try {
                SeatManager seatManager = new SeatManager(rowNumber, seatsPerRow);
                Show showEntry = new Show(showNumber, showTitle, seatManager, cancellationWindow);
                configureShow(showEntry);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("An error occurred while creating the show, please try again.");
            }
            
        }
    }

    public void viewShow(Scanner scanner) {
        System.out.print("Enter Show Number: ");
        String selectedShowNumber = scanner.nextLine();
        
        if (showManager.showExists(selectedShowNumber)){
            Show selectedShow = showManager.getShow(selectedShowNumber);
            displayShow(selectedShow);
        } else {
            System.out.println("Show number " + selectedShowNumber + " does not exist.");
        }
    }

    public void bookShow(Scanner scanner, String bookingUsername) {
        System.out.print("Enter Show Number: ");
        String selectedShowNumber = scanner.nextLine();
        if (showManager.showExists(selectedShowNumber)){
            Show selectedShow = showManager.getShow(selectedShowNumber);
            if (selectedShow.canBookShow(bookingUsername)) {
                System.out.print("Enter Seat Number(s): ");
                String selectedSeats = scanner.nextLine();
                String[] selectedSeatsArray = selectedSeats.split(",");
                if (selectedShow.getSeatManager().getAvailableSeatCount() < selectedSeatsArray.length){
                    throw new IllegalArgumentException("Maximum rows or columns exceeded");
                }
                if (showManager.bookShow(selectedShow, selectedSeatsArray, bookingUsername)) {
                    System.out.println(
                            selectedShow.getBookings().getBookingInfoForUser(selectedShowNumber, bookingUsername)
                                    .toString());
                    System.out.println("Booking completed successfully!");
                } else {
                    System.out.println("There was an error during booking. Please try again.");
                }
            } else {
                System.out.println("You cannot book this show anymore.");
            }
        } else {
            System.out.println("Show number " + selectedShowNumber + " does not exist. ");
        }
    }

    public void displayShow(Show selectedShow) {
        selectedShow.printShowInfo();
    }

    @Override
    public void configureShow(Show show) {
        showManager.addShow(show.getId(), show);
    }

    public void cancelShow(Scanner scanner, String bookingUsername) {
        System.out.print("Enter Ticket Number: ");
        String ticketNumber = scanner.nextLine();
        showManager.cancelShowBooking(ticketNumber, bookingUsername);
    }

}
