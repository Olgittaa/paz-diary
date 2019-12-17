package sk.upjs.paz.diary.persistence;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DaoFactory {
	INSTANCE;

	private ISubjectDAO subjectDao;
	private IExamDAO examDao;
	private IHomeworkDAO homeworkDao;
	private ILessonDAO lessonDao;

	private JdbcTemplate jdbcTemplate;

	public ISubjectDAO getSubjectDao() {
		return getSubjectDao(false);
	}

	public ISubjectDAO getSubjectDao(boolean isTestingDao) {
		if (subjectDao == null) {
			subjectDao = new SubjectDao(initializeJdbcTemplate(isTestingDao));
		}
		return subjectDao;
	}

	public IExamDAO getExamDao() {
		return getExamDao(false);
	}

	public IExamDAO getExamDao(boolean isTestingDao) {
		if (examDao == null) {
			examDao = new ExamDao(initializeJdbcTemplate(isTestingDao));
		}
		return examDao;
	}

	public ILessonDAO getLessonDao() {
		return getLessonDao(false);
	}

	public ILessonDAO getLessonDao(boolean isTestingDao) {
		if (lessonDao == null) {
			lessonDao = new LessonDao(initializeJdbcTemplate(isTestingDao));
		}
		return lessonDao;
	}

	public IHomeworkDAO getHomeworkDao() {
		return getHomeworkDao(false);
	}

	public IHomeworkDAO getHomeworkDao(boolean isTestingDao) {
		if (homeworkDao == null) {
			homeworkDao = new HomeworkDao(initializeJdbcTemplate(isTestingDao));
		}
		return homeworkDao;
	}

	private JdbcTemplate initializeJdbcTemplate(boolean isTestTemplate) {
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