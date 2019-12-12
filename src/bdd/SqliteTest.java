package bdd;
import java.sql.*;

public class SqliteTest {
	public static void connect() {
		Connection con =null;
		try {
			String url = "jdbc:sqlite::memory:C:\\Program Files\\Sqlite\\Chinook_Sqlite.sqlite";
			con =DriverManager.getConnection(url);
			System.out.println("Connection to sqlite has been established");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
	}
	public static void main (String[] args) {
		 connect();
	 }
}
 
