import java.io.*;
import java.util.*;

public class Main {

	public Main() {
		
	}
	
	private static void ladeDatei(String dbName) {
		File datei = new File(dbName);
		
		if(!datei.canRead() || !datei.isFile()) {
			System.exit(0);
		}
		
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(dbName));
			String zeile = null;
			
			while((zeile = in.readLine()) != null) {
				
				if(zeile.equals("New_Entity: \"actor_id\",\"actor_name\"")) {
					//in.readLine();
					
					HashMap<Integer, Actor> actors = new HashMap<Integer, Actor>();
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"movie_id\",\"movie_title\",\"movie_plot\",\"genre_name\",\"movie_released\",\"movie_imdbVotes\",\"movie_imdbRating\"")) {
						String[] split = zeile.split("\"");
						
						actors.put(Integer.parseInt(split[1]), new Actor(Integer.parseInt(split[1]), split[3]));
					}
					
				} if(zeile.equals("New_Entity: \"movie_id\",\"movie_title\",\"movie_plot\",\"genre_name\",\"movie_released\",\"movie_imdbVotes\",\"movie_imdbRating\"")) {
					//in.readLine();
					
					HashMap<Integer, Movie> movies = new HashMap<Integer, Movie>();
					
					while((zeile = in.readLine()) != "New_Entity: \"director_id\",\"director_name\"") {
						String[] split = zeile.split("\"");
						
						movies.put(Integer.parseInt(split[1]), new Movie(Integer.parseInt(split[1]), split[3], split[5], split[7], split[9], Integer.parseInt(split[11]), Double.parseDouble(split[13])));
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}
	

	public static void main(String[] args) {
		String dbName = "./src//movieproject.db";
		ladeDatei(dbName);
	}

}
