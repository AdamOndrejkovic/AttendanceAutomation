package gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RegisterStudentController{

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton registerStudentButton;

    @FXML
    private JFXButton cancelStudentButton;

    @FXML
    void cancelStudent(ActionEvent event) {
        Stage stage = (Stage) cancelStudentButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerStudent(ActionEvent event) {

    }

}
