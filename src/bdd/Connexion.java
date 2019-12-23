package bdd;
import java.sql.*;
import java.util.ArrayList;

public class Connexion {
	private Connection con;
	private DatabaseMetaData dbmd;
	
	public void connect(String url) {
		try {
			Class.forName("org.sqlite.JDBC");
			con =DriverManager.getConnection("jdbc:sqlite:"+ url);
			dbmd = con.getMetaData();
			//System.out.println("Connection to sqlite has been established \n");

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
	// methode qui permet de recuperer les noms des tables
	public ArrayList<String> getNameTable(){
		
		ArrayList<String> tableName = new ArrayList<String>();
		try {
		ResultSet result = dbmd.getTables(null, null, null, new String[] {"table"});
		while (result.next()) {
			tableName.add(result.getString("TABLE_NAME"));
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			}catch(SQLException ex) {
				ex.printStackTrace();
				System.err.println(ex.getMessage());
				}
		return tableName;
		}
	
	//methode qui permet d'avoir les attributs d'une table en ayant sa position dans la liste des tables
	
	public ArrayList<String> getNameAttributes(String a){
		ArrayList<String> attribut = new ArrayList<String>();
		String sql = "SELECT * FROM " +a ;
		try {
			Statement st = con.createStatement();
			if(getNameTable().contains(a)) {
				ResultSet result = st.executeQuery(sql);
				ResultSetMetaData rsmd = result.getMetaData();
				int  column = rsmd.getColumnCount();
				for(int i = 1 ; i<= column; i++) {
					String value = rsmd.getColumnName(i);
					attribut.add(value);
				}
				
			}else {
				throw(new IllegalArgumentException());
			}
			
			
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			}catch(SQLException ex) {
				ex.printStackTrace();
				System.err.println(ex.getMessage());
				}
		return attribut;
	}
	
}
 
