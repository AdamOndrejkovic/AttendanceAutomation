package be;

public class Student {
    private final String name;
    private final String surname;
    private final String email;
    final String password;
    private final String className;

    public Student(String name, String surname, String email, String className) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.className = className;
        password = "International Class 2021_EASV";
    }

    public String getStudentCorrespondingClassName() {
        return className;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
