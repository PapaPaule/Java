
public class Regisseur {

	protected int director_id;
	protected String director_name;
	
	public Regisseur() {
		
	}
	
	public Regisseur(int director_id, String director_name) {
		this.director_id = director_id;
		this.director_name = director_name;
	}
	
	public int getDirectorId() {
		return this.director_id;
	}
	
	public String getDirectorName() {
		return this.director_name;
	}

}
