package sk.upjs.paz.diary.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

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
	private JFXButton cancelButton;

	@FXML
	private JFXButton saveButton;

	@FXML
	void cancelButtonClick(ActionEvent event) {
		closeWindow(cancelButton);
	}

	public SubjectDescriptionController() {

	}

	@FXML
	void openInBrowserOnMouseClick(MouseEvent event) {
		if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			LOGGER.info("Action browse is not supported on this platform");
			return;
		}
		try {
			Desktop.getDesktop().browse(new URI(""));
		} catch (IOException | URISyntaxException e) {
			LOGGER.error("Browsing error", e);
		}
	}

	@FXML
	void saveButtonClick(ActionEvent event) {

	}

	@FXML
	void sendEmailImageViewClick(MouseEvent event) {

	}

}
