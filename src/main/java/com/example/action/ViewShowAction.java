package com.example.action;

import java.util.Scanner;

import com.example.manager.ShowManager;
import com.example.model.Show;

public class ViewShowAction implements UserAction {
    protected ShowManager showManager;

    public ViewShowAction(ShowManager showManager) {
        this.showManager = showManager;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("View Show");
        System.out.println("--------------------------------");
        System.out.print("Enter Show Number: ");
        String selectedShowNumber = scanner.nextLine();

        if (showManager.showExists(selectedShowNumber)) {
            Show selectedShow = showManager.getShow(selectedShowNumber);
            selectedShow.printShowInfo();
            displayMoreDetails(selectedShow);
        } else {
            System.out.println("Show number " + selectedShowNumber + " does not exist.");
        }
    }

    protected void displayMoreDetails(Show selectedShow) {
        return;
    }

}
