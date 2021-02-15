package bll;

import be.User;
import dao.Data;

import java.util.List;

public class LogIn {
    
    private Data data = new Data();
    
    public int validateUser(String username, String password) {
        List<User> users = data.getUsers();

        for (User user: users) {

            if (user.getUserName().equals(username)){
                System.out.println("Found " + username);
                if (user.getPassword().equals(password)) {
                    System.out.println("Password is " + password);
                    if(user.isTeacher()){
                        return 1;
                    }else{
                        return 2;
                    }
                }else{
                    System.out.println("Password doesn't match");
                    return -1;
                }

            }else{
                System.out.println("Not found" + username);
            }

        }

        return -1;
    }
}
