package dal.db;

import be.user.Student;
import be.user.Teacher;
import dal.ITeacherRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTeacherRepository implements ITeacherRepository {
    private DatabaseConnection connection;

    public DBTeacherRepository() {
        connection = new DatabaseConnection();
    }

    @Override
    public Teacher getTeacher(int ID) {
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Teacher WHERE ID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,ID);

            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()){
                    return new Teacher(
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getInt("ID"));
                }
            }
        } catch (SQLException ex) {
            //TODO
        }
        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "Select * From Teacher";
            Statement statement = con.createStatement();

            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    Teacher teacher = new Teacher(
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Email"),
                            resultSet.getInt("ID"));
                    teachers.add(teacher);
                }
                return teachers;
            }
        } catch (SQLException ex) {
            //TODO
        }
        return null;
    }

    @Override
    public void registerTeacher(String firstName, String lastName, String email, String password) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO Teacher Values(?,?,?,?)";
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
