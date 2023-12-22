package Controllers;

import Exceptions.InvalidCredentials;
import Roles.User;
import Roles.UserInterface;
import Roles.UserMock;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    static File testfile = null;
    UserController userControllerTest;
    static int count = 0;
    @BeforeEach
    void createTestFile() {
        count++;
        try{

            testfile = new File("testUsers"+ count +".dat");
            testfile.createNewFile();
        }
        catch(Exception e){
            //no i dont want to
        }

        userControllerTest = new UserController(testfile);
    }
    @AfterAll
    static void deleteTestFile(){
        for(int i=0;i<=count;i++){
            System.out.println(count);
            File deleteFile = new File("testUsers"+ (i+1) +".dat");
            deleteFile.deleteOnExit();
        }
    }

    @Test
    void writeUsertoFileTest() {
        UserMock testUser1 = new UserMock("Test1","Test1");
        assertTrue(userControllerTest.writeUsertoFile(testUser1));
        UserMock testUser2 = new UserMock("Test2","Test2");
        assertTrue(userControllerTest.writeUsertoFile(testUser2));
        UserController temp = new UserController(null);
        assertFalse(temp.writeUsertoFile(testUser2));
    }

    @Test
    void checkUserCredentialsTest() {
        UserMock testUser3 = new UserMock("Test3","Test3");
        assertTrue(userControllerTest.writeUsertoFile(testUser3));
        try{
            assertEquals(testUser3,userControllerTest.checkUserCredentials("Test3","Test3"));
            assertThrows( InvalidCredentials.class, () -> userControllerTest.checkUserCredentials("Test3","Test4"));
            assertThrows( InvalidCredentials.class, () -> userControllerTest.checkUserCredentials("Test4","Test3"));
        }
        catch(Exception ex){
            //
        }

    }

    @Test
    void getUserListTest() {
        ArrayList<UserInterface> list = new ArrayList<>();
        list.add(new UserMock("Test1","Test1"));
        list.add(new UserMock("Test2","Test2"));
        list.add(new UserMock("Test3","Test3"));
        UserMock testUser1 = new UserMock("Test1","Test1");
        UserMock testUser2 = new UserMock("Test2","Test2");
        UserMock testUser3 = new UserMock("Test3","Test3");
        userControllerTest.writeUsertoFile(testUser1);
        userControllerTest.writeUsertoFile(testUser2);
        userControllerTest.writeUsertoFile(testUser3);
        ArrayList<UserInterface>  userList = userControllerTest.getUserList();
        assertEquals(list,userList);
    }

    @Test
    void registerUserTest() {
        userControllerTest.registerUser(new UserMock("Test1","Test1"));
        userControllerTest = new UserController(testfile);
        ArrayList<UserInterface>  userList = userControllerTest.getUserList();
        ArrayList<UserInterface> list = new ArrayList<>();
        list.add(new UserMock("Test1","Test1"));
        assertEquals(list,userList);
    }

    @Test
    void deleteUserTest() {
        ArrayList<UserInterface> list = new ArrayList<>();
        list.add(new UserMock("Test1","Test1"));
        list.add(new UserMock("Test2","Test2"));
        list.add(new UserMock("Test3","Test3"));
        UserMock testUser1 = new UserMock("Test1","Test1");
        UserMock testUser2 = new UserMock("Test2","Test2");
        UserMock testUser3 = new UserMock("Test3","Test3");
        userControllerTest.writeUsertoFile(testUser1);
        userControllerTest.writeUsertoFile(testUser2);
        userControllerTest.writeUsertoFile(testUser3);
        assertTrue(userControllerTest.deleteUser(testUser3));
        userControllerTest = new UserController(null);
        assertFalse(userControllerTest.deleteUser(testUser3));
    }

    @Test
    void getTotalSalariesTest() {
        assertEquals(new ArrayList<UserInterface>(), userControllerTest.getUserList());
        userControllerTest.registerUser(new UserMock("Test1", "Test1"));
        userControllerTest.registerUser(new UserMock("Test2", "Test2"));
        userControllerTest.registerUser(new UserMock("Test3", "Test3"));
        assertEquals(900.0, userControllerTest.getTotalSalaries());
    }
}