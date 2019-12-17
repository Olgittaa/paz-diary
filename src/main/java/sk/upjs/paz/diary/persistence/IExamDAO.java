package sk.upjs.paz.diary.persistence;

import java.util.List;

import sk.upjs.paz.diary.entity.Exam;

public interface IExamDAO {
	List<Exam> getAllExams();

	List<Exam> getExamsBySubjectId(Long id);

	void save(Exam exam);

	void remove(Exam exam);
}
