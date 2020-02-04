package sk.upjs.paz.diary.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.upjs.paz.diary.entity.Exam;
import sk.upjs.paz.diary.entity.Subject;
import sk.upjs.paz.diary.persistence.DaoFactory;
import sk.upjs.paz.diary.persistence.IExamDAO;
import sk.upjs.paz.diary.persistence.ISubjectDAO;

class ExamDaoTest {

	private IExamDAO dao = DaoFactory.INSTANCE.getExamDao(true);
	private ISubjectDAO subjectDao = DaoFactory.INSTANCE.getSubjectDao(true);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllExams() {
		assertNotNull(dao.getAllExams());
		assertFalse(dao.getAllExams().isEmpty());
	}
	
	@Test
	void testSave() {
		Subject subject = new Subject();
		subject.setName("example");
		Long subjectId = subjectDao.save(subject).getId();
		subject.setId(subjectId);
		Exam exam = new Exam();
		exam.setSubject(subject);
		exam.setDateTime(LocalDateTime.of(2019, 12, 15, 14, 20));
		int beforeSave = dao.getAllExams().size();
		Long id = dao.save(exam).getId();
		exam.setId(id);
		int afterSave = dao.getAllExams().size();
		assertTrue(afterSave == beforeSave + 1);
	}
	

	@Test
	void testRemove() {
		Subject subject = new Subject();
		subject.setName("example");
		Long subjectId = subjectDao.save(subject).getId();
		subject.setId(subjectId);
		Exam exam = new Exam();
		exam.setSubject(subject);
		exam.setDateTime(LocalDateTime.of(2019, 12, 15, 14, 20));
		exam.setLocation("location");
		Long id = dao.save(exam).getId();
		exam.setId(id);
		int afterSave = dao.getAllExams().size();
		
		
		dao.remove(exam);
		int afterDelete = dao.getAllExams().size();
		System.out.println(afterSave);
		System.out.println(afterDelete);
		assertTrue(afterDelete == afterSave - 1);
	}
}
