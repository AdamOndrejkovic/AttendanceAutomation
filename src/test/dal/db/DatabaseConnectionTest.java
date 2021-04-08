package dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    private static Connection connection;
    private static DatabaseConnection databaseConnection;

    @BeforeAll
    public static void setUp(){
        databaseConnection = new DatabaseConnection();
        try {
            connection = new DatabaseConnection().getConnection();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanUp(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @DisplayName("Testing the database connection")
    @Test
    void getConnection() {

        try {
            Assertions.assertTrue(!databaseConnection.getConnection().isClosed());
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}