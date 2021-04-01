package dal.db;

import be.user.Student;
import dal.IStudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStudentRepository implements IStudentRepository {
    private DatabaseConnection connection;

    public DBStudentRepository() {
        connection = new DatabaseConnection();
    }

    @Override
    public Student getStudent(int ID) {
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Student WHERE ID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,ID);

            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()){
                    Student student = new Student(
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getInt("ID"));
                    return student;
                }
            }
        } catch (SQLException ex) {
            //TODO
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Student";
            Statement statement = con.createStatement();

            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    Student student = new Student(
                            resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Email"),
                    resultSet.getInt("ID"));
                    students.add(student);
                }
                return students;
            }
        } catch (SQLException ex) {
            //TODO
        }
        return null;
    }

    @Override
    public void registerStudent(String firstName, String lastName, String email, String password) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO Student Values(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,email);
            statement.setString(4,password);
            statement.execute();
        } catch (SQLException ex) {
            //TODO
        }
    }
}
