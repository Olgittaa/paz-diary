package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubjectDaoTest {

	private ISubjectDAO dao = DaoFactory.getSubjectDao(true);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllSubjects() {
		assertNotNull(dao.getAllSubjects());
		assertFalse(dao.getAllSubjects().isEmpty());
	}

	@Test
	void testGetNameById() {
		fail("Not yet implemented");

	}

	@Test
	void testGetSubjectById() {
		fail("Not yet implemented");
	}

}
