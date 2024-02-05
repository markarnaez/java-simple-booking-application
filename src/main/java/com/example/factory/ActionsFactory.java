package com.example.factory;

import java.util.Map;
import java.util.function.Supplier;

public interface ActionsFactory {
    Map<String, Supplier<UserAction>> getActionMap();

    default UserAction createAction(String actionName) {
        Map<String, Supplier<UserAction>> actionMap = getActionMap();
        Supplier<UserAction> actionSupplier = actionMap.get(actionName);
        if (actionSupplier != null) {
            return actionSupplier.get();
        } else {
            throw new IllegalArgumentException("Invalid action: " + actionName);
        }
    }
}
