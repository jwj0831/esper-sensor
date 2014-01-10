package nfm.subway.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionMaker implements ConnectionMaker {
	private static String DB_PATH = "jdbc:mysql://117.16.146.121:3306/aircleaner";
	//private static String DB_PATH = "jdbc:mysql://127.0.0.1:3306/aircleaner";
	private static String DB_USER = "esper";
	private static String DB_PW = "esper";
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c  = DriverManager.getConnection(DB_PATH, DB_USER, DB_PW);
		return c;
	}
}
