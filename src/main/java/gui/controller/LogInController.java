package gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
       // System.out.println(Mock_data.getStudentsList());
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.equals("student") && password.equals("student"))
        {
            System.out.println("Welcome student!");
        }
        if(username.equals("teacher") && password.equals("teacher"))
        {
            System.out.println("Welcome teacher!");
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
