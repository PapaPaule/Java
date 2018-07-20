package V1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author MaderP
 * Die Klasse Empfehlung beinhaltet alle Empfehlungsalgorithmen.
 */
public class Empfehlung {

	/**
	 * @param genre
	 * @param db
	 * @param limit
	 * @return movies / moviesFinal
	 * Die Funktion getEmpfehlungByGenre gibt Filmempfehlungen nach dem Genre sortiert
	 * zur�ck. Zuerst werden alle Filme der angegebenen Genres in eine Liste geschrieben
	 * und anschlie�end nach dem Overall Rating (60% User Rating - 40% IMDB Rating)
	 * sortiert. Am Ende wird die Liste auf das angegebene Limit reduziert.
	 */
	public static ArrayList<Movie> getEmpfehlungByGenre (String genre, Data db, int limit) {
		
		//splitte die einzelnen Genres
		String[] genres = genre.split(",");
		ArrayList<Movie> movies = new ArrayList<>();
		
		
		for (int i = 0; i < genres.length; i++) {
			
			for (Movie movie : db.getMovies().values()) {
				
				//suche alle Filme des Genres mit einer Overall Bewertung von mehr als 2.5 und f�ge sie der Liste movies hinzu
				if (movie.getGenre().toLowerCase().equals(genres[i].toLowerCase()) && movie.getOverallRating() > 2.5) {
					
					movies.add(movie);
				
				}
				
			}
			
		}
		
		//Sortieren der Filme in der Liste movies nach Overall Rating
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				
				if (o1.getOverallRating() > o2.getOverallRating()) {
					
					return 1;
					
				} if (o1.getOverallRating() > o2.getOverallRating()) {
					
					return -1;
			
				} else {
				
					return 0;
			
				}
			}
		});
		
		//Falls Liste länger als Limit, füge der neuen Liste die ersten [Limit] Einträge hinzu, falls nicht, gebe die Ursprungsliste zurück
		if (limit < movies.size()) {
			
			ArrayList<Movie> moviesFinal = new ArrayList<>();
			
			for (int i = 0; i < limit; i++) {
			
				moviesFinal.add(movies.get(i));
			
			}
			
			return moviesFinal;
			
		} else {
		
			return movies;
		
		}
		
	}
	
	/**
	 * @param in
	 * @param db
	 * @param limit
	 * @return movies / moviesFinal
	 * Die Methode getEmpfehlungenByActor gibt Filmempfehlungen für gegebene Schauspieler zurück.
	 * Es werden alle Filme herausgesucht, in denen der oder die Schauspieler mitspielen
	 * und diese werden nach dem Overallrating sortiert und ggf. an das Limit an Einträgen
	 * angepasst.
	 */
	public static ArrayList<Movie> getEmpfehlungByActor (String in, Data db, int limit) {
		
		//Splitte die einzelnen Namen
		String[] actors = in.split(",");
		ArrayList<Movie> movies = new ArrayList<>();
		
		for (int i = 0; i < actors.length; i++) {
			
			for (Actor actor : db.getActors().values()) {
				
				//Suche die genannten Schauspieler und füge die Liste actedMovies zu den Empfehlungsfilmen hinzu
				if (actor.getName().toLowerCase().equals(actors[i].toLowerCase())) {
					
					movies.addAll(actor.getActedMovies());
					
				}
				
			}
			
		}
		
		//Doppelte Einträge werden gesucht und gelöscht
		for (int i = 0; i < movies.size(); i++) {
			
			for (int j = i + 1; j < movies.size(); j++) {
				
				if (movies.get(i).getId() == movies.get(j).getId()) {
					
					movies.remove(j);
					j = j - 1;
					
				}
				
			}
			
		}
		
		//Nach Overall Rating sortieren
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				
				if (o1.getOverallRating() > o2.getOverallRating()) {
			
					return 1;

				} if (o1.getOverallRating() > o2.getOverallRating()) {
				
					return -1;
				
				} else {
				
					return 0;
				
				}
				
			}
			
		});
		
		//Die Liste wird an das gegebene Limit angepasst
		if(limit < movies.size()) {
			
			ArrayList<Movie> moviesFinal = new ArrayList<>();
			
			for (int i = 0; i < limit; i++) {
				
				moviesFinal.add(movies.get(i));
				
			}
			
			return moviesFinal;
			
		} else {
			
			return movies;
			
		}
		
	}
	
	/**
	 * @param in
	 * @param db
	 * @param limit
	 * @return movies / moviesFinal
	 * Die Methode getEmpfehlungByDirector sucht nach den gegebenen Regisseuren
	 * und f�gt alle Filme, die von diesen directed wurden einer Liste hinzu,
	 * die nach dem Overall Rating sortiert wird und anschlie�end zur�ck 
	 * gegeben wird.
	 */
	public static ArrayList<Movie> getEmpfehlungByDirector (String in, Data db, int limit) {
		
		//Splitte die gegebenen Regisseure
		String[] directors = in.split(",");
		ArrayList<Movie> movies = new ArrayList<>();
		
		for (int i = 0; i < directors.length; i++) {
			
			 for (Director director : db.getDirectors().values()) {
				
				 //Suche nach den gesuchten Regisseuren und füge die Liste directedMovies zur Empfehlungsliste hinzu.
				 if (director.getName().toLowerCase().equals(directors[i].toLowerCase())) {
					 
					 movies.addAll(director.getDirectedMovies());
					 
				 }
				 
			}
			
		}
		
		//Doppelte Einträge werden gesucht und gelöscht
		for (int i = 0; i < movies.size(); i++) {
					
			for (int j = i + 1; j < movies.size(); j++) {
				
				if (movies.get(i).getId() == movies.get(j).getId()) {
							
					movies.remove(j);
					j = j - 1;
						
				}
						
			}
					
		}
		
		//Liste mit Empfehlungen nach Overall Rating sortieren
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				
				if (o1.getOverallRating() > o2.getOverallRating()) {
				
					return 1;
				
				} if (o1.getOverallRating() > o2.getOverallRating()) {
				
					return -1;
				
				} else {
			
					return 0;
				
				}
				
			}
			
		});
		
		//Liste an das Limit anpassen
		if (limit < movies.size()) {
			
			ArrayList<Movie> moviesFinal = new ArrayList<>();
			
			for (int i = 0; i < limit; i++) {
				
				moviesFinal.add(movies.get(i));
				
			}
			
			return moviesFinal;
			
		} else {
			
			return movies;
			
		}
		
	}

}
