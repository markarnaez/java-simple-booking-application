package com.example.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.example.action.*;
import com.example.action.UserAction;
import com.example.manager.ShowManager;
import com.example.model.User;

public class AdminActionsFactory implements ActionsFactory {
    private Map<String, Supplier<UserAction>> actionMap;
    private User user;

    public AdminActionsFactory(ShowManager showManager, User user) {
        this.actionMap = new HashMap<>();
        this.user = user;
        actionMap.put("1", () -> new ListShowsAction(showManager));
        actionMap.put("2", () -> new SetupShowAction(showManager));
        actionMap.put("3", () -> new AdminViewShowAction(showManager));
        actionMap.put("4", () -> new LogoutAction(user));
    }

    @Override
    public Map<String, Supplier<UserAction>> getActionMap() {
        return actionMap;
    }
}
