package dao;

import be.Attendance;
import be.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static List<User> users;

    public UserDAO() {
        users = new ArrayList<>();
        users.add(new User(0, "Peter", "0", "0", false));
        users.add(new User(1, "Ava", "Ava4353", "12345", true));
        users.add(new User(2, "Adam", "Adam0923", "12345", true));
        users.add(new User(3, "Jeppe", "Jeppe0923", "12345", true));
        users.add(new User(4, "Mantas", "Mantas0923", "12345", true));
        users.add(new User(5, "Peter", "Peter4345", "12345", false));
        users.add(new User(6, "Rubio", "Rubio7342", "12345", false));
        users.add(new User(7, "Tanner", "Tanner4352", "12345", false));
        users.add(new User(8, "Carl", "Carl0232", "12345", false));
        users.add(new User(9, "Robin", "Robin2113", "12345", false));
        users.add(new User(10, "Steve", "Steve0232", "12345", false));
    }

    public User getUserByID(int id) {
        for (User user : users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
