package dal.db;

import be.Class;
import be.Date;
import dal.IClassRepository;
import error.ErrorHandler;
import gui.controller.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DBClassRepository implements IClassRepository {
    private DatabaseConnection connection;
    private ErrorHandler errorHandler;

    public DBClassRepository() {
        connection = new DatabaseConnection();
        errorHandler = new ErrorHandler();
    }

    @Override
    public void createClass(String className) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO Class Values(?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, className);
            statement.execute();
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue when creating a class", ex);
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
            errorHandler.errorDevelopmentInfo("Issue getting a class", ex);
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
            errorHandler.errorDevelopmentInfo("Issue getting all classes", ex);
        }
        return null;
    }

    @Override
    public List<Class> getAllStudentClasses(int studentID) {
        List<Class> classes = new ArrayList<>();

        try (Connection con = connection.getConnection()) {
            String sql = "SELECT ClassID, Class.ClassName FROM StudentClass INNER JOIN Class ON StudentClass.ClassID = Class.ID WHERE StudentID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, studentID);

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    classes.add(new Class(resultSet.getInt("ClassID"),
                            resultSet.getString("ClassName")));
                }
                return classes;
            }

        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue getting student classes", ex);
        }
        return null;
    }

    @Override
    public List<Class> getAllTeacherClasses(int teacherID) {
        List<Class> classes = new ArrayList<>();

        try (Connection con = connection.getConnection()) {
            String sql = "SELECT ClassID, Class.ClassName FROM TeacherClass INNER JOIN Class ON TeacherClass.ClassID = Class.ID WHERE TeacherID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, teacherID);

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    classes.add(new Class(resultSet.getInt("ClassID"),
                            resultSet.getString("ClassName")));
                }
                return classes;
            }

        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue getting all teacher classes", ex);
        }
        return null;
    }

    @Override
    public void assignStudent(int studentID, int classID) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO StudentClass VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, studentID);
            statement.setInt(2, classID);
            statement.execute();
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue when creating a class", ex);
        }
    }

    @Override
    public void assignTeacher(int teacherID, int classID) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO TeacherClass VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, teacherID);
            statement.setInt(2, classID);
            statement.execute();
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue when assigning teacher", ex);
        }
    }

    @Override
    public void removeStudent(int studentID, int classID) {
        try (Connection con = connection.getConnection()) {
            String sql = "DELETE FROM StudentClass WHERE StudentID = ? AND ClassID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, studentID);
            statement.setInt(2, classID);
            statement.execute();
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue when removing student", ex);
        }
    }

    @Override
    public void addStudentPresence(int studentID, int classID, Date date) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO StudentPresence VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, studentID);
            statement.setInt(2, classID);
            statement.setString(3, date.toString());
            statement.execute();
            Alert.displayAlert("Student Class","Attendance successfully submitted!");
        } catch (SQLException ex) {
            Alert.displayAlert("Student Class","Failed to submit Attendance!");
            errorHandler.errorDevelopmentInfo("Issue add student presence", ex);
        }
    }

    @Override
    public void removeTeacher(int teacherID, int classID) {
        try (Connection con = connection.getConnection()) {
            String sql = "DELETE FROM TeacherClass WHERE TeacherID = ? AND ClassID = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, teacherID);
            statement.setInt(2, classID);
            statement.execute();
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue removing teacher", ex);
        }
    }

    @Override
    public void deleteClassDate(int classID, Date date) {
        try (Connection con = connection.getConnection()) {
            String sql = "DELETE FROM ClassSchedule WHERE ClassID = ? AND ClassDate = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, classID);
            statement.setString(2, date.toString());
            statement.execute();
            Alert.displayAlert("Class Date","Class date has been successfully deleted!");
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue deleting date", ex);
        }
    }

    @Override
    public void editClassDate(int classID, Date oldDate,Date newDate) {
        try (Connection con = connection.getConnection()) {
            String sql = "UPDATE ClassSchedule SET ClassDate = ? WHERE ClassID = ? AND ClassDate = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, newDate.toString());
            statement.setInt(2, classID);
            statement.setString(3, oldDate.toString());
            statement.execute();
            Alert.displayAlert("Class Date","Class date has been successfully changed!");
        } catch (SQLException ex) {
            Alert.displayAlert("Class Date","Error changing date!");
            errorHandler.errorDevelopmentInfo("Issue adding date", ex);
        }
    }

    @Override
    public void addClassDate(int classID, Date date) {
        try (Connection con = connection.getConnection()) {
            String sql = "INSERT INTO ClassSchedule VALUES(?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, classID);
            statement.setString(2, date.toString());
            statement.execute();
            Alert.displayAlert("Class Date","Class date has been successfully added!");
        } catch (SQLException ex) {
            Alert.displayAlert("Class Date","Error adding date!");
            errorHandler.errorDevelopmentInfo("Issue adding date", ex);
        }
    }

    @Override
    public List<Date> getClassSchedule(int classID) {
        List<Date> schedule = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "SELECT ClassDate FROM ClassSchedule WHERE ClassID = ?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, classID);

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String[] arrayDate = resultSet.getString("ClassDate").split("-");
                    int year = Integer.parseInt(arrayDate[0]);
                    int month = Integer.parseInt(arrayDate[1]);
                    int day = Integer.parseInt(arrayDate[2]);

                    schedule.add(new Date(year, month, day));
                }
                return schedule;
            }
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue when getting class schedule", ex);
        }
        return null;
    }

    @Override
    public List<Date> getStudentPresence(int studentID, int classID) {
        List<Date> presence = new ArrayList<>();
        try (Connection con = connection.getConnection()) {
            String sql = "SELECT PresenceDate FROM StudentPresence Where StudentID = ? AND ClassID = ?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, studentID);
            statement.setInt(2, classID);

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String[] arrayDate = resultSet.getString("PresenceDate").split("-");
                    int year = Integer.parseInt(arrayDate[0]);
                    int month = Integer.parseInt(arrayDate[1]);
                    int day = Integer.parseInt(arrayDate[2]);

                    presence.add(new Date(year, month, day));
                }
                return presence;
            }
        } catch (SQLException ex) {
            errorHandler.errorDevelopmentInfo("Issue when getting student presence", ex);
        }
        return null;
    }

    @Override
    public List<Date> getStudentAbsence(int studentID, int classID) {
        List<String> schedule = getClassSchedule(classID).stream().map(Date::toString).collect(Collectors.toList());
        List<String> presence = getStudentPresence(studentID, classID).stream().map(Date::toString).collect(Collectors.toList());

        if(schedule.removeAll(presence)){
            return schedule.stream().map(Date::new).collect(Collectors.toList()); // returns absence dates
        }

        return null;
    }
}
