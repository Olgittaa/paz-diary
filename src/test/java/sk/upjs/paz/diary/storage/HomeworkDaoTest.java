package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Homework;

class HomeworkDaoTest {

	private IHomeworkDAO dao = DaoFactory.getHomeworkDao(true);
	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao(true);

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
	void testGetAllHomeworkSorted() {
		assertNotNull(dao.getAllHomeworkSorted());
		assertFalse(dao.getAllHomeworkSorted().isEmpty());

		List<LocalDateTime> homeworksSorted = new ArrayList<LocalDateTime>();
		for (Homework homework : dao.getAllHomeworkSorted()) {
			homeworksSorted.add(homework.getDeadline());
		}

		List<LocalDateTime> homeworks = new ArrayList<LocalDateTime>();
		for (Homework homework : dao.getAllHomework()) {
			homeworks.add(homework.getDeadline());
		}

		Collections.sort(homeworks);

		assertEquals(homeworks, homeworksSorted);
	}

	@Test
	void testGetHomeworkSortedOnWeekSorted() {
		assertNotNull(dao.getHomeworkOnWeekSorted());
		assertFalse(dao.getHomeworkOnWeekSorted().isEmpty());
	}

	@Test
	void testGetHomeworkBySubjectId() {
		if (subjectDao.getAllSubjects().size() != 0) {
			Long id = subjectDao.getAllSubjects().get(0).getId();
			List<Homework> homeworks = new ArrayList<Homework>();
			for (Homework homework : dao.getAllHomework()) {
				if (homework.getSubject().getId() == id) {
					homeworks.add(homework);
				}
			}
			assertEquals(homeworks, dao.getHomeworkBySubjectId(id));
		} else {
			assertTrue(true);
		}
	}
}
