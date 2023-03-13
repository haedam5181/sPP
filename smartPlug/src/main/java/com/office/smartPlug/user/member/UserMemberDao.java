package com.office.smartPlug.user.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMemberDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	PasswordEncoder passwordEncoder;

	public UserMemberVo login_confirm(UserMemberVo userMemberVo) {

		List<UserMemberVo> userMemberVos = new ArrayList<UserMemberVo>();

		String sql = "SELECT * FROM t_member WHERE t_id = ?";

		try {

			userMemberVos = jdbcTemplate.query(sql, new RowMapper<UserMemberVo>() {

				@Override
				public UserMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {

					UserMemberVo userMemberVo = new UserMemberVo();
					userMemberVo.setT_no(rs.getInt("t_no"));
					userMemberVo.setT_id(rs.getString("t_id"));
					userMemberVo.setT_pw(rs.getString("t_pw"));

					return userMemberVo;
				}

			}, userMemberVo.getT_id());

			if (!passwordEncoder.matches(userMemberVo.getT_pw(), userMemberVos.get(0).getT_pw())) {
				userMemberVos.clear();
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return userMemberVos.size() > 0 ? userMemberVos.get(0) : null;

	}

	public boolean isMember(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] isMember() CALLED!!");

		String sql = "SELECT COUNT(*) FROM t_member WHERE t_id = ?";
		int result = 0;

		result = jdbcTemplate.queryForObject(sql, Integer.class, userMemberVo.getT_id());

		return result > 0 ? true : false;
	}

	public int create_account_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] create_account_confirm() CALLED!!");

		int result = UserMemberService.CREATE_ACCOUNT_FAIL;

		String sql = "INSERT INTO t_member(" + "t_id, " + "t_pw) " + "VALUES(?, ?)";

		result = jdbcTemplate.update(sql, userMemberVo.getT_id(), passwordEncoder.encode(userMemberVo.getT_pw()));

		result = UserMemberService.CREATE_ACCOUNT_SUCCESS;

		return result;
	}
/*
	public UserMemberVo my_page(String t_id) {
		System.out.println("[UserMemberDao] my_page() CALLED!!");

		String sql = "SELECT " + "m.m_no 			no, " + "m.m_id 			id, " + "m.m_pw 			pw, "
				+ "m.m_mail 		mail, " + "m.m_phone 		phone, " + "m.m_reg_date 	reg_date, "
				+ "m.m_mod_date 	mod_date, " + "ma.ma_able 		able, " + "ma.ma_comment 	comment "

				+ "FROM tbl_member m "

				+ "JOIN tbl_member_able ma " + "ON m.ma_able = ma.ma_able "

				+ "WHERE m.m_id = ?";

		List<UserMemberVo> userMemberVos = null;

		userMemberVos = jdbcTemplate.query(sql, new RowMapper<UserMemberVo>() {

			@Override
			public UserMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {

				UserMemberVo userMemberVo = new UserMemberVo();
				userMemberVo.setT_no(rs.getInt("no"));
				userMemberVo.setT_id(rs.getString("id"));									
				userMemberVo.setT_pw(rs.getString("pw"));

				return userMemberVo;

			}

		}, t_id);

		System.out.println("userMemberVos: " + userMemberVos);

		return userMemberVos != null ? userMemberVos.get(0) : null;

	}
*/
	public int my_page_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] my_page_confirm() CALLED!!");

		String sql = "UPDATE t_member SET WHERE t_no = ?";

		int result = 0;

		result = jdbcTemplate.update(sql,userMemberVo.getT_no());

		return result;
	}

	public int pw_modify_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] pw_modify_confirm() CALLED!!");

		String sql = "UPDATE t_member SET t_pw = ? WHERE t_id = ?";

		int result = 0;
		
		try {
			
			result = jdbcTemplate.update(sql, passwordEncoder.encode(userMemberVo.getT_pw()) ,userMemberVo.getT_id());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}

}
