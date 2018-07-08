package V1;

import java.util.*;

public class Movie {
	
	protected int id;
	protected String title;
	protected String plot;
	protected String genre;
	protected String release;
	protected Director director;
	protected List<Actor> actors;
	protected List<Review> reviews;
	protected int reviewAnz;
	protected double rating;
	
	
	public Movie(int id, String title, String plot, String genre, String release, Director director, int reviewAnz, double rating) {
		this.id = id;
		this.title = title;
		this.plot = plot;
		this.genre = genre;
		this.release = release;
		this.director = director;
		this.reviewAnz = reviewAnz;
		this.rating = rating;
		this.actors = new ArrayList<Actor>();
		this.reviews = new ArrayList<Review>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getPlot() {
		return this.plot;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public String getRelease() {
		return this.release;
	}
	
	public Director getDirector() {
		return this.director;
	}
	
	public int getReviewAnz() {
		return this.reviewAnz;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public List<Actor> getActors() {
		return this.actors;
	}
	
	public List<Review> getReviews() {
		return this.reviews;
	}
	
}
