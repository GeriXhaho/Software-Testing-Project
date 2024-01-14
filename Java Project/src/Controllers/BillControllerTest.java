package Controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BillControllerTest {
    static File testfile = null;
    BillController billController;
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
        billController = new BillController(testfile);
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

    }

    @Test
    void writeBilltoTxt() {
    }

    @Test
    void billtoString() {
    }

    @Test
    void calculateIdNumber() {
    }

    @Test
    void billDate() {
    }

    @Test
    void setDate() {
    }

    @Test
    void printLibrarianRecords() {
    }

    @Test
    void totalRecords() {
    }

    @Test
    void printBookRecords() {
    }

    @Test
    void totalbookIncome() {
    }
}