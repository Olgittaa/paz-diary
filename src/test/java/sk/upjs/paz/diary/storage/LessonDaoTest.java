package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
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

		}
	}

	@Test
	void testGetWeekSchedule() {
		if (dao.getAllLessons().size() != 0) {
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

	@Test
	void testSaveAndRemove() {
		Subject subject = new Subject();
		subject.setEmail("email@upjs.sk");
		subject.setName("example");
		subject.setSite("example.com");
		Long subjectId = subjectDao.save(subject).getId();
		subject.setId(subjectId);
		Lesson lesson = new Lesson();
		lesson.setSubject(subject);
		lesson.setType("Practice");
		lesson.setDuration(2);
		lesson.setLocation("sa1c03");
		lesson.setDateTime(LocalDateTime.of(2019, 12, 15, 14, 20));
		int beforeSave = dao.getAllLessons().size();
		Long id = dao.save(lesson).getId();
		lesson.setId(id);
		int afterSave = dao.getAllLessons().size();
		assertTrue(afterSave == beforeSave + 1);
		dao.remove(lesson);
		int afterDelete = dao.getAllLessons().size();
		assertTrue(afterDelete == afterSave - 1);
	}
}
