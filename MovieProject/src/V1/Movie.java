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
	protected List<Director> directors;
	protected List<Actor> actors;
	protected List<Review> reviews;
	protected int reviewAnz;
	protected double rating;
	
	
	/**
	 * @param id
	 * @param title
	 * @param plot
	 * @param genre
	 * Legt einen neuen Film an mit ID, Titel, Plot und Genre.
	 * Es wird eine neue leere Liste actors f�r Schauspieler des Typs Actors,
	 * eine leere Liste reviews f�r Bewertungen des Typs Review
	 * und eine leere Liste directors f�r Regisseure des Typs Director
	 * angelegt.
	 */
	public Movie(int id, String title, String plot, String genre) {
		this.id = id;
		this.title = title;
		this.plot = plot;
		this.genre = genre;
		this.actors = new ArrayList<Actor>();
		this.reviews = new ArrayList<Review>();
		this.directors = new ArrayList<Director>();
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
	public List<Director> getDirector() {
		return this.directors;
	}
	
	/**
	 * @return reviewAnz
	 * Gibt die Anzahl an Reviews zur�ck.
	 */
	public int getReviewAnz() {
		return this.reviewAnz;
	}
	
	/**
	 * @return rating
	 * Gibt das durchschnittliche Rating zur�ck.
	 */
	public double getRating() {
		return this.rating;
	}
	
	/**
	 * @return actors
	 * Gibt die Liste an Schauspielern des Films zur�ck.
	 */
	public List<Actor> getActors() {
		return this.actors;
	}
	
	/**
	 * @return reviews
	 * Gibt eine liste an Reviews des Films zur�ck.
	 */
	public List<Review> getReviews() {
		return this.reviews;
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
	public void setReviewAnz(int anz) {
		this.reviewAnz = anz;
	}
	
	/**
	 * @param rating
	 * Legt das durchschnittliche Rating des Films fest.
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	/**
	 * @param review
	 * F�gt ein neues Review zur Review-Liste hinzu,
	 * �berarbeitet das Rating und erh�ht die Reviewanzahl.
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
		this.rating = (this.reviewAnz * this.rating + review.rating) / (this.reviewAnz + 1);
		this.reviewAnz++;
	}
	
}