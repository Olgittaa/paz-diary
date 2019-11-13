package sk.upjs.paz.diary.gui;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz.diary.businessLogic.DaoFactory;
import sk.upjs.paz.diary.businessLogic.IMySqlDAO;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.perzistent.SubjectFXModel;

public class ScheduleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

	@FXML
	private ListView<?> mondayListView;

	@FXML
	private ListView<?> wednesdayListView;

	@FXML
	private ListView<?> tuesdayListView;

	@FXML
	private ListView<?> thursdayListView;

	@FXML
	private ListView<?> fridayListView;

	@FXML
	private Label monthLabel;

	@FXML
	private ListView<Subject> subjectListView;

	@FXML
	private Button removeSubjectButton;

	@FXML
	private Button addSubjectButton;

	private IMySqlDAO dao = DaoFactory.getMySqlDao();

	private SubjectFXModel subjectFXModel = new SubjectFXModel();
	private Subject currentSubject = new Subject();

	@FXML
	void addSubjectButtonClick(ActionEvent event) {
		loadWindow("editSubject.fxml", "Editing subject", new EditSubjectController(currentSubject));
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

	/**
	 * Initializes window using fxml file
	 * 
	 * @param xmlFileName - name of a fxml file which will be loaded
	 * @param windowTitle - title of a window(stage)
	 */
	private void loadWindow(String fxmlFileName, String windowTitle) {
		loadWindow(fxmlFileName, windowTitle, null);
	}

	/**
	 * Initializes window using fxml file
	 * 
	 * @param fxmlFileName - name of a fxml file which will be loaded
	 * @param windowTitle  - windowTitle title of a window(stage)
	 * @param controller   - controller for the fxml
	 */
	private void loadWindow(String fxmlFileName, String windowTitle, Object controller) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
			fxmlLoader.setController(controller);
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			Stage modalStage = new Stage();
			modalStage.setTitle(windowTitle);
			modalStage.setScene(scene);
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.showAndWait(); // код за loadWindow не будет выполняться пока окно открыто
		} catch (LoadException e) {
			LOGGER.error("Wrong controller \"" + controller + "\"", e);
		} catch (IOException e) {
			LOGGER.error("Cant load fxml file\"" + fxmlFileName + "\"", e);
		}
	}

}
