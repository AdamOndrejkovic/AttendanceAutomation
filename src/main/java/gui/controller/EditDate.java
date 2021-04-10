package gui.controller;

import be.Date;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.Calendar;

import java.util.concurrent.atomic.AtomicReference;

public class EditDate {
    public static Date editDate(Date date) {
        AtomicReference<Date> editedDate = new AtomicReference<>();
        Stage stage = new Stage();

        DatePicker datePicker = new DatePicker();
        Button button = new Button("Confirm Edit");

        button.setOnAction(actionEvent -> {
            if (datePicker.getValue() != null) {
                int year = datePicker.getValue().getYear();
                int month = datePicker.getValue().getMonthValue();
                int day = datePicker.getValue().getDayOfMonth();
                if (date.getYear() != year || date.getMonth() != month || date.getDay() != day) {

                    if (year >= Calendar.getYear() && month >= Calendar.getMonth() && day >= Calendar.getDay()) {
                        editedDate.set(new Date(year, month, day));
                        stage.close();
                    }
                }
            }
        });

        VBox vbox = new VBox(new Label("Selected: " + " > " + date + " < "), new Label("Edited Date: "), datePicker, button);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(10);

        stage.setScene(new Scene(vbox, 200, 150));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        return editedDate.get();
    }
}
