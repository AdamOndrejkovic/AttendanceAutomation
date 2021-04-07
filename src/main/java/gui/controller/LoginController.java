package gui.controller;

import be.user.Student;
import be.user.Teacher;
import be.user.User;
import bll.AuthenticationManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton logInButton;

    private Session session = Session.getInstance();
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/register/RegisterTeacher.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerStudent(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/register/RegisterStudent.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToTeachersView(Stage stage, User user) {
        session.startSession(user, stage);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userpage/TeacherPage.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            session.stopSession(stage);
        }
    }

    public void goToStudentsView(Stage stage, User user) {
        session.startSession(user, stage);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userpage/StudentPage.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            session.stopSession(stage);
        }
    }
}
