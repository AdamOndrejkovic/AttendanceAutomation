package bll;

import be.User;
import dao.UserDAO;

import java.util.List;

public class LoginManager {

    private UserDAO userDAO;

    public LoginManager() {
        userDAO = new UserDAO();
    }

    public int validateUser(String username, String password) {
        List<User> users = userDAO.getUsers();

        for (User user : users) {

            if (user.getUserName().equals(username)) {
                if (user.getPassword().equals(password)) {
                    if (user.isTeacher()) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    return -1;
                }

            } else {
            }

        }

        return -1;
    }
}
