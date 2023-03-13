package com.office.smartPlug.user.smartPlug;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.office.smartPlug.user.smartPlug.page.Criteria;

@Component
public class UserSmartPlugDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
/*	
	public int smartPlug_write_confirm(UserSmartPlugVo userSmartPlugVo) {
		System.out.println("[UserSmartPlugDao] smartPlug_write_confirm() CALLED!!");
		
		String sql = "INSERT INTO tbl_smartPlug("
						+ "t_id, "
						+ "d_txt, "
						+ "d_img_name,"
						+ "d_write_full_year, "
						+ "d_write_month, "
						+ "d_write_date, "
						+ "d_write_day, "
						+ "d_write_hours, "
						+ "d_write_minutes, "
						+ "d_write_seconds, "
						+ "d_reg_date, "
						+ "d_mod_date) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		int result = 0;
		try {
			
			result = jdbcTemplate.update(sql, 
										 userSmartPlugVo.getT_id(),
										 userSmartPlugVo.getD_write_month(),
										 userSmartPlugVo.getD_write_date(),
										 userSmartPlugVo.getD_write_day(),
										 userSmartPlugVo.getD_write_hours(),
										 userSmartPlugVo.getD_write_minutes(),
										 userSmartPlugVo.getD_write_seconds());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}
*/
	public List<UserSmartPlugVo> getSmartPlugItems(Map<String, String> msgMap, Criteria criteria) {
		System.out.println("[UserSmartPlugDao] getSmartPlugItems() CALLED!!");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "SELECT * FROM t_device ";
			   sql += "WHERE 1 = 1 ";
			
			   if (msgMap.get("t_id") != null || msgMap.get("t_id").equals("") || msgMap.get("t_id").equals(" ")) {
				   sql += "AND t_id = ? ";
				   args.add(msgMap.get("t_id"));
			   }
				
			   if (Integer.parseInt(msgMap.get("full_year")) > 0) {
				   sql += "AND d_write_full_year = ? ";
				   args.add(msgMap.get("full_year"));
			   }
				   
			   if (Integer.parseInt(msgMap.get("month")) > 0) {
				   sql += "AND d_write_month = ? ";
				   args.add(msgMap.get("month"));
			   }
				   
			   if (Integer.parseInt(msgMap.get("date")) > 0) {
				   sql += "AND d_write_date = ? ";
				   args.add(msgMap.get("date"));
			   }
				   
			   sql += "ORDER BY d_no ASC ";
			   sql += "LIMIT " + criteria.getSkip() + ", " + criteria.getAmount();
		
		List<UserSmartPlugVo> userSmartPlugVos = null;
		
		System.out.println(sql);
		
		try {
			
			userSmartPlugVos = jdbcTemplate.query(sql, new RowMapper<UserSmartPlugVo>() {

				@Override
				public UserSmartPlugVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserSmartPlugVo userSmartPlugVo = new UserSmartPlugVo();
					userSmartPlugVo.setD_no(rs.getInt("d_no"));
					userSmartPlugVo.setT_id(rs.getString("t_id"));
					userSmartPlugVo.setD_status(rs.getInt("d_status"));
					userSmartPlugVo.setD_reg_date(rs.getString("d_reg_date"));
					userSmartPlugVo.setD_mod_date(rs.getString("d_mod_date"));
					
					return userSmartPlugVo;
				}
				
			}, args.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userSmartPlugVos;
	}
/*
	public int deleteSmartPlugItem(Map<String, String> msgMap) {
		System.out.println("[UserSmartPlugDao] deleteSmartPlugItem() CALLED!!");
		
		String sql = "DELETE FROM tbl_smartPlug WHERE d_no = ?";
		
		int result = 0;
		
		try {
			
			result = jdbcTemplate.update(sql, msgMap.get("d_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public UserSmartPlugVo smartPlug_modify(UserSmartPlugVo userSmartPlugVo) {
		System.out.println("[UserSmartPlugDao] smartPlug_modify() CALLED!!");
		
		String sql = "SELECT * FROM tbl_smartPlug WHERE d_no = ?";
		
		List<UserSmartPlugVo> userSmartPlugVos = null;
		
		try {
			
			userSmartPlugVos = jdbcTemplate.query(sql, new RowMapper<UserSmartPlugVo>() {

				@Override
				public UserSmartPlugVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserSmartPlugVo userSmartPlugVo = new UserSmartPlugVo();
					userSmartPlugVo.setD_no(rs.getInt("d_no"));
					userSmartPlugVo.setT_id(rs.getString("t_id"));
					userSmartPlugVo.setD_status(rs.getInt("d_status"));
					userSmartPlugVo.setD_reg_date(rs.getString("d_reg_date"));
					userSmartPlugVo.setD_mod_date(rs.getString("d_mod_date"));
					
					return userSmartPlugVo;
					
				}
				
			}, userSmartPlugVo.getD_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userSmartPlugVos != null ? userSmartPlugVos.get(0) : null;
		
	}
*/
	/*
	public int smartPlug_modify_confirm(UserSmartPlugVo userSmartPlugVo) {
		System.out.println("[UserSmartPlugDao] smartPlug_modify_confirm() CALLED!!");
		
		String  sql = "UPDATE tbl_smartPlug SET d_txt = ?, ";
		
		if (userSmartPlugVo.getD_img_name() != null)
				sql += "d_img_name = ?, ";
		
				sql += "d_mod_date = NOW() WHERE d_no = ?";
				
		int result = 0;
		
		try {
			
			if (userSmartPlugVo.getD_img_name() != null)
				result = jdbcTemplate.update(sql, 
											 userSmartPlugVo.getD_txt(), 
											 userSmartPlugVo.getD_img_name(), 
											 userSmartPlugVo.getD_no());
			else
				result = jdbcTemplate.update(sql, 
											 userSmartPlugVo.getD_txt(), 
											 userSmartPlugVo.getD_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
				
		return result;
	}
*/
	public UserSmartPlugVo getSmartPlugItem(Map<String, String> msgMap) {
		System.out.println("[UserSmartPlugDao] getSmartPlugItem() CALLED!!");
		
		String sql = "SELECT * FROM t_device WHERE d_no = ?";
		
		List<UserSmartPlugVo> userSmartPlugVos = null;
		
		try {
			
			userSmartPlugVos = jdbcTemplate.query(sql, new RowMapper<UserSmartPlugVo>() {

				@Override
				public UserSmartPlugVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserSmartPlugVo userSmartPlugVo = new UserSmartPlugVo();
					userSmartPlugVo.setD_no(rs.getInt("d_no"));
					userSmartPlugVo.setT_id(rs.getString("t_id"));
					userSmartPlugVo.setD_status(rs.getInt("d_status"));
					userSmartPlugVo.setD_reg_date(rs.getString("d_reg_date"));
					userSmartPlugVo.setD_mod_date(rs.getString("d_mod_date"));
					
					return userSmartPlugVo;
				}
				
			}, msgMap.get("d_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userSmartPlugVos != null ? userSmartPlugVos.get(0) : null;
		
	}

	public int getTotalCnt(Map<String, String> msgMap) {
		System.out.println("[UserSmartPlugDao] getTotalCnt() CALLED!!");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "SELECT COUNT(*) FROM tbl_smartPlug ";
				sql += "WHERE 1 = 1 ";
				
		   	   if (msgMap.get("t_id") != null || msgMap.get("t_id").equals("") || msgMap.get("t_id").equals(" ")) {
					sql += "AND t_id = ? ";
					args.add(msgMap.get("t_id"));
			   }
				
			   if (Integer.parseInt(msgMap.get("full_year")) > 0) {
				   sql += "AND d_write_full_year = ? ";
				   args.add(msgMap.get("full_year"));
			   }
				   
			   if (Integer.parseInt(msgMap.get("month")) > 0) {
				   sql += "AND d_write_month = ? ";
				   args.add(msgMap.get("month"));
			   }
				   
			   if (Integer.parseInt(msgMap.get("date")) > 0) {
				   sql += "AND d_write_date = ? ";
				   args.add(msgMap.get("date"));
			   }
		
		int totalCnt = -1;
		
		try {
			
			totalCnt = jdbcTemplate.queryForObject(sql, 
												  Integer.class, 
												  args.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return totalCnt;
	}

}
