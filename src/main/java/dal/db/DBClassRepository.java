package dal.db;

import be.Class;
import be.user.Student;
import be.user.Teacher;
import dal.IClassRepository;

import java.util.List;

public class DBClassRepository implements IClassRepository {
    private DatabaseConnection connection;

    public DBClassRepository() {
        connection = new DatabaseConnection();
    }

    @Override
    public void createClass(String className) {

    }

    @Override
    public List<Class> getAllClasses() {
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
