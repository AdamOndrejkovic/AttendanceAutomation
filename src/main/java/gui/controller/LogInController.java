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

public class LogInController {
    private Session session = Session.getInstance();

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

        AuthenticationManager authenticationManager = new AuthenticationManager();
        User user = (User) authenticationManager.checkCredintials(username, password);


        if (user instanceof Teacher) {
            System.out.println("Welcome Teacher");
            goToTeachersView();
        } else if (user instanceof Student) {
            System.out.println("Welcome Student!");
            goToStudentsView();
        } else {
            System.out.println("Wrong username or password");
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

    public void goToTeachersView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/teacherPage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) logInButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToStudentsView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/studentPage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
