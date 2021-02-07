package gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton logInButton;

    @FXML
    void logIn(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(username.equals("student") && password.equals("student"))
        {
            System.out.println("Welcome!");
        }
        else
            System.out.println("Wrong username or password");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
