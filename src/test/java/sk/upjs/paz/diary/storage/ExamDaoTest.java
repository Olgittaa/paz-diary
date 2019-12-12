package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExamDaoTest {

	private IExamDAO dao = DaoFactory.getExamDao(true);

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
		fail("Not yet implemented");

	}
}
