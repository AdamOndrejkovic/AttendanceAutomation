package dao;

import be.User;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public List<User> users = new ArrayList<>();

    public Data(){
        //adding users to the user list
        users.add(new User(1,"Ava", "Ava4353","12345",true));
        users.add(new User(2,"Rose","Rose9823","12345",true));
        users.add(new User(3,"Jeppe","Jeppe0923","12345",true));
        users.add(new User(4,"Mark","Mark2483","12345",true));
        users.add(new User(5,"Peter","Peter4345","12345",false));
        users.add(new User(6,"Rubio","Rubio7342","12345",false));
        users.add(new User(7,"Tanner","Tanner4352","12345",false));
        users.add(new User(8,"Carl","Carl0232","12345",false));
        users.add(new User(9,"Kelsey","Kelsey9827","12345",false));
        users.add(new User(10,"Saba","Saba7463","12345",false));
        users.add(new User(11,"Felicity","Felic1823","12345",false));
        users.add(new User(12,"Anna","Anna9243","12345",false));
        users.add(new User(13,"Hans","Hans2539","12345",false));
        users.add(new User(14,"Gorge","Gorge0636","12345",false));
        users.add(new User(15,"Pia","Pia2643","12345",false));
        users.add(new User(16,"Bret","Bret9834","12345",false));
        users.add(new User(17,"John","John1546","12345",false));
    }

    public List<User> getUsers() {
        return users;
    }
}
