package V1;

import java.util.*;

/**
 * @author MaderP
 * Die Klasse Data beschreibt eine Datenbank, in der
 * alle Daten gespeichert werden. Sie besteht aus einer
 * HashMap movies, einer HashMap actors, einer HashMap
 * directors und einer HashMap users.
 */
public class Data {

	protected HashMap<Integer, Movie> movies;
	protected HashMap<Integer, Actor> actors;
	protected HashMap<Integer, Director> directors;
	protected HashMap<String, User> users;
	
	/**
	 * Legt eine neue Datenbank mit den leeren HashMaps movies (Integer, Movie),
	 * actors (Integer, Actor), directors (Integer, Director) und 
	 * users (String, User) an.
	 */
	public Data() {
		this.movies = new HashMap<Integer, Movie>();
		this.actors = new HashMap<Integer, Actor>();
		this.directors = new HashMap<Integer, Director>();
		this.users = new HashMap<String, User>();
	}
	
	/**
	 * @return movies
	 * Gibt die Liste mit allen Filmen vom Typ Movie zurück.
	 */
	public HashMap<Integer, Movie> getMovies() {
		return this.movies;
	}
	
	/**
	 * @return actors
	 * Gibt die Liste mit allen Schauspielern vom Typ Actor zurück.
	 */
	public HashMap<Integer, Actor> getActors() {
		return this.actors;
	}
	
	/**
	 * @return directors
	 * Gibt die Liste mit allen Regisseuren vom Typ Director zurück.
	 */
	public HashMap<Integer, Director> getDirectors() {
		return this.directors;
	}
	
	/**
	 * @return users
	 * Gibt die Liste mit allen Benutzern vom Typ User zurück.
	 */
	public HashMap<String, User> getUsers() {
		return this.users;
	}
	
	/**
	 * @param movie
	 * Fügt der HashMap movies einen neuen Film hinzu.
	 * Als Key wird die Movie-ID benutzt und als Value das Objekt Movie.
	 */
	public void addMovie(Movie movie) {
		this.movies.put(movie.id, movie);
	}
	
	/**
	 * @param actor
	 * Fügt der HashMap actors einen neuen Schauspieler hinzu.
	 * Als Key wird die Actor-ID und als Value das Objekt Actor benutzt.
	 */
	public void addActor(Actor actor) {
		this.actors.put(actor.id, actor);
	}
	
	/**
	 * @param director
	 * Fügt der HashMap directors einen neuen Regisseur hinzu.
	 * Als Key wird die Director-ID und als Value das Objekt Director benutzt.
	 */
	public void addDirector(Director director) {
		this.directors.put(director.id, director);
	}
	
	/**
	 * @param user
	 * Fügt der HashMap users einen neuen Benutzer hinzu.
	 * Als Key wird der User-Name und als Value das Objekt User benutzt.
	 */
	public void addUser(User user) {
		this.users.put(user.name, user);
	}
	
}
