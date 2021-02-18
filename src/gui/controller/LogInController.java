package gui.controller;

import bll.LoginManager;
import bll.UserManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton logInButton;

    private LoginManager loginManager;
    private UserManager userManager;


    public LogInController(){
        loginManager = new LoginManager();
        userManager = new UserManager();
    }
    @FXML
    void logIn(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(loginManager.validateUser(username, password) == 1){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/TeacherView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Teacher");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(loginManager.validateUser(username,password) == 2){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/studentsPage.fxml"));
                StudentPageController spc = new StudentPageController();
                fxmlLoader.setController(spc);
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Student");
                stage.setScene(new Scene(root1));
                stage.show();

                spc.setLoggedUserID(userManager.getID(username));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong with logging in!!!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
