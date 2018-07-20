package V1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	 * @param db
	 * Druckt einen gewissen Teil der Datenbank db.
	 */
	public static void printDb(Data db) {
		
		for(Movie movie: db.movies.values()) {
			System.out.print(movie.getTitle() + ": " + movie.getPlot() + ", Genre: " + movie.getGenre() + ", by ");
			for(Director director: movie.directors) {
				System.out.print(director.getName() + ", ");
			}
			System.out.print(movie.getOverallRating() + "\n");
		}
		
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
	
}
