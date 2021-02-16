package dao;

import be.Attended;
import be.User;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public List<User> users = new ArrayList<>();
    public List<Attended> attendance = new ArrayList();

    public Data(){
        attendance.add(new Attended(1,5,"SCO","16/02/2021",true));
        attendance.add(new Attended(2,5,"SCO","17/02/2021",true));
        attendance.add(new Attended(3,5,"DBO","18/02/2021",false));
        attendance.add(new Attended(4,5,"ITO","19/02/2021",true));

        attendance.add(new Attended(5,6,"SCO","16/02/2021",true));
        attendance.add(new Attended(6,6,"SCO","17/02/2021",true));
        attendance.add(new Attended(7,6,"DBO","18/02/2021",false));
        attendance.add(new Attended(8,6,"ITO","19/02/2021",true));

        attendance.add(new Attended(9,7,"SCO","16/02/2021",true));
        attendance.add(new Attended(10,7,"SCO","17/02/2021",true));
        attendance.add(new Attended(11,7,"DBO","18/02/2021",true));
        attendance.add(new Attended(12,7,"ITO","19/02/2021",true));

        attendance.add(new Attended(13,8,"SCO","16/02/2021",false));
        attendance.add(new Attended(14,8,"SCO","17/02/2021",false));
        attendance.add(new Attended(15,8,"DBO","18/02/2021",false));
        attendance.add(new Attended(16,8,"ITO","19/02/2021",true));




        //adding users to the user list
        users.add(new User(1,"Ava", "Ava4353","12345",true));
        users.add(new User(3,"Jeppe","Jeppe0923","12345",true));
        users.add(new User(5,"Peter","Peter4345","12345",false));
        users.add(new User(6,"Rubio","Rubio7342","12345",false));
        users.add(new User(7,"Tanner","Tanner4352","12345",false));
        users.add(new User(8,"Carl","Carl0232","12345",false));
    }

    public List<User> getUsers() {
        return users;
    }
}
