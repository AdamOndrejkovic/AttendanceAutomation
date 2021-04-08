package dal.db;

import be.user.Teacher;
import dal.ITeacherRepository;
import error.ErrorHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTeacherRepository implements ITeacherRepository {
    private DatabaseConnection connection;
    private ErrorHandler errorHandler;

    public DBTeacherRepository() {
        connection = new DatabaseConnection();
        errorHandler = new ErrorHandler();
    }

    @Override
    public Teacher getTeacher(int ID) {
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Teacher WHERE ID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, ID);
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    return new Teacher(
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getInt("ID"));
                }
            }
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue getting a teacher", ex);
        }
        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Teacher";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    teachers.add(new Teacher(
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getInt("ID")));
                }
                return teachers;
            }
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue getting all teachers", ex);
        }
        return null;
    }

    @Override
    public boolean registerTeacher(String firstName, String lastName, String email, String password) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO Teacher Values(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue registering a teacher", ex);
        }
        return false;
    }

    public void deleteTeacherTest(String email, String password){
        try (Connection con = connection.getConnection()) {
            String sql = "DELETE FROM Teacher WHERE  Email= ? AND Password = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.execute();
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue deleting a teacher", ex);
        }
    }
}
