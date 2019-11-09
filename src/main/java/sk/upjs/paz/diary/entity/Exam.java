package sk.upjs.paz.diary.entity;

import java.time.LocalDate;

public class Exam extends StudyObject {
	private LocalDate date;
	private String location;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
