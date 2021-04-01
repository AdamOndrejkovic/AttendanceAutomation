package dal.db;

import be.Class;
import be.user.Student;
import be.user.Teacher;
import dal.IClassRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBClassRepository implements IClassRepository {
    private DatabaseConnection connection;

    public DBClassRepository() {
        connection = new DatabaseConnection();
    }

    @Override
    public void createClass(String className) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO Class Values(?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, className);
            statement.execute();
        } catch (SQLException ex) {
            //TODO
        }
    }

    @Override
    public Class getClass(int classID) {
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Class WHERE ID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, classID);
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    return new Class(
                            resultSet.getInt("ID"),
                            resultSet.getString("ClassName"));
                }
            }
        } catch (SQLException ex) {
            //TODO
        }
        return null;
    }

    @Override
    public List<Class> getAllClasses() {
        List<Class> classes = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Class";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    classes.add(new Class(resultSet.getInt("ID"),
                            resultSet.getString("ClassName")));
                }
                return classes;
            }
        } catch (SQLException ex) {
            //TODO
        }
        return null;
    }

    @Override
    public List<Class> getAllStudentClasses(int studentID) {
        return null;
    }

    @Override
    public List<Class> getAllTeacherClasses(int teacherID) {
        return null;
    }

    @Override
    public void assignStudentClass(int studentID, int classID) {

    }

    @Override
    public void assignTeacherClass(int teacherID, int classID) {

    }

    @Override
    public void removeStudentClass(int studentID, int classID) {

    }

    @Override
    public void removeTeacherClass(int teacherID, int classID) {

    }
}
