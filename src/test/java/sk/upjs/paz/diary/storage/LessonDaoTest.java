package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Subject;

class LessonDaoTest {

	private ILessonDAO dao = DaoFactory.getLessonDao(true);
	private ISubjectDAO subjectDao = DaoFactory.getSubjectDao(true);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
//проблемааааааааааааааааааааааааааааааааааааааааааааааааааааа
	@Test
	void testGetAllLessons() {
		assertNotNull(dao.getAllLessons());
		assertFalse(dao.getAllLessons().isEmpty());
	}
//проблемааааааааааааааааааааааааааааааааааааааааааааааааааааа
	@Test
	void testGetLessonsBySubjectId() {
		if (subjectDao.getAllSubjects().size() != 0) {
			Subject subject = subjectDao.getAllSubjects().get(0);
			Long id = subject.getId();

			List<Lesson> lessons = new ArrayList<Lesson>();
			for (Lesson lesson : dao.getAllLessons()) {
				if (lesson.getSubject().getId() == id) {
					lessons.add(lesson);
				}
				assertEquals(lessons, dao.getLessonsBySubjectId(id));
			}
		} else {
			assertTrue(true);
		}
	}
//проблемааааааааааааааааааааааааааааааааааааааааааааааааааааа
	@Test
	void testGetWeekSchedule() {
		assertNotNull(dao.getAllLessons());
		assertFalse(dao.getWeekSchedule().isEmpty());
	}

	@Test
	void testGetDaySchedule() {
		for (int i = 2; i < 7; i++) {
			assertNotNull(dao.getDaySchedule(i));
			assertFalse(dao.getDaySchedule(i).isEmpty());
		}
	}
}
