package co.com.bd;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDatos {
	private static Connection conn;
	
	public static Connection getConnection() throws SQLException{
		if (conn != null && conn.isValid(15)) {
			return conn;
			
		} else {
			
			Properties props = new Properties();
			
			
			InputStream is = BaseDatos.class.getClassLoader().getResourceAsStream("app.properties");
			
			
			try {
				
				props.load(is);

				
				is.close();

				
				conn = DriverManager.getConnection(props.getProperty("url"), props);
				
			
			} catch (Exception e) {
				// TODO 
				e.printStackTrace();

			
			}
			
			return conn;
			
		}

	}

}
