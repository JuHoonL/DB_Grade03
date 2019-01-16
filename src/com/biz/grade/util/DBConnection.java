package com.biz.grade.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection dbConn;
	
	public static Connection getInstance() {
		return dbConn;
	}

	public static void setDbConn(Connection dbConn) {
		DBConnection.dbConn = dbConn;
	}

	/*
	 * Java 1.7이상에서만 사용되는 Keyword로 static 생성자라고 부른다.
	 * 
	 * 원래 생성자라는 메서드는 외부에서 new라는 키워드를 사용해서 메서드를 호출하는 형식으로 사용된다.
	 * 
	 * static 생성자는 프로젝트가 시작될 때 자동으로 실행이 되는 메서드이다.
	 */
	static {
		//dbConn 멤버변수를  초기화하는 메서드
		//dbConn 멤버변수 : db에 접속하기 위한 통로를 마련하고
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String strurl = "jdbc:oracle:thin:@localhost:1521:xe";
			String struser = "gradeUSER";
			String strpassword = "1234";
			
			dbConn = DriverManager.getConnection(strurl, struser, strpassword);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
