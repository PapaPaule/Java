package V1;

import java.util.*;

/**
 * @author MaderP
 * Die Klasse Actor beschreibt einen Schauspieler mit einer
 * ID, einem Namen und einer Liste an Movies, in denen er
 * mitgespielt hat.
 */
public class Actor {

	protected int id;
	protected String name;
	protected ArrayList<Movie> actedMovies;
	
	/**
	 * @param id
	 * @param name
	 * Legt einen neuen Actor mit einer ID und einem
	 * Namen an. Es wird eine neue leere Liste actedMovies
	 * angelegt, in der Filme vom Typ Movie gespeichert 
	 * werden k�nnen.
	 */
	public Actor(int id, String name) {
		this.id = id;
		this.name = name;
		this.actedMovies = new ArrayList<Movie>();
	}
	
	/**
	 * @return id
	 * Gibt die ID zur�ck.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return name
	 * Gibt den Namen zur�ck.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return actedMovies
	 * Gibt die Liste an Movies zur�ck, in denen
	 * der Actor mitgespielt hat.
	 */
	public ArrayList<Movie> getActedMovies() {
		return this.actedMovies;
	}
	
	/**
	 * @param movie
	 * F�gt der Liste actedMovies einen neuen Film
	 * vom Typ Movie hinzu.
	 */
	public void addActedMovie(Movie movie) {
		this.actedMovies.add(movie);
	}
	
}
