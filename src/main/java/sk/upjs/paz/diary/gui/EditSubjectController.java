package sk.upjs.paz.diary.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.SubjectFXModel;

public class EditSubjectController {

	@FXML
	private Button addSubjectButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField emailTextField;

	@FXML
	private TextField siteTextField;

	@FXML
	private Button cancelSubjectButton;

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

		//FIXME
		nameTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue == null || newValue.trim().isEmpty()) {
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
