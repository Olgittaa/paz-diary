package sk.upjs.paz.diary.gui;

import java.io.File;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sk.upjs.paz.diary.pdf.PdfWriter;
import sk.upjs.paz.diary.pdf.SchedulePdfWriter;

public class FileChooserController extends Controller {

	private final FileChooser FILE_CHOOSER;
	private PdfWriter pdfWriter;
	
	public FileChooserController() {
		FILE_CHOOSER = new FileChooser();
		FILE_CHOOSER.setTitle("Choose or create file");
		FILE_CHOOSER.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
	}

	@FXML
	private JFXButton selectFileButton;

	@FXML
	void selectFileButtonClick(ActionEvent event) {
		Stage stage = new Stage();
		File file = FILE_CHOOSER.showOpenDialog(stage);
		if (file == null || !file.getName().endsWith(".pdf")) {
			showAlert(AlertType.WARNING, "Warning", "Warning!", "You should choose or create a \".pdf\" file");
			return;
		}
		createPdf(file.getAbsolutePath());
		stage.hide();
	}

	private void createPdf(String fileName) {
		pdfWriter = new SchedulePdfWriter(fileName);
		pdfWriter.writeToPdf();
	}
}