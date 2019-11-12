package sk.upjs.paz.diary.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Exam extends StudyObject {
	private LocalDateTime dateTime;
	private String location;

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

	@Override
	public String toString() {
		return "Exam [date=" + dateTime + ", location=" + location + "]\n";
	}

}
