package database;

import java.sql.Date;
import java.util.ArrayList;

public class Star {
	private int id;
	private String first_name;
	private String last_name;
	private Date dob;
	private String photo_url;
	private ArrayList<Movie> movies;
	
	public Star(int id, String first_name, String last_name, Date dob, String photo_url) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.photo_url = photo_url;
		this.movies = new ArrayList<Movie>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public void addMovie(Movie movie)
	{
		movies.add(movie);
	}
	
	public void clearMovies()
	{
		movies.clear();
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
}
