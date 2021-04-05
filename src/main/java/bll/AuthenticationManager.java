package bll;

import dal.IAuthentication;
import dal.db.DBAuthentication;

public class AuthenticationManager {
    private IAuthentication authentication;

    public AuthenticationManager() {
        authentication = new DBAuthentication();
    }


    public Object checkCredintials(String username, String password) {
        return authentication.getAuthentication(username, password);
    }


}
