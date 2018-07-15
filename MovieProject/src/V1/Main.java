package V1;

import java.io.*;

/**
 * @author MaderP
 * In der Main-Klasse werden alle wichtigen Funktionen definiert.
 */
public class Main {
	
	/**
	 * @param input
	 * @return split1
	 * Die Funktion deleteSpace bekommt den Inhalt einer Zeile als String,
	 * teilt diesen in einzelne Strings auf, um die einzelnen Daten der
	 * Liste zu bekommen und entfernt anschließend noch die Leerzeichen am
	 * Anfang und Ende eines Strings.
	 * Sie gibt einen Array an Strings zurück mit den einzelnen Teilen.
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
	 * @param dbName
	 * @param db
	 * @return db
	 * Die Funktion ladeDB bekommt den Dateipfad zur Datenbankdatei und
	 * eine Datenbank vom Typ Data als Input.
	 * Es werden alle Daten aus der Datei ausgelesen, die jeweiligen Objekte
	 * erstellt und anschließend in die HashMaps der Datenbank db 
	 * eingefügt.
	 */
	public static Data ladeDB(String dbName, Data db) {
		File datei = new File(dbName);
		
		//prüfe ob Datei korrekt
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
						if(split.length > 5 && !split[5].equals(""))	movie.setReviewAnz(Integer.parseInt(split[5]));
						if(split.length > 6 && !split[6].equals(""))	movie.setRating(Double.parseDouble(split[6]));
						
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
						
						//Movie zu ActedMovies des Actors hinzufügen
						db.actors.get(Integer.parseInt(split[0])).addActedMovie(db.movies.get(Integer.parseInt(split[1])));
						
						//Actor zu Actors des Movies hinzufügen
						db.movies.get(Integer.parseInt(split[1])).addActor(db.actors.get(Integer.parseInt(split[0])));
						
					}
					
				//Falls Director-Movie Beziehung
				} else if(zeile.equals("New_Entity: \"director_id\",\"movie_id\"")) {
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"user_name\",\"rating\",\"movie_id\"")) {
						
						String[] split = deleteSpace(zeile);
						
						//Ordne Director Movie zu
						db.movies.get(Integer.parseInt(split[1])).setDirector(db.directors.get(Integer.parseInt(split[0])));
						
						//Füge Movie in DirectedMovies des Directors ein
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
	 * @param db
	 * Druckt einen gewissen Teil der Datenbank db.
	 */
	public static void printDb(Data db) {
		
		for(Movie movie: db.movies.values()) {
			System.out.print(movie.getTitle() + ": " + movie.getPlot() + ", with: ");
			for(Actor actor: movie.actors) {
				System.out.print(actor.getName() + ", ");
			}
			System.out.print(movie.getRating() + ", Anz: " + movie.reviewAnz + "\n");
		}
		
	}
	
	/**
	 * @param args
	 * Beinhaltet den Ablauf aller Funktionen.
	 */
	public static void main(String[] args) {
		String dbName = "./src//movieproject.db";
		Data db = new Data();
		db = ladeDB(dbName, db);
		printDb(db);
	}

}