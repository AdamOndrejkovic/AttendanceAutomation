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

public class LogInController implements Initializable {


    private Teacher teacher;
    private Student student;

    public LogInController(){
        //Mock_data mockData;
    }

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton logInButton;

    @FXML
    void logIn(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        IClassRepository classRepository = new DBClassRepository();
        classRepository.createClass("DBO");

        AuthenticationManager authenticationManager = new AuthenticationManager();
        User user = (User) authenticationManager.checkCredintials(username, password);


        if(user instanceof  Teacher)
        {
            System.out.println("Welcome Teacher");
        }
        if(user instanceof Student)
        {
            System.out.println("Welcome Student!");
        }
        else
            System.out.println("Wrong username or password");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void registerTeacher(ActionEvent actionEvent) throws IOException {
        Parent root2;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/registerTeacher.fxml"));
        root2 = (Parent) fxmlLoader.load();
        fxmlLoader.<RegisterTeacherController>getController().setController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.centerOnScreen();
        stage.show();
    }
}
