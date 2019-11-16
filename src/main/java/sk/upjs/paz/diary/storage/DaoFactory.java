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

	/**
	 * Creates new SubjectDao if it doesn't exist or returns already existing object
	 * 
	 * @return a singleton of a SubjectDao object
	 */
	public static ISubjectDAO getSubjectDao() {
		if (subjectDao == null) {
			subjectDao = new SubjectDao(initializeJdbcTemplate());
		}
		return subjectDao;
	}

	/**
	 * Creates new ExamDao if it doesn't exist or returns already existing object
	 * 
	 * @return a singleton of a ExamDao object
	 */
	public static IExamDAO getExamDao() {
		if (examDao == null) {
			examDao = new ExamDao(initializeJdbcTemplate());
		}
		return examDao;
	}

	/**
	 * Creates new LessonDao if it doesn't exist or returns already existing object
	 * 
	 * @return a singleton of a LessonDao object
	 */
	public static ILessonDAO getLessonDao() {
		if (lessonDao == null) {
			lessonDao = new LessonDao(initializeJdbcTemplate());
		}
		return lessonDao;
	}

	/**
	 * Creates new HomeworkDao if it doesn't exist or returns already existing
	 * object
	 * 
	 * @return a singleton of a HomeworkDao object
	 */
	public static IHomeworkDAO getHomeworkDao() {
		if (homeworkDao == null) {
			homeworkDao = new HomeworkDao(initializeJdbcTemplate());
		}
		return homeworkDao;
	}

	/**
	 * Creates new JdbcTemplate object if it doesn't exist or returns already
	 * existing
	 * 
	 * @return jdbcTemplate
	 */
	private static JdbcTemplate initializeJdbcTemplate() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setDatabaseName("diary");
			dataSource.setUser("super-student");
			dataSource.setPassword("super-password");
			dataSource.setURL("jdbc:mysql://localhost/diary?serverTimezone=Europe/Bratislava");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}

}
