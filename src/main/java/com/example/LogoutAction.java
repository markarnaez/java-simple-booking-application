package com.example;

import java.util.Scanner;

public class LogoutAction implements UserAction{

    private User user;

    public LogoutAction(User user) {
        this.user = user;
    }

    @Override
    public void execute(Scanner scanner) {
        user.logout();
    }
    
}
