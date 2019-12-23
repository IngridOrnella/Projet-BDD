package bdd;

import java.util.ArrayList;
import java.util.Scanner;

public class MainDataBase {
	
	
	
	public static void main(String [] args) {
		
		System.out.println("entrer l'url de la base de données : ");
		Scanner scan = new Scanner(System.in);
		String url = scan.nextLine();
		Connexion connectt = new Connexion();
		connectt.connect(url);
		FuncDep func = new FuncDep(connectt);
		ArrayList<ArrayList<String>> arr = func.ReadFuncdep();
		//System.out.println( "le contenu de FUNCDEP est le suivant: \n");
		//System.out.println("[Table_name , lhs, rhs]");
		for(ArrayList<String> i: arr){
			System.out.println(i);
		}
		
		func.satisfaction("AA", "A", "B");
	}
}
