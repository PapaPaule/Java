package V1;

class Review {

	protected User user;
	protected int rating;
	protected Movie movie;
	
	public Review(User user, int rating, Movie movie) {
		this.user = user;
		this.rating = rating;
		this.movie = movie;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public Movie getMovie() {
		return this.movie;
	}
	
}
