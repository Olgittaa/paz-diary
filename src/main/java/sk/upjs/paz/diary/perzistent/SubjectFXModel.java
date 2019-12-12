package sk.upjs.paz.diary.perzistent;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz.diary.entity.Subject;

public class SubjectFXModel {
	private Long id;
	private StringProperty name;
	private StringProperty site;
	private StringProperty email;

	public SubjectFXModel() {
		name = new SimpleStringProperty();
		site = new SimpleStringProperty();
		email = new SimpleStringProperty();
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public StringProperty getSiteProperty() {
		return site;
	}

	public StringProperty getEmailProperty() {
		return email;
	}

	public void setNameProperty(StringProperty name) {
		this.name = name;
	}

	public void setSiteProperty(StringProperty site) {
		this.site = site;
	}

	public void setEmailProperty(StringProperty email) {
		this.email = email;
	}

	public String getName() {
		return name.get();
	}

	public String getSite() {
		return site.get();
	}

	public String getEmail() {
		return email.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public void setSite(String site) {
		this.site.set(site);
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void load(Subject subject) {
		setId(subject.getId());
		setName(subject.getName());
		setSite(subject.getSite());
		setEmail(subject.getEmail());
	}

	public Subject getSubject() {
		Subject subject = new Subject();
		subject.setName(name.get());
		subject.setSite(site.get());
		subject.setEmail(email.get());
		subject.setId(getId());
		return subject;
	}
}