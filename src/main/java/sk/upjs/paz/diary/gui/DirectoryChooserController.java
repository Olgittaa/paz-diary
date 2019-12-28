package sk.upjs.paz.diary.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sk.upjs.paz.diary.pdf.PdfWriter;
import sk.upjs.paz.diary.pdf.SchedulePdfWriter;

public class DirectoryChooserController extends Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryChooserController.class);

	private final DirectoryChooser DIRECTORY_CHOOSER;
	private PdfWriter pdfWriter;

	@FXML
	private JFXButton selectDirectoryButton;

	public DirectoryChooserController() {
		DIRECTORY_CHOOSER = new DirectoryChooser();
		DIRECTORY_CHOOSER.setTitle("Choose a directory");
	}

	@FXML
	void selectDirectoryButtonClick(ActionEvent event) {
		Stage stage = new Stage();
		File dir = DIRECTORY_CHOOSER.showDialog(stage);
		if (dir != null) {
			createPdf(dir.toPath());
		}
		stage.hide();
	}

	private void createPdf(Path path) {
		try {
			pdfWriter = new SchedulePdfWriter(path);
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
		pdfWriter.writeToPdf();
	}
}