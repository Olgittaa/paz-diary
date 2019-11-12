package sk.upjs.paz.diary.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sk.upjs.paz.diary.entity.Subject;

public class EditExamsController {

    @FXML
    private Button saveExamButton;

    @FXML
    private TextField locationTextField;

    @FXML
    private ComboBox<Subject> subjectCheckBox;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    void initialize() {
    	subjectCheckBox.getSelectionModel().selectFirst();
    }
    
    @FXML
    void saveExam(ActionEvent event) {

    }

}
