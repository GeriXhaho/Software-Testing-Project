package Directories.Helpers;

public interface BookInterface {
    String getIsbn13();

    int getQuantity();

    String StocktoString();

    String getTitle();

    float getSPrice();

    Author getAuthor();

    float getBprice();

    String recordToString();

    void addQuantity(int i);
}
