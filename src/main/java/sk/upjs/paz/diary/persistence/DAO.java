package sk.upjs.paz.diary.persistence;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class DAO {
	private JdbcTemplate jdbcTemplate;

	public DAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}