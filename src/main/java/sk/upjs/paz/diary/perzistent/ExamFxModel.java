package sk.upjs.paz.diary.perzistent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.storage.DaoFactory;

public class ExamFxModel {
	private Long id;
	private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();
	private ObjectProperty<LocalTime> timeProperty = new SimpleObjectProperty<>();
	private StringProperty locationProperty = new SimpleStringProperty();
	private ObjectProperty<Subject> subjectProperty = new SimpleObjectProperty<>();

	public LocalDate getDate() {
		return dateProperty.get();
	}

	public void setDate(LocalDate date) {
		dateProperty.set(date);
	}

	public LocalTime getTime() {
		return timeProperty.get();
	}

	public void setTime(LocalTime time) {
		timeProperty.set(time);
	}

	public String getLocation() {
		return locationProperty.get();
	}

	public void setLocation(String location) {
		locationProperty.set(location);
	}

	public Subject getSubject() {
		return subjectProperty.get();
	}

	public void setSubject(Subject subject) {
		subjectProperty.set(subject);
	}

	public ObjectProperty<LocalDate> getDateProperty() {
		return dateProperty;
	}

	public void setDateProperty(ObjectProperty<LocalDate> dateProperty) {
		this.dateProperty = dateProperty;
	}

	public ObjectProperty<LocalTime> getTimeProperty() {
		return timeProperty;
	}

	public void setTimeProperty(ObjectProperty<LocalTime> timeProperty) {
		this.timeProperty = timeProperty;
	}

	public StringProperty getLocationProperty() {
		return locationProperty;
	}

	public void setLocationProperty(StringProperty locationProperty) {
		this.locationProperty = locationProperty;
	}

	public ObjectProperty<Subject> getSubjectProperty() {
		return subjectProperty;
	}

	public void setSubjectProperty(ObjectProperty<Subject> subjectProperty) {
		this.subjectProperty = subjectProperty;
	}

	public Exam getExam(Long idSubject) {
		Exam exam = new Exam();
		exam.setDateTime(LocalDateTime.of(getDate(), getTime()));
		exam.setLocation(getLocation());
		exam.setSubject(getSubject());
		exam.setSubject(DaoFactory.getSubjectDao().getSubjectById(idSubject));
		exam.setId(id);
		return exam;
	}

	public void loadFromExam(Exam exam) {
		setDate(exam.getDateTime().toLocalDate());
		setTime(exam.getDateTime().toLocalTime());
		setLocation(exam.getLocation());
		id = exam.getId();
		setSubject(exam.getSubject());
	}

	@Override
	public String toString() {
		return "ExamFXModel [id=" + id + ", dateProperty=" + dateProperty + ", timeProperty=" + timeProperty
				+ ", locationProperty=" + locationProperty + ", subjectProperty=" + subjectProperty + "]";
	}

}
