package com.example.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.example.action.*;
import com.example.manager.ShowManager;
import com.example.model.User;

public class BuyerActionsFactory implements ActionsFactory {
    private Map<String, Supplier<UserAction>> actionMap;
    private User user;

    public BuyerActionsFactory(ShowManager showManager, User user) {
        this.actionMap = new HashMap<>();
        actionMap.put("1", () -> new ListShowsAction(showManager));
        actionMap.put("2", () -> new ViewShowAction(showManager));
        actionMap.put("3", () -> new BookShowAction(showManager));
        actionMap.put("4", () -> new CancelBookingAction(showManager));
        actionMap.put("5", () -> new ListAllBuyerBookings(showManager, user));
        actionMap.put("6", () -> new LogoutAction(user));
    }

    @Override
    public Map<String, Supplier<UserAction>> getActionMap() {
        return actionMap;
    }
}
