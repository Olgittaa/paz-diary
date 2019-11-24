package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import sk.upjs.paz.diary.entity.Homework;

public class HomeworkDescriptionController {

	@FXML
	private JFXTextArea descriptionTextArea;

	@FXML
	void initialize() {
		descriptionTextArea.setText(homework.getDescription());
	}

	private Homework homework;

	public HomeworkDescriptionController(Homework homework) {
		this.homework = homework;
	}

}
