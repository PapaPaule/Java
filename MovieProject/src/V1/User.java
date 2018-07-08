package V1;

import java.util.*;

public class User {

	protected String name;
	protected List<Review> reviews;
	
	public User(String name) {
		this.name = name;
		this.reviews = new ArrayList<Review>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Review> getReviews() {
		return this.reviews;
	}
	
}
