package gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CreateNewStudent {

    public TextField fldName;
    public TextField fldSurName;
    public TextField fldEmail;
    public Button buttonCreate;
    public Button buttonCancel;

    public void openNewWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CreateNewStudent.class.getResource("/view/CreateNewStudentView.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnCreateStudent(){
        TeacherPageController.newName = fldName.getText();
        TeacherPageController.newSurName = fldSurName.getText();
        TeacherPageController.newEMail = fldEmail.getText();
        closeWindow(buttonCreate);
    }

    public void btnCancel() {
        closeWindow(buttonCancel);
    }

    public void closeWindow(Button name){
        Stage stage = (Stage) name.getScene().getWindow();
        stage.hide();
    }

}