package sk.upjs.paz.diary.entity;

import java.time.LocalDate;

public class HomeWork extends StudyObject {
	private LocalDate deadline;
	private String description;

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
