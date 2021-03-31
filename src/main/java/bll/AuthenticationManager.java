package bll;

import dal.IAuthentication;
import dal.db.DBAuthentication;

public class AuthenticationManager {
    private IAuthentication authentication;

    public AuthenticationManager() {
        authentication = new DBAuthentication();
    }

    public boolean authenticateTeacher(String email, String password) {
        return authentication.authenticateTeacher(email, password);
    }

    public boolean authenticateStudent(String email, String password) {
        return authentication.authenticateStudent(email, password);
    }
}
