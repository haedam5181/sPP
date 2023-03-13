package com.office.smartPlug.user.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ControlDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int sendData(int data) {
		System.out.println("[ControlDao]sendData");

		String sql = "INSERT INTO t_device(t_id, d_status, d_reg_date, d_mod_date) VALUES(?, ?, NOW(), NOW())";

		int result = jdbcTemplate.update(sql, data);
		
		System.out.println("[ControlDao]sendData---------------->" + result);

		return result;
	}

}
