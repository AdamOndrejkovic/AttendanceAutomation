package dal.db;

import dal.IAuthentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAuthentication implements IAuthentication {
    private DatabaseConnection connection;

    public DBAuthentication() {
        connection = new DatabaseConnection();
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
            //TODO exception
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
            //TODO exception
        }
        return false;
    }
}
