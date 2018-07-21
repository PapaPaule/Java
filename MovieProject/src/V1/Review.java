package V1;

/**
 * @author MaderP
 * Die Klasse Review definiert ein verfasstes Review eines Users.
 * Ein Review enth�lt den Verfasser (User), ein Rating
 * und den zugeh�rigen Film vom Typ Movie.
 */
class Review {

	protected User user;
	protected Double rating;
	protected Movie movie;
	
	/**
	 * @param user
	 * @param rating
	 * @param movie
	 * Legt ein Review an mit einem User, einem Rating und dem Movie.
	 */
	public Review(User user, Double rating, Movie movie) {
		this.user = user;
		this.rating = rating;
		this.movie = movie;
	}
	
	/**
	 * @return User
	 * Gibt den User zur�ck.
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * @return Rating
	 * Gibt das Rating eines Reviews zur�ck.
	 */
	public Double getRating() {
		return this.rating;
	}
	
	/**
	 * @return Movie
	 * Gibt den Film des Reviews zur�ck.
	 */
	public Movie getMovie() {
		return this.movie;
	}
	
}
