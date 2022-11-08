
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;



public class Debat {

	
	
	// a la creation d'un debat l'ajout d'argument aucun n'argument n'est contredit
	private ArrayList<Argument>args;
	private int nbrArg;
	private ArrayList<ArrayList<String>>solu;
	//la clé est l'argument et sa valeur l'argument qu'il le contredit
	private HashMap<Argument, ArrayList<Argument>>relation;
	
	
	
		public Debat(int nbrArg) {
			this.args=new ArrayList<>();
			String id="";
			for(int i=0;i<nbrArg;i++) {
				id="A"+(i+1);
				Argument ar= new Argument(id);
				args.add(ar);
			}
			relation= new HashMap<>();
			this.solu=new ArrayList<>();
		}
	
	
		
	
	
	public ArrayList<ArrayList<String>> getSolu() {
			return solu;
		}





		public void setSolu(ArrayList<ArrayList<String>> solu) {
			this.solu = solu;
		}





	public void afficherArgument() {
		
		for(Argument ar:args) {
			
			System.out.println(""+ar.getId());
		}
	}
	
	
	
	
	// renvoie true si une relation existe entre deux argument
	
		public ArrayList<Argument> getArgs() {
		return args;
	}




	public void setArgs(ArrayList<Argument> args) {
		this.args = args;
	}




		public boolean verifieRelation(String arg1,String arg2) {
			
			boolean exist=false;
			 
			for(Entry<Argument,ArrayList<Argument>>arg:relation.entrySet()) {
				  
					if(arg.getKey().equals(id_to_arg(arg1)) && arg.getValue().equals(id_to_arg(arg2))){
						
						exist=true;
					}
			}
			
		return exist;
	}
	
	
	
	
		
	//retour l'argument qui a pour id entré en parametre
	public Argument id_to_arg(String arg1) {
		
		Argument arg=null;
		for(int i=0;i<args.size();i++) {
			if(args.get(i).getId().equals(arg1)) {
				arg=args.get(i);
			}
		}
		
		return arg;
		
	}
	
	
	
	
	
	//permet de verifier si un argument a deja des argument qu'il contredit
	
	public boolean relaExiste(String arg1) {
		boolean exist=false;
		Argument arg=id_to_arg(arg1);
		 if(relation.containsKey(arg)) {
			 
			 exist=true;
			 
		 }
		 
		 return exist;
	}
	
	
	
	
	
	 
	
	
	
		public boolean sontEnrelation(String arg1,String arg2) {
			
			Argument ar1=id_to_arg(arg1);
			Argument ar2=id_to_arg(arg2);
			boolean existe=false;
			
			if(ar1.getEstContre().contains(ar2) && ar2.getEstContre().contains(ar1)) {
				
				existe=true;
			}
			
			return existe;
		}
	
	
		
		//pour tout element a qui contredit un element de E, il exist un element de E qui contredit a
		
		
		
		
		
		
		
		
		
		
		
	
	//arg1 contredit par arg2
	public void ajouterContradiction(String arg1,String arg2) {
		
		 if(verifieRelation(arg1,arg2)==false && (arg1.equals(arg2))==false ) {
				
						Argument argA=id_to_arg( arg1);
						Argument argB=id_to_arg( arg2);
						
						if(this.args.contains(argA)&& this.args.contains(argB)) {
							argA.getEstContre().add(argB);
							this.relation.put(argA, argA.getEstContre());
						}
						else {
							System.out.println("Un des argument n'existe pas");
						}
						
			}
				
			else {
					System.out.println("Probleme avec les arguments");
				}
		
}
	
	
	
	
	//affiche les relations
	
	public void afficherRelation() {
		
		for(Entry<Argument, ArrayList<Argument>>arg:relation.entrySet()) {
			
			System.out.print(" "+arg.getKey().getId()+"[");
			for(int i=0;i<arg.getValue().size();i++) {
				
				if(i==arg.getValue().size()-1) {
				System.out.print(""+arg.getValue().get(i).getId()+"");
				}else {
					
					System.out.print(""+arg.getValue().get(i).getId()+",");
				}
			}
			System.out.print("]");
			
			System.out.println();
			
		}
		System.out.println();
	}
	
	
	

	
	public boolean verifieList(ArrayList<ArrayList<String>>list,String arg1,String arg2) {
		
			boolean existe= false;
			
			for(int i=0;i<list.size();i++) {
				
				if(!(list.get(i).contains(arg1)) && !(list.get(i).contains(arg2))) {
					existe=true;
				}
				
			}
			
			return existe;
			
	}
	
	
	
	
	
public void ajouterArg(String arg1,ArrayList<ArrayList<Argument>>arr) {
		
		boolean contient=false;
			for(int i=0;i<arr.size();i++) {
				 
				if((arr.get(i).contains(arg1))) {
					
					contient=true;
					break;
				}
			}
			
			System.out.println(""+contient);
			
		
	}
	
	
	
	


//renvoie une liste de arg à partir d'une  liste de string
	public ArrayList<Argument> listStringToArgument(ArrayList<String>list){
		
			ArrayList<Argument>arg=new ArrayList<>();
			
			for(int i=0;i<list.size();i++) {
				for(int j=0;j<args.size();j++) {
					if(list.get(i).equals(args.get(j).getId())) {
						arg.add(args.get(j));
					}
				}
			}
		
			return arg;
	}
	
	
	
	
	//transforme une liste de arg en string

