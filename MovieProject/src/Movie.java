
public class Movie {

	protected int movie_id;
	protected String movie_title;
	protected String movie_plot;
	protected String genre_name;
	protected String movie_released;
	protected int movie_imdbVotes;
	protected double imdbRating;
	
	public Movie() {
		
	}
	
	public Movie(int movie_id, String movie_title, String movie_plot, String genre_name, String movie_released, int movie_imdbVotes, double imdbRating) {
		this.movie_id = movie_id;
		this.movie_title = movie_title;
		this.movie_plot = movie_plot;
		this.genre_name = genre_name;
		this.movie_released = movie_released;
		this.movie_imdbVotes = movie_imdbVotes;
		this.imdbRating = imdbRating;
	}
	
	public int getMovieId() {
		return this.movie_id;
	}
	
	public String getMovieTitle() {
		return this.movie_title;
	}
	
	public String getMoviePlot() {
		return this.movie_plot;
	}
	
	public String getGenreName() {
		return this.genre_name;
	}
	
	public String getMovieReleased() {
		return this.movie_released;
	}
	
	public int getMovieImdbVotes() {
		return this.movie_imdbVotes;
	}
	
	public double getImdbRating() {
		return this.imdbRating;
	}

}
