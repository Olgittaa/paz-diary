package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Lesson;
import sk.upjs.paz.diary.entity.Lesson.LessonType;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.ILessonDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;
import sk.upjs.paz.diary.entity.Subject;

class LessonDaoTest {

	private ILessonDAO dao = DaoFactory.INSTANCE.getLessonDao(true);
	private ISubjectDAO subjectDao = DaoFactory.INSTANCE.getSubjectDao(true);

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
		List<Lesson> lessons = dao.getWeekSchedule();
		for (Lesson lesson : lessons) {
			assertTrue(lesson.getTillDate().isBefore(LocalDateTime.now()));
		}
	}

	@Test
	void testGetDaySchedule() {
		for (int i = 0; i < 7; i++) {
			for (Lesson lesson : dao.getDaySchedule(DayOfWeek.of(i))) {
				assertEquals((lesson.getDayOfWeek().getValue()), i);
			}
		}
	}

	@Test
	void testSave() {
		Subject subject = new Subject();
		subject.setName("example");
		Long subjectId = subjectDao.save(subject).getId();
		subject.setId(subjectId);

		Lesson lesson = new Lesson();
		lesson.setSubject(subject);
		lesson.setType(LessonType.PRACTICE);
		lesson.setDuration(2);
		lesson.setLocation("location");
		lesson.setTillDate(LocalDateTime.of(2019, 12, 15, 0, 0));
		lesson.setDayOfWeek(DayOfWeek.of(3));
		lesson.setStartTime(LocalTime.now());

		int beforeSave = dao.getAllLessons().size();
		Long id = dao.save(lesson).getId();
		lesson.setId(id);
		int afterSave = dao.getAllLessons().size();
		assertTrue(afterSave == beforeSave + 1);
	}

	@Test
	void testRemove() {
		Subject subject = new Subject();
		subject.setName("example");
		Long subjectId = subjectDao.save(subject).getId();
		subject.setId(subjectId);

		Lesson lesson = new Lesson();
		lesson.setSubject(subject);
		lesson.setType(LessonType.PRACTICE);
		lesson.setDuration(2);
		lesson.setLocation("location");
		lesson.setTillDate(LocalDateTime.of(2019, 12, 15, 0, 0));
		lesson.setDayOfWeek(DayOfWeek.of(3));
		lesson.setStartTime(LocalTime.now());

		Long id = dao.save(lesson).getId();
		lesson.setId(id);
		int afterSave = dao.getAllLessons().size();
		dao.remove(lesson);
		int afterDelete = dao.getAllLessons().size();
		assertTrue(afterDelete == afterSave - 1);
	}

}
