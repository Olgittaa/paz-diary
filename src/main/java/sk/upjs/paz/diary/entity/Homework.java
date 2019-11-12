package sk.upjs.paz.diary.entity;

import java.time.LocalDate;

public class Homework extends StudyObject {
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

	@Override
	public String toString() {
		return "Homework [deadline=" + deadline + ", description=" + description + "]\n";
	}

}
