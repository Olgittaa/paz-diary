package sk.upjs.paz.diary.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HomeworkDescriptionController {

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	void initialize() {
		descriptionTextArea.setText(description);
	}

	private String description;

	public HomeworkDescriptionController(String description) {
		this.description = description;
	}

}
