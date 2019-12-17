package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.IExamDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;

class ExamDaoTest {

	private IExamDAO dao = DaoFactory.INSTANCE.getExamDao(true);
	private ISubjectDAO subjectDao = DaoFactory.INSTANCE.getSubjectDao(true);

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
}
