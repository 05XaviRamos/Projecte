package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {
	static Connection connection;
	public static Boolean insertSql(int type_id, int brand_id, String country_code, double alcohol_content, String description, double volume, double price, String name) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(5);
		return statement.execute("INSERT INTO drinks ('name', 'type_id', 'brand_id', 'country_code', 'alcohol_content', 'description', 'volume', 'price') VALUES ('" + name + "'," + type_id + "," + brand_id + ", '" + country_code + "', " + alcohol_content + ", '" + description + "', " + volume + ", " + price + ")");
	}

	public static Boolean insertSql(String name, String table) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(5);
		return statement.execute("INSERT INTO '" + table + "'('name') VALUES ('" + name + "')");
	}
	public static Boolean insertSql(String name, String country_code, String table) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(5);
		return statement.execute("INSERT INTO '" + table + "' ('name', 'country_code') VALUES ('" + name + "', '" + country_code + "')");
	}

	public static Boolean updateSql(int id,  String table, String name) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(5);
		return statement.execute("UPDATE '" + table + "' SET name = '" + name + "' WHERE drink_id = " + id);        
	}

	public static Boolean updateSql(int id,  String table, String name, String country_code) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(5);
		return statement.execute("UPDATE '" + table + "' SET name = '" + name + "', country_code = '" + country_code + "' WHERE drink_id = " + id);        
	}

	public static Boolean updateSql(int type_id, int brand_id, String country_code, double alcohol_content, String description, double volume, double price, String name, int id) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(5);
		return statement.execute("UPDATE drinks SET name = '" + name + "', type_id = " + type_id + ", brand_id = " + brand_id + ", country_code = '" + country_code + "', alcohol_content = " + alcohol_content + ", description = '" + description + "', volume = " + volume + ", price = " + price + " WHERE drink_id = " + id ); 
	}

	public static Boolean deleteSql(int id, String table) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(5);
		return statement.execute("DELETE FROM '" + table + "' WHERE drink_id='" + id + "'");

	}

	public static ResultSet selectSql(String table) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(5);
		return statement.executeQuery("SELECT * FROM '" + table + "'");

	}

	public static void connect(Connection conection) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:alcoholic_drinks.db");
		} catch (SQLException e) {
			System.out.println("No s'ha pogut establir la connexió amb la base de dades.");
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
}
