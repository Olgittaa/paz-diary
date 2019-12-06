package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class EditLessonController extends Controller{

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
       
    }
}
