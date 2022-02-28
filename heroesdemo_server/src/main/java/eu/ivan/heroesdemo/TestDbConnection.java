package eu.ivan.heroesdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestDbConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Test connection for Postgres");
		Class.forName("org.postgresql.Driver");
		
		String url = "jdbc:postgresql://localhost/heroes";
		Properties props = new Properties();
		props.setProperty("user","hero");
		props.setProperty("password","hero");
		props.setProperty("ssl","false");
		Connection conn = DriverManager.getConnection(url, props);
		
		PreparedStatement query = conn.prepareStatement("select * from heroes");
		ResultSet result = query.executeQuery();
		while (result.next()) {
			System.out.println(String.format("%d %s", result.getInt("id"), result.getString("name")));
		}
		
		

	}

}
