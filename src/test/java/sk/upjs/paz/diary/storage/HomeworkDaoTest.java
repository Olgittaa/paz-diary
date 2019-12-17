package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.IHomeworkDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;

class HomeworkDaoTest {

	private IHomeworkDAO dao = DaoFactory.INSTANCE.getHomeworkDao(true);
	private ISubjectDAO subjectDao = DaoFactory.INSTANCE.getSubjectDao(true);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllHomework() {
		assertNotNull(dao.getAllHomework());
		assertFalse(dao.getAllHomework().isEmpty());
	}

	@Test
	void testGetHomeworkSortedOnWeekSorted() {
		assertNotNull(dao.getHomeworkOnWeekSorted());
		assertFalse(dao.getHomeworkOnWeekSorted().isEmpty());
	}

	@Test
	void testSaveAndRemove() {
		Subject subject = new Subject();
		subject.setEmail("email@upjs.sk");
		subject.setName("example");
		subject.setSite("example.com");
		Long subjectId = subjectDao.save(subject).getId();
		subject.setId(subjectId);
		Homework homework = new Homework();
		homework.setSubject(subject);
		homework.setDescription("example");
		homework.setStatus(false);
		homework.setDeadline(LocalDateTime.of(2019, 12, 15, 14, 20));
		int beforeSave = dao.getAllHomework().size();
		Long id = dao.save(homework).getId();
		homework.setId(id);
		int afterSave = dao.getAllHomework().size();
		assertTrue(afterSave == beforeSave + 1);
		dao.remove(homework);
		int afterDelete = dao.getAllHomework().size();
		assertTrue(afterDelete == afterSave - 1);
	}
}
