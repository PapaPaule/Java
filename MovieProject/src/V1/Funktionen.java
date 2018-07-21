package V1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author maede
 * Die Klasse Funktionen beinhaltet alle wichtigen Funktionen, die für das Projekt wichtig sind.
 * Es enthält das Programm zum Einlesen der Datei in die Datenbank, die Funktion zum Löschen
 * von Leerzeichen am Anfang oder Ende eines Strings, die Methode zum Einlesen und Auswerten
 * der Argumente beim Programmaufruf, die Funktion zum Schreiben des Ergebnisses in die Datei
 * und die Testmodus-Funktion.
 */
public class Funktionen {

	/**
	 * @param dbName
	 * @param db
	 * @return db
	 * Die Funktion ladeDB bekommt den Dateipfad zur Datenbankdatei und
	 * eine Datenbank vom Typ Data als Input.
	 * Es werden alle Daten aus der Datei ausgelesen, die jeweiligen Objekte
	 * erstellt und anschlie�end in die HashMaps der Datenbank db 
	 * eingef�gt.
	 */
	public static Data ladeDB(String dbName, Data db) {
		File datei = new File(dbName);
		
		//pr�fe ob Datei korrekt
		if(!datei.canRead() || !datei.isFile()) {
			System.exit(0);
		}
		
		BufferedReader in = null;
		//Data db = new Data();
		
		try {
			
			in = new BufferedReader(new FileReader(dbName));
			String zeile = null;
			zeile = in.readLine();
			
			//zeilenweises Einlesen der Daten aus datei
			while(zeile != null) {
				
				//Falls Actor-Zeilen
				if(zeile.equals("New_Entity: \"actor_id\",\"actor_name\"")) {
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"movie_id\",\"movie_title\",\"movie_plot\",\"genre_name\",\"movie_released\",\"movie_imdbVotes\",\"movie_imdbRating\"")) {
						
						String[] split = deleteSpace(zeile);
						
						//Actor anlegen und in die entsprechenden Listen packen
						Actor actor = new Actor(Integer.parseInt(split[0]), split[1]);
						db.addActor(actor);
						
					}
				
				//Falls Movie-Zeile
				} else if(zeile.equals("New_Entity: \"movie_id\",\"movie_title\",\"movie_plot\",\"genre_name\",\"movie_released\",\"movie_imdbVotes\",\"movie_imdbRating\"")) {
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"director_id\",\"director_name\"")) {
						
						String[] split = deleteSpace(zeile);
								
						//Movie anlegen und in die entsprechenden Listen packen
						Movie movie = new Movie(Integer.parseInt(split[0]), split[1], split[2], split[3]);
						
						if(split.length > 4 && !split[4].equals("")) 	movie.setRelease(split[4]);
						if(split.length > 5 && !split[5].equals(""))	movie.setImdbAnz(Integer.parseInt(split[5]));
						if(split.length > 6 && !split[6].equals(""))	movie.setImdbRating(Double.parseDouble(split[6]));
						
						db.addMovie(movie);
					
					}
					
				//Falls Director-Zeile
				} else if(zeile.equals("New_Entity: \"director_id\",\"director_name\"")) {
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"actor_id\",\"movie_id\"")) {
						
						String[] split = deleteSpace(zeile);
						
						//Director anlegen
						Director director = new Director(Integer.parseInt(split[0]), split[1]);
						db.addDirector(director);
						
					}
					
				//Falls Actor-Movie Beziehung
				} else if(zeile.equals("New_Entity: \"actor_id\",\"movie_id\"")) {
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"director_id\",\"movie_id\"")) {
						
						String[] split = deleteSpace(zeile);
						
						//Movie zu ActedMovies des Actors hinzuf�gen
						db.actors.get(Integer.parseInt(split[0])).addActedMovie(db.movies.get(Integer.parseInt(split[1])));
						
						//Actor zu Actors des Movies hinzuf�gen
						db.movies.get(Integer.parseInt(split[1])).addActor(db.actors.get(Integer.parseInt(split[0])));
						
					}
					
				//Falls Director-Movie Beziehung
				} else if(zeile.equals("New_Entity: \"director_id\",\"movie_id\"")) {
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"user_name\",\"rating\",\"movie_id\"")) {
						
						String[] split = deleteSpace(zeile);
						
						//Ordne Director Movie zu
						db.movies.get(Integer.parseInt(split[1])).addDirector(db.directors.get(Integer.parseInt(split[0])));
						
						//F�ge Movie in DirectedMovies des Directors ein
						db.directors.get(Integer.parseInt(split[0])).addDirectedMovie(db.movies.get(Integer.parseInt(split[1])));
						
					}
					
				//Falls Bewertung
				} else if(zeile.equals("New_Entity: \"user_name\",\"rating\",\"movie_id\"")) {
					
					while((zeile = in.readLine()) != null) {
						
						String[] split = deleteSpace(zeile);
						
						User user;
						
						if(!db.users.containsKey(split[0])) {
							
							//User anlegen falls noch nicht in db
							user = new User(split[0]);
							db.addUser(user);
							
						} else {
							
							user = db.users.get(split[0]);
							
						}
						
						//Review anlegen
						Review review = new Review(user, Double.parseDouble(split[1]), db.movies.get(Integer.parseInt(split[2])));
						
						//Review User zuordnen
						db.users.get(split[0]).addReview(review);
						
						//Review Movie zuordnen
						db.movies.get(Integer.parseInt(split[2])).addReview(review);
						
					}
					
				}
				
			}
			
		} catch(IOException e) {
			
			e.printStackTrace();
		
		} finally {
			
			if(in != null) {
				
				try {
					
					in.close();
				
				} catch (IOException e) {
					
					e.printStackTrace();
				
				}
				
			}
			
		}
		
		return db;
		
	}
	
	/**
	 * @param input
	 * @return split1
	 * Die Funktion deleteSpace bekommt den Inhalt einer Zeile als String,
	 * teilt diesen in einzelne Strings auf, um die einzelnen Daten der
	 * Liste zu bekommen und entfernt anschlie�end noch die Leerzeichen am
	 * Anfang und Ende eines Strings.
	 * Sie gibt einen Array an Strings zur�ck mit den einzelnen Teilen.
	 */
	public static String[] deleteSpace(String input) {
		
		String[] split = input.substring(1, input.length()-1).split("\",\"");
		String[] split1 = new String[split.length];
		
		for(int i = 0; i < split.length; i++) {
			
			split1[i] = split[i].trim();
			
		}
		
		return split1;
		
	}
	
	/**
	 * @param args
	 * @param db
	 * @return empfehlungenFinal
	 * Die Methode readArgs filtert die Argumente des Aufruf der Main Methode heraus
	 * und ruft die entsprechenden Empfehlungs-Funktionen auf und gibt anschließend eine
	 * Liste mit den priorisierten Filmen zurück.
	 */
	public static ArrayList<Movie> readArgs(String[] args, Data db) {
		
		int limit = 200;
		ArrayList<ArrayList<Movie>> listen = new ArrayList<>();
		ArrayList<Movie> moviesContains = new ArrayList<>();
		ArrayList<Movie> empfehlungen = new ArrayList<>();
		ArrayList<Movie> prio1 = new ArrayList<>();
		ArrayList<Movie> prio2 = new ArrayList<>();
		ArrayList<Movie> prio3 = new ArrayList<>();
		ArrayList<Movie> prio4 = new ArrayList<>();
		
		//Falls ein Limit angegeben wird, wird dieses in die Variable limit geschrieben
		for (String string : args) {
			
			if(string.toLowerCase().contains("--limit=")) {
				
				String[] split = string.split("=");
				limit = Integer.parseInt(split[1]);
				
			}
			
		}
		
		//Die verschiedenen Argumente werden untersucht und die jeweilige Empfehlungs-Funktion wird aufgerufen
		//Die einzelnen Ergebnis-Listen werden in eine Liste mit Listen gepackt
		for (String string : args) {
			
			if (string.toLowerCase().contains("--genre=")) {
				
				String[] split = string.split("=");
				listen.add(Empfehlung.getEmpfehlungByGenre(split[1], db, limit));
				
			}
			
			if (string.toLowerCase().contains("--actor=")) {
				
				String[] split = string.split("=");
				listen.add(Empfehlung.getEmpfehlungByActor(split[1], db, limit));
				
			}
			
			if (string.toLowerCase().contains("--director=")) {
				
				String[] split = string.split("=");
				listen.add(Empfehlung.getEmpfehlungByDirector(split[1], db, limit));
				
			}
			
			if (string.toLowerCase().contains("--film=")) {
				
				String[] split = string.split("=");
				listen.add(Empfehlung.getEmpfehlungByFilm(split[1], db, limit));
				
				//Sollte das Argument --film= angegeben sein, werden Filme, deren Name das Argument beinhalten in eine gesonderte Liste moviesContains geschrieben
				for (Movie movie : Empfehlung.getEmpfehlungByFilm(split[1], db, limit)) {
					
					if (movie.getTitle().toLowerCase().contains(split[1].toLowerCase())) {
						
						moviesContains.add(movie);
						
					}
					
				}
				
			}
			
		}
		
		for (Movie movie : listen.get(0)) {
			
			int i = 0;
			
			for (ArrayList<Movie> list : listen) {
				
				if (list.contains(movie)) {
					
					i++;
					
				}
				
			}
				
			//Die Filme werden priorisiert. Je mehr der angegebenen Kriterien sie erfüllen, desto höher die Priorität
			if (i == 4) {
				
				prio1.add(movie);
				
			} else if (i == 3) {
				
				prio2.add(movie);
				
			} else if (i == 2) {
				
				prio3.add(movie);
				
			} else if (i == 1) {
				
				prio4.add(movie);
				
			}
			
		}
		
		//Sortieren der Liste moviesContains nach der Overall Bewertung
		Collections.sort(moviesContains, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				
				if (o1.getOverallRating() < o2.getOverallRating()) {
						
					return 1;
						
				} if (o1.getOverallRating() > o2.getOverallRating()) {
						
					return -1;
						
				} else {
					
					return 0;
						
				}
						
			}
					
		});
		
		//Sortieren der Liste prio1 nach der Overall Bewertung
		Collections.sort(prio1, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				
				if (o1.getOverallRating() < o2.getOverallRating()) {
				
					return 1;
				
				} if (o1.getOverallRating() > o2.getOverallRating()) {
				
					return -1;
				
				} else {
			
					return 0;
				
				}
				
			}
			
		});
		
		//Sortieren der Liste prio2 nach dem Overall Rating
		Collections.sort(prio2, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				
				if (o1.getOverallRating() < o2.getOverallRating()) {
				
					return 1;
				
				} if (o1.getOverallRating() > o2.getOverallRating()) {
				
					return -1;
				
				} else {
			
					return 0;
				
				}
				
			}
			
		});
		
		//Sortieren der Liste prio3 nach der Overall Bewertung
		Collections.sort(prio3, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
						
				if (o1.getOverallRating() < o2.getOverallRating()) {
						
					return 1;
						
				} if (o1.getOverallRating() > o2.getOverallRating()) {
						
					return -1;
						
				} else {
					
					return 0;
						
				}
						
			}
					
		});
		
		//Sortieren der Liste prio4 nach der Overall Bewertung
		Collections.sort(prio4, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
								
				if (o1.getOverallRating() < o2.getOverallRating()) {
								
					return 1;
								
				} if (o1.getOverallRating() > o2.getOverallRating()) {
								
					return -1;
								
				} else {
							
					return 0;
								
				}
								
			}
							
		});
		
		//Zusammenfügen der Listen nach Priorität
		//moviesContains als erstes, da diese die Filme beinhalten mit dem gesuchten Namen (nur falls "--film"-Argument angegeben). Danach nach Prio absteigend
		empfehlungen.addAll(moviesContains);
		empfehlungen.addAll(prio1);
		empfehlungen.addAll(prio2);
		empfehlungen.addAll(prio3);
		empfehlungen.addAll(prio4);
		
		//Lösche die letzten Argumente, falls die Liste länger als das gegebene Limit ist
		if(empfehlungen.size() > limit) {
			
			for (int i = empfehlungen.size() - 1; i >= limit; i--) {
				
				empfehlungen.remove(i);
				
			}
			
		}
		
		return empfehlungen;
		
	}
	
	/**
	 * @param ergebnis
	 * Die Methode schreibeInDatei bekommt eine Liste von Filmen als Argument und schreibt diese 
	 * in die Datei 'ergebnis.txt'. Filme werden mit dem Titel und der Overall Bewertung angegeben.
	 */
	public static void schreibeInDatei(ArrayList<Movie> ergebnis) {

		try {
		
			FileWriter fw = new FileWriter("ergebnis.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			DecimalFormat f = new DecimalFormat("#0.0");
			String n = System.getProperty("line.separator");
			
			for (Movie movie : ergebnis) {
				
				bw.write(movie.getTitle() + ", Bewertung: " + f.format(movie.getOverallRating()) + n);
				System.out.println(movie.getTitle() + ", Bewertung: " + f.format(movie.getOverallRating()));
				
			}
	    
			bw.close();
			
	    } catch (IOException e) {
	    	
	    	e.printStackTrace();
	    	
	    }
		
	}
	
	/**
	 * @param db
	 * Die Methode testModus wird aufgerufen, wenn der Testmodus aktiviert wurde.
	 * Sie startet die 3 vorgegebenen Testaufrufe und schreibt die Ergebnisse 
	 * geordnet in die Datei 'results.txt'
	 */
	public static void testModus(Data db) {
		
		try {
			
			FileWriter fw = new FileWriter("result.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			DecimalFormat f = new DecimalFormat("#0.0");
			String n = System.getProperty("line.separator");
			
			String[] test1 = {"--genre=Thriller", "--film=Matrix Revolutions", "--limit=10"};
			String[] test2 = {"--film=Indiana Jones and the Temple of Doom", "--genre=Adventure", "--limit=15"};
			String[] test3 = {"--actor=Jason Statham,Keanu Reeves", "--genre=Action", "--limit=50"};
			
			bw.write("Ergebnis 1. Abfrage:" + n + n);
			
			for (Movie movie : readArgs(test1, db)) {
				
				bw.write(movie.getTitle() + ", Bewertung: " + f.format(movie.getOverallRating()) + n);
				
			}
			
			bw.write(n + n + "Ergebnis 2. Abfrage:" + n + n);
			
			for (Movie movie : readArgs(test2, db)) {
				
				bw.write(movie.getTitle() + ", Bewertung: " + f.format(movie.getOverallRating()) + n);
				
			}
			
			bw.write(n + n + "Ergebnis 3. Abfrage:" + n + n);
			
			for (Movie movie : readArgs(test3, db)) {
				
				bw.write(movie.getTitle() + ", Bewertung: " + f.format(movie.getOverallRating()) + n);
				
			}
			
			bw.close();
			
		} catch (IOException e) {
	    	
	    	e.printStackTrace();
	    	
	    }
		
	}
	
}
