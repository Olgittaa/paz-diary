package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Lesson;

class LessonDaoTest {

	private ILessonDAO dao = DaoFactory.getLessonDao(true);
	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao(true);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllLessons() {
		assertNotNull(dao.getAllLessons());
		assertFalse(dao.getAllLessons().isEmpty());
	}

	@Test
	void testGetLessonsBySubjectId() {
		if (subjectDao.getAllSubjects().size() != 0) {
			Long id = subjectDao.getAllSubjects().get(0).getId();
			List<Lesson> lessons = new ArrayList<Lesson>();
			for (Lesson lesson : dao.getAllLessons()) {
				if (lesson.getSubject().getId() == id) {
					lessons.add(lesson);
				}
			}

		} else {
			assertTrue(true);
		}
	}

	@Test
	void testGetWeekSchedule() {
		List<Lesson> lessons = dao.getWeekSchedule();
		Date date = Date.from(lessons.get(0).getDateTime().atZone(ZoneId.systemDefault()).toInstant());
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		for (int i = 1; i < lessons.size(); i++) {
			Date newDate = Date.from(lessons.get(i).getDateTime().atZone(ZoneId.systemDefault()).toInstant());
			Calendar newCalendar = new GregorianCalendar();
			newCalendar.setTime(newDate);
			assertEquals(week, newCalendar.get(Calendar.WEEK_OF_YEAR));
		}
	}

	@Test
	void testGetDaySchedule() {
		for (int i = 2; i < 7; i++) {
			for (Lesson lesson : dao.getDaySchedule(i)) {
				System.out.println(i + " " + (lesson.getDateTime().getDayOfWeek().getValue() + 1));
				assertEquals((lesson.getDateTime().getDayOfWeek().getValue() + 1), i);
			}
		}
	}
}
