package com.example.action;

import java.util.Scanner;

import com.example.manager.ShowManager;
import com.example.model.Show;

public class BookShowAction implements UserAction {
    private ShowManager showManager;

    public BookShowAction(ShowManager showManager) {
        this.showManager = showManager;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("Book Show");
        System.out.println("--------------------------------");
        System.out.print("Enter Show Number: ");
        String selectedShowNumber = scanner.nextLine();

        if (showManager.showExists(selectedShowNumber)) {
            Show selectedShow = showManager.getShow(selectedShowNumber);
            String username = showManager.getCurrentUser().getUsername();
            if (selectedShow.canBookShow(username)) {
                System.out.print("Enter Seat Number(s): ");
                String selectedSeats = scanner.nextLine();
                String[] selectedSeatsArray = selectedSeats.split(",");
                if (selectedShow.getSeatManager().getAvailableSeatCount() < selectedSeatsArray.length) {
                    throw new IllegalArgumentException("Maximum rows or columns exceeded");
                }
                if (showManager.bookShow(selectedShow, selectedSeatsArray)) {
                    System.out.println(selectedShow.getBookingManager()
                            .getBookingInfoForUser(selectedShowNumber, username).toString());
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

}
