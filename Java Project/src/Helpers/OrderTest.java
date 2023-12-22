package Helpers;

import org.junit.jupiter.api.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class OrderTest {
    private static String string = "23/01/2003";
    private static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static Date newDate;

    private static Order orderTest;


    static {
        try {
            newDate = formatter.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeAll
    static void CreateObject(){
        orderTest = new Order(1,newDate,"Order1", "Geri Vogel");
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void toStringTest(){
        Assertions.assertEquals(orderTest.toString(), "Order_1 issued by Order1, Total: 0.0");
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void addAndGetQuantityTest(){
        orderTest.addQuantity(2);
        Assertions.assertEquals(orderTest.getQuantity(), 2);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void addAndGetTotalTest(){
        orderTest.addTotal(20);
        Assertions.assertEquals(orderTest.getTotal(), 20.0);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getDate(){
        string = "23/01/2003";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date newDate;


        try {
            newDate = formatter.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(orderTest.getDate(), newDate);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void getBookBoughtTest(){
        Assertions.assertEquals(orderTest.getBookbought(), "Geri Vogel");
    }
}
