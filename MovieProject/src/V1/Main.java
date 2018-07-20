package V1;

/**
 * @author MaderP
 * In der Main-Klasse werden die benötigten Funktionen aufgerufen.
 */
public class Main {
	
	/**
	 * @param args
	 * Beinhaltet den Ablauf aller Funktionen.
	 */
	public static void main(String[] args) {
		
		String dbName = "./src//movieproject.db";
		Data db = new Data();
		db = Funktionen.ladeDB(dbName, db);
		Funktionen.printDb(db);
		Empfehlung.getEmpfehlungByActor("blabla", db, 4);
		
	}

}