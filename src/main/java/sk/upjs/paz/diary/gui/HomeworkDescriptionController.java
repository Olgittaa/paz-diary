package sk.upjs.paz.diary.gui;

import java.time.Duration;
import java.time.LocalDateTime;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import sk.upjs.paz.diary.entity.Homework;

public class HomeworkDescriptionController extends Controller {
	@FXML
	private Label subjectLabel;

	@FXML
	private Label timeLabel;

	@FXML
	private JFXTextArea descriptionTextArea;

	private Homework homework;

	public HomeworkDescriptionController(Homework homework) {
		this.homework = homework;
	}

	@FXML
	void initialize() {
		subjectLabel.setAlignment(Pos.CENTER);
		subjectLabel.setText(homework.getSubject().toString());

		Duration between = Duration.between(LocalDateTime.now(), homework.getDeadline());
		long seconds = between.getSeconds();

		int days = (int) (seconds / (24 * 3600));
		int hours = (int) ((seconds - (days * 24 * 3600)) / 3600);
		int minutes = (int) ((seconds - (hours * 3600)) / (3600 * 60));

		timeLabel.setAlignment(Pos.CENTER);
		timeLabel.setText(days + "D " + hours + "H " + minutes + "M left");

		descriptionTextArea.setText(homework.getDescription());
	}
}
