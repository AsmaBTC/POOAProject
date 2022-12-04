import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	private Scanner sc;
	private int nbr;
	private Debat debat;

	public Menu(int nbr) {
		this.sc = new Scanner(System.in);
		this.nbr = nbr;
		debat = new Debat(nbr);
	}

	public void afficher() {
		debat.afficherArgument();
	}

	public void premierMenu() {

		// debat.affichermatrice();

		int choix = 0;

		do {

			debat.affichermatrice();
			System.out.println("1) Ajouter une contradiction");

			System.out.println("2) Fin");

			choix = sc.nextInt();

			if (choix == 1) {

				System.out.println("Argument 1:");
				String arg1 = sc.next();
				System.out.println("Arguement 2: ");
				String arg2 = sc.next();
				debat.ajouterContradiction(arg1, arg2);
				premierMenu();

			}

			if (choix == 2) {

				debat.affichermatrice();
				System.out.println("Fin de la construction du graphe \n");
				System.out.println("Vous pouvez maintenant construire votre solution");
				deuxiemeMenu(debat.getSoluVide());
			}

		} while (choix < 1 || choix > 2);

	}

	public void deuxiemeMenu(ArrayList<String> list) {

		int choix = 0;

		do {
			System.out.println("1) Ajouter un argument");
			System.out.println("2) Retirer un argument");
			System.out.println("3) Verifier la solution");
			System.out.println("4) Fin");
			choix = sc.nextInt();

			if (choix == 1) {
				System.out.println("Quel argument voulez vous ajouter??");
				String arg = sc.next();
				// System.out.println(debat.ajouterArgument_E(arg));
				debat.ajouterArgumentDansListeVite(arg);

				System.out.println(debat.getSoluVide());
				deuxiemeMenu(debat.getSoluVide());
			}

			if (choix == 2) {
				System.out.println("Quel argument voulez vous supprimer??");
				String arg = sc.next();
				debat.retirer_De_La_Liste_Vide(arg);
				// System.out.println(arr);
				System.out.println("" + debat.getSoluVide());
				deuxiemeMenu(debat.getSoluVide());
				// debat.retirer_De_la_Solution(arg);
			}

			if (choix == 3) {
				System.out.println("" + debat.verificationSolution(debat.getSoluVide()));
				deuxiemeMenu(debat.getSoluVide());
			}
			if (choix == 4) {

				// debat.validationOUpas();
				System.out.println("Fin du programme");
				// System.out.println(""+debat.verification_Solution(debat.getSoluVide()));
				break;
			} else if (choix != 1 || choix != 2 || choix != 3 || choix != 4)
				System.out.println("Pas d'option " + choix + " dans le menu !, veuillez saisir 1,2,3 ou 4");

		} while (choix < 1 || choix > 3);

	}

}

/*
 * import java.util.ArrayList; import java.util.Scanner;
 * 
 * public class Menu {
 * 
 * private Scanner sc; private int nbr; private Debat debat;
 * 
 * public Menu(int nbr) { this.sc = new Scanner(System.in); this.nbr = nbr;
 * debat = new Debat(nbr); }
 * 
 * public void afficher() { debat.afficherArgument(); }
 * 
 * public void premierMenu() {
 * 
 * int choix = 0;
 * 
 * do { System.out.println("1) Ajouter une contradiction");
 * 
 * System.out.println("2) Fin");
 * 
 * choix = sc.nextInt();
 * 
 * if (choix == 1) { System.out.println("Argument 1:"); String arg1 = sc.next();
 * System.out.println("Arguement 2: "); String arg2 = sc.next();
 * debat.ajouterContradiction(arg1, arg2); premierMenu(); }
 * 
 * if (choix == 2) {
 * 
 * System.out.println(debat.solution()); deuxiemeMenu(debat.getSolu()); }
 * 
 * } while (choix < 1 || choix > 2);
 * 
 * }
 * 
 * public void deuxiemeMenu(ArrayList<ArrayList<String>> list) {
 * 
 * int choix = 0;
 * 
 * do { System.out.println("1) Ajouter un argument");
 * System.out.println("2) Retirer un argument");
 * System.out.println("3) Verifier la solution"); choix = sc.nextInt();
 * 
 * if (choix == 1) { System.out.println("Quel argument voulez vous ajouter??");
 * String arg = sc.next(); // System.out.println(debat.ajouterArgument_E(arg));
 * deuxiemeMenu(debat.ajouterArgument_E(arg)); }
 * 
 * if (choix == 2) {
 * System.out.println("Quel argument voulez vous supprimer??"); String arg =
 * sc.next(); ArrayList<ArrayList<String>> arr;
 * debat.setSolu(debat.retirerArgument_E(arg)); // System.out.println(arr);
 * System.out.println("" + debat.getSolu()); deuxiemeMenu(debat.getSolu()); //
 * debat.retirer_De_la_Solution(arg); }
 * 
 * if (choix == 3) { System.out.println("" + debat.getSolu()); } } while (choix
 * < 1 || choix > 3);
 * 
 * }
 * 
 * }
 */