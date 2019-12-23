package bdd;
import java.sql.*;
import java.util.ArrayList;

public class FuncDep {
	private Connexion conn  ;
	
	
	public FuncDep(Connexion conn) {
		this.conn = conn;	
	}
	
/*	public void createTable() {
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
	}*/
	
	public void addDf(String rel,String[]lhs,String rhs) {
		String sql = "INSERT INTO FuncDep (table_name, lhs , rhs) VALUES(?,?,?)";
		
	}
	
	
	public  boolean satisfaction(String table, String lhs,String rhs) {
		boolean sas = true;
		ArrayList<String> tab = new ArrayList<String>();
		tab = conn.getNameAttributes(table);
		String[] att = lhs.split(" ");
		String ls ="";
		for(int i =0; i<att.length; i++) {
			if(i!= att.length - 1)
			ls += att[i]+",";
		}
	
		Statement stmt1 = null;
		ArrayList<ArrayList<String>> tplg = selectTuple(ls,table);
		ArrayList<ArrayList<String>> tpld = selectTuple(rhs , table);
		System.out.println("Dépendance non ssatisfaite 2");
		for (int i=0 ; i<tplg.size(); i++) {
			System.out.println("Dépendance non ssatisfaite 3");
			ArrayList corr = new ArrayList();
			corr = tpld.get(i);
			for (int j=0; j<tplg.size(); j++) {
			
				if(!tplg.get(j).equals(corr.get(i))) {
					removeDf(table, lhs, rhs);
					System.out.println("Dépendance non ssatisfaite");
					sas = false;
					return sas;
				}
			}
			
		}
		System.out.println("Dépendance satisfaite");
		return sas;
	}
	
	public void removeDf(String table, String l,String r) {
		
		try {
			String str = "DELETE FROM FuncDep WHERE (table_name = "+table+ " and lhs =" +l+ "and rhs ="+r+")";
			Statement st= conn.getCon().createStatement();
			ResultSet rslt = st.executeQuery(str);
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
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
	
	public ArrayList<ArrayList<String>> selectTuple(String a, String table){
		ArrayList<ArrayList<String>> tuple = new ArrayList<>();
		String sql = "SELECT "+a+ " FROM " +table;
		Statement stmt;
		try {
			stmt = conn.getCon().createStatement();
			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData rs = result.getMetaData();
			int nbcol = rs.getColumnCount();
			 ArrayList<String> a1 = new ArrayList<>();
			while(result.next()) {
			
			 for (int i = 1; i<=nbcol; i++) {
				 a1.add(result.getObject(i).toString());
			 }
			 tuple.add(a1);
			 }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return tuple;
		}
		
}