	public ArrayList<String> listArgToString(ArrayList<Argument>list){
		
		
		ArrayList<String>arg=new ArrayList<>();
		
		for(int i=0;i<list.size();i++) {
			
			arg.add(list.get(i).getId());
		}
		
	
		return arg;
		
	}
	
	
		
	public 	ArrayList<ArrayList<String>> solution() {
			
			ArrayList<ArrayList<String>> ar= new ArrayList<>();
			//args
			
			 for(int i=0;i<args.size()-1;i++) {
				 
				  for(int j=1;j<args.size();j++) {
					 
					  if(!args.get(j).getId().equals(args.get(i).getId())) {
						  
						  
						  if(!(sontEnrelation(args.get(i).getId(),args.get(j).getId()))){
							  
							  //System.out.println(i+" "+args.get(i).getId()+" "+j+" et "+args.get(j).getId()+ " "+ !(sontEnrelation(args.get(i).getId(),args.get(j).getId())));
					
							  
							 // System.out.println(""+!(verifieList(ar,args.get(i).getId(),args.get(j).getId())));
							  
								       ArrayList<String>arr= new ArrayList<>();
										  arr.add(args.get(i).getId());
										  arr.add(args.get(j).getId());
										  ar.add(arr);
								  
							 // System.out.println(""+args.get(i).getId()+" et "+args.get(j).getId());
							//  	if(!(ar.contains(args.get(i).getId()) && ar.contains(args.get(j).getId()))){
							  		
							  		
							  	//	ar.add(args.get(i).getId());
							  		 //.out.println(args.get(i).getId()+" et "+args.get(j).getId()+": "+sontEnrelation(args.get(i).getId(),args.get(j).getId())  );
							  //	}
						  }
						  
					  }
				// System.out.println(args.get(i).getId()+" et "+args.get(j).getId()+": "+sontEnrelation(args.get(i).getId(),args.get(j).getId())  );
				 }
			 }
			//System.out.println(""+sontEnrelation(args.get(0).getId(),args.get(1).getId()));
			
			
			
				  //if((sontEnrelation(args.get(i).getId(),args.get(i--).getId()))){
					 				
					// 				if((ar.contains(args.get(i)))) {
					 //					ar.add(args.get(i).getId());
			
			 this.setSolu(ar);
			 return solu;
	}
			
	
	
	
	
	
	
	public ArrayList<ArrayList<String>> solution2(ArrayList<Argument> args){
		
	
	
	
	ArrayList<ArrayList<String>> ar= new ArrayList<>();
	//args
	
	 for(int i=0;i<args.size()-1;i++) {
		 
		  for(int j=1;j<args.size();j++) {
			 
			  if(!args.get(j).getId().equals(args.get(i).getId())) {
				  
				  
				  if(!(sontEnrelation(args.get(i).getId(),args.get(j).getId()))){
					  
					 // System.out.println(i+" "+args.get(i).getId()+" "+j+" et "+args.get(j).getId()+ " "+ !(sontEnrelation(args.get(i).getId(),args.get(j).getId())));
			
					  
					//  System.out.println(""+!(verifieList(ar,args.get(i).getId(),args.get(j).getId())));
					  
						       ArrayList<String>arr= new ArrayList<>();
								  arr.add(args.get(i).getId());
								  arr.add(args.get(j).getId());
								  ar.add(arr);
						  
					//  System.out.println(""+args.get(i).getId()+" et "+args.get(j).getId());
						if(!(ar.contains(args.get(i).getId()) && ar.contains(args.get(j).getId()))){
					  		
					  		
					  	//ar.add(args.get(i).getId());
					  		// System.out.println(args.get(i).getId()+" et "+args.get(j).getId()+": "+sontEnrelation(args.get(i).getId(),args.get(j).getId())  );
					  //	}
				  }
				  
			  }
		// System.out.println(args.get(i).getId()+" et "+args.get(j).getId()+": "+sontEnrelation(args.get(i).getId(),args.get(j).getId())  );
		 }
	 }
	//System.out.println(""+sontEnrelation(args.get(0).getId(),args.get(1).getId()));
	
	
	
		  //if((sontEnrelation(args.get(i).getId(),args.get(i--).getId()))){
			 				
			// 				if((ar.contains(args.get(i)))) {
			 //		
	 }
	this.setSolu(ar);
	 return solu;
	
	 
	}
	
	
	
	
	 
	
	 
