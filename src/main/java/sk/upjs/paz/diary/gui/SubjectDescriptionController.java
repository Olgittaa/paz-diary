package sk.upjs.paz.diary.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.storage.DaoFactory;

public class SubjectDescriptionController extends Controller {
	/** Logger */
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@FXML
	private JFXTextField nameTextField;

	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXTextField siteTextField;

	@FXML
	private JFXListView<Lesson> lessonsListView;

	private Subject subject;

	public SubjectDescriptionController(Subject subject) {
		this.subject = subject;
	}

	@FXML
	void initialize() {
		nameTextField.setText(subject.getName());
		emailTextField.setText(subject.getEmail());
		siteTextField.setText(subject.getSite());

		lessonsListView.setItems(
				FXCollections.observableArrayList(DaoFactory.getLessonDao().getLessonsBySubjectId(subject.getId())));
	}

	@FXML
	void openInBrowserOnMouseClick(MouseEvent event) {
		if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			LOGGER.info("Action browsing is not supported on this platform");
			return;
		} else if (subject.getSite() == null) {
			showAlert(AlertType.WARNING, "Warning", "No site provided", "You should provide subject's site first");
			return;
		}
		try {
			Desktop.getDesktop().browse(new URI(subject.getSite()));
		} catch (IOException | URISyntaxException e) {
			LOGGER.error("Browsing error", e);
		}
	}

	@FXML
	void sendEmailImageViewClick(MouseEvent event) {
		if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
			LOGGER.info("Action mailng is not supported on this platform");
			return;
		} else if (subject.getEmail() == null) {
			showAlert(AlertType.WARNING, "Warning", "No email provided", "You should provide teacher's email first");
			return;
		}

		try {
			Desktop.getDesktop().mail(new URI("mailto:" + subject.getEmail()));
		} catch (IOException | URISyntaxException e) {
			LOGGER.error("Mailing error", e);
		}
	}

}
