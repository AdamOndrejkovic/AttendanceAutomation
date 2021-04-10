package gui.controller;

import be.Class;
import be.Date;
import be.user.Student;
import gui.model.TeacherModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import utility.Months;

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
    @FXML
    private ChoiceBox<String> choiceMonth;
    @FXML
    private Text txtFullName;
    @FXML
    private Text txtClass;

    private TeacherModel teacherModel;

    private Calendar calendar = Calendar.getInstance(Locale.GERMANY);

    public TeacherPageController() {
        teacherModel = new TeacherModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classListView.setItems(teacherModel.getClassOverview());
        choiceMonth.setItems(FXCollections.observableList(Months.getValues()));
        txtFullName.setText(Session.getInstance().getUser().getFirstName() + " " + Session.getInstance().getUser().getLastName());
        classListView.getSelectionModel().selectedItemProperty().addListener(observable -> {
            teacherModel.updateStudentsOverview();
            teacherModel.updateSheduleOverview(classListView.getSelectionModel().getSelectedItem().getId());
            studentListView.setItems(teacherModel.getStudentsOverview());
            scheduleListView.setItems(teacherModel.getSheduleOverview());
            txtClass.setText(classListView.getSelectionModel().getSelectedItem().getName());
        });
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
                Alert.displayAlert("Class Date", "You can't add dates frome the past!");
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
