package com.office.smartPlug;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DatabaseConnect {
	
	@Test
	public void connectDB() {
		
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String URL = "jdbc:mysql://192.168.0.105:3306/iot_db?serverTimezone=Asia/Seoul";
		final String USER_ID = "user0003";
		final String USER_PW = "user0003";
		
		try {
			
			// 드라이버 로드 in 메모리
			Class.forName(JDBC_DRIVER);
			
			// DB 연결
			Connection connection = DriverManager.getConnection(URL, USER_ID, USER_PW);
			System.out.println("connection: " + connection);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
