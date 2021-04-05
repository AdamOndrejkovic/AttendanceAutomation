package gui.controller;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class RegisterTeacherController{
    @FXML
    private JFXTextField fistName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton registerTeacherButton;

    @FXML
    private JFXButton cancelTeacherButton;

    @FXML
    void cancelTeacher(ActionEvent event) {
        Stage stage = (Stage) cancelTeacherButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerTeacher(ActionEvent event) {

    }


    public void setController(LoginController logInController) {
    }

}
