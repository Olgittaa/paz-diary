package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Homework;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.IHomeworkDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;

class HomeworkDaoTest {

	private IHomeworkDAO homeworkDao = DaoFactory.INSTANCE.getHomeworkDao(true);
	private ISubjectDAO subjectDao = DaoFactory.INSTANCE.getSubjectDao(true);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllHomework() {
		List<Homework> allHomework = homeworkDao.getAllHomework();
		assertNotNull(allHomework);
		assertFalse(allHomework.isEmpty());
	}

	@Test
	void testGetHomeworkOnWeekSorted() {
		assertNotNull(homeworkDao.getHomeworkOnWeekSorted());
		assertFalse(homeworkDao.getHomeworkOnWeekSorted().isEmpty());
	}

	@Test
	void testSave() {
		Subject subject = new Subject();
		subject.setName("example");
		Long subjectId = subjectDao.save(subject).getId();
		subject.setId(subjectId);
		Homework homework = new Homework();
		homework.setSubject(subject);
		homework.setDescription("example");
		homework.setStatus(false);
		homework.setDeadline(LocalDateTime.of(2019, 12, 15, 14, 20));
		int beforeSave = homeworkDao.getAllHomework().size();
		Long id = homeworkDao.save(homework).getId();
		homework.setId(id);
		int afterSave = homeworkDao.getAllHomework().size();
		assertTrue(afterSave == beforeSave + 1);
	}

	@Test
	void testRemove() {
		Subject subject = new Subject();
		subject.setName("example");
		Long subjectId = subjectDao.save(subject).getId();
		subject.setId(subjectId);
		Homework homework = new Homework();
		homework.setSubject(subject);
		homework.setDescription("example");
		homework.setStatus(false);
		homework.setDeadline(LocalDateTime.of(2019, 12, 15, 14, 20));
		Long id = homeworkDao.save(homework).getId();
		homework.setId(id);
		int afterSave = homeworkDao.getAllHomework().size();

		homeworkDao.remove(homework);
		int afterDelete = homeworkDao.getAllHomework().size();
		assertTrue(afterDelete == afterSave - 1);
	}
}
