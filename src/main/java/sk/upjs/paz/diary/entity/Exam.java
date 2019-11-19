package sk.upjs.paz.diary.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exam extends StudyObject {
	private LocalDateTime dateTime;
	private String location;
	private Subject subject;
	
	public Exam(LocalDateTime dateTime, String location) {
		this.dateTime = dateTime;
		this.location = location;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public String getStringDeadline() {
		return getDateTime().format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Subject getSubject() {
		return subject;
	}

	@Override
	public String toString() {
		return "Exam [date=" + dateTime + ", location=" + location + "]\n";
	}

}
