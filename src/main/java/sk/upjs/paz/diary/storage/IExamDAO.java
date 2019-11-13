package sk.upjs.paz.diary.storage;

import java.util.List;

import sk.upjs.paz.diary.entity.Exam;

public interface IExamDAO {
	List<Exam> getAllExams();
	
	List<Exam> getExamsBySubjectName(String name);
}
