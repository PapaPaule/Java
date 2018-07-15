package V1;

/**
 * @author MaderP
 * Die Klasse Review definiert ein verfasstes Review eines Users.
 * Ein Review enthält den Verfasser (User), ein Rating
 * und den zugehörigen Film vom Typ Movie.
 */
class Review {

	protected User user;
	protected double rating;
	protected Movie movie;
	
	/**
	 * @param user
	 * @param rating
	 * @param movie
	 * Legt ein Review an mit einem User, einem Rating und dem Movie.
	 */
	public Review(User user, double rating, Movie movie) {
		this.user = user;
		this.rating = rating;
		this.movie = movie;
	}
	
	/**
	 * @return User
	 * Gibt den User zurück.
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * @return Rating
	 * Gibt das Rating eines Reviews zurück.
	 */
	public double getRating() {
		return this.rating;
	}
	
	/**
	 * @return Movie
	 * Gibt den Film des Reviews zurück.
	 */
	public Movie getMovie() {
		return this.movie;
	}

	/**
	 * @param user
	 * Legt den User fest.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param rating
	 * Legt das Rating fest.
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * @param movie
	 * Legt den Movie fest.
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
}
