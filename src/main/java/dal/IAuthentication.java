package dal;

public interface IAuthentication {
    boolean authenticateTeacher(String email, String password);
    boolean authenticateStudent(String email, String password);
}
