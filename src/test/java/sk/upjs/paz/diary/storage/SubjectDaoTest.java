package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Subject;

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
	void testGetAllSubjectsSorted() {
		assertNotNull(dao.getAllSubjectsSorted());
		assertFalse(dao.getAllSubjectsSorted().isEmpty());

		List<String> subjectNameSorted = new ArrayList<String>();
		for (Subject subject : dao.getAllSubjectsSorted()) {
			subjectNameSorted.add(subject.getName());
		}

		List<String> subjectName = new ArrayList<String>();
		for (Subject subject : dao.getAllSubjects()) {
			subjectName.add(subject.getName());
		}

		Collections.sort(subjectName);

		assertEquals(subjectName, subjectNameSorted);
	}

	@Test
	void testGetNameById() {
		if (dao.getAllSubjects().size() != 0) {
			Subject subject = dao.getAllSubjects().get(0);
			String name = subject.getName();
			Long id = subject.getId();
			assertEquals(name, dao.getNameById(id));
		} else {
			assertTrue(true);
		}
	}

	@Test
	void testGetSubjectById() {
		if (dao.getAllSubjects().size() != 0) {
			Subject subject = dao.getAllSubjects().get(0);
			Long id = subject.getId();
			assertEquals(subject, dao.getSubjectById(id));
		} else {
			assertTrue(true);
		}
	}
}
