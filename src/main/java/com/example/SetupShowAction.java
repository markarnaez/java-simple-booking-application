package com.example;

import java.util.Scanner;

public class SetupShowAction implements UserAction{
    private ShowManager showManager;

    public SetupShowAction(ShowManager showManager) {
        this.showManager = showManager;
    }

    @Override
    public void execute(Scanner scanner){
        System.out.println("Setup Show");
        System.out.println("--------------------------------");
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
            showManager.addShow(showEntry);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("An error occurred while creating the show, please try again.");
        }
    }
    
}