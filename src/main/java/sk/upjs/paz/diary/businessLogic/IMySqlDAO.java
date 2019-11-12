package sk.upjs.paz.diary.businessLogic;

import java.util.List;

import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;

public interface IMySqlDAO {
	/**
	 * Extracts exams out of database
	 * 
	 * @return list of Exam
	 */
	List<Exam> getAllExams();

	/**
	 * Extracts homework out of database
	 * 
	 * @return list of Homework
	 */
	List<Homework> getAllHomework();

	/**
	 * Extracts lessons out of database
	 * 
	 * @return list of Lesson
	 */
	List<Lesson> getAllLessons(); // is it ok?

	/**
	 * Extracts subjects out of database
	 * 
	 * @return list of Subject
	 */
	List<Subject> getAllSubjects();

	/**
	 * Inserts new or edits existing subject in a database
	 */
	void saveSubject(Subject subject);
	
}
