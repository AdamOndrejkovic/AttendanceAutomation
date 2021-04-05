package gui.controller;

import be.user.Student;
import be.user.Teacher;
import be.user.User;
import bll.AuthenticationManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dal.IClassRepository;
import dal.IStudentRepository;
import dal.ITeacherRepository;
import dal.db.DBAuthentication;
import dal.db.DBClassRepository;
import dal.db.DBStudentRepository;
import dal.db.DBTeacherRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton logInButton;

    private AuthenticationManager authenticationManager;

    public LoginController(){
        authenticationManager = new AuthenticationManager();
    }
    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = (User) authenticationManager.checkCredintials(username, password);


        if (user != null) {
            Stage stage = (Stage) logInButton.getScene().getWindow();

            if (user instanceof Teacher) {
                goToTeachersView(stage,user);
            } else if (user instanceof Student) {
                goToStudentsView(stage,user);
            }
        } else {
            Alert.displayAlert("Wrong Details!", "Entered Credentials are wrong!");
        }
    }

    public void registerTeacher(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/registerTeacher.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) logInButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToTeachersView(Stage stage, User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/teacherPage.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            Session.getInstance().startSession(user, stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToStudentsView(Stage stage, User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/studentPage.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            Session.getInstance().startSession(user, stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
