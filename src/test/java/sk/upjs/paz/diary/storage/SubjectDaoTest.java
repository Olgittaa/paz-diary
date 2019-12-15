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
		}
	}

	@Test
	void testGetSubjectById() {
		if (dao.getAllSubjects().size() != 0) {
			Subject subject = dao.getAllSubjects().get(0);
			Long id = subject.getId();
			assertEquals(subject, dao.getSubjectById(id));
		}
	}
	
	@Test
	void testGetSubjectByName() {
		if (dao.getAllSubjects().size() != 0) {
			Subject subject = dao.getAllSubjects().get(0);
			String name = subject.getName();
			assertEquals(subject, dao.getSubjectByName(name));
		}
	}
	
	@Test
	void testSaveAndRemove() {
			Subject subject = new Subject();
			subject.setEmail("email@upjs.sk");
			subject.setName("example");
			subject.setSite("example.com");
			int beforeSave = dao.getAllSubjects().size();
			Long id = dao.save(subject).getId();
			subject.setId(id);
			int afterSave = dao.getAllSubjects().size();
			assertTrue(beforeSave == afterSave - 1);
			
			dao.remove(subject);
			int afterDelete = dao.getAllSubjects().size();
			assertTrue(afterDelete == afterSave - 1);
	}
}
