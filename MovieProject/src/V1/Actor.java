package V1;

import java.util.*;

public class Actor {

	protected int id;
	protected String name;
	protected List<Movie> actedMovies;
	
	public Actor(int id, String name) {
		this.id = id;
		this.name = name;
		this.actedMovies = new ArrayList<Movie>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Movie> getActedMovies() {
		return this.actedMovies;
	}
	
	public void addActedMovie(Movie movie) {
		this.actedMovies.add(movie);
	}
	
}
