package sk.upjs.paz.diary.gui;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.SubjectFXModel;
import sk.upjs.paz.diary.storage.DaoFactory;
import sk.upjs.paz.diary.storage.ISubjectDAO;

public class ScheduleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

	@FXML
	private ListView<Lesson> mondayListView;

	@FXML
	private ListView<Lesson> wednesdayListView;

	@FXML
	private ListView<Lesson> tuesdayListView;

	@FXML
	private ListView<Lesson> thursdayListView;

	@FXML
	private ListView<Lesson> fridayListView;

	@FXML
	private Label monthLabel;

	@FXML
	private ListView<Subject> subjectListView;

	@FXML
	private Button removeSubjectButton;

	@FXML
	private Button addSubjectButton;

	private ISubjectDAO dao = DaoFactory.getSubjectDao();

	private SubjectFXModel subjectFXModel = new SubjectFXModel();
	private Subject currentSubject = new Subject();

	@FXML
	void addSubjectButtonClick(ActionEvent event) {
		String fxmlFileName = "editSubject.fxml";
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
			fxmlLoader.setController(new EditSubjectController(currentSubject));
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage modalStage = new Stage();
			modalStage.setTitle("Edit subject");
			modalStage.setScene(scene);
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.showAndWait(); // код за loadWindow не будет выполняться пока окно открыто
		} catch (IOException e) {
			LOGGER.error("Cant load fxml file\"" + fxmlFileName + "\"", e);
		}
			
		refreshListView(subjectFXModel.getSubject());
	}

	/**
	 * 
	 * @param newSubject - added/edited subject
	 */
	private void refreshListView(Subject newSubject) {
		List<Subject> currentSubjects = dao.getAllSubjects();

		for (Subject subject : currentSubjects) {
			if (subject.equals(newSubject)) {
				subject = newSubject;
				currentSubjects.add(subject);
				break;
			}
		}
		subjectListView.setItems(FXCollections.observableArrayList(currentSubjects));
	}

	@FXML
	void removeSubjectButtonClick(ActionEvent event) {

	}

	@FXML
	void initialize() {
		subjectListView.setItems(FXCollections.observableArrayList(dao.getAllSubjects()));
	}
}
