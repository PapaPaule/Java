
public class Rating {

	protected String user_name;
	protected double rating;
	protected int rating_movie_id;
	
	public Rating() {
		
	}
	
	public Rating(String user_name, double rating, int rating_movie_id) {
		this.user_name = user_name;
		this.rating = rating;
		this.rating_movie_id = rating_movie_id;
	}
	
	public String getUserName() {
		return this.user_name;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public int getRatingMovieId() {
		return this.rating_movie_id;
	}

}