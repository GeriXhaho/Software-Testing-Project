package TestSourcesRoot;

import Directories.Helpers.Author;
import Directories.Helpers.Book;
import Directories.Helpers.Gender;
import Directories.Helpers.Order;
import Directories.Controllers.OrderController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderControllerIntegrationTest {
    static File testfile = null;
    OrderController orderControllerIntegrationTest;
    static int count = 0;
    @BeforeEach
    void createTestFile() {
        count++;
        try{

            testfile = new File("testOrdersIntegrated"+ count +".dat");
            testfile.createNewFile();
        }
        catch(Exception e){
            //no I don't want to
        }

        orderControllerIntegrationTest = new OrderController(testfile);
    }
    @AfterEach
    void deleteTestFile(){
        for(int i=0;i<=count;i++){
            System.out.println(count);
            File deleteFile = new File("testOrdersIntegrated"+ (i+1) +".dat");
            deleteFile.deleteOnExit();
        }
    }
    @Test
    void writeOrdertoFile() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2024);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 16);
        Date datetest =  cal.getTime();
        int id = 1;
        String name = "Order 1";
        String bookbought = "Today";

        Order testOrder1 = new Order(id, datetest, name, bookbought);
        try {
            assertTrue(orderControllerIntegrationTest.writeOrdertoFile(testOrder1));
            Order testOrder2 = new Order(id, datetest, name, bookbought);
            assertThrows( Exception.class, () -> orderControllerIntegrationTest.writeOrdertoFile(testOrder2));
            OrderController temp = new OrderController(null);
            assertFalse(temp.writeOrdertoFile(testOrder2));
        } catch (Exception e) {
            //no I don't want to
        }

    }

    @Test
    void calculateTotal() {
        Author author = new Author("Geri", "Xhaho", Gender.FEMALE);
        Book book = new Book("123", "Kodi i DaVincit", "Gay niggas", 120, 150, 10, author, true);
        assertEquals(1500, orderControllerIntegrationTest.calculateTotal(book, 10));
    }

    @Test
    void calculateIdNumber() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 =  cal.getTime();
        int id = 1;
        String name = "Order 1";
        String bookbought = "Today";
        cal.set(2024, Calendar.JANUARY, 13);
        Date date2 =  cal.getTime();
        int id1 = 2;
        String name1 = "Order 2";
        String bookbought1 = "Today";
        cal.set(2024, Calendar.JANUARY, 12);
        Date date3 =  cal.getTime();
        int id2 = 3;
        String name2 = "Order 3";
        String bookbought2 = "Today";

        try {
            Order testOrder1 = new Order(id, date1, name, bookbought);
            Order testOrder2 = new Order(id1, date2, name1, bookbought1);
            Order testOrder3 = new Order(id2, date3, name2, bookbought2);

            orderControllerIntegrationTest.writeOrdertoFile(testOrder1);
            orderControllerIntegrationTest.writeOrdertoFile(testOrder2);
            orderControllerIntegrationTest.writeOrdertoFile(testOrder3);

            assertEquals(3, orderControllerIntegrationTest.calculateIdNumber());
        } catch(Exception e) {
            //no I don't want to
        }

    }

    @Test
    void orderDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 16);
        Date date =  cal.getTime();
        assertEquals(date.toString(), orderControllerIntegrationTest.orderDate().toString());
    }

    @Test
    void setDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 10);
        Date date =  cal.getTime();
        assertEquals(0, date.compareTo(orderControllerIntegrationTest.setDate(10, 1, 2000)));
    }

    @Test
    void printBookRecords() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 =  cal.getTime();
        int id = 1;
        String name = "Order 1";
        String bookbought = "Today";

        cal.set(2024, Calendar.JANUARY, 13);
        Date date2 =  cal.getTime();
        int id1 = 2;
        String name1 = "Order 2";
        String bookbought1 = "Today";

        cal.set(2024, Calendar.JANUARY, 12);
        Date date3 =  cal.getTime();
        int id2 = 3;
        String name2 = "Order 3";
        String bookbought2 = "Today";

        cal.set(2024, Calendar.JANUARY, 11);
        Date date4 =  cal.getTime();

        cal.set(2024, Calendar.JANUARY, 15);
        Date date5 =  cal.getTime();


        try {
            Order testOrder1 = new Order(id, date1, name, bookbought);
            Order testOrder2 = new Order(id1, date2, name1, bookbought1);
            Order testOrder3 = new Order(id2, date3, name2, bookbought2);

            orderControllerIntegrationTest.writeOrdertoFile(testOrder1);
            orderControllerIntegrationTest.writeOrdertoFile(testOrder2);
            orderControllerIntegrationTest.writeOrdertoFile(testOrder3);

            assertEquals("Today\t0\t0.0\n" +
                    "Today\t0\t0.0\n" +
                    "Today\t0\t0.0\n", orderControllerIntegrationTest.printBookRecords(date4, date5));
        } catch(Exception e) {
            //no I don't want to
        }
    }

    @Test
    void totalBookExpenses() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 =  cal.getTime();
        int id = 1;
        String name = "Order 1";
        String bookbought = "Today";

        cal.set(2024, Calendar.JANUARY, 13);
        Date date2 =  cal.getTime();
        int id1 = 2;
        String name1 = "Order 2";
        String bookbought1 = "Today";

        cal.set(2024, Calendar.JANUARY, 12);
        Date date3 =  cal.getTime();
        int id2 = 3;
        String name2 = "Order 3";
        String bookbought2 = "Today";

        cal.set(2024, Calendar.JANUARY, 11);
        Date date4 =  cal.getTime();

        cal.set(2024, Calendar.JANUARY, 15);
        Date date5 =  cal.getTime();

        try {
            Order testOrder1 = new Order(id, date1, name, bookbought);
            Order testOrder2 = new Order(id1, date2, name1, bookbought1);
            Order testOrder3 = new Order(id2, date3, name2, bookbought2);

            orderControllerIntegrationTest.writeOrdertoFile(testOrder1);
            orderControllerIntegrationTest.writeOrdertoFile(testOrder2);
            orderControllerIntegrationTest.writeOrdertoFile(testOrder3);

            assertEquals(0.0, orderControllerIntegrationTest.totalBookExpenses(date4, date5));
        } catch(Exception e) {
            //no I don't want to
        }
    }
}
