package V1;

import java.util.*;

public class Data {

	protected HashMap<Integer, Movie> movies;
	protected HashMap<Integer, Actor> actors;
	protected HashMap<Integer, Director> directors;
	protected HashMap<String, User> users;
	protected HashMap<String, Review> reviews;
	
	public Data() {
		this.movies = new HashMap<Integer, Movie>();
		this.actors = new HashMap<Integer, Actor>();
		this.directors = new HashMap<Integer, Director>();
		this.users = new HashMap<String, User>();
		this.reviews = new HashMap<String, Review>();
	}
	
}
