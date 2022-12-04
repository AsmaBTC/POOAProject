import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Debat {

	// a la creation d'un debat l'ajout d'argument aucun n'argument n'est contredit
	private ArrayList<Argument> args;
	private int nbrArg;
	private ArrayList<ArrayList<String>> solu;
	private ArrayList<String> soluVide;
	// la clé est l'argument et sa valeur l'argument qu'il le contredit
	private HashMap<Argument, ArrayList<Argument>> relation;
	private String[][] tableau;
	private HashMap<String, String> Validation;

	public Debat(int nbrArg) {

		this.Validation = new HashMap<>();
		this.args = new ArrayList<>();
		String id = "";

		for (int i = 0; i < nbrArg; i++) {

			id = "A" + (i + 1);
			Argument ar = new Argument(id);
			args.add(ar);

		}

		relation = new HashMap<>();
		this.solu = new ArrayList<>();
		this.soluVide = new ArrayList<>();

		this.tableau = new String[nbrArg + 1][nbrArg + 1];
		int a = 0;

		tableau[0][0] = " ";
		tableau[0][1] = "    ";

		// tableau[0][1]=args.get(0).getId();
		// tableau[0][2]=args.get(1).getId();
		// tableau[0][3]=args.get(2).getId();
		// tableau[0][4]=args.get(3).getId();

		for (int j = 0; j < args.size(); j++) {

			tableau[0][j + 1] = args.get(j).getId();
		}

		for (int i = 0; i < args.size(); i++) {

			tableau[i + 1][0] = args.get(i).getId();
		}

		for (int x = 1; x < tableau.length; x++) {

			for (int y = 1; y < tableau.length; y++) {

				tableau[x][y] = "false";
			}
		}

		// this.affichermatrice();

	}

	public ArrayList<String> getSoluVide() {
		return soluVide;
	}

	public void setSoluVide(ArrayList<String> soluVide) {
		this.soluVide = soluVide;
	}

	public ArrayList<ArrayList<String>> getSolu() {
		return solu;
	}

	public void setSolu(ArrayList<ArrayList<String>> solu) {
		this.solu = solu;
	}

	public void afficherArgument() {

		for (Argument ar : args) {

			System.out.println("" + ar.getId());
		}
	}

	// renvoie true si une relation existe entre deux argument

	public ArrayList<Argument> getArgs() {
		return args;
	}

	public void setArgs(ArrayList<Argument> args) {
		this.args = args;
	}

	public boolean verifieRelation(String arg1, String arg2) {

		boolean exist = false;

		for (Entry<Argument, ArrayList<Argument>> arg : relation.entrySet()) {

			if (arg.getKey().equals(id_to_arg(arg1)) && arg.getValue().equals(id_to_arg(arg2))) {

				exist = true;
			}
		}

		return exist;
	}

	// retour l'argument qui a pour id entré en parametre
	public Argument id_to_arg(String arg1) {

		Argument arg = null;
		for (int i = 0; i < args.size(); i++) {
			if (args.get(i).getId().equals(arg1)) {
				arg = args.get(i);
			}
		}

		return arg;

	}

	// permet de verifier si un argument a deja des argument qu'il contredit

	public boolean relaExiste(String arg1) {
		boolean exist = false;
		Argument arg = id_to_arg(arg1);
		if (relation.containsKey(arg)) {

			exist = true;

		}

		return exist;
	}

	public boolean sontEnrelation(String arg1, String arg2) {

		Argument ar1 = id_to_arg(arg1);
		Argument ar2 = id_to_arg(arg2);
		boolean existe = false;

		if (ar1.getEstContre().contains(ar2) && ar2.getEstContre().contains(ar1)) {

			existe = true;
		}

		return existe;
	}

	// pour tout element a qui contredit un element de E, il exist un element de E
	// qui contredit a

	// arg1 contredit par arg2
	public void ajouterContradiction(String arg1, String arg2) {

		if (verifieRelation(arg1, arg2) == false && (arg1.equals(arg2)) == false) {

			Argument argA = id_to_arg(arg1);
			Argument argB = id_to_arg(arg2);

			if (this.args.contains(argA) && this.args.contains(argB)) {

				if (!argA.getEstContre().contains(argB)) {
					argA.getEstContre().add(argB);
					argB.getContre().add(argA);
					this.relation.put(argA, argA.getEstContre());
					this.indiceI(arg1);
					this.indiceJ(arg2);
					this.ajouterArc(this.indiceI(arg1), this.indiceJ(arg2));

				}
			} else {
				System.out.println("Un des argument n'existe pas");
			}

		}

		else {
			System.out.println("Probleme avec les arguments");
		}

	}

	// affiche les relations

	public void afficherRelation() {

		for (Entry<Argument, ArrayList<Argument>> arg : relation.entrySet()) {

			System.out.print(" " + arg.getKey().getId() + "[");
			for (int i = 0; i < arg.getValue().size(); i++) {

				if (i == arg.getValue().size() - 1) {
					System.out.print("" + arg.getValue().get(i).getId() + "");
				} else {

					System.out.print("" + arg.getValue().get(i).getId() + ",");
				}
			}
			System.out.print("]");

			System.out.println();

		}
		System.out.println();
	}

	public boolean verifieList(ArrayList<ArrayList<String>> list, String arg1, String arg2) {

		boolean existe = false;

		for (int i = 0; i < list.size(); i++) {

			if (!(list.get(i).contains(arg1)) && !(list.get(i).contains(arg2))) {
				existe = true;
			}

		}

		return existe;

	}

	public void ajouterArg(String arg1, ArrayList<ArrayList<Argument>> arr) {

		boolean contient = false;
		for (int i = 0; i < arr.size(); i++) {

			if ((arr.get(i).contains(arg1))) {

				contient = true;
				break;
			}
		}

		System.out.println("" + contient);

	}

//renvoie une liste de arg à partir d'une  liste de string
	public ArrayList<Argument> listStringToArgument(ArrayList<String> list) {

		ArrayList<Argument> arg = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < args.size(); j++) {
				if (list.get(i).equals(args.get(j).getId())) {
					arg.add(args.get(j));
				}
			}
		}

		return arg;
	}

	// transforme une liste de arg en string

	public ArrayList<String> listArgToString(ArrayList<Argument> list) {

		ArrayList<String> arg = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {

			arg.add(list.get(i).getId());
		}

		return arg;

	}

	public ArrayList<ArrayList<String>> solution() {

		ArrayList<ArrayList<String>> ar = new ArrayList<>();

		for (int i = 0; i < args.size() - 1; i++) {

			for (int j = 1; j < args.size(); j++) {

				if (!args.get(j).getId().equals(args.get(i).getId())) {

					if (!(sontEnrelation(args.get(i).getId(), args.get(j).getId()))) {

						ArrayList<String> arr = new ArrayList<>();
						arr.add(args.get(i).getId());
						arr.add(args.get(j).getId());
						ar.add(arr);

					}

				}

			}
		}

		this.setSolu(ar);
		return solu;
	}

	public ArrayList<ArrayList<String>> solution2(ArrayList<Argument> args) {

		ArrayList<ArrayList<String>> ar = new ArrayList<>();
		// args

		for (int i = 0; i < args.size() - 1; i++) {

			for (int j = 1; j < args.size(); j++) {

				if (!args.get(j).getId().equals(args.get(i).getId())) {

					if (!(sontEnrelation(args.get(i).getId(), args.get(j).getId()))) {

						ArrayList<String> arr = new ArrayList<>();
						arr.add(args.get(i).getId());
						arr.add(args.get(j).getId());
						ar.add(arr);

						if (!(ar.contains(args.get(i).getId()) && ar.contains(args.get(j).getId()))) {

						}

					}

				}
			}

		}
		this.setSolu(ar);
		return solu;

	}

	public boolean existeDeja(String arg) {

		ArrayList<ArrayList<String>> ar = solution();
		boolean existe = false;
		for (int i = 0; i < ar.size(); i++) {

			if (ar.get(i).contains(arg)) {
				existe = true;
				break;
			}

		}

		return existe;
	}

	public boolean existeDeja2(String arg, ArrayList<String> list) {
		boolean existe = false;

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).equals(arg)) {

				existe = true;
				break;

			}
		}

		return existe;
	}

	// renvoie les composant de solution en une seule liste
	public ArrayList<String> toSolution() {

		ArrayList<String> argu = new ArrayList<>();

		ArrayList<ArrayList<String>> arr = solution();

		for (int i = 0; i < arr.size(); i++) {

			for (int j = 0; j < arr.get(i).size(); j++) {

				if (!(argu.contains(arr.get(i).get(j)))) {

					argu.add(arr.get(i).get(j));
				}

			}

		}

		return argu;

	}

	public void defend(String arg) {

	}

	public ArrayList<String> retirer_De_la_Solution(String arg) {

		ArrayList<String> argu = new ArrayList<>();

		ArrayList<ArrayList<String>> arr = solu;

		for (int i = 0; i < arr.size(); i++) {

			if (arr.get(i).contains(arg)) {
				arr.get(i).remove(arg);
			}

		}

		for (int i = 0; i < arr.size(); i++) {

			for (int j = 0; j < arr.get(i).size(); j++) {

				if (!argu.contains(arr.get(i).get(j))) {
					argu.add(arr.get(i).get(j));
				}
			}
		}

		return argu;

	}

	public ArrayList<String> ajouter_Dans_Solution(String arg) {

		ArrayList<String> argu = new ArrayList<>();

		ArrayList<ArrayList<String>> arr = solution();
		ArrayList<String> ar = new ArrayList<>();
		ar.add(arg);
		arr.add(ar);

		for (int i = 0; i < arr.size(); i++) {

			for (int j = 0; j < arr.get(i).size(); j++) {

				if (!(argu.contains(arr.get(i).get(j)))) {

					argu.add(arr.get(i).get(j));
				}

			}

		}

		return argu;

	}

	public void ajouterArgumentDansListeVite(String arg) {

		this.Validation.clear();

		if (!this.soluVide.isEmpty()) {

			if (!existeDeja2(arg, this.soluVide)) {
				// System.out.println("Morel");
				this.soluVide.add(arg);

			} else {
				System.out.println("Desol� cet argument existe");
			}
		}

		else {

			this.soluVide.add(arg);
		}

		// return soluVide;

	}

	public ArrayList<ArrayList<String>> ajouterArgument_E(String arg) {

		if (existeDeja(arg) || existeDeja2(arg, toSolution())) {

			System.out.println("Desol� cet argument existe deja");
		}

		else {

			return solution2(listStringToArgument(ajouter_Dans_Solution(arg)));

		}

		return null;
	}

	public ArrayList<ArrayList<String>> retirerArgument_E(String arg) {

		if (!existeDeja(arg)) {

			System.out.println("Desol� cet argument n'existe pas");
		}

		return solution2(listStringToArgument(retirer_De_la_Solution(arg)));
	}

	public void retirer_De_La_Liste_Vide(String arg) {
		this.Validation.clear();

		if (this.soluVide.contains(arg)) {

			this.soluVide.remove(arg);
		} else {
			System.out.println("cet argument n'est pas dans la solution");
		}

	}

	// permet de verifier la solution entrer par l'utilisateur
	public ArrayList<String> verificationSolution(ArrayList<String> list) {

		if (list.isEmpty()) {
			System.out.println("Cette solution est acceptable");
			this.Validation.put("Acceptable", "Cette solution est acceptable");
		}

		else if (list.size() == 1) {

			System.out.println("Cette solution est acceptable");
			this.Validation.put("Acceptable", "Cette solution est acceptable");
		}

		else {

			ArrayList<Argument> arr = listStringToArgument(list);

			for (int i = 0; i < arr.size() - 1; i++) {

				for (int j = 1; j < arr.size(); j++) {

					if (!arr.get(j).getId().equals(arr.get(i).getId())) {

						if ((sontEnrelation(arr.get(i).getId(), arr.get(j).getId()))) {
							System.out.println("Solution non admissible (contradiction entre " + arr.get(i).getId()
									+ " et " + arr.get(j).getId() + ")");

							System.out.println("Votre solution n'est pas la bonne des arguments se contredisent");
							this.Validation.put("Non acceptable",
									"Votre solution n'est pas la bonne des arguments se contredisent");
							break;

						}

						else {

							if (se_defendent(arr.get(i).getId(), arr.get(j).getId())) {

								// System.out.println(""+arr.get(i).getId()+" defend "+arr.get(j).getId());
								System.out.println("Cette solution est acceptable");
								this.Validation.put("Acceptable", "Cette solution est acceptable");
							} else {
								System.out.println("Votre solution est acceptable");
								this.Validation.put("Acceptable", "Cette solution est acceptable");

							}
						}

					}

				}

			}
		}
		return soluVide;

	}

	public void validationOUpas() {

		System.out.println("" + this.getSoluVide());
		// System.out.println(""+this.Validation.size());

		for (Entry<String, String> map : this.Validation.entrySet()) {
			System.out.println(map.getKey() + map.getValue());
		}

	}

	public boolean se_defendent(String A, String B) {

		int a = 0, b = 0;
		boolean defense = false;
		System.out.println("" + id_to_arg(A).getContre().size());

		for (int i = 0; i < id_to_arg(A).getContre().size(); i++) {

			if (id_to_arg(B).getEstContre().contains(id_to_arg(A).getContre().get(i))) {

				a++;
			}
		}

		for (int i = 0; i < id_to_arg(B).getContre().size(); i++) {

			if (id_to_arg(A).getEstContre().contains(id_to_arg(B).getContre().get(i))) {

				b++;
			}
		}

		if (a == id_to_arg(A).getContre().size() && b == id_to_arg(B).getContre().size()) {
			defense = true;
		}
		return defense;
	}

	public void affichermatrice() {

		for (int i = 0; i < this.tableau.length; i++) {

			for (int j = 0; j < this.tableau.length; j++) {

				if (i == 0 && (j == 0 || j == 1 || j == 2 || j == 3 || j == 4)) {
					System.out.print(" " + tableau[0][j] + "   ");
				}

				else {

					System.out.print(" " + tableau[i][j]);

				}

			}
			System.out.println();
		}

		System.out.println();
	}

	public int indiceI(String arg1) {

		int x = 0;

		for (int j = 0; j < tableau.length; j++) {
			if (tableau[0][j].equals(arg1)) {
				x = j;
			}

		}

		return x;

	}

	public int indiceJ(String arg1) {

		int y = 0;

		for (int i = 0; i < tableau.length; i++) {
			if (tableau[i][0].equals(arg1)) {
				y = i;
			}

		}

		return y;

	}

	public void ajouterArc(int i, int j) {

		tableau[i][j] = "true";
	}

}

