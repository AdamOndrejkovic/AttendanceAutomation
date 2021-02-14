package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.EventObject;

public class TeacherViewController {
    @FXML private TableView overviewTable;
    @FXML private MenuButton menuBarSelectClass;
    @FXML private ListView studentList;
    @FXML private Text lblTotalPercentage;
    @FXML private Text lblMissedClassCount;
    @FXML private Text lblNotAttendedAtAll;
    private EventObject event;

    @FXML
    public void btnCreateNewClass(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/view/CreateNewStudentView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @FXML
    public void btnCreateNewStudent(ActionEvent actionEvent) {
    }
    @FXML
    public void btnEditClass(ActionEvent actionEvent) {
    }
    @FXML
    public void btnEditStudentsInfo(ActionEvent actionEvent) {
    }
    @FXML
    public void btnDeleteClass(ActionEvent actionEvent) {
    }
    @FXML
    public void btnDeleteStudent(ActionEvent actionEvent) {
    }
}
