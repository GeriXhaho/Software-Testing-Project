package Helpers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BillTest {

    @Test
    void addTotal() {

        Book mockedBook = Mockito.mock(Book.class);
        when(mockedBook.getBprice()).thenReturn(10.0f);


        Bill bill = new Bill(1, new Date(), "Kevi Hoxha");


        bill.addBook(mockedBook.toString());
        bill.addTotal(mockedBook.getBprice(), 3);

        // Verify that the methods were called as expected
        verify(mockedBook, times(1)).getBprice();

        // Perform assertions based on the mocked values
        assertEquals(30.0f, bill.getTotal());

    }



    @Test
    void getTotal() {
        // Create an instance of Bill
        Bill bill = new Bill(1, new Date(), "John Doe");

        // Verify that it instanitates at 0
        assertEquals(0.0f, bill.getTotal());

        // Add total for a book
        bill.addTotal(20.0f, 3);

        // Verify that the total amount is updated correctly
        assertEquals(60.0f, bill.getTotal());

        // Add total for another book
        bill.addTotal(15.0f, 2);

        // Verify that the total amount is updated correctly
        assertEquals(90.0f, bill.getTotal());

        // Add total for yet another book
        bill.addTotal(10.0f, 5);

        // Verify that the total amount is updated correctly
        assertEquals(140.0f, bill.getTotal());
    }


    @Test
    void addBillText() {
        // While creating this test it seems a bit stupid since I am testing if the text is getting added to the bill
        // I do this by using another method that needs to be tested so it feels like its not a unit test
        // Create an instance of Bill
//        Bill bill = new Bill(1, new Date(), "Henri Cela");
//
//        // Add bill text
//        bill.addBillText("Book purchased: Procrastination Book");
//
//        // Verify that the bill text is updated correctly
//        String expected = "Bill_ID 1\t\t\t\t\t" + bill.getBilldate() + System.lineSeparator() +
//                System.lineSeparator() +
//                "Book purchased: Procrastination Book" + System.lineSeparator() +
//                "Total:\t0.0" + System.lineSeparator() +
//                "Issued by: Henri Cela";
//        assertEquals(expected, bill.getBillText());
//
//        // Add more bill text
//        bill.addBillText("\nAdditional details...");
//
//        // Verify that the bill text is updated correctly
//        expected += System.lineSeparator() + "Additional details...";
//        assertEquals(expected, bill.getBillText());
    }

    @Test
    void getBillText() {
    }

    @Test
    void getID() {
        // Create an instance of Bill with ID 1
        Bill bill = new Bill(1, new Date(), "Joan Padan");

        // Verify that the getID method returns the correct ID
        assertEquals(1, bill.getID());

    }

    @Test
    void getBilldate() {
        // Tolerance for time difference in milliseconds
        final long TIME_TOLERANCE = 1000; // 1 second
        // Create an instance of Bill
        Bill bill = new Bill(1, new Date(), "Pookie Bear");

        // Get the bill date
        Date billDate = bill.getBilldate();

        // Verify that the bill date is not null
        assertNotNull(billDate);

        // Verify that the bill date is within an acceptable range
        Date currentDate = new Date();
        long timeDifference = Math.abs(billDate.getTime() - currentDate.getTime());
        assertTrue(timeDifference <= TIME_TOLERANCE,
                "The time difference is more than the allowed tolerance: " + timeDifference + " milliseconds.");
    }

    @Test
    void getBillName() {
        // Create an instance of Bill
        String expectedBillName = "Henri Hoxha";
        Bill bill = new Bill(1, new Date(), expectedBillName);

        // Get the bill name
        String actualBillName = bill.getBillName();

        // Verify that the returned bill name matches the expected bill name
        assertEquals(expectedBillName, actualBillName);
    }

    @Test
    void getQuantity() {
        // Create an instance of Bill
        Bill bill = new Bill(1, new Date(), "Kevin Cela");

        assertEquals(0,bill.getQuantity() );

        // Add quantities for different books
        bill.addTotal(20.0f, 3);
        bill.addTotal(15.0f, 2);
        bill.addTotal(10.0f, 5);

        // Get the total quantity from the Bill
        int actualQuantity = bill.getQuantity();

        // Verify that the returned quantity matches the expected total quantity
        assertEquals(3 + 2 + 5, actualQuantity);
    }

    @Test
    void addBook() {
        // Create an instance of Bill
        Bill bill = new Bill(1, new Date(), "Xhaho Hoxha");

        // Add books to the bill
        List<String> expectedBookList = Arrays.asList("Book1", "Book2", "Book3");
        for (String book : expectedBookList) {
            bill.addBook(book);
        }

        // Get the list of books from the Bill
        List<String> actualBookList = bill.getBooklist();

        // Verify that the returned list of books matches the expected list
        assertEquals(expectedBookList, actualBookList);
    }


    @Test
    void getBooklist() {
        // Create an instance of Bill
        Bill bill = new Bill(1, new Date(), "Johnny Boy");

        // Create mocked books
        String mockedBook1 = "MockedBook1";
        String mockedBook2 = "MockedBook2";
        String mockedBook3 = "MockedBook3";

        // Add mocked books to the bill
        List<String> expectedBookList = List.of(mockedBook1, mockedBook2, mockedBook3);
        for (String book : expectedBookList) {
            bill.addBook(book);
        }

        // Get the list of books from the Bill
        List<String> actualBookList = bill.getBooklist();

        // Verify that the returned list of books matches the expected list
        assertEquals(expectedBookList, actualBookList);

    }

    @Test
    void getQuantitylist() {
        // Create an instance of Bill
        Bill bill = new Bill(1, new Date(), "Testing notfun");

        // Add quantities for different books
        bill.addTotal(20.0f, 3);
        bill.addTotal(15.0f, 2);
        bill.addTotal(10.0f, 5);

        // Get the list of quantities from the Bill
        List<Integer> actualQuantityList = bill.getQuantitylist();

        // Expected list of quantities
        List<Integer> expectedQuantityList = Arrays.asList(3, 2, 5);

        // Verify that the returned list of quantities matches the expected list
        assertEquals(expectedQuantityList, actualQuantityList);

    }

    @Test
    void getPricelist() {
        // Create an instance of Bill
        Bill bill = new Bill(1, new Date(), "Geri Hoxha");

        // Add prices for different books
        bill.addTotal(20.0f, 3);
        bill.addTotal(15.0f, 2);
        bill.addTotal(10.0f, 5);

        // Get the list of prices from the Bill
        List<Float> actualPriceList = bill.getPricelist();

        // Expected list of prices
        List<Float> expectedPriceList = Arrays.asList(20.0f, 15.0f, 10.0f);

        // Verify that the returned list of prices matches the expected list
        assertEquals(expectedPriceList, actualPriceList);
    }
}