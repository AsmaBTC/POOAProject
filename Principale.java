import java.util.ArrayList;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 	 
		 Scanner sc= new Scanner(System.in);
 		 
		System.out.println("Entrez le Nombre d'argument que vous voulez");
		int nbr=sc.nextInt();
		
		Menu menu= new Menu(nbr);
		menu.premierMenu(); 
		
		sc.close();
	  
	  
	}

}
























/*
import java.util.ArrayList;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		System.out.println("Entrez le nombre d'argument: ");
		int nbr=sc.nextInt();
		Menu menu= new Menu(nbr);
		menu.afficher();
		menu.premierMenu();
		
		/*
		
	 Debat debat= new Debat(nbr);
	 debat.afficherArgument();
	 debat.ajouterContradiction("A1", "A2");
	 debat.ajouterContradiction("A2", "A1");
	 
	 debat.ajouterContradiction("A2", "A3");
	 debat.ajouterContradiction("A3", "A2");
	 
	 debat.ajouterContradiction("A3", "A4");
	 debat.ajouterContradiction("A4", "A3");
	 
	 debat.solution();
		System.out.println(debat.getSolu());
		System.out.println(debat.retirerArgument_E("A1"));
		*/
		
		
