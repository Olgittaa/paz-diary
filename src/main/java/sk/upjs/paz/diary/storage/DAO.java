package sk.upjs.paz.diary.storage;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class DAO {
	protected JdbcTemplate jdbcTemplate;

	public DAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}