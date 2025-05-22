package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {
	static Connection connection;
	public static Boolean insertSql(Integer type_id, Integer brand_id, String name) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
        statement.setQueryTimeout(5);
        return statement.execute("INSERT INTO drinks ('name', 'type_id', 'brand_id') VALUES ('" + name + "'," + type_id + "," + brand_id + ")");
	}
	
	public static Boolean updateSql(Integer drink_id, String newValue, String column) throws SQLException {
		connect(connection);
		Statement statement = connection.createStatement();
        statement.setQueryTimeout(5);
        if (column.equals("volume") || column.equals("alcohol_content") || column.equals("price")) {
        	return statement.execute("UPDATE drinks SET '" + column + "'='" + Double.parseDouble(newValue)  + "' WHERE drink_id = " + drink_id);
        } else {
        	return statement.execute("UPDATE drinks SET '" + column + "'='" + newValue + "' WHERE drink_id = " + drink_id);
        }
        
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
