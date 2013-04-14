package com.magiccube.core.util.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Xingling Chen
 * @since JDK1.6
 * @history 2011-05-11 Xingling build
 */
public abstract class JDBCUtil {

	private final static Log LOGGER = LogFactory.getLog(JDBCUtil.class);

	final static String driver = "com.mysql.jdbc.Driver";

	final static String connURL = "jdbc:mysql://127.0.0.1:3306/1000funs";

	final static String userName = "root";

	final static String passWord = "root";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOGGER.error("where is the jar of mysql's driver ?" + e);
		}
		try {
			conn = DriverManager.getConnection(connURL, userName, passWord);
		} catch (SQLException e) {
			LOGGER.error("is the connection infomation right ?(url=" + connURL
					+ ",username=" + userName + ",password=" + passWord + ")"
					+ e);
		}
		return conn;
	}

	public static void release(Object... objects) {
		for (Object object : objects) {
			try {
				if (object instanceof Connection) {
					((Connection) object).close();
				} else if (object instanceof PreparedStatement) {
					((PreparedStatement) object).close();
				} else if (object instanceof Statement) {
					((Statement) object).close();
				} else if (object instanceof ResultSet) {
					((ResultSet) object).close();
				}
			} catch (Exception e) {
				LOGGER.error("error in release connection resource:" + e);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("connection:" + getConnection());
	}

}
