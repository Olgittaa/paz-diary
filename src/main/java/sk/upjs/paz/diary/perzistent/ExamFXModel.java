package sk.upjs.paz.diary.perzistent;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExamFXModel {
	private StringProperty stringProperty = new SimpleStringProperty();
	private LongProperty idProperty = new SimpleLongProperty();
	
	private ObjectProperty subjectProperty = new SimpleObjectProperty();
	
}
