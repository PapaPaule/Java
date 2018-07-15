package V1;

import java.util.*;

/**
 * @author MaderP
 * Die Klasse User legt einen neuen Benutzer an.
 * Dieser hat einen Namen und eine Liste mit verfassten Reviews.
 */
public class User {

	protected String name;
	protected List<Review> reviews;

	/**
	 * @param name
	 * Der User wird mit einem Namen angelegt und es wird eine neue Liste erstellt,
	 * die mit Reviews gefüllt werden kann.
	 */
	public User(String name) {
		this.name = name;
		this.reviews = new ArrayList<Review>();
	}

	/**
	 * @param name
	 * Legt den Namen des Users fest.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param review
	 * Der Liste an verfassten Reviews des Users
	 * wird ein Review hinzugefügt.
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	/**
	 * @return name
	 * Name des Users wird zurückgegeben.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return reviews
	 * Die Liste an verfassten Reviews des Users wird zurückgegeben.
	 */
	public List<Review> getReviews() {
		return this.reviews;
	}

}
