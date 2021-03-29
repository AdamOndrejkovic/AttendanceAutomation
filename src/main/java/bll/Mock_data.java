package bll;

import be.StudentClasses;
import be.Student;
import be.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mock_data {
    private File mock_data = new File("Resources/Mock_data.txt");
    private List<Student> studentsList = new ArrayList<>();
    private List<Teacher> teachersList = new ArrayList<>();
    private List<StudentClasses> allClassesList = new ArrayList<>();
    private List<Student> forShowingList= new ArrayList<>();

    public Mock_data() throws FileNotFoundException {
        allClassesList.add(new StudentClasses("Show all students"));
        extractData(mock_data);
    }

    public List<Student> getStudentList(){
        return studentsList;
    }

    public List<StudentClasses> getClassList() {
        return allClassesList;
    }

    /*
    -----------------------------------------------------------------------------------
    Manually create/edit/delete student methods
    -----------------------------------------------------------------------------------
     */

    public void addStudent(String name, String surName, String email, String className){
        Student student = new Student(name,surName,email,className);
        studentsList.add(student);

    }

    public void deleteStudent(int selectedItems){
        studentsList.remove(selectedItems);
    }

    /*
    -----------------------------------------------------------------------------------
    Manually create/edit/delete teacher methods
    -----------------------------------------------------------------------------------
     */

    public void addTeacher(String name, String surName, String email){
        Teacher teacher = new Teacher(name,surName,email);
        teachersList.add(teacher);
    }

    public void editTeacher(){

    }

    public void deleteTeacher(){

    }

    /*
    -----------------------------------------------------------------------------------
    Manually create/edit/delete class methods
    -----------------------------------------------------------------------------------
     */

    public void createClass(String name) {
        StudentClasses studentClasses = new StudentClasses(name);
        allClassesList.add(studentClasses);
    }

    public void editClass(){

    }

    public void deleteClass(int selectedMenuItem) {
        allClassesList.remove(selectedMenuItem);
    }

    /*
    -----------------------------------------------------------------------------------
    Add All students from *.txt file
    Check of each student corresponding class if id doesn't exist than create it.
    Sort all students by corresponding class
    -----------------------------------------------------------------------------------
     */


    public void extractData(File data) throws FileNotFoundException {
        Scanner scanner = new Scanner(data);
        while (scanner.hasNextLine()) {
            String fileContent = "";
            fileContent = fileContent.concat(scanner.nextLine() + "\n");
            String[] result = fileContent.split(",");
            if (result.length == 7 && fileContent.contains("True")) {
                for (int i = 0; i < result.length; i++) {
                    result[i] = result[i].trim();
                }
                if (!checkIfClassExists(new StudentClasses(result[6]))){
                    createClass(result[6]);

                }
                addStudent(String.valueOf(result[1]), String.valueOf(result[2]), String.valueOf(result[3]),String.valueOf(result[6]));
            } else if(fileContent.contains("False")){
                addTeacher(String.valueOf(result[1]), String.valueOf(result[2]), String.valueOf(result[3]));
            }
        }
        scanner.close();
        sort();
    }

    private boolean checkIfClassExists(StudentClasses name){
        for (StudentClasses studentClassesList : allClassesList){
            if (studentClassesList.getName().contentEquals(name.getName())){
                return true;
            }
        }
        return false;
    }

    public void sort(){
        for (StudentClasses classList : allClassesList){
            for (Student studentList : studentsList){
                if (classList.getName().contains(studentList.getStudentCorrespondingClassName())){
                    classList.getClassListtttt().add(studentList);
                }
            }
        }
    }
}
