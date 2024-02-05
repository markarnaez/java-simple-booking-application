package com.example.model;

import com.example.manager.ShowManager;

public abstract class User {
    protected ShowManager showManager;
    protected String username;
    protected String password;
    protected String[] menu;
    protected boolean isLoggedIn;

    protected User(ShowManager showManager) {
        this.showManager = showManager;
        this.isLoggedIn = true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public abstract boolean login();

    public void logout() {
        this.isLoggedIn = false;
    }

    public String[] getMenu() {
        return menu;
    }
}
