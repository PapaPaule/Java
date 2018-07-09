package V1;

import java.io.*;
import java.util.*;

public class Main {

	public static void ladeDB(String dbName) {
		File datei = new File(dbName);
		
		//prüfe ob Datei korrekt
		if(!datei.canRead() || !datei.isFile()) {
			System.exit(0);
		}
		
		BufferedReader in = null;
		Data db = new Data();
		
		try {
			
			in = new BufferedReader(new FileReader(dbName));
			String zeile = null;
			
			//zeilenweises Einlesen der Daten aus datei
			while((zeile = in.readLine()) != null) {
				
				//Falls Actor-Zeilen
				if(zeile.equals("New_Entity: \"actor_id\",\"actor_name\"")) {
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"movie_id\",\"movie_title\",\"movie_plot\",\"genre_name\",\"movie_released\",\"movie_imdbVotes\",\"movie_imdbRating\"")) {
						
						String[] split = zeile.split("\"");
					
						//Actor anlegen und in die entsprechenden Listen packen
						Actor actor = new Actor(Integer.parseInt(split[1]), split[3]);
						db.addActor(actor);
						
					}
				
				//Falls Movie-Zeile
				} else if(zeile.equals("New_Entity: \"movie_id\",\"movie_title\",\"movie_plot\",\"genre_name\",\"movie_released\",\"movie_imdbVotes\",\"movie_imdbRating\"")) {
					
					while(!(zeile = in.readLine()).equals("New_Entity: \"director_id\",\"director_name\"")) {
						
						String[] split = zeile.split("\"");
					
						//Movie anlegen und in die entsprechenden Listen packen
						Movie movie = new Movie(Integer.parseInt(split[1]), split[3], split[5], split[7], split[9], Integer.parseInt(split[11]), Double.parseDouble(split[13]));
						db.addMovie(movie);
					
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
					
					e.printStackTrace();
				
				}
				
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		String dbName = "./src//movieproject.db";
		ladeDB(dbName);
	}

}
