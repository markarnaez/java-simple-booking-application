package com.example;

import java.util.Scanner;

public class CancelBookingAction implements UserAction {
    private ShowManager showManager;

    public CancelBookingAction(ShowManager showManager) {
        this.showManager = showManager;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("Cancel Booking");
        System.out.println("--------------------------------");
        System.out.print("Enter Ticket Number: ");
        String ticketNumber = scanner.nextLine();
        showManager.cancelShowBooking(ticketNumber);
    }
    
}
