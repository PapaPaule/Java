package V1;

import java.util.*;

public class Data {

	protected HashMap<Integer, Movie> movies;
	protected HashMap<Integer, Actor> actors;
	protected HashMap<Integer, Director> directors;
	protected HashMap<String, User> users;
	
	public Data() {
		this.movies = new HashMap<Integer, Movie>();
		this.actors = new HashMap<Integer, Actor>();
		this.directors = new HashMap<Integer, Director>();
		this.users = new HashMap<String, User>();
	}
	
	public HashMap<Integer, Movie> getMovies() {
		return this.movies;
	}
	
	public HashMap<Integer, Actor> getActors() {
		return this.actors;
	}
	
	public HashMap<Integer, Director> getDirectors() {
		return this.directors;
	}
	
	public HashMap<String, User> getUsers() {
		return this.users;
	}
	
	public void addMovie(Movie movie) {
		this.movies.put(movie.id, movie);
	}
	
	public void addActor(Actor actor) {
		this.actors.put(actor.id, actor);
	}
	
	public void addDirector(Director director) {
		this.directors.put(director.id, director);
	}
	
	public void addUser(User user) {
		this.users.put(user.name, user);
	}
	
}
