
public class Actor {

	protected int actor_id;
	protected String actor_name;
	
	public Actor() {
		
	}
	
	public Actor(int actor_id, String actor_name) {
		this.actor_id = actor_id;
		this.actor_name = actor_name;
	}
	
	public int getActorId() {
		return this.actor_id;
	}

	public String getActorName() {
		return this.actor_name;
	}
	
}
