package com.project2.main;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 메인 - DB연동 객체 선언 클래스
 * @author sist47
 *
 */
public class DBUtil {
	
	public static final String SERVERIP;
	public static final String SERVERID;
	public static final String SERVERPW;

	 static {
	      SERVERIP = "211.63.89.49";
	      SERVERID = "qtest13";
	      SERVERPW = "java1234";
	  }



	public Connection connect() {

		Connection conn = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String password = "java1234";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, id, password);

			return conn;

		} catch (Exception e) {
			System.out.println("Connection : " + e.toString());
		}

		return null;

	}

	public Connection connect(String server, String id, String password) {

		
		Connection conn = null;

		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, id, password);

			return conn;

		} catch (Exception e) {
			System.out.println("Connection : " + e.toString());
		}

		return null;

	}

}
