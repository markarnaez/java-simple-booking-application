package com.example;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.example.action.UserAction;
import com.example.factory.ActionsFactory;
import com.example.factory.AdminActionsFactory;
import com.example.factory.BuyerActionsFactory;
import com.example.factory.UserFactory;
import com.example.manager.ShowManager;
import com.example.model.User;
import com.example.user.UserTypeEnum;
import com.example.util.UtilityHelper;

public class Main {
    private ShowManager showManager;
    User currentUser;
    ActionsFactory actionFactory;

    public Main() {
        this.showManager = new ShowManager();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        UserFactory userFactory = new UserFactory();

        AtomicBoolean isQuit = new AtomicBoolean(false);
        while (true) {
            while (currentUser == null) {
                currentUser = getLoginUser(scanner, userFactory, isQuit);
                if (isQuit.get())
                    return;
            }
            UtilityHelper.clearConsole();

            while (currentUser.isLoggedIn()) {
                String[] actionMenu = UtilityHelper.showMenu(currentUser.getMenu());
                String action = UtilityHelper.getValidChoice(actionMenu, scanner);
                UserAction userAction = actionFactory.createAction(action);
                if (userAction != null) {
                    UtilityHelper.clearConsole();
                    showManager.setCurrentUser(currentUser);
                    userAction.execute(scanner);
                }

            }
            currentUser = null;
        }
    }

    private static String[] getLoginMenu() {
        System.out.println("================================================================");
        System.out.println("Welcome to Test Booking Application");
        System.out.println("================================================================");
        return new String[] { "Log in as Admin", "Log in as Buyer", "Quit" };
    }

    private User getLoginUser(Scanner scanner, UserFactory userFactory, AtomicBoolean isQuit) {
        String[] loginMenu = UtilityHelper.showMenu(getLoginMenu());
        String loginType = UtilityHelper.getValidChoice(loginMenu, scanner);
        try {
            switch (loginType) {
                case "1":
                    currentUser = userFactory.createUser(UserTypeEnum.ADMIN, showManager);
                    actionFactory = new AdminActionsFactory(showManager, currentUser);
                    return currentUser;
                case "2":
                    currentUser = userFactory.createUser(UserTypeEnum.BUYER, showManager);
                    actionFactory = new BuyerActionsFactory(showManager, currentUser);
                    return currentUser;
                case "3":
                    System.out.println("Thank you for using. Goodbye!");
                    isQuit.set(true);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
