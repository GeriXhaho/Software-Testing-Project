package Controllers;

import Helpers.*;
import Roles.User;
import Roles.UserInterface;
import Roles.UserMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BillControllerIntegrationTest {
    static File testfile = null;
    BillController billControllerTest;
    static int count = 0;
    @BeforeEach
    void createTestFile() {
        count++;
        try{

            testfile = new File("testBills"+ count +".dat");
            testfile.createNewFile();
        }
        catch(Exception e){
            //no I don't want to
        }
        billControllerTest = new BillController(testfile);
    }
    @AfterAll
    static void deleteTestFile(){
        for(int i=0;i<=count;i++){
            System.out.println(count);
            File deleteFile = new File("testBills"+ (i+1) +".dat");
            deleteFile.deleteOnExit();
        }
    }
    @Test
    void writeBilltoFile() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 = cal.getTime();
        Bill testBill1 = new Bill(1, date1, "Hoxha Geri");
        assertFalse(billControllerTest.writeBilltoFile(testBill1));
        testBill1.addTotal(11,22);
        assertTrue(billControllerTest.writeBilltoFile(testBill1));
        BillController temp = new BillController(null);
        assertFalse(temp.writeBilltoFile(testBill1));
    }

    @Test
    void billtoString() {
        Author author = new Author("Hoxha", "Xhaho", Gender.FEMALE);
        Book book1 = new Book("asdasd", "Geri Kevi Hoxha", "aads", 50, 50,  10, author, true);
        assertEquals("Geri Kevi Hoxha by Hoxha Xhaho\t\t\t10 x 50.0 leke\n", billControllerTest.billtoString(book1, 10));
    }

    @Test
    void calculateIdNumber() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 = cal.getTime();
        Bill testBill1 = new Bill(1, date1, "Hoxha Geri");
        testBill1.addTotal(1100,22);
        System.out.println(testBill1.getTotal());
        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill1);
        assertEquals(4,billControllerTest.calculateIdNumber());
    }

    @Test
    void billDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date =  cal.getTime();
        assertEquals(0, date.compareTo(billControllerTest.billDate()));
    }

    @Test
    void setDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 10);
        Date date =  cal.getTime();
        assertEquals(0, date.compareTo(billControllerTest.setDate(10, 1, 2000)));
    }

    @Test
    void writeBilltoTxt() {
    }

    @Test
    void printLibrarianRecords() {
        User user1 = new User("Test1","1234");
        User user2 = new User("Test2","1234");
        ArrayList<UserInterface> userlist = new ArrayList<>();
        userlist.add(user1);
        userlist.add(user2);
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
        assertEquals("", billControllerTest.printLibrarianRecords(date4,date5,userlist));
        Bill testBill1 = new Bill(1,date1,"Test1");
        testBill1.addTotal(11,22);

        Bill testBill2 = new Bill(2,date2,"Test1");
        testBill2.addTotal(11,22);

        Bill testBill3 = new Bill(3,date3,"Test1");
        testBill3.addTotal(11,22);

        Bill testBill4 = new Bill(4,date4,"Test1");
        testBill3.addTotal(11,22);

        Bill testBill5 = new Bill(4,date5,"Test1");
        testBill5.addTotal(11,22);

        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill2);
        billControllerTest.writeBilltoFile(testBill3);
        billControllerTest.writeBilltoFile(testBill4);
        billControllerTest.writeBilltoFile(testBill5);
        assertEquals("Test1\t3\t\t88\t\t\t968.0 $\n" +
                "Test2\t0\t\t0\t\t\t0.0 $\n", billControllerTest.printLibrarianRecords(date4,date5,userlist));
    }

    @Test
    void totalRecords() {
        assertEquals("0 Books, 0.0$",billControllerTest.totalRecords());
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
        Author author = new Author("Hoxha", "Xhaho", Gender.FEMALE);
        Book book1 = new Book("asdasd", "Geri Kevi Hoxha", "aads", 50, 50,  10, author, true);

        Bill testBill1 = new Bill(1,date1,"NameTest");
        testBill1.addTotal(11,22);
        testBill1.addBook(book1.getTitle());

        Bill testBill2 = new Bill(2,date2,"NameTest");
        testBill2.addTotal(11,22);
        testBill2.addBook(book1.getTitle());


        Bill testBill3 = new Bill(3,date3,"NameTest");
        testBill3.addTotal(11,22);
        testBill3.addBook(book1.getTitle());


        Bill testBill4 = new Bill(4,date4,"NameTest");
        testBill4.addTotal(11,22);
        testBill4.addBook(book1.getTitle());


        Bill testBill5 = new Bill(4,date5,"NameTest");
        testBill5.addTotal(11,22);
        testBill5.addBook(book1.getTitle());


        assertEquals("", billControllerTest.printBookRecords(date4,date5));

        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill2);
        billControllerTest.writeBilltoFile(testBill3);
        billControllerTest.writeBilltoFile(testBill4);
        billControllerTest.writeBilltoFile(testBill5);
        assertEquals("Geri Kevi Hoxha\t22\t242.0\n" +
                "Geri Kevi Hoxha\t22\t242.0\n" +
                "Geri Kevi Hoxha\t22\t242.0\n", billControllerTest.printBookRecords(date4,date5));
    }

    @Test
    void totalbookIncome() {
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
        Author author = new Author("Hoxha", "Xhaho", Gender.FEMALE);
        Book book1 = new Book("asdasd", "Geri Kevi Hoxha", "aads", 50, 50,  10, author, true);

        Bill testBill1 = new Bill(1,date1,"NameTest");
        testBill1.addTotal(11,22);
        testBill1.addBook(book1.getTitle());

        Bill testBill2 = new Bill(2,date2,"NameTest");
        testBill2.addTotal(11,22);
        testBill2.addBook(book1.getTitle());


        Bill testBill3 = new Bill(3,date3,"NameTest");
        testBill3.addTotal(11,22);
        testBill3.addBook(book1.getTitle());


        Bill testBill4 = new Bill(4,date4,"NameTest");
        testBill4.addTotal(11,22);
        testBill4.addBook(book1.getTitle());


        Bill testBill5 = new Bill(4,date5,"NameTest");
        testBill5.addTotal(11,22);
        testBill5.addBook(book1.getTitle());
        assertEquals(0, billControllerTest.totalbookIncome(date4,date5));
        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill2);
        billControllerTest.writeBilltoFile(testBill3);
        billControllerTest.writeBilltoFile(testBill4);
        billControllerTest.writeBilltoFile(testBill5);
        assertEquals(726.0, billControllerTest.totalbookIncome(date4,date5));
    }
}