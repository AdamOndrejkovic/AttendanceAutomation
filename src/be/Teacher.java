package be;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
        private int id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private List<StudentClasses> studentClassesList;

        public Teacher(String name, String surname, String email) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.password = password;
            studentClassesList = new ArrayList<>();
        }
    }