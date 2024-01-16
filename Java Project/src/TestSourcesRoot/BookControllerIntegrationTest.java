package TestSourcesRoot;

import Directories.Controllers.BookController;
import Directories.Helpers.Author;
import Directories.Helpers.Book;
import Directories.Helpers.BookInterface;
import Directories.Helpers.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerIntegrationTest {
    static File testfile = null;
    BookController bookControllerTest;
    static int count = 0;
    @BeforeEach
    void createTestFile() {
        count++;
        try{

            testfile = new File("testBooksIntegrated"+ count +".dat");
            testfile.createNewFile();
        }
        catch(Exception e){
            //no I don't want to
        }
        bookControllerTest = new BookController(testfile);
    }
    @AfterEach
    void deleteTestFile(){
        for(int i=0;i<=count;i++){
            System.out.println(count);
            File deleteFile = new File("testBooksIntegrated"+ (i+1) +".dat");
            deleteFile.deleteOnExit();
        }
    }
    @Test
    void writeBooktoFile() {
        Author author = new Author("Hoxha", "Xhaho", Gender.FEMALE);
        Book testBook1 = new Book("asdasd", "Geri Kevi Hoxha", "aads", 50, 50,  10, author, true);
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
        Author author = new Author("Hoxha", "Xhaho", Gender.FEMALE);
        Book testBook1 = new Book("asdasd", "Geri Kevi Hoxha", "aads", 50, 50,  10, author, true);
        bookControllerTest.writeBooktoFile(testBook1);
        assertEquals("asdasd Geri Kevi Hoxha by Hoxha Xhaho x 10\n", bookControllerTest.getBookCollection());
    }

    @Test
    void getBookList() {
        Author author = new Author("Hoxha", "Xhaho", Gender.FEMALE);
        Book testBook1 = new Book("asdasd", "Geri Kevi Hoxha", "aads", 50, 50,  10, author, true);
        ArrayList<BookInterface> booklist = new ArrayList<>();
        booklist.add(testBook1);
        bookControllerTest.writeBooktoFile(testBook1);
        assertEquals(booklist, bookControllerTest.getBookList());
}
}