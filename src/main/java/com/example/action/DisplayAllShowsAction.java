package com.example.action;

import java.util.Scanner;

import com.example.manager.ShowManager;

public class DisplayAllShowsAction implements UserAction {
    private ShowManager showManager;

    public DisplayAllShowsAction(ShowManager showManager) {
        this.showManager = showManager;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("List of Shows");
        showManager.displayShowList();
    }

}
