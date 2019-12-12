package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
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
	void testGetHomeworkSortedOnWeek() {
		List<Homework> homeworks = dao.getHomeworkOnWeekSorted();
		Date date = Date.from(homeworks.get(0).getDeadline().atZone(ZoneId.systemDefault()).toInstant());
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		for (int i = 1; i < homeworks.size(); i++) {
			Date newDate = Date.from(homeworks.get(i).getDeadline().atZone(ZoneId.systemDefault()).toInstant());
			Calendar newCalendar = new GregorianCalendar();
			newCalendar.setTime(newDate);
			assertEquals(week, newCalendar.get(Calendar.WEEK_OF_YEAR));
		}
	}

	@Test
	void testGetHomeworkBySubjectId() {
		fail("Not yet implemented");

	}
}
