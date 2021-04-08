package dal.db;

import be.user.Student;
import dal.IStudentRepository;
import error.ErrorHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStudentRepository implements IStudentRepository {
    private DatabaseConnection connection;
    private ErrorHandler errorHandler;

    public DBStudentRepository() {
        connection = new DatabaseConnection();
        errorHandler = new ErrorHandler();
    }

    @Override
    public Student getStudent(int ID) {
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Student WHERE ID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, ID);
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    return new Student(
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getInt("ID"));
                }
            }
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue getting a student", ex);
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Student";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    students.add(new Student(
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getInt("ID")));
                }
                return students;
            }
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue getting all students", ex);
        }
        return null;
    }

    @Override
    public boolean registerStudent(String firstName, String lastName, String email, String password) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO Student Values(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue registering a student", ex);
        }
        return false;
    }

    public void deleteStudentTest(String email, String password){
        try (Connection con = connection.getConnection()) {
            String sql = "DELETE FROM Student WHERE  Email = ? AND Password = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.execute();
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue deleting a student", ex);
        }
    }
}
