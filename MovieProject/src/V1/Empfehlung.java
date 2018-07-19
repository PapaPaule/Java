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
	 * zurück. Zuerst werden alle Filme der angegebenen Genres in eine Liste geschrieben
	 * und anschließend nach dem Overall Rating (60% User Rating - 40% IMDB Rating)
	 * sortiert. Am Ende wird die Liste auf das angegebene Limit reduziert.
	 */
	public static ArrayList<Movie> getEmpfehlungByGenre(String genre, Data db, int limit) {
		
		//splitte die einzelnen Genres
		String[] split = genre.split(",");
		ArrayList<Movie> movies = new ArrayList<>();
		
		
		for (int i = 0; i < split.length; i++) {
			
			for (Movie movie : db.getMovies().values()) {
				
				//suche alle Filme des Genres mit einer Overall Bewertung von mehr als 2.5 und füge sie der Liste movies hinzu
				if(movie.getGenre().toLowerCase().equals(split[i].toLowerCase()) && movie.getOverallRating() > 2.5) {
					
					movies.add(movie);
				
				}
				
			}
			
		}
		
		Collections.sort(movies, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				
				if(o1.getOverallRating() > o2.getOverallRating()) {
					return 1;
				} if(o1.getOverallRating() > o2.getOverallRating()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		ArrayList<Movie> moviesFinal = new ArrayList<>();
		
		if(limit > movies.size()) {
			for(int i = 0; i < limit; i++) {
			
				moviesFinal.add(movies.get(i));
			
			}
			
			return moviesFinal;
			
		} else {
		
			return movies;
		
		}
		
	}
	
	

}
