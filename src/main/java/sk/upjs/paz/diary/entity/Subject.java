package sk.upjs.paz.diary.entity;

import java.util.HashSet;
import java.util.Set;

public class Subject extends StudyObject {
	private String name;
	private String site;
	private String email;

	private Set<Lesson> lessons = new HashSet<>();
	private Set<Homework> homeworks = new HashSet<>();
	private Set<Exam> exams = new HashSet<>();

	public void addLesson(Lesson... lessons) {
		for (Lesson lesson : lessons)
			this.lessons.add(lesson);
	}

	public void addHomework(Homework... homeworks) {
		for (Homework homework : homeworks)
			this.homeworks.add(homework);
	}

	public void addExam(Exam... exams) {
		for (Exam exam : exams)
			this.exams.add(exam);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
