package gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Session extends TimerTask {
    private static Session INSTANCE = null;

    private Timer timer;
    private int userID;
    private Stage stage;

    private Session() {
        timer = new Timer();
        stage = null;
        userID = -1;
    }

    public void startSession(int userID, Stage stage) {
        if (userID > 0) {
            setUserID(userID);
            this.stage = stage;
            timer.schedule(this, 5000);
        }
    }

    private void returnToLogin(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Session Finished!");
            stage.setScene(scene);
            stage.show();
            userID = -1;
            this.stage = null;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Session();
        }
        return INSTANCE;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;

    }

    @Override
    public void run() {
        returnToLogin(stage);
    }
}
