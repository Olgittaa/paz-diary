package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sk.upjs.paz.diary.entity.Subject;

public class EditExamsController {

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTimePicker timePicker;

    @FXML
    private JFXComboBox<Subject> subjectCheckBox;

    @FXML
    private JFXTextField locationTextField;

    @FXML
    private JFXButton saveExamButton;
    
    @FXML
    void initialize() {
    	subjectCheckBox.getSelectionModel().selectFirst();
    }
    
    @FXML
    void saveExam(ActionEvent event) {

    }

}
