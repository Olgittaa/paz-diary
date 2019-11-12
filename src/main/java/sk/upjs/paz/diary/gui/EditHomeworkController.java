package sk.upjs.paz.diary.gui;

import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;

public class EditHomeworkController {

	@FXML
	private Button saveHomeworkButton;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private DatePicker deadlineDatePicker;

	@FXML
	private ComboBox<Subject> subjectComboBox;

	private List<Homework> homework = new LinkedList<>();

	@FXML
	void initialize() {
		subjectComboBox.getSelectionModel().selectFirst();
	}

	@FXML
	void saveHomework(ActionEvent event) {
		
	}

}
