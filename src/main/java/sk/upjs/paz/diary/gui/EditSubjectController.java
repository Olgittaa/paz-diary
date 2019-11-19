package sk.upjs.paz.diary.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.SubjectFXModel;

public class EditSubjectController {

	@FXML
	private JFXTextField nameTextField;

	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXTextField siteTextField;

	@FXML
	private JFXButton addSubjectButton;

	@FXML
	private JFXButton cancelSubjectButton;

	private SubjectFXModel editedSubject;

	public EditSubjectController() {
		editedSubject = new SubjectFXModel();
	}

	public EditSubjectController(Subject subject) {
		this();
		editedSubject.load(subject);
	}

	@FXML
	void initialize() {
		nameTextField.textProperty().bindBidirectional(editedSubject.getNameProperty());
		siteTextField.textProperty().bindBidirectional(editedSubject.getSiteProperty());
		emailTextField.textProperty().bindBidirectional(editedSubject.getEmailProperty());

		// FIXME
		nameTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null || newValue.trim().isEmpty()) {
					addSubjectButton.setDisable(true);
				} else {
					addSubjectButton.setDisable(false);
				}
			}
		});
	}

	@FXML
	void addSubjectButtonClick(ActionEvent event) {
		addSubjectButton.getScene().getWindow().hide();
	}

	@FXML
	void cancelSubjectButtonClick(ActionEvent event) {
		addSubjectButton.getScene().getWindow().hide();
	}

}
