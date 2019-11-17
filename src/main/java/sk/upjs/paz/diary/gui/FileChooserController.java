package sk.upjs.paz.diary.gui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sk.upjs.paz.diary.pdf.SchedulePdfWriter;

public class FileChooserController {

	private final FileChooser FILE_CHOOSER;

	public FileChooserController() {
		FILE_CHOOSER = new FileChooser();
		FILE_CHOOSER.setTitle("Choose or create file");
		FILE_CHOOSER.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
	}

	@FXML
	private Button selectFileButton;

	@FXML
	void selectFileButtonClick(ActionEvent event) {
		Stage stage = new Stage();
		File file = FILE_CHOOSER.showOpenDialog(stage);
		if (file == null || !file.getName().endsWith(".pdf")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alert");
			alert.setHeaderText("Warning!");
			alert.setContentText("You should choose or create a \".pdf\" file");
			alert.show();
			return;
		}
		createPdf(file.getAbsolutePath());
		stage.hide();
	}

	private void createPdf(String fileName) {
		new SchedulePdfWriter(fileName).createPdf();
	}
}