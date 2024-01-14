package Controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

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
            //no i dont want to
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
    }

    @Test
    void checkifOrderexists() {
    }

    @Test
    void calculateTotal() {
    }

    @Test
    void calculateIdNumber() {
    }

    @Test
    void orderDate() {
    }

    @Test
    void setDate() {
    }

    @Test
    void printBookRecords() {
    }

    @Test
    void totalBookExpenses() {
    }
}