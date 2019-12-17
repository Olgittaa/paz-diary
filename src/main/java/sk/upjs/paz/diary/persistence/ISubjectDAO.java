package sk.upjs.paz.diary.persistence;

import java.util.List;

import sk.upjs.paz.diary.entity.Subject;

public interface ISubjectDAO {
	/**
	 * Returns a list of subjects which gets from database
	 * 
	 * @return list of Subject
	 */
	List<Subject> getAllSubjects();

	String getNameById(Long id);

	Subject getSubjectById(Long id);

	Subject save(Subject subject);

	void remove(Subject subject);

	List<Subject> getAllSubjectsSorted();

	Subject getSubjectByName(String name);

}
