package bdd;
import java.sql.*;
import java.util.ArrayList;

public class FuncDef {
	Connexion conn  ;
	public FuncDef(Connexion conn) {
		this.conn = conn;
	}
	
	public void createTable() {
		String sql = " CREATE TABLE IF NOT EXISTS FuncDep (\n"
				+ "        table_name  text NOT NULL,\n"
				+ "         lhs        text NOT NULL,\n"
				+ "         rhs        text NOT NULL)";
		
		try {
			Statement stmt = conn.getCon().createStatement();
				stmt.execute(sql);
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void addDf(String rel,String[]lhs,String rhs) {
		String sql = "INSERT INTO FuncDep (table_name, lhs , rhs) VALUES(?,?,?)";
		
	}
	
	
	public boolean satisfaction(String lhs , String rhs) {
		{
			
		}
		return true;
	}
	
	public ArrayList<ArrayList<String>> ReadFuncdep(){
		ArrayList<ArrayList<String>> arr = new ArrayList();
		try {
			createTable();
			String str = "SELECT * FROM FuncDef";
			Statement stmt = conn.getCon().createStatement();
			ResultSet result = stmt.executeQuery(str);
			ResultSetMetaData rsd = result.getMetaData();
			int nbcol = rsd.getColumnCount();
			while(result.next()) {
				ArrayList<String> re = new ArrayList();
				for(int i = 1;i <= nbcol;i++) {
					String res = result.getString(i);
					re.add(res);
				}
				arr.add(re);
			}
		} catch (SQLException e) {
			System.out.println("THE TABLE DOESN'T EXIST IN THE DATABASE");
			//e.printStackTrace();
		}
		
		return arr;
		
	}
	
	
	
}

