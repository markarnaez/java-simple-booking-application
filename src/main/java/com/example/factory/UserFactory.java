package com.example.factory;

import com.example.manager.ShowManager;

public class UserFactory {
    public User createUser(UserTypeEnum userType, ShowManager showManager) {
        switch (userType) {
            case ADMIN:
                User admin = new AdminUser(showManager);
                return admin.login() ? admin : null;
            case BUYER:
                User buyer = new BuyerUser(showManager);
                return buyer.login() ? buyer : null;
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}
