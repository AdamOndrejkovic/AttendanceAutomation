package dal.db;

import be.user.Student;
import be.user.Teacher;
import dal.IAuthentication;
import error.ErrorHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAuthentication implements IAuthentication {
    private DatabaseConnection connection;
    private ErrorHandler errorHandler;


    public DBAuthentication() {
        connection = new DatabaseConnection();
        errorHandler = new ErrorHandler();
    }

    @Override
    public Object getAuthentication(String email, String password){

        if (authenticateTeacher(email, password)){
            return getTeacherWithCredintials(email, password);
        }else if(authenticateStudent(email,password)){
            return getStudentWithCredintials(email,password);
        }
        return null;
    }

    @Override
    public boolean authenticateTeacher(String email, String password) {
        try (Connection con = connection.getConnection()) {
            String sql = "SELECT Email, Password FROM Teacher WHERE Email = ? AND Password = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue in Dao DB Authentication. ", ex);
        }
        return false;
    }

    @Override
    public boolean authenticateStudent(String email, String password) {
        try (Connection con = connection.getConnection()) {
            String sql = "SELECT Email, Password FROM Student WHERE Email = ? AND Password = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue in Dao DB Authentication. ", ex);
        }
        return false;
    }

    public Teacher getTeacherWithCredintials(String email, String password){
        try (Connection con = connection.getConnection()) {
            String sql = "SELECT * FROM Teacher WHERE Email = ? AND Password = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
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
            errorHandler.errorDevelopmentInfo("Issue in Dao DB Authentication. ", ex);
        }
        return null;
    }

    public Student getStudentWithCredintials(String email, String password){
        try (Connection con = connection.getConnection()) {
            String sql = "SELECT * FROM Student WHERE Email = ? AND Password = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
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
            errorHandler.errorDevelopmentInfo("Issue in Dao DB Authentication. ", ex);
        }
        return null;
    }
}
