package Controllers;

import Helpers.BillMock;
import Helpers.BookMock;
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

class BillControllerTest {
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
        BillMock testBill1 = new BillMock(1, date1, "Hoxha Geri");
        assertTrue(billControllerTest.writeBilltoFile(testBill1));
        testBill1.total = 0;
        assertFalse(billControllerTest.writeBilltoFile(testBill1));
        BillController temp = new BillController(null);
        testBill1.total = 1500;
        assertFalse(temp.writeBilltoFile(testBill1));
    }

    @Test
    void billtoString() {
        BookMock testbook1 = new BookMock();
        assertEquals("Geri Kevi Hoxha by Hoxha Xhaho\t\t\t10 x 50.0 leke\n", billControllerTest.billtoString(testbook1, 10));
    }

    @Test
    void calculateIdNumber() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 14);
        Date date1 = cal.getTime();
        BillMock testBill1 = new BillMock(1, date1, "Hoxha Geri");
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
        UserMock user1 = new UserMock("Test1","1234");
        UserMock user2 = new UserMock("Test2","1234");
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
        BillMock testBill1 = new BillMock(1,date1,"Test1");
        BillMock testBill2 = new BillMock(2,date2,"Test1");
        BillMock testBill3 = new BillMock(3,date3,"Test1");
        BillMock testBill4 = new BillMock(4,date4,"Test1");
        BillMock testBill5 = new BillMock(4,date5,"Test1");
        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill2);
        billControllerTest.writeBilltoFile(testBill3);
        billControllerTest.writeBilltoFile(testBill4);
        billControllerTest.writeBilltoFile(testBill5);
        assertEquals("Test1\t3\t\t30\t\t\t4500.0 $\n" +
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
        BillMock testBill1 = new BillMock(1,date1,"NameTest");
        BillMock testBill2 = new BillMock(2,date2,"NameTest");
        BillMock testBill3 = new BillMock(3,date3,"NameTest");
        BillMock testBill4 = new BillMock(4,date4,"NameTest");
        BillMock testBill5 = new BillMock(4,date5,"NameTest");
        assertEquals("", billControllerTest.printBookRecords(date4,date5));
        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill2);
        billControllerTest.writeBilltoFile(testBill3);
        billControllerTest.writeBilltoFile(testBill4);
        billControllerTest.writeBilltoFile(testBill5);
        assertEquals("Test1\t6\t900.0\n" +
                "Test2\t4\t600.0\n" +
                "Test1\t6\t900.0\n" +
                "Test2\t4\t600.0\n" +
                "Test1\t6\t900.0\n" +
                "Test2\t4\t600.0\n", billControllerTest.printBookRecords(date4,date5));
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
        BillMock testBill1 = new BillMock(1,date1,"NameTest");
        BillMock testBill2 = new BillMock(2,date2,"NameTest");
        BillMock testBill3 = new BillMock(3,date3,"NameTest");
        BillMock testBill4 = new BillMock(4,date4,"NameTest");
        BillMock testBill5 = new BillMock(4,date5,"NameTest");
        assertEquals(0, billControllerTest.totalbookIncome(date4,date5));
        billControllerTest.writeBilltoFile(testBill1);
        billControllerTest.writeBilltoFile(testBill2);
        billControllerTest.writeBilltoFile(testBill3);
        billControllerTest.writeBilltoFile(testBill4);
        billControllerTest.writeBilltoFile(testBill5);
        assertEquals(4500.0, billControllerTest.totalbookIncome(date4,date5));
    }
}