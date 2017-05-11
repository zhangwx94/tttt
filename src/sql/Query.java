package sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import database.*;

public class Query {
	public static Connection makeConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/moviedb?autoReconnect=true&useSSL=false",
					"root", "123");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Customer verifyCustomer(String email, String password, Connection conn) throws SQLException {
		String sql = "SELECT id, first_name, last_name, cc_id, address, email, password FROM customers WHERE email = ? AND password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		ResultSet res = pstmt.executeQuery();
		Customer validCustomer = null;
		if (res.next()) {
			validCustomer = new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
					res.getString(5), res.getString(6), res.getString(7));
		}
		return validCustomer;
	}
	
	public static boolean verifyEmployee(String email, String password, Connection conn) throws SQLException {
		String sql = "SELECT email, password FROM employees WHERE email = ? AND password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		ResultSet res = pstmt.executeQuery();
		if (res.next() == false) {
			return false;
		}
		else{
			return true;
		}
	}

	public static ArrayList<String> getGenreName(Connection conn) throws SQLException {
		String sql = "SELECT name FROM genres ORDER BY name ASC";
		ResultSet res = conn.createStatement().executeQuery(sql);
		ArrayList<String> genre_name = new ArrayList<String>();

		while (res.next()) {
			genre_name.add(res.getString(1));
		}
		return genre_name;
	}

	public static ArrayList<Movie> searchMovies(String id, String title, String year, String director,
			String first_name, String last_name, Connection conn) throws SQLException {
		String whereCase = " WHERE 1=1";
		String orderCase = " ORDER BY movies.title ASC";
		String sql = "SELECT stars.id, stars.first_name, stars.last_name, stars.dob, stars.photo_url, movies.id, movies.title, movies.year, movies.director, movies.banner_url, movies.trailer_url, "
				+ "genres.id, genres.name FROM movies "
				+ "INNER JOIN stars_in_movies ON stars_in_movies.movie_id = movies.id "
				+ "INNER JOIN stars ON stars_in_movies.star_id = stars.id "
				+ "INNER JOIN genres_in_movies ON genres_in_movies.movie_id = movies.id "
				+ "INNER JOIN genres ON genres.id = genres_in_movies.genre_id ";
		if (id != null && !id.isEmpty()) {
			whereCase = whereCase + " AND movies.id =\"" + id + "\"";
		}
		if (title != null && !title.isEmpty()) {
			whereCase += " AND movies.title LIKE \"%" + title + "%\"";
		}
		if (year != null && !year.isEmpty()) {
			whereCase += " AND movies.year LIKE \"%" + year + "%\"";
		}
		if (director != null && !director.isEmpty()) {
			whereCase += " AND movies.director like \"%" + director + "%\"";
		}
		if (first_name != null && !first_name.isEmpty()) {
			whereCase += " AND stars.first_name like \"%" + first_name + "%\"";
		}
		if (last_name != null && !last_name.isEmpty()) {
			whereCase += " AND stars.last_name like \"%" + last_name + "%\"";
		}
		sql += whereCase + orderCase;
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		HashMap<Integer, Movie> map = new HashMap<Integer, Movie>();
		while (res.next()) {
			Star star = new Star(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getString(5));
			Genre genres = new Genre(res.getInt(12), res.getString(13));
			if (map.containsKey(res.getInt(6))) {
				Movie movie = map.get(res.getInt(6));
				boolean addStar = true;
				boolean addGenre = true;
				for (Star temp : movie.getStars()) {
					if (temp.getId() == star.getId()) {
						addStar = false;
					}
				}
				if (addStar) {
					movie.addStar(star);
				}
				for (Genre temp : movie.getGenres()) {
					if (temp.getId() == genres.getId()) {
						addGenre = false;
					}
				}
				if (addGenre) {
					movie.addGenre(genres);
				}
				map.put(movie.getId(), movie);
			} else {
				ArrayList<Genre> genresArray = new ArrayList<Genre>();
				genresArray.add(genres);
				ArrayList<Star> stars = new ArrayList<Star>();
				stars.add(star);
				Movie movie = new Movie(res.getInt(6), res.getString(7), res.getInt(8), res.getString(9),
						res.getString(10), res.getString(11), genresArray, stars);
				map.put(movie.getId(), movie);
			}
		}
		ArrayList<Movie> movies = new ArrayList<Movie>(map.values());
		return movies;
	}

	public static ArrayList<Movie> browseMovies(String title, String genre, Connection conn) throws SQLException {
		String whereCase = " WHERE 1=1";
		String sql = "SELECT stars.id, stars.first_name, stars.last_name, stars.dob, stars.photo_url, movies.id, movies.title, movies.year, movies.director, movies.banner_url, movies.trailer_url, "
				+ "genres.id, genres.name FROM movies "
				+ "INNER JOIN stars_in_movies ON stars_in_movies.movie_id = movies.id "
				+ "INNER JOIN stars ON stars_in_movies.star_id = stars.id "
				+ "INNER JOIN genres_in_movies ON genres_in_movies.movie_id = movies.id "
				+ "INNER JOIN genres ON genres.id = genres_in_movies.genre_id ";
		if (title != null && !title.isEmpty()) {
			whereCase += " AND movies.title LIKE \"" + title + "%\"";
		}
		if (genre != null && !genre.isEmpty()) {
			whereCase += " AND genres.name LIKE \"%" + genre + "%\"";
		}
		sql += whereCase;
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		HashMap<Integer, Movie> map = new HashMap<Integer, Movie>();
		while (res.next()) {
			Star star = new Star(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getString(5));
			Genre genres = new Genre(res.getInt(12), res.getString(13));
			if (map.containsKey(res.getInt(6))) {
				Movie movie = map.get(res.getInt(6));
				boolean addStar = true;
				boolean addGenre = true;
				for (Star temp : movie.getStars()) {
					if (temp.getId() == star.getId()) {
						addStar = false;
					}
				}
				if (addStar) {
					movie.addStar(star);
				}
				for (Genre temp : movie.getGenres()) {
					if (temp.getId() == genres.getId()) {
						addGenre = false;
					}
				}
				if (addGenre) {
					movie.addGenre(genres);
				}
				map.put(movie.getId(), movie);
			} else {
				ArrayList<Genre> genresArray = new ArrayList<Genre>();
				genresArray.add(genres);
				ArrayList<Star> stars = new ArrayList<Star>();
				stars.add(star);
				Movie movie = new Movie(res.getInt(6), res.getString(7), res.getInt(8), res.getString(9),
						res.getString(10), res.getString(11), genresArray, stars);
				map.put(movie.getId(), movie);
			}
		}
		ArrayList<Movie> movies = new ArrayList<Movie>(map.values());
		return movies;
	}

	public static Star searchStar(String id, Connection connection) throws SQLException {
		String sql = "SELECT movies.id, movies.title, movies.year, stars.id, stars.first_name, stars.last_name, stars.dob, stars.photo_url FROM stars"
				+ " INNER JOIN stars_in_movies ON stars_in_movies.star_id=stars.id"
				+ " INNER JOIN movies ON stars_in_movies.movie_id=movies.id" + " WHERE stars.id = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet res = pstmt.executeQuery();
		res.next();
		Star star = new Star(res.getInt(4), res.getString(5), res.getString(6), res.getDate(7), res.getString(8));
		star.addMovie(new Movie(res.getInt(1), res.getString(2), res.getInt(3), "", "", ""));
		while (res.next()) {
			star.addMovie(new Movie(res.getInt(1), res.getString(2), res.getInt(3), "", "", ""));
		}

		return star;
	}

	public static boolean insertSale(Cart cart, Customer customer, Connection connection) throws SQLException {
		for (CartItem item : cart.getCartItems()) {
			String sql = "INSERT INTO sales(customer_id, movie_id, sale_date) VALUES(?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			java.util.Date date = new java.util.Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String now = dateFormat.format(date).toString();
			pstmt.setInt(1, customer.getId());
			pstmt.setInt(2, item.getMovieId());
			pstmt.setDate(3, Date.valueOf(now));
			for (int i = 0; i < item.getQty(); i++) {
				int resultSet = pstmt.executeUpdate();
				if (resultSet != 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int AddStar(String name, String dob, String photo_url, Connection conn) throws Exception{
		String[] star_name = new String[2];
		if(name.equals("")){
			return 0;
		}
		String[] names = name.split(" ");
		if(names.length == 1){
			star_name[0] = "";
			star_name[1] = names[0];
		}
		else if(names.length == 2){
			star_name[0] = names[0];
			star_name[1] = names[1];
		}
		if(dob.equals("")){
			return 0;
		}
		if(!dob.equals("")){
			String[] dobs = dob.split("-");
			if(dobs.length!=3){
				return 0;
			}
		}
		String sql = "INSERT INTO stars(first_name, last_name, dob, photo_url) VALUES (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, star_name[0]);
		pstmt.setString(2, star_name[1]);
		pstmt.setString(3, dob);
		pstmt.setString(4, photo_url);
		int num = pstmt.executeUpdate();
		return num;
	}
}
