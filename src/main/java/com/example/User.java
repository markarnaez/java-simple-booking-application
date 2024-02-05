package com.example;
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

    protected void setUsername(String username) {
        this.username = username;
    }

    protected String getUsername() {
        return this.username;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public abstract boolean login();

    public void logout(){
        this.isLoggedIn = false;
    }

    public String[] getMenu(){
        return menu;
    }
}
