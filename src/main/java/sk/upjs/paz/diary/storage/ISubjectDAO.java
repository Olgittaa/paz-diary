package sk.upjs.paz.diary.storage;

import java.util.List;

import sk.upjs.paz.diary.entity.Subject;

public interface ISubjectDAO {
	/**
	 * Extracts subjects out of database
	 * 
	 * @return list of Subject
	 */
	List<Subject> getAllSubjects();

	
	
}
