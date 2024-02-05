package com.example.action;

import java.util.Scanner;

import com.example.model.User;

public class LogoutAction implements UserAction {

    private User user;

    public LogoutAction(User user) {
        this.user = user;
    }

    @Override
    public void execute(Scanner scanner) {
        user.logout();
    }

}
