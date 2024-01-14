package Helpers;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Book implements BookInterface,Serializable {
	@Serial
	private static final long serialVersionUID = 5296705482940410483L;
	private String isbn13;
	private String title;
	private String description;
	private float sprice;
	private float bprice;
	private Author author;
	private ArrayList<Genre> genres = new ArrayList<>();
	private int quantity;

	private boolean paperback; // or e-book

	public Book(String isbn13, String title, String description, float sprice, float bprice, int quantity,Author author,
			boolean paperback) {
		this.isbn13 = isbn13;
		this.title = title;
		this.sprice = sprice;
		this.bprice = bprice;
		this.author = author;
		this.description = description;
		this.paperback = paperback;
		this.quantity = quantity;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getSPrice() {
		return sprice;
	}

	public void setSPrice(float price) {this.sprice = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}

	public void addGenres(Genre... genres) {
		for (Genre genre : genres)
			this.addGenre(genre);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	public boolean isPaperback() {
		return paperback;
	}

	public void setPaperback(boolean paperback) {
		this.paperback = paperback;
	}
	public float getBprice(){
		return bprice;
	}

	@Override
	public String toString() {
		return (this.title + " by " + this.author.toString() + "\n");
	}
	public String recordToString() {
		return (this.title + " by " + this.author.toString());
	}

	public String StocktoString() {
		return (this.isbn13 + " "+ this.title + " by " + this.author.toString() + " x " + this.quantity+"\n");
	}
	public String enhancedtoString(){
		return (this.title + " by " + this.author.toString() + "\n" + this.description + "\nIs paperback: "
				+ this.paperback + "\nIsbn13: " + this.isbn13 + "\n" + this.genres + "\n" + this.quantity + " x "
				+ this.sprice + " leke\n");
	}
}