package com.example;

public interface UserActions {

    public User login();

    public void logout();

    public String[] getMenu();

    public boolean invokeAction(String action);

    public void configureShow(Show show);

}
