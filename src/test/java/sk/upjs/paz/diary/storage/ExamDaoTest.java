package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.IExamDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;

class ExamDaoTest {

	private IExamDAO dao = DaoFactory.getExamDao(true);
	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao(true);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllExams() {
		assertNotNull(dao.getAllExams());
		assertFalse(dao.getAllExams().isEmpty());
	}

	@Test
	void testGetExamsBySubjectId() {
		if (subjectDao.getAllSubjects().size() != 0) {
			Long id = subjectDao.getAllSubjects().get(0).getId();
			List<Exam> exams = new ArrayList<Exam>();
			for (Exam exam : dao.getAllExams()) {
				if (exam.getSubject().getId() == id) {
					exams.add(exam);
				}
			}
			assertEquals(exams, dao.getExamsBySubjectId(id));
		}
	}
}
