package Controllers;

import Exceptions.InvalidCredentials;
import Helpers.BookMock;
import Helpers.OrderMock;
import Roles.UserMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {
    static File testfile = null;
    OrderController orderControllerTest;
    static int count = 0;
    @BeforeEach
    void createTestFile() {
        count++;
        try{

            testfile = new File("testOrders"+ count +".dat");
            testfile.createNewFile();
        }
        catch(Exception e){
            //no I don't want to
        }

        orderControllerTest = new OrderController(testfile);
    }
    @AfterAll
    static void deleteTestFile(){
        for(int i=0;i<=count;i++){
            System.out.println(count);
            File deleteFile = new File("testOrders"+ (i+1) +".dat");
            deleteFile.deleteOnExit();
        }
    }
    @Test
    void writeOrdertoFile() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2024);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 14);
        Date datetest =  cal.getTime();

        OrderMock testOrder1 = new OrderMock(datetest);
        try {
            assertTrue(orderControllerTest.writeOrdertoFile(testOrder1));
            OrderMock testOrder2 = new OrderMock(datetest);
            assertThrows( Exception.class, () -> orderControllerTest.writeOrdertoFile(testOrder2));
            OrderController temp = new OrderController(null);
            assertFalse(temp.writeOrdertoFile(testOrder2));
        } catch (Exception e) {
            //no I don't want to
        }

    }

    @Test
    void calculateTotal() {
        BookMock book = new BookMock();
        assertEquals(1500, orderControllerTest.calculateTotal(book, 10));
    }

    @Test
    void calculateIdNumber() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 13);
        Date date2 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 12);
        Date date3 =  cal.getTime();

        try {
            OrderMock testOrder1 = new OrderMock(date1);
            OrderMock testOrder2 = new OrderMock(date2);
            OrderMock testOrder3 = new OrderMock(date3);

            orderControllerTest.writeOrdertoFile(testOrder1);
            orderControllerTest.writeOrdertoFile(testOrder2);
            orderControllerTest.writeOrdertoFile(testOrder3);

            assertEquals(3,orderControllerTest.calculateIdNumber());
        } catch(Exception e) {
            //no I don't want to
        }

    }

    @Test
    void orderDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date =  cal.getTime();
        assertEquals(date,orderControllerTest.orderDate());
    }

    @Test
    void setDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 10);
        Date date =  cal.getTime();
        assertEquals(0, date.compareTo(orderControllerTest.setDate(10, 1, 2000)));
    }

    @Test
    void printBookRecords() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 13);
        Date date2 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 12);
        Date date3 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 11);
        Date date4 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 15);
        Date date5 =  cal.getTime();

        try {
            OrderMock testOrder1 = new OrderMock(date1);
            OrderMock testOrder2 = new OrderMock(date2);
            OrderMock testOrder3 = new OrderMock(date3);

            orderControllerTest.writeOrdertoFile(testOrder1);
            orderControllerTest.writeOrdertoFile(testOrder2);
            orderControllerTest.writeOrdertoFile(testOrder3);

            assertEquals("Geri Kevi Hoxha\t15\t1500.0\n" +
                    "Geri Kevi Hoxha\t15\t1500.0\n" +
                    "Geri Kevi Hoxha\t15\t1500.0\n",orderControllerTest.printBookRecords(date4, date5));
        } catch(Exception e) {
            //no I don't want to
        }
    }

    @Test
    void totalBookExpenses() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 13);
        Date date2 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 12);
        Date date3 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 11);
        Date date4 =  cal.getTime();
        cal.set(2024, Calendar.JANUARY, 15);
        Date date5 =  cal.getTime();

        try {
            OrderMock testOrder1 = new OrderMock(date1);
            OrderMock testOrder2 = new OrderMock(date2);
            OrderMock testOrder3 = new OrderMock(date3);

            orderControllerTest.writeOrdertoFile(testOrder1);
            orderControllerTest.writeOrdertoFile(testOrder2);
            orderControllerTest.writeOrdertoFile(testOrder3);

            assertEquals(4500.0,orderControllerTest.totalBookExpenses(date4, date5));
        } catch(Exception e) {
            //no I don't want to
        }
    }
}