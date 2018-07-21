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
		
		String dbName = "./src//movieproject_orig.db";
		Data db = new Data();
		//Die Datei wird in die Datenbank db eingelesen
		db = Funktionen.ladeDB(dbName, db);
		
		Boolean test = false;
		
		//Überprüfe, ob Testmodus aktiviert werden soll
		for (String arg : args) {
			
			if (arg.contains("--test=true")) {
				
				test = true;
				
			}
			
		}
		
		if (test) {
			
			Funktionen.testModus(db);
			System.out.println("Der Testmodus wurde gestartet. Das Ergebnis wird in die Datei 'results.txt' geschrieben.");
			
		} else {
			
			Funktionen.schreibeInDatei(Funktionen.readArgs(args, db));
			System.out.println("\n\nDas Ergebnis ihrer Anfrage wurde zusätzlich in die Datei 'ergebnis.txt' geschrieben.");
			
		}
		
	}

}