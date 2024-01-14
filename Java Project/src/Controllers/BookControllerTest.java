package Controllers;

import Helpers.Author;
import Helpers.BookInterface;
import Helpers.BookMock;
import Helpers.Gender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.ArrayEquals;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {
    static File testfile = null;
    BookController bookControllerTest;
    static int count = 0;
    @BeforeEach
    void createTestFile() {
        count++;
        try{

            testfile = new File("testBooks"+ count +".dat");
            testfile.createNewFile();
        }
        catch(Exception e){
            //no I don't want to
        }
        bookControllerTest = new BookController(testfile);
    }
    @AfterAll
    static void deleteTestFile(){
        for(int i=0;i<=count;i++){
            System.out.println(count);
            File deleteFile = new File("testBooks"+ (i+1) +".dat");
            deleteFile.deleteOnExit();
        }
    }
    @Test
    void writeBooktoFile() {
        BookMock testBook1 = new BookMock();
        assertTrue(bookControllerTest.writeBooktoFile(testBook1));
        assertFalse(bookControllerTest.writeBooktoFile(testBook1));
        BookController temp = new BookController(null);
        assertFalse(temp.writeBooktoFile(testBook1));
    }

    @Test
    void getAvailableAuthors() {
        Author[] authors = new Author[] {
                new Author("Fyodor", "Dostoevsky", Gender.MALE),
                new Author("Noam", "Chomsky", Gender.MALE),
                new Author("Anne", "Applebaum", Gender.FEMALE),
                new Author("George", "Orwell", Gender.MALE) };
        assertArrayEquals(authors,bookControllerTest.getAvailableAuthors());
    }

    @Test
    void getBookCollection() {
        BookMock testBook1 = new BookMock();
        bookControllerTest.writeBooktoFile(testBook1);
        assertEquals("17456890 Geri Kevi Hoxha by Hoxha Xhaho x 17\n", bookControllerTest.getBookCollection());
    }

    @Test
    void getBookList() {
        BookMock testBook1 = new BookMock();
        ArrayList<BookInterface> booklist = new ArrayList<>();
        booklist.add(testBook1);
        bookControllerTest.writeBooktoFile(testBook1);
        assertEquals(booklist, bookControllerTest.getBookList());
    }
}