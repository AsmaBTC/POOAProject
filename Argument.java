
import java.util.ArrayList;

public class Argument {

	private String id;
	private ArrayList<Argument>contre;
	private ArrayList<Argument>estContre;
	
	public Argument(String id) {
		this.id=id;
		this.contre= new ArrayList<>();
		this.estContre=new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Argument> getContre() {
		return contre;
	}

	public void setContre(ArrayList<Argument> contre) {
		this.contre = contre;
	}

	public ArrayList<Argument> getEstContre() {
		return estContre;
	}

	public void setEstContre(ArrayList<Argument> estContre) {
		this.estContre = estContre;
	}
	
	
	
}