package com.example.action;

import java.util.Scanner;

import com.example.manager.SeatManager;
import com.example.manager.ShowManager;
import com.example.model.Show;

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

        scanner.nextLine();
        try {
            if (!showManager.showExists(showNumber)) {
                SeatManager seatManager = new SeatManager(rowNumber, seatsPerRow);
                Show showEntry = new Show(showNumber, showTitle, seatManager, cancellationWindow);
                showManager.addShow(showEntry);
            } else {
                System.out.println("The show you are trying to add is already existing.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("An error occurred while creating the show, please try again.");
        }
    }
    
}
