package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;

public class HomeworkDescriptionController {

	@FXML
	private JFXTextArea descriptionTextArea;

	@FXML
	void initialize() {
		descriptionTextArea.setText(description);
	}

	private String description;

	public HomeworkDescriptionController(String description) {
		this.description = description;
	}

}
