package V1;

import java.util.*;

/**
 * @author MaderP
 * Die Klasse Movie definiert einen Film. Ein Film beinhaltet
 * die Film-ID, den Titel, die Beschreibung (Plot), das Genre,
 * das Release-Datum, den Director, eine Liste mit Schauspielern,
 * die mitspielen, eine Liste an Reviews �ber den Film, die Anzahl
 * an Reviews und das durchschnittliche Rating.
 */
public class Movie {
	
	protected int id;
	protected String title;
	protected String plot;
	protected String genre;
	protected String release;
	protected ArrayList<Director> directors;
	protected ArrayList<Actor> actors;
	protected ArrayList<Review> reviews;
	protected int userRatingAnz;
	protected Double userRating;
	protected int imdbAnz;
	protected Double imdbRating;
	protected Double overallRating;
	
	
	/**
	 * @param id
	 * @param title
	 * @param plot
	 * @param genre
	 * Legt einen neuen Film an mit ID, Titel, Plot und Genre.
	 * Es wird eine neue leere Liste actors f�r Schauspieler des Typs Actors,
	 * eine leere Liste reviews f�r Bewertungen des Typs Review
	 * und eine leere Liste directors f�r Regisseure des Typs Director
	 * angelegt. Die Bewertung uns Anzahl der Bewertungen wird mit 
	 * Null initialisiert.
	 */
	public Movie(int id, String title, String plot, String genre) {
		
		String[] titleSplit = title.split(",");
		if (titleSplit.length == 2) {
			
			this.title = (titleSplit[1] + " " + titleSplit[0]).trim();
			
		} else {
			
			this.title = title;
			
		}
		
		this.id = id;
		this.plot = plot;
		this.genre = genre;
		this.actors = new ArrayList<Actor>();
		this.reviews = new ArrayList<Review>();
		this.directors = new ArrayList<Director>();
		this.userRating = 0.0;
		this.userRatingAnz = 0;
		this.overallRating = 0.0;
		
	}
	
	/**
	 * @return id
	 * Gibt die Movie-ID zur�ck.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return title
	 * Gibt den Film-Titel zur�ck.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * @return plot
	 * Gibt die Beschreibung des Films zur�ck.
	 */
	public String getPlot() {
		return this.plot;
	}
	
	/**
	 * @return genre
	 * Gibt das Genre des Films zur�ck.
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * @return release
	 * Gibt das Releasedatum zur�ck.
	 */
	public String getRelease() {
		return this.release;
	}
	
	/**
	 * @return director
	 * Gibt den Director des Films zur�ck.
	 */
	public ArrayList<Director> getDirectors() {
		return this.directors;
	}
	
	/**
	 * @return reviewAnz
	 * Gibt die Anzahl an Reviews zur�ck.
	 */
	public int getImdbAnz() {
		return this.imdbAnz;
	}
	
	/**
	 * @return rating
	 * Gibt das durchschnittliche Rating zur�ck.
	 */
	public Double getImdbRating() {
		return this.imdbRating;
	}
	
	/**
	 * @return actors
	 * Gibt die Liste an Schauspielern des Films zur�ck.
	 */
	public ArrayList<Actor> getActors() {
		return this.actors;
	}
	
	/**
	 * @return reviews
	 * Gibt eine liste an Reviews des Films zur�ck.
	 */
	public ArrayList<Review> getReviews() {
		return this.reviews;
	}
	
	/**
	 * @return userRatingAnz
	 * Gibt die Anzahl der User Ratings zurück.
	 */
	public int getUserRatingAnz() {
		return this.userRatingAnz;
	}
	
	/**
	 * @return userRating
	 * Gibt die durchschnittliche User Bewertung zurück.
	 */
	public Double getUserRating() {
		return this.userRating;
	}
	
	/**
	 * @return overallRating
	 * Gibt die Bewertung des Films (60% User Rating - 40% IMDB Rating) zur�ck.
	 */
	public Double getOverallRating() {
		return this.overallRating;
	}
	
	/**
	 * @param actor
	 * F�gt actor der Liste an Schauspielern hinzu.
	 */
	public void addActor(Actor actor) {
		this.actors.add(actor);
	}
	
	/**
	 * @param director
	 * F�gt einen Regisseur vom Typ Director der Liste directors hinzu.
	 */
	public void addDirector(Director director) {
		this.directors.add(director);
	}
	
	/**
	 * @param release
	 * Legt das Releasedatum fest.
	 */
	public void setRelease(String release) {
		this.release = release;
	}
	
	/**
	 * @param anz
	 * Legt die Anzhal an Reviews fest.
	 */
	public void setImdbAnz(int anz) {
		this.imdbAnz = anz;
	}
	
	/**
	 * @param rating
	 * Legt das durchschnittliche Rating des Films fest.
	 */
	public void setImdbRating(Double rating) {
		this.imdbRating = rating;
		this.overallRating = this.imdbRating / 2;
	}
	
	/**
	 * @param review
	 * F�gt ein neues Review zur Review-Liste hinzu,
	 * �berarbeitet das Rating und erh�ht die Reviewanzahl.
	 * Au�erdem wird das Overall Rating angelegt oder �berarbeitet.
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
		this.userRating = ( this.userRating * this.userRatingAnz + review.getRating() ) / ( this.userRatingAnz + 1);
		this.userRatingAnz++;
		
		if(this.imdbRating != null) {
			this.overallRating = (this.imdbRating / 2) * 0.4 + this.userRating * 0.6;
		} else {
			this.overallRating = this.userRating;
		}
	}
	
}