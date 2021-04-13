package gui.controller;

import be.Class;
import be.Date;
import be.user.Student;
import be.user.User;
import gui.model.TeacherModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import utility.Months;

import java.net.URL;
import java.util.Arrays;
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
    @FXML
    private ChoiceBox<Months> choiceMonth;
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
    private TableColumn<User, Double> studentAttendance;

    private TeacherModel teacherModel;

    private Calendar calendar = Calendar.getInstance(Locale.GERMANY);

    public TeacherPageController() {
        teacherModel = new TeacherModel();
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
        });


        studentFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        studentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        studentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        attendanceTable.setItems(teacherModel.getStudentsOverview());
        attendanceTable.getSelectionModel().selectedItemProperty().addListener((observableValue, student, t1) -> {
            System.out.println(observableValue);
            System.out.println(student);
            System.out.println(t1);
        });
        //attendanceTable.setItems(teacherModel.getStudentsOverview());

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
