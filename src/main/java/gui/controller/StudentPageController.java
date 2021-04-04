package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable{

    private Calendar calendar = Calendar.getInstance(Locale.GERMANY);

    @FXML
    private TilePane tileCalendar;
    @FXML
    private Text textMonth;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void actionMonthClick(ActionEvent e) {
        int month = Month.valueOf(((Button) e.getSource()).getText().toUpperCase()).getValue();
        textMonth.setText(((Button) e.getSource()).getText());
        int daysInMonth = YearMonth.of(calendar.get(Calendar.YEAR), month).lengthOfMonth();

        if (!tileCalendar.getChildren().isEmpty()) {
            tileCalendar.getChildren().clear();
        }
        for (int i = 1; i < daysInMonth + 1; i++) {
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);

            Label label = new Label(String.valueOf(i));
            vbox.getChildren().add(label);

            vbox.setOnMouseClicked(mouseEvent -> {
                if (Integer.parseInt(label.getText()) == calendar.get(Calendar.DAY_OF_MONTH) && (calendar.get(Calendar.MONTH) + 1) == month) {
                    vbox.setStyle("-fx-background-color: green");
                }
            });

            tileCalendar.getChildren().add(vbox);
        }
    }
}
