package bdd;
import java.sql.*;
import java.util.ArrayList;

public class FuncDef {
	private Connexion conn  ;
	
	
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
				ArrayList<ArrayList<String>> tbl = new ArrayList();
				FuncDef func = new FuncDef(conn);
				tbl = func.ReadFuncdep();
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void addDf(String rel,String[]lhs,String rhs) {
		String sql = "INSERT INTO FuncDep (table_name, lhs , rhs) VALUES(?,?,?)";
		
	}
	
	
	public  boolean satisfaction(String table) {
		
		
		
			
		
		return true;
	}
	
	public ArrayList<ArrayList<String>> ReadFuncdep(){
		ArrayList<ArrayList<String>> arr = new ArrayList();
		try {
			
			String str = "SELECT * FROM FuncDep";
			Statement stmt = conn.getCon().createStatement();
			ResultSet result = stmt.executeQuery(str);
			ResultSetMetaData rsd = result.getMetaData();
			int nbcol = rsd.getColumnCount();
			while(result.next()) {
				ArrayList<String> re = new ArrayList();
				for(int i = 1;i <= nbcol;i++) {
					String res = result.getObject(i).toString();
					re.add(res);
				}
				arr.add(re);
			}
		} catch (SQLException e) {
			
			//System.out.println(e.getMessage());
			//e.printStackTrace();
			String sql = " CREATE TABLE IF NOT EXISTS FuncDep (\n"
					+ "        table_name  text NOT NULL,\n"
					+ "         lhs        text NOT NULL,\n"
					+ "         rhs        text NOT NULL)";
			
			try {
				Statement stmt = conn.getCon().createStatement();
					stmt.execute(sql);
					
					
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			
		}
		
		return arr;
		
	}
	 
	
	
}

