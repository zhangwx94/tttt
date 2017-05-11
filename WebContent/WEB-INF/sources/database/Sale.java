package database;

import java.sql.Date;

public class Sale {
	private int id;
	private int customer_id;
	private int movie_id;
	private Date sale_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCustomerId() {
		return customer_id;
	}

	public void setCustomerId(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getMovieId() {
		return movie_id;
	}

	public void setMovieId(int movie_id) {
		this.movie_id = movie_id;
	}

	public Date getSaleDate() {
		return sale_date;
	}

	public void setSaleDate(Date sale_date) {
		this.sale_date = sale_date;
	}
}
