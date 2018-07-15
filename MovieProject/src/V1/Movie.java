package V1;

import java.util.*;

/**
 * @author MaderP
 * Die Klasse Movie definiert einen Film. Ein Film beinhaltet
 * die Film-ID, den Titel, die Beschreibung (Plot), das Genre,
 * das Release-Datum, den Director, eine Liste mit Schauspielern,
 * die mitspielen, eine Liste an Reviews über den Film, die Anzahl
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
	 * Es wird eine neue leere Liste actors für Schauspieler des Typs Actors,
	 * eine leere Liste reviews für Bewertungen des Typs Review
	 * und eine leere Liste directors für Regisseure des Typs Director
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
	 * Gibt die Movie-ID zurück.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return title
	 * Gibt den Film-Titel zurück.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * @return plot
	 * Gibt die Beschreibung des Films zurück.
	 */
	public String getPlot() {
		return this.plot;
	}
	
	/**
	 * @return genre
	 * Gibt das Genre des Films zurück.
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * @return release
	 * Gibt das Releasedatum zurück.
	 */
	public String getRelease() {
		return this.release;
	}
	
	/**
	 * @return director
	 * Gibt den Director des Films zurück.
	 */
	public List<Director> getDirector() {
		return this.directors;
	}
	
	/**
	 * @return reviewAnz
	 * Gibt die Anzahl an Reviews zurück.
	 */
	public int getReviewAnz() {
		return this.reviewAnz;
	}
	
	/**
	 * @return rating
	 * Gibt das durchschnittliche Rating zurück.
	 */
	public double getRating() {
		return this.rating;
	}
	
	/**
	 * @return actors
	 * Gibt die Liste an Schauspielern des Films zurück.
	 */
	public List<Actor> getActors() {
		return this.actors;
	}
	
	/**
	 * @return reviews
	 * Gibt eine liste an Reviews des Films zurück.
	 */
	public List<Review> getReviews() {
		return this.reviews;
	}
	
	/**
	 * @param actor
	 * Fügt actor der Liste an Schauspielern hinzu.
	 */
	public void addActor(Actor actor) {
		this.actors.add(actor);
	}
	
	/**
	 * @param director
	 * Fügt einen Regisseur vom Typ Director der Liste directors hinzu.
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
	 * Fügt ein neues Review zur Review-Liste hinzu,
	 * überarbeitet das Rating und erhöht die Reviewanzahl.
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
		this.rating = (this.reviewAnz * this.rating + review.rating) / (this.reviewAnz + 1);
		this.reviewAnz++;
	}
	
}