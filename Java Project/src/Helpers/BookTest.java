package Helpers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class BookTest {
    private static Book bookTest;

    private static ArrayList<Genre> genre = new ArrayList<>(5);
    @BeforeAll
    static void createBookTest(){
        Author authorTest = new Author("Geri", "Hoxha", Gender.FEMALE);
         bookTest= new Book("ffwefwef", "Geri Vogel", "historia e gerit tvogel",  9, 8, 20, authorTest, false);
         genre.add(Genre.FANTASY);

    }


    @Test
    @Order(1)
    void getIsbn13Test(){
        String isbn = "ffwefwef";
        Assertions.assertEquals(bookTest.getIsbn13(), isbn);
    }

    @Test
    @Order(2)
    void getTitleTest(){
        String title = "Geri Vogel";
        Assertions.assertEquals(bookTest.getTitle(), title);
    }

    @Test
    @Order(3)
    void getSPriceTest(){
        float price = 9;
        Assertions.assertEquals(bookTest.getSPrice(), price);
    }

    @Test
    @Order(4)
    void getAuthorTest(){
        Author prov = new Author("Geri", "Hoxha", Gender.FEMALE);
        Assertions.assertEquals(bookTest.getAuthor(), prov);
    }



    @Test
    void getAndAddGenreTest(){
        ArrayList<Genre> genres = new ArrayList<>();
        Assertions.assertEquals(genres, bookTest.getGenres());
        genres.add(Genre.FANTASY);
        genres.add(Genre.ACTION);
        genres.add(Genre.DYSTOPIAN);
        genres.add(Genre.HISTORICAL);
        genres.add(Genre.MYSTRERY);
        bookTest.addGenres(Genre.FANTASY, Genre.ACTION, Genre.DYSTOPIAN, Genre.HISTORICAL, Genre.MYSTRERY);
        Assertions.assertEquals(genres, bookTest.getGenres());
    }

    @Test
    @Order(5)
    void getDescriptionTest(){
        String description = "historia e gerit tvogel";
        Assertions.assertEquals(bookTest.getDescription(), description);
    }

    @Test
    @Order(6)
    void getQuantityTest(){
        int quantity = 20;
        Assertions.assertEquals(bookTest.getQuantity(), quantity);
    }

    @Test
    @Order(7)
    void isPaperbackTest(){
        boolean ispaperback = false;
        Assertions.assertEquals(bookTest.isPaperback(), ispaperback);
    }

    @Test
    @Order(8)
    void getBpriceTest(){
        float price = 8;
        Assertions.assertEquals(bookTest.getBprice(), price);
    }

    @Test
    @Order(9)
    void toStringTest(){
    }

    @Test
    @Order(10)
    void recordToStringTest(){
        Assertions.assertEquals(bookTest.recordToString(), "Geri Vogel by Geri Hoxha");

    }
    @Test
    @Order(11)
    void StockToStringTest(){
        Assertions.assertEquals(bookTest.StocktoString(), "ffwefwef Geri Vogel by Geri Hoxha x 20\n");

    }

    @Test
    @Order(12)
    void enhancedToStringTest(){
        Assertions.assertEquals(bookTest.enhancedtoString(), "Geri Vogel by Geri Hoxha\nhistoria e gerit tvogel\nIs paperback: false\nIsbn13: ffwefwef\n[]\n20 x 9.0 leke\n");
    }

    @Test
    @Order(13)
    void setIsbn13Test(){
        bookTest.setIsbn13("dsdasd");
        Assertions.assertEquals(bookTest.getIsbn13(), "dsdasd");
    }

    @Test
    @Order(14)
    void setPaperBackTest(){
        bookTest.setPaperback(true);
        Assertions.assertEquals(bookTest.isPaperback(), true);
    }

    @Test
    @Order(15)
    void setDescriptionTest(){
        bookTest.setDescription("sesht historia e gerit tvogle kjo");
        Assertions.assertEquals(bookTest.getDescription(), "sesht historia e gerit tvogle kjo");
    }

    @Test
    @Order(16)
    void addQuantityTest(){
        bookTest.addQuantity(1);
        Assertions.assertEquals(bookTest.getQuantity(), 21);
    }

    @Test
    @Order(17)
    void setTitleTest(){
        bookTest.setTitle("Kevi Vogel");
        Assertions.assertEquals(bookTest.getTitle(), "Kevi Vogel");
    }

    @Test
    @Order(18)
    void setSPriceTest(){
        bookTest.setSPrice(20);
        Assertions.assertEquals(bookTest.getSPrice(), 20);
    }

    @Test
    @Order(19)
    void setAuthorTest(){
        Author prov2 = new Author("Geri", "Hoxha", Gender.FEMALE);
        bookTest.setAuthor(prov2);
        Assertions.assertEquals(bookTest.getAuthor(), prov2);
    }
}
