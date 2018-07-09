package V1;

import java.util.*;

public class Director {

	protected int id;
	protected String name;
	protected List<Movie> directedMovies;
	
	public Director(int id, String name) {
		this.id = id;
		this.name = name;
		this.directedMovies = new ArrayList<Movie>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Movie> getDirectedMovies() {
		return this.directedMovies;
	}
	
	public void addDirectedMovie(Movie movie) {
		this.directedMovies.add(movie);
	}
	
}
