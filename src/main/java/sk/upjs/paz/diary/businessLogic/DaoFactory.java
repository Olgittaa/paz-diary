package sk.upjs.paz.diary.businessLogic;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DaoFactory {
	private static IMySqlDAO mySqlDAO;
	// private JdbcTemplate jdbcTemplate;

	private DaoFactory() {
		throw new IllegalAccessError("Non instantiating class");
	}

	public static IMySqlDAO getMySqlDao() {
		if (mySqlDAO == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setDatabaseName("diary");
			dataSource.setUser("super-student");
			dataSource.setPassword("super-password");
			dataSource.setURL("jdbc:mysql://localhost/diary?serverTimezone=Europe/Bratislava");

			mySqlDAO = new MySqlDataAccessObject(new JdbcTemplate(dataSource));
		}
		return mySqlDAO;
	}

}
