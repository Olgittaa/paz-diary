package sk.upjs.paz.diary.entity;

import java.util.LinkedList;
import java.util.List;

public class Subject extends StudyObject {
	private String name;
	private String site;
	private String email;

	private List<Lesson> lessons;
	private List<Homework> homeworks;
	private List<Exam> exams;

	public Subject() {
		name = new String();
		site = new String();
		email = new String();

		lessons = new LinkedList<>();
		homeworks = new LinkedList<>();
		exams = new LinkedList<>();
	}

	public Subject(String name, String site, String email) {
		this();
		this.name = name;
		this.site = site;
		this.email = email;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public void setHomework(List<Homework> homeworks) {
		this.homeworks = homeworks;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
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
		return name + "(\n" + "lessons:" + lessons + "\n" + "homework:" + homeworks + "\n" + "exams:" + exams + ")\n";
	}

}
