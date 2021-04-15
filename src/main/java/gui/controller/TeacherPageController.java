package gui.controller;

import be.Class;
import be.Date;
import be.user.Student;
import be.user.User;
import gui.model.TeacherModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class TeacherPageController implements Initializable {
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<Class> classListView;
    @FXML
    private ListView<Student> studentListView;
    @FXML
    private ListView<Date> scheduleListView;
    /*@FXML
    private ChoiceBox<Months> choiceMonth;*/
    @FXML
    private Text txtFullName;
    @FXML
    private Text txtClass;

    @FXML
    private TableView<Student> attendanceTable;

    @FXML
    private TableColumn<User, String> studentFirstName;

    @FXML
    private TableColumn<User, String> studentLastName;

    @FXML
    private TableColumn<User, String> studentEmail;

    @FXML
    private TableColumn<Student, Double> studentAttendance;

    private TeacherModel teacherModel;

    private int selectedClass;

    private StudentAttendanceController studentAttendanceController;

    private Calendar calendar = Calendar.getInstance(Locale.GERMANY);

    public TeacherPageController() {
        teacherModel = new TeacherModel();
        studentAttendanceController = new StudentAttendanceController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classListView.setItems(teacherModel.getClassOverview());
        scheduleListView.setPlaceholder(new Label("Please select a class"));
        scheduleListView.setMinWidth(125.00);
        txtFullName.setText(Session.getInstance().getUser().getFirstName() + " " + Session.getInstance().getUser().getLastName());
        classListView.getSelectionModel().selectedItemProperty().addListener(observable -> {
            teacherModel.updateStudentsOverview();
            teacherModel.updateSheduleOverview(classListView.getSelectionModel().getSelectedItem().getId());
            scheduleListView.setItems(teacherModel.getSheduleOverview());
            txtClass.setText(classListView.getSelectionModel().getSelectedItem().getName());
            selectedClass = classListView.getSelectionModel().getSelectedItem().getId();
            studentFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            studentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            studentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            for (Student student: teacherModel.getStudentsOverview()) {
                student.setAttendance(student.getId(), selectedClass);
            }
            studentAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        });



        attendanceTable.setItems(teacherModel.getStudentsOverview());
        attendanceTable.getSelectionModel().selectedItemProperty().addListener((observableValue, student, newSelectedStudent) -> {
            if (newSelectedStudent != null){
                goToStudentAttendanceView(newSelectedStudent.getId(), selectedClass);
            }
            System.out.println(observableValue);
            System.out.println(student);
            System.out.println(newSelectedStudent);
        });

    }

    private void goToStudentAttendanceView(int studentId, int classId) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userpage/StudentAttendance.fxml"));
            Parent root = fxmlLoader.load();

            studentAttendanceController = fxmlLoader.getController();
            studentAttendanceController.setStudentId(studentId);
            studentAttendanceController.setClassId(classId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Attendance");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void submitDate(ActionEvent actionEvent) {
        if (classListView.getSelectionModel().getSelectedItem() != null && datePicker.getValue() != null) {
            int year = datePicker.getValue().getYear();
            int month = datePicker.getValue().getMonthValue();
            int day = datePicker.getValue().getDayOfMonth();
            Date currentDate = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

            if (year >= currentDate.getYear() && month >= currentDate.getMonth() && day >= currentDate.getDay()) {
                int classID = classListView.getSelectionModel().getSelectedItem().getId();
                teacherModel.addClassDate(classID, new Date(year, month, day));
            } else {
                Alert.displayAlert("Class Date", "You can't add dates from the past!");
            }
        } else {
            Alert.displayAlert("Class Date", "Select class or pick date!");
        }

    }

    public void deleteDate(ActionEvent actionEvent) {
        if (classListView.getSelectionModel().getSelectedItem() != null && scheduleListView.getSelectionModel().getSelectedItem() != null) {
            int classID = classListView.getSelectionModel().getSelectedItem().getId();
            Date date = scheduleListView.getSelectionModel().getSelectedItem();
            teacherModel.deleteClassDate(classID, date);
        }
    }

    public void editDate(ActionEvent actionEvent) {
        if (classListView.getSelectionModel().getSelectedItem() != null && scheduleListView.getSelectionModel().getSelectedItem() != null) {
            Date selectedDate = scheduleListView.getSelectionModel().getSelectedItem();
            Date editedDate = EditDate.editDate(selectedDate);

            if (editedDate != null) {
                int classID = classListView.getSelectionModel().getSelectedItem().getId();
                teacherModel.editClassDate(classID, selectedDate, editedDate);
            }
        }
    }
}
