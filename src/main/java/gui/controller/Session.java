package gui.controller;

import be.user.User;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Session extends TimerTask {
    private static Session INSTANCE = null;
    private Stage stage;

    private Timer timer;
    private User user;

    private Session() {
        timer = new Timer();
        user = null;
    }

    public void startSession(User user, Stage stage) {
        this.user = user;
        this.stage = stage;
        timer.schedule(this, 900000);
    }

    public void stopSession(Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException ioException) {
                    System.exit(0);
                }
                user = null;
                timer.cancel();
                Alert.displayAlert("Session finished!","Your session has finished");
            }
        });

    }

    public static Session getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Session();
        }
        return INSTANCE;
    }

    public User getUser(){
        return user;
    }


    @Override
    public void run() {
        stopSession(stage);
    }
}
