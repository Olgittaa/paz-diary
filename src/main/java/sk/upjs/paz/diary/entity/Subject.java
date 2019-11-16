package sk.upjs.paz.diary.entity;

public class Subject extends StudyObject {

	private Long id;
	private String name;
	private String site;
	private String email;

	//лушче пусть каждый помнит свой сабджект, это легче реализовать
//	private List<Lesson> lessons;
//	private List<Homework> homeworks;
//	private List<Exam> exams;

	public Subject() {
		name = new String();
		site = new String();
		email = new String();
	}

	public Subject(Long id, String name, String site, String email) {
		this();
		this.id = id;
		this.name = name;
		this.site = site;
		this.email = email;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
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
