package com.example.action;

import java.util.Scanner;

import com.example.manager.ShowManager;

public class RemoveShowAction implements UserAction {
    private ShowManager showManager;

    public RemoveShowAction(ShowManager showManager) {
        this.showManager = showManager;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("Remove Show");
        System.out.println("--------------------------------");
        System.out.print("Enter Show Number: ");
        String selectedShowNumber = scanner.nextLine();

        if (showManager.showExists(selectedShowNumber)) {
            showManager.removeShow(selectedShowNumber);
            System.out.println("Show was removed successfully.");
        } else {
            System.out.println("The show you are trying to remove does not exist.");
        }
    }
    
}