	public boolean existeDeja(String arg) {
		
		ArrayList<ArrayList<String>>ar=solution();
		boolean existe=false;
			for(int i=0;i<ar.size();i++) {
				
					if(ar.get(i).contains(arg)) {
						existe=true;
						break;
					}
				
			}
			
	 return existe;
	}
	
	
	
	public boolean existeDeja2(String arg,ArrayList<String>list) {
		boolean existe=false;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).equals(arg)){
				
				existe=true;
				break;
				
			}
		}
		
		return existe;
	}
	
	
	
	
	
	//renvoie les composant de solution en une seule liste
	public ArrayList<String> toSolution(){
		
		ArrayList<String>argu= new ArrayList<>();
					
					ArrayList<ArrayList<String>>arr=solution();
					 
				  for(int i=0;i<arr.size();i++) {
						
						
						for(int j=0;j<arr.get(i).size();j++) {
							
								if(!(argu.contains(arr.get(i).get(j)))) {
									
									argu.add(arr.get(i).get(j));
								}
							
						}
						
					}
		
		return argu;
		
	}
	
	
	
	
	
public ArrayList<String> retirer_De_la_Solution(String arg){
		
		ArrayList<String>argu= new ArrayList<>();
		
		ArrayList<ArrayList<String>>arr=solu;
		
		 for(int i=0;i<arr.size();i++) {
			 
			 	 if(arr.get(i).contains(arg)) {
			 		 arr.get(i).remove(arg);
			 	 }
			 
		 }
		 
		 for(int i=0;i<arr.size();i++) {
			 
			 for(int j=0;j<arr.get(i).size();j++) {
				 
				 if(!argu.contains(arr.get(i).get(j))) {
					 argu.add(arr.get(i).get(j));
				 }
			 }
		 }
		
		return argu;
		
	}
	
	
public ArrayList<String> ajouter_Dans_Solution(String arg){
	
	ArrayList<String>argu= new ArrayList<>();
	
	ArrayList<ArrayList<String>>arr=solution();
	ArrayList<String>ar= new ArrayList<>();
	ar.add( arg);
	arr.add(ar);
	
	for(int i=0;i<arr.size();i++) {
		
		
		for(int j=0;j<arr.get(i).size();j++) {
			
				if(!(argu.contains(arr.get(i).get(j)))) {
					
					argu.add(arr.get(i).get(j));
				}
			
		}
		
	}
	
	return argu;
	
}	

					 

	public ArrayList<ArrayList<String>> ajouterArgument_E(String arg) {
		
		
		 
		 		if(existeDeja(arg) || existeDeja2(arg,toSolution())) {
		 			
		 			System.out.println("Desolé cet argument existe deja");
		 		}
		 		
		 		else {

		 			//System.out.println(""+ajouter_Dans_Solution(arg));
		 			//listStringToArgument(ajouter_Dans_Solution(arg));
		 			//System.out.println(""+solution2(listStringToArgument(ajouter_Dans_Solution(arg))));
		 			return solution2(listStringToArgument(ajouter_Dans_Solution(arg)));
		 			//listStringToArgument(ArrayList<String>list)
		 		}
		 
		 		return null;
	}
	
	
	public ArrayList<ArrayList<String>> retirerArgument_E(String arg) {
		
		if(!existeDeja(arg) ) {
 			
 			System.out.println("Desolé cet argument n'existe pas");
 		}
		//else {
			
			//System.out.println(""+retirer_De_la_Solution(arg));
			//listStringToArgument(retirer_De_la_Solution(arg));
			//System.out.println(""+solution2(listStringToArgument(retirer_De_la_Solution(arg))));
			
		//}
		
		return solution2(listStringToArgument(retirer_De_la_Solution(arg)));
	}

				 
			 
			
			 
		
}