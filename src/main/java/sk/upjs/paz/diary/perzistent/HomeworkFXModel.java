package sk.upjs.paz.diary.perzistent;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HomeworkFXModel {
	
	private LocalDate deadline;
	private StringProperty description = new SimpleStringProperty();
	
}
