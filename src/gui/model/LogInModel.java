package gui.model;

import bll.LogIn;

public class LogInModel {

    private LogIn logIn = new LogIn();

    public int validateUser(String username, String password) {
       return logIn.validateUser( username,password);
    }
}
