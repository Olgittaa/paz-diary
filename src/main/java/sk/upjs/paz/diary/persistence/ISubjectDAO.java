package sk.upjs.paz.diary.persistence;

import java.util.List;

import sk.upjs.paz.diary.entity.Subject;

public interface ISubjectDAO {
	List<Subject> getAllSubjects();

	Subject getSubjectById(Long id);

	Subject save(Subject subject);

	void remove(Subject subject);

	List<Subject> getAllSubjectsSorted();

	Subject getSubjectByName(String name);

}
