package gui.controller;

import bll.RegisterManager;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class RegisterTeacherController{

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
    private JFXButton registerTeacherButton;

    @FXML
    private JFXButton cancelTeacherButton;

    @FXML
    void cancelTeacher(ActionEvent event) {
        Stage stage = (Stage) cancelTeacherButton.getScene().getWindow();
        stage.close();
    }

    public RegisterTeacherController(){
        registerManager = new RegisterManager();
    }

    @FXML
    void registerTeacher(ActionEvent event) {
        if(registerManager.registerTeacher(firstName.getText(), lastName.getText(), email.getText(), password.getText())){
            cancelTeacher(event);
        }else{
            Alert.displayAlert("Error", "Ops something went wrong while creating a new student account. Please try again later!");
            firstName.setText("");
            lastName.setText("");
            email.setText("");
            password.setText("");
        }
    }


    public void setController(LoginController logInController) {
    }

}
