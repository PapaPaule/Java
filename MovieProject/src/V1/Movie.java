package V1;

import java.util.*;

/**
 * @author MaderP
 * Die Klasse Movie definiert einen Film. Ein Film beinhaltet
 * die Film-ID, den Titel, die Beschreibung (Plot), das Genre,
 * das Release-Datum, den Director, eine Liste mit Schauspielern,
 * die mitspielen, eine Liste an Reviews ï¿½ber den Film, die Anzahl
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
	 * Es wird eine neue leere Liste actors fï¿½r Schauspieler des Typs Actors,
	 * eine leere Liste reviews fï¿½r Bewertungen des Typs Review
	 * und eine leere Liste directors fï¿½r Regisseure des Typs Director
	 * angelegt. Die Bewertung uns Anzahl der Bewertungen wird mit 
	 * Null initialisiert.
	 */
	public Movie(int id, String title, String plot, String genre) {
		this.id = id;
		this.title = title;
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
	 * Gibt die Movie-ID zurï¿½ck.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return title
	 * Gibt den Film-Titel zurï¿½ck.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * @return plot
	 * Gibt die Beschreibung des Films zurï¿½ck.
	 */
	public String getPlot() {
		return this.plot;
	}
	
	/**
	 * @return genre
	 * Gibt das Genre des Films zurï¿½ck.
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * @return release
	 * Gibt das Releasedatum zurï¿½ck.
	 */
	public String getRelease() {
		return this.release;
	}
	
	/**
	 * @return director
	 * Gibt den Director des Films zurï¿½ck.
	 */
	public List<Director> getDirector() {
		return this.directors;
	}
	
	/**
	 * @return reviewAnz
	 * Gibt die Anzahl an Reviews zurï¿½ck.
	 */
	public int getImdbAnz() {
		return this.imdbAnz;
	}
	
	/**
	 * @return rating
	 * Gibt das durchschnittliche Rating zurï¿½ck.
	 */
	public Double getImdbRating() {
		return this.imdbRating;
	}
	
	/**
	 * @return actors
	 * Gibt die Liste an Schauspielern des Films zurï¿½ck.
	 */
	public List<Actor> getActors() {
		return this.actors;
	}
	
	/**
	 * @return reviews
	 * Gibt eine liste an Reviews des Films zurï¿½ck.
	 */
	public List<Review> getReviews() {
		return this.reviews;
	}
	
	/**
	 * @return userRatingAnz
	 * Gibt die Anzahl der User Ratings zurÃ¼ck.
	 */
	public int getUserRatingAnz() {
		return this.userRatingAnz;
	}
	
	/**
	 * @return userRating
	 * Gibt die durchschnittliche User Bewertung zurÃ¼ck.
	 */
	public Double getUserRating() {
		return this.userRating;
	}
	
	/**
	 * @return overallRating
	 * Gibt die Bewertung des Films (60% User Rating - 40% IMDB Rating) zurück.
	 */
	public Double getOverallRating() {
		return this.overallRating;
	}
	
	/**
	 * @param actor
	 * Fï¿½gt actor der Liste an Schauspielern hinzu.
	 */
	public void addActor(Actor actor) {
		this.actors.add(actor);
	}
	
	/**
	 * @param director
	 * Fï¿½gt einen Regisseur vom Typ Director der Liste directors hinzu.
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
	 * Fï¿½gt ein neues Review zur Review-Liste hinzu,
	 * ï¿½berarbeitet das Rating und erhï¿½ht die Reviewanzahl.
	 * Außerdem wird das Overall Rating angelegt oder überarbeitet.
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