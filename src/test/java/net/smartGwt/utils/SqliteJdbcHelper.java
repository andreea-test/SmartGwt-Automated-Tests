package net.smartGwt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteJdbcHelper {

	private static final String TABLE_NAME = "timing";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_TIME_RUN = "time_run";

	private static Connection conn = null;
	private static int rowsAffected = 0;

	public static void connect() {
		connect(null);
	}

	public static void connect(String dbName) {
		if (dbName == null || dbName.isEmpty()) {
			dbName = "test.db";
		} else {
			dbName = dbName.replaceAll("\\s+", "");
		}

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
		} catch (Exception e) {
			printException(e);
		}
		System.out.println("Opened database successfully");
	}

	private static void tryCommit() {
		try {
			if (!conn.getAutoCommit()) {
				conn.commit();
			}
		} catch (Exception e) {
			printException(e);
		}
		System.out.println("Commited! Rows affected: " + rowsAffected);
		rowsAffected = 0;
	}

	public static void disconnect() {
		try {
			tryCommit();
			conn.close();
		} catch (Exception e) {
			printException(e);
		}
		System.out.println("Closed database.");
	}

	public static void createTable() {
		try {
			Statement statement = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TIME_RUN + " REAL);";
			statement.executeUpdate(sql);
			statement.close();
		} catch (Exception e) {
			printException(e);
		}
		System.out.println("Table created successfully");
	}

	public static void insert(long executionTime) {
		try {
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_ID + ", " + COLUMN_TIME_RUN + ")"
					+ "VALUES (NULL, " + executionTime + ");";
			rowsAffected += statement.executeUpdate(sql);
			statement.close();
		} catch (Exception e) {
			printException(e);
		}
		System.out.println("'" + executionTime + "' inserted!");
	}

	public static Times getTimes() {
		try {
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC LIMIT 2;";
			ResultSet resultSet = statement.executeQuery(sql);

			Times times = new Times();
			if (resultSet.next())
				times.Last = resultSet.getLong(COLUMN_TIME_RUN);

			statement.close();

			return times;

		} catch (Exception e) {
			printException(e);
		}
		return null;
	}

	private static void printException(Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}
}
