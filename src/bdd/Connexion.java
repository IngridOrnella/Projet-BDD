package bdd;
import java.sql.*;
import java.util.ArrayList;

public class Connexion {
	Connection con;
	public void connect(String url) {
		try {
			Class.forName("org.sqlite.JDBC");
			con =DriverManager.getConnection("jdbc:sqlite:"+ url);
			System.out.println("Connection to sqlite has been established");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con == null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
	}
	public Connection getCon() {
		return con;
	}
	public static void main(String [] args) {
		Connexion connectt = new Connexion();
		connectt.connect("C:\\Users\\Ingrid Fondja\\Desktop\\test.db");
		FuncDef func = new FuncDef(connectt);
		ArrayList<ArrayList<String>> arr = func.ReadFuncdep();
		for(ArrayList<String> i: arr){
			System.out.println(i);
		}
		
	}
}
 
