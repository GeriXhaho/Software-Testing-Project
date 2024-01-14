package Helpers;

import java.io.Serializable;

public class BookMock implements BookInterface, Serializable {

    String isbn13;
    int quantity;
    Author author;
    String title;
    public BookMock(){
        this.isbn13 = "17456890";
        this.quantity = 17;
        this.author = new Author("Hoxha", "Xhaho", Gender.MALE);
        this.title = "Geri Kevi Hoxha";
    }
    @Override
    public String getIsbn13() {

        return this.isbn13;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public String StocktoString() {
        return this.isbn13 + " "+ this.title + " by " + this.author.toString() + " x " + this.quantity+"\n";
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public float getSPrice() {
        return 50;
    }

    @Override
    public Author getAuthor() {
        return this.author;
    }

    @Override
    public float getBprice() {
        return 150;
    }

    @Override
    public String recordToString() {
        return null;
    }

    @Override
    public void addQuantity(int i) {

    }
}
