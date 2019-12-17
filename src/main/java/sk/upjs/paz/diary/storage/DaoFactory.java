package sk.upjs.paz.diary.storage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DaoFactory {
	private static ISubjectDAO subjectDao;
	private static IExamDAO examDao;
	private static IHomeworkDAO homeworkDao;
	private static ILessonDAO lessonDao;

	private static JdbcTemplate jdbcTemplate;

	private DaoFactory() {
		throw new IllegalAccessError("Non instantiating class");
	}

	public static ISubjectDAO getSubjectDao() {
		return getSubjectDao(false);
	}

	public static ISubjectDAO getSubjectDao(boolean isTestingDao) {
		if (subjectDao == null) {
			subjectDao = new SubjectDao(initializeJdbcTemplate(isTestingDao));
		}
		return subjectDao;
	}

	public static IExamDAO getExamDao() {
		return getExamDao(false);
	}

	public static IExamDAO getExamDao(boolean isTestingDao) {
		if (examDao == null) {
			examDao = new ExamDao(initializeJdbcTemplate(isTestingDao));
		}
		return examDao;
	}

	public static ILessonDAO getLessonDao() {
		return getLessonDao(false);
	}

	public static ILessonDAO getLessonDao(boolean isTestingDao) {
		if (lessonDao == null) {
			lessonDao = new LessonDao(initializeJdbcTemplate(isTestingDao));
		}
		return lessonDao;
	}

	public static IHomeworkDAO getHomeworkDao() {
		return getHomeworkDao(false);
	}

	public static IHomeworkDAO getHomeworkDao(boolean isTestingDao) {
		if (homeworkDao == null) {
			homeworkDao = new HomeworkDao(initializeJdbcTemplate(isTestingDao));
		}
		return homeworkDao;
	}

	private static JdbcTemplate initializeJdbcTemplate(boolean isTestTemplate) {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("super-student");
			dataSource.setPassword("super-password");
			if (isTestTemplate) {
				dataSource.setDatabaseName("test_diary");
				dataSource.setURL("jdbc:mysql://localhost/test_diary?serverTimezone=Europe/Bratislava");
			} else {
				dataSource.setDatabaseName("diary");
				dataSource.setURL("jdbc:mysql://localhost/diary?serverTimezone=Europe/Bratislava");
			}
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
}