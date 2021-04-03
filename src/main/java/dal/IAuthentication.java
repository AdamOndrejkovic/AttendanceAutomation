package dal;

public interface IAuthentication {
    public Object getAuthentication(String email, String password);
    boolean authenticateTeacher(String email, String password);
    boolean authenticateStudent(String email, String password);
}
