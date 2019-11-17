package sk.upjs.paz.diary.storage;

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
}
