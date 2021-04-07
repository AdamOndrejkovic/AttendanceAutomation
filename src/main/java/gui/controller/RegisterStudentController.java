package gui.controller;

import bll.RegisterManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RegisterStudentController{

    private RegisterManager registerManager;

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

    public RegisterStudentController(){
        registerManager = new RegisterManager();
    }

    @FXML
    void cancelStudent(ActionEvent event) {
        Stage stage = (Stage) cancelStudentButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerStudent(ActionEvent event) {
        if(registerManager.registerStudent(firstName.getText(), lastName.getText(), email.getText(), password.getText())){
            cancelStudent(event);
        }else{
            Alert.displayAlert("Error", "Ops something went wrong while creating a new student account. Please try again later!");
            firstName.setText("");
            lastName.setText("");
            email.setText("");
            password.setText("");
        }
    }

}