/*
 * import java.util.ArrayList; import java.util.HashMap; import
 * java.util.Map.Entry;
 * 
 * public class Debat {
 * 
 * // a la creation d'un debat l'ajout d'argument aucun n'argument n'est
 * contredit private ArrayList<Argument> args; private int nbrArg; private
 * ArrayList<ArrayList<String>> solu; // la clé est l'argument et sa valeur
 * l'argument qu'il le contredit private HashMap<Argument, ArrayList<Argument>>
 * relation;
 * 
 * public Debat(int nbrArg) { this.args = new ArrayList<>(); String id = ""; for
 * (int i = 0; i < nbrArg; i++) { id = "A" + (i + 1); Argument ar = new
 * Argument(id); args.add(ar); } relation = new HashMap<>(); this.solu = new
 * ArrayList<>(); }
 * 
 * public ArrayList<ArrayList<String>> getSolu() { return solu; }
 * 
 * public void setSolu(ArrayList<ArrayList<String>> solu) { this.solu = solu; }
 * 
 * public void afficherArgument() {
 * 
 * for (Argument ar : args) {
 * 
 * System.out.println("" + ar.getId()); } }
 * 
 * // renvoie true si une relation existe entre deux argument
 * 
 * public ArrayList<Argument> getArgs() { return args; }
 * 
 * public void setArgs(ArrayList<Argument> args) { this.args = args; }
 * 
 * public boolean verifieRelation(String arg1, String arg2) {
 * 
 * boolean exist = false;
 * 
 * for (Entry<Argument, ArrayList<Argument>> arg : relation.entrySet()) {
 * 
 * if (arg.getKey().equals(id_to_arg(arg1)) &&
 * arg.getValue().equals(id_to_arg(arg2))) {
 * 
 * exist = true; } }
 * 
 * return exist; }
 * 
 * // retour l'argument qui a pour id entré en parametre public Argument
 * id_to_arg(String arg1) {
 * 
 * Argument arg = null; for (int i = 0; i < args.size(); i++) { if
 * (args.get(i).getId().equals(arg1)) { arg = args.get(i); } }
 * 
 * return arg;
 * 
 * }
 * 
 * // permet de verifier si un argument a deja des argument qu'il contredit
 * 
 * public boolean relaExiste(String arg1) { boolean exist = false; Argument arg
 * = id_to_arg(arg1); if (relation.containsKey(arg)) {
 * 
 * exist = true;
 * 
 * }
 * 
 * return exist; }
 * 
 * public boolean sontEnrelation(String arg1, String arg2) {
 * 
 * Argument ar1 = id_to_arg(arg1); Argument ar2 = id_to_arg(arg2); boolean
 * existe = false;
 * 
 * if (ar1.getEstContre().contains(ar2) && ar2.getEstContre().contains(ar1)) {
 * 
 * existe = true; }
 * 
 * return existe; }
 * 
 * // pour tout element a qui contredit un element de E, il exist un element de
 * E // qui contredit a
 * 
 * // arg1 contredit par arg2 public void ajouterContradiction(String arg1,
 * String arg2) {
 * 
 * if (verifieRelation(arg1, arg2) == false && (arg1.equals(arg2)) == false) {
 * 
 * Argument argA = id_to_arg(arg1); Argument argB = id_to_arg(arg2);
 * 
 * if (this.args.contains(argA) && this.args.contains(argB)) {
 * argA.getEstContre().add(argB); this.relation.put(argA, argA.getEstContre());
 * } else { System.out.println("Un des argument n'existe pas"); }
 * 
 * }
 * 
 * else { System.out.println("Probleme avec les arguments"); }
 * 
 * }
 * 
 * // affiche les relations
 * 
 * public void afficherRelation() {
 * 
 * for (Entry<Argument, ArrayList<Argument>> arg : relation.entrySet()) {
 * 
 * System.out.print(" " + arg.getKey().getId() + "["); for (int i = 0; i <
 * arg.getValue().size(); i++) {
 * 
 * if (i == arg.getValue().size() - 1) { System.out.print("" +
 * arg.getValue().get(i).getId() + ""); } else {
 * 
 * System.out.print("" + arg.getValue().get(i).getId() + ","); } }
 * System.out.print("]");
 * 
 * System.out.println();
 * 
 * } System.out.println(); }
 * 
 * public boolean verifieList(ArrayList<ArrayList<String>> list, String arg1,
 * String arg2) {
 * 
 * boolean existe = false;
 * 
 * for (int i = 0; i < list.size(); i++) {
 * 
 * if (!(list.get(i).contains(arg1)) && !(list.get(i).contains(arg2))) { existe
 * = true; }
 * 
 * }
 * 
 * return existe;
 * 
 * }
 * 
 * public void ajouterArg(String arg1, ArrayList<ArrayList<Argument>> arr) {
 * 
 * boolean contient = false; for (int i = 0; i < arr.size(); i++) {
 * 
 * if ((arr.get(i).contains(arg1))) {
 * 
 * contient = true; break; } }
 * 
 * System.out.println("" + contient);
 * 
 * }
 * 
 * //renvoie une liste de arg à partir d'une liste de string public
 * ArrayList<Argument> listStringToArgument(ArrayList<String> list) {
 * 
 * ArrayList<Argument> arg = new ArrayList<>();
 * 
 * for (int i = 0; i < list.size(); i++) { for (int j = 0; j < args.size(); j++)
 * { if (list.get(i).equals(args.get(j).getId())) { arg.add(args.get(j)); } } }
 * 
 * return arg; }
 * 
 * // transforme une liste de arg en string
 * 
 * public ArrayList<String> listArgToString(ArrayList<Argument> list) {
 * 
 * ArrayList<String> arg = new ArrayList<>();
 * 
 * for (int i = 0; i < list.size(); i++) {
 * 
 * arg.add(list.get(i).getId()); }
 * 
 * return arg;
 * 
 * }
 * 
 * public ArrayList<ArrayList<String>> solution() {
 * 
 * ArrayList<ArrayList<String>> ar = new ArrayList<>(); // args
 * 
 * for (int i = 0; i < args.size() - 1; i++) {
 * 
 * for (int j = 1; j < args.size(); j++) {
 * 
 * if (!args.get(j).getId().equals(args.get(i).getId())) {
 * 
 * if (!(sontEnrelation(args.get(i).getId(), args.get(j).getId()))) {
 * 
 * // System.out.println(i+" "+args.get(i).getId()+" "+j+" et //
 * "+args.get(j).getId()+ " "+ //
 * !(sontEnrelation(args.get(i).getId(),args.get(j).getId())));
 * 
 * //
 * System.out.println(""+!(verifieList(ar,args.get(i).getId(),args.get(j).getId(
 * ))));
 * 
 * ArrayList<String> arr = new ArrayList<>(); arr.add(args.get(i).getId());
 * arr.add(args.get(j).getId()); ar.add(arr);
 * 
 * // System.out.println(""+args.get(i).getId()+" et "+args.get(j).getId()); //
 * if(!(ar.contains(args.get(i).getId()) && ar.contains(args.get(j).getId()))){
 * 
 * // ar.add(args.get(i).getId()); //
 * .out.println(args.get(i).getId()+" et "+args.get(j).getId()+": //
 * "+sontEnrelation(args.get(i).getId(),args.get(j).getId()) ); // } }
 * 
 * } // System.out.println(args.get(i).getId()+" et "+args.get(j).getId()+": //
 * "+sontEnrelation(args.get(i).getId(),args.get(j).getId()) ); } } //
 * System.out.println(""+sontEnrelation(args.get(0).getId(),args.get(1).getId())
 * );
 * 
 * // if((sontEnrelation(args.get(i).getId(),args.get(i--).getId()))){
 * 
 * // if((ar.contains(args.get(i)))) { // ar.add(args.get(i).getId());
 * 
 * this.setSolu(ar); return solu; }
 * 
 * public ArrayList<ArrayList<String>> solution2(ArrayList<Argument> args) {
 * 
 * ArrayList<ArrayList<String>> ar = new ArrayList<>(); // args
 * 
 * for (int i = 0; i < args.size() - 1; i++) {
 * 
 * for (int j = 1; j < args.size(); j++) {
 * 
 * if (!args.get(j).getId().equals(args.get(i).getId())) {
 * 
 * if (!(sontEnrelation(args.get(i).getId(), args.get(j).getId()))) {
 * 
 * // System.out.println(i+" "+args.get(i).getId()+" "+j+" et //
 * "+args.get(j).getId()+ " "+ //
 * !(sontEnrelation(args.get(i).getId(),args.get(j).getId())));
 * 
 * //
 * System.out.println(""+!(verifieList(ar,args.get(i).getId(),args.get(j).getId(
 * ))));
 * 
 * ArrayList<String> arr = new ArrayList<>(); arr.add(args.get(i).getId());
 * arr.add(args.get(j).getId()); ar.add(arr);
 * 
 * // System.out.println(""+args.get(i).getId()+" et "+args.get(j).getId()); if
 * (!(ar.contains(args.get(i).getId()) && ar.contains(args.get(j).getId()))) {
 * 
 * // ar.add(args.get(i).getId()); //
 * System.out.println(args.get(i).getId()+" et "+args.get(j).getId()+": //
 * "+sontEnrelation(args.get(i).getId(),args.get(j).getId()) ); // } }
 * 
 * } // System.out.println(args.get(i).getId()+" et "+args.get(j).getId()+": //
 * "+sontEnrelation(args.get(i).getId(),args.get(j).getId()) ); } } //
 * System.out.println(""+sontEnrelation(args.get(0).getId(),args.get(1).getId())
 * );
 * 
 * // if((sontEnrelation(args.get(i).getId(),args.get(i--).getId()))){
 * 
 * // if((ar.contains(args.get(i)))) { // } this.setSolu(ar); return solu;
 * 
 * }
 * 
 * public boolean existeDeja(String arg) {
 * 
 * ArrayList<ArrayList<String>> ar = solution(); boolean existe = false; for
 * (int i = 0; i < ar.size(); i++) {
 * 
 * if (ar.get(i).contains(arg)) { existe = true; break; }
 * 
 * }
 * 
 * return existe; }
 * 
 * public boolean existeDeja2(String arg, ArrayList<String> list) { boolean
 * existe = false; for (int i = 0; i < list.size(); i++) { if
 * (list.get(i).equals(arg)) {
 * 
 * existe = true; break;
 * 
 * } }
 * 
 * return existe; }
 * 
 * // renvoie les composant de solution en une seule liste public
 * ArrayList<String> toSolution() {
 * 
 * ArrayList<String> argu = new ArrayList<>();
 * 
 * ArrayList<ArrayList<String>> arr = solution();
 * 
 * for (int i = 0; i < arr.size(); i++) {
 * 
 * for (int j = 0; j < arr.get(i).size(); j++) {
 * 
 * if (!(argu.contains(arr.get(i).get(j)))) {
 * 
 * argu.add(arr.get(i).get(j)); }
 * 
 * }
 * 
 * }
 * 
 * return argu;
 * 
 * }
 * 
 * public ArrayList<String> retirer_De_la_Solution(String arg) {
 * 
 * ArrayList<String> argu = new ArrayList<>();
 * 
 * ArrayList<ArrayList<String>> arr = solu;
 * 
 * for (int i = 0; i < arr.size(); i++) {
 * 
 * if (arr.get(i).contains(arg)) { arr.get(i).remove(arg); }
 * 
 * }
 * 
 * for (int i = 0; i < arr.size(); i++) {
 * 
 * for (int j = 0; j < arr.get(i).size(); j++) {
 * 
 * if (!argu.contains(arr.get(i).get(j))) { argu.add(arr.get(i).get(j)); } } }
 * 
 * return argu;
 * 
 * }
 * 
 * public ArrayList<String> ajouter_Dans_Solution(String arg) {
 * 
 * ArrayList<String> argu = new ArrayList<>();
 * 
 * ArrayList<ArrayList<String>> arr = solution(); ArrayList<String> ar = new
 * ArrayList<>(); ar.add(arg); arr.add(ar);
 * 
 * for (int i = 0; i < arr.size(); i++) {
 * 
 * for (int j = 0; j < arr.get(i).size(); j++) {
 * 
 * if (!(argu.contains(arr.get(i).get(j)))) {
 * 
 * argu.add(arr.get(i).get(j)); }
 * 
 * }
 * 
 * }
 * 
 * return argu;
 * 
 * }
 * 
 * public ArrayList<ArrayList<String>> ajouterArgument_E(String arg) {
 * 
 * if (existeDeja(arg) || existeDeja2(arg, toSolution())) {
 * 
 * System.out.println("Desolé cet argument existe deja"); }
 * 
 * else {
 * 
 * // System.out.println(""+ajouter_Dans_Solution(arg)); //
 * listStringToArgument(ajouter_Dans_Solution(arg)); //
 * System.out.println(""+solution2(listStringToArgument(ajouter_Dans_Solution(
 * arg)))); return solution2(listStringToArgument(ajouter_Dans_Solution(arg)));
 * // listStringToArgument(ArrayList<String>list) }
 * 
 * return null; }
 * 
 * public ArrayList<ArrayList<String>> retirerArgument_E(String arg) {
 * 
 * if (!existeDeja(arg)) {
 * 
 * System.out.println("Desolé cet argument n'existe pas"); } // else {
 * 
 * // System.out.println(""+retirer_De_la_Solution(arg)); //
 * listStringToArgument(retirer_De_la_Solution(arg)); //
 * System.out.println(""+solution2(listStringToArgument(retirer_De_la_Solution(
 * arg))));
 * 
 * // }
 * 
 * return solution2(listStringToArgument(retirer_De_la_Solution(arg))); }
 * 
 * }
 */