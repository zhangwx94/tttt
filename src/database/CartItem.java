package database;

public class CartItem {

	private Movie movie;
	private int qty;

	public CartItem(Movie movie, int qty) {
		this.movie = movie;
		this.qty = qty;
	}

	public int getMovieId() {
		return movie.getId();
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void addQuantity(int qty) {
		this.qty += qty;
	}
}
