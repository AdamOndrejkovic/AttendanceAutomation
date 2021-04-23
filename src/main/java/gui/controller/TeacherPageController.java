package gui.controller;

import be.Class;
import be.Date;
import be.user.Student;
import be.user.User;
import gui.model.TeacherModel;
import javafx.application.Platform;
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
import java.util.concurrent.locks.ReentrantLock;

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

    private StudentAttendanceController studentAttendanceController;

    private Calendar calendar = Calendar.getInstance();

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

        studentFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        studentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        studentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));

        classListView.getSelectionModel().selectedItemProperty().addListener(observable -> {
                    int selectedClass = classListView.getSelectionModel().getSelectedItem().getId();
                    teacherModel.updateStudentsOverview(selectedClass);
                    for (Student student : teacherModel.getStudentsOverview()) {
                        student.setAttendance(student.getId(), selectedClass);
                    }
                    teacherModel.updateSheduleOverview(selectedClass);
                    scheduleListView.setItems(teacherModel.getSheduleOverview());
                    attendanceTable.setItems(teacherModel.getStudentsOverview());
                    txtClass.setText(classListView.getSelectionModel().getSelectedItem().getName());
        });

        attendanceTable.getSelectionModel().selectedItemProperty().addListener((observableValue, student, newSelectedStudent) -> {
            if (newSelectedStudent != null) {
                int selectedClass = classListView.getSelectionModel().getSelectedItem().getId();
                goToStudentAttendanceView(newSelectedStudent.getId(), selectedClass);
            }
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
            stage.setResizable(false);
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
