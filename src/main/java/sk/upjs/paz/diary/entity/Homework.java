package sk.upjs.paz.diary.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.converter.LocalDateTimeStringConverter;

public class Homework extends StudyObject {

	private long id;
	private LocalDateTime deadline;
	private String description;
	private boolean status;
	private Long idSubject;

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public String getStringDeadline() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
		return getDeadline().format(formatter);
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDone() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Long idSubject) {
		this.idSubject = idSubject;
	}

	@Override
	public String toString() {
		return "Homework [deadline=" + deadline + ", description=" + description + "]";
	}

}
