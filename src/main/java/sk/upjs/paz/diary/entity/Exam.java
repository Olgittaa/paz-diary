package sk.upjs.paz.diary.entity;

import java.time.LocalDateTime;

public class Exam extends StudyObject {
	private LocalDateTime dateTime;
	private String location;
	private Long idSubject;

	public Exam() {
	}

	public Exam(LocalDateTime dateTime, String location) {
		this.dateTime = dateTime;
		this.location = location;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
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

	public Long getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Long idSubject) {
		this.idSubject = idSubject;
	}

	@Override
	public String toString() {
		return "Exam [date=" + dateTime + ", location=" + location + "]\n";
	}

}
