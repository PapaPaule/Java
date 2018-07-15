package V1;

import java.util.*;

/**
 * @author MaderP
 * Die Klasse Director definiert einen Director mit einer ID,
 * einem Namen und einer Liste an Filmen, die von ihm directed 
 * wurden.
 */
public class Director {

	protected int id;
	protected String name;
	protected List<Movie> directedMovies;
	
	/**
	 * @param id
	 * @param name
	 * Legt einen neuen Director an mit der ID und dem Namen.
	 * Es wird eine neue leere Liste directedMovies angelegt,
	 * in der Filme vom Typen Movie gespeichert werden können.
	 */
	public Director(int id, String name) {
		this.id = id;
		this.name = name;
		this.directedMovies = new ArrayList<Movie>();
	}
	
	/**
	 * @return id
	 * Gibt die ID des Directors zurück.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return name
	 * Gibt den Namen des Directors zurück.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return directedMovies
	 * Gibt die Liste an Filmen zurück, die vom Director
	 * directed wurden.
	 */
	public List<Movie> getDirectedMovies() {
		return this.directedMovies;
	}
	
	/**
	 * @param movie
	 * Fügt der Liste directedMovies einen neuen Film
	 * vom Typ Movie hinzu.
	 */
	public void addDirectedMovie(Movie movie) {
		this.directedMovies.add(movie);
	}
	
}
