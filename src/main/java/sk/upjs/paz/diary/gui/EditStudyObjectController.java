package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditStudyObjectController extends Controller{

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTimePicker timePicker;

    @FXML
    private JFXButton removeStudyObjectButton;

    @FXML
    private JFXButton saveStudyObjectButton;

    @FXML
    private JFXTextField typeTextField;

    @FXML
    void removeStudyObject(ActionEvent event) {

    }

    @FXML
    void saveStudyObject(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'editLecturePractice.fxml'.";
        assert timePicker != null : "fx:id=\"timePicker\" was not injected: check your FXML file 'editLecturePractice.fxml'.";
        assert removeStudyObjectButton != null : "fx:id=\"removeStudyObjectButton\" was not injected: check your FXML file 'editLecturePractice.fxml'.";
        assert saveStudyObjectButton != null : "fx:id=\"saveStudyObjectButton\" was not injected: check your FXML file 'editLecturePractice.fxml'.";
        assert typeTextField != null : "fx:id=\"typeTextField\" was not injected: check your FXML file 'editLecturePractice.fxml'.";

    }
}
