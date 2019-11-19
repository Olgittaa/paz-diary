package sk.upjs.paz.diary.storage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * The Factory class is responsible for creating data access objects if do not
 * exists or for returning already existing object
 * 
 * @author Yevhenii Kozhevin
 * @author Olga Charna
 */
public class DaoFactory {
	/**
	 * Singleton of a SubjectDao
	 */
	private static ISubjectDAO subjectDao;
	/**
	 * Singleton of a ExamDao
	 */
	private static IExamDAO examDao;
	/**
	 * Singleton of a HomeworkDao
	 */
	private static IHomeworkDAO homeworkDao;
	/**
	 * Singleton of a LessonDao
	 */
	private static ILessonDAO lessonDao;

	private static JdbcTemplate jdbcTemplate;

	private DaoFactory() {
		throw new IllegalAccessError("Non instantiating class");
	}

	public static ISubjectDAO getSubjectDao() {
		return getSubjectDao(false);
	}

	/**
	 * Creates new SubjectDao if it doesn't exist or returns already existing object
	 * 
	 * @param isTestingDao -
	 * @return a singleton of a SubjectDao object
	 */
	public static ISubjectDAO getSubjectDao(boolean isTestingDao) {
		if (subjectDao == null) {
			subjectDao = new SubjectDao(initializeJdbcTemplate(isTestingDao));
		}
		return subjectDao;
	}

	public static IExamDAO getExamDao() {
		return getExamDao(false);
	}

	/**
	 * Creates new ExamDao if it doesn't exist or returns already existing object
	 * 
	 * @param isTestingDao -
	 * @return a singleton of a ExamDao object
	 */
	public static IExamDAO getExamDao(boolean isTestingDao) {
		if (examDao == null) {
			examDao = new ExamDao(initializeJdbcTemplate(isTestingDao));
		}
		return examDao;
	}

	public static ILessonDAO getLessonDao() {
		return getLessonDao(false);
	}

	/**
	 * Creates new LessonDao if it doesn't exist or returns already existing object
	 * 
	 * @param isTestingDao -
	 * @return a singleton of a LessonDao object
	 */
	public static ILessonDAO getLessonDao(boolean isTestingDao) {
		if (lessonDao == null) {
			lessonDao = new LessonDao(initializeJdbcTemplate(isTestingDao));
		}
		return lessonDao;
	}

	public static IHomeworkDAO getHomeworkDao() {
		return getHomeworkDao(false);
	}

	/**
	 * Creates new HomeworkDao if it doesn't exist or returns already existing
	 * object
	 * 
	 * @param isTestingDao - 
	 * @return a singleton of a HomeworkDao object
	 */
	public static IHomeworkDAO getHomeworkDao(boolean isTestingDao) {
		if (homeworkDao == null) {
			homeworkDao = new HomeworkDao(initializeJdbcTemplate(isTestingDao));
		}
		return homeworkDao;
	}

	/**
	 * Creates new JdbcTemplate object if it doesn't exist or returns already
	 * existing
	 * 
	 * @param isTestTemplate -
	 * @return jdbcTemplate
	 */
	private static JdbcTemplate initializeJdbcTemplate(boolean isTestTemplate) {
		if (jdbcTemplate == null) {
			String databaseName;
			if (isTestTemplate)
				databaseName = "test_diary";
			else
				databaseName = "diary";

			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setDatabaseName(databaseName);
			dataSource.setUser("super-student");
			dataSource.setPassword("super-password");
			dataSource.setURL("jdbc:mysql://localhost/diary?serverTimezone=Europe/Bratislava");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}

}
