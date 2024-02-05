package com.example;

import java.util.Scanner;

public class ListShowsAction implements UserAction{
    private ShowManager showManager;

    public ListShowsAction(ShowManager showManager) {
        this.showManager = showManager;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("List of existing shows");
        System.out.println("--------------------------------");
        showManager.displayShowList();
    }
    
    
}
