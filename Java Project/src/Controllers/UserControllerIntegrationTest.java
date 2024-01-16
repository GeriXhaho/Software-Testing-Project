package Controllers;

import Exceptions.InvalidCredentials;
import Helpers.Role;
import Roles.User;
import Roles.UserInterface;
import Roles.UserMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import static Controllers.UserControllerTest.count;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerIntegrationTest {
    static File testfile= null;
    UserController userControllerIntegrationTest;
    static int count = 0;
    @BeforeEach
    void createTestFile(){
        count++;
        try{
            testfile= new File("testUsers"+ count + ".dat");
            testfile.createNewFile();

        }
        catch (Exception e){
            //No, I don't want to
        }
        userControllerIntegrationTest = new UserController(testfile);
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
void writeUsertoFileTest(){
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 2003);
    calendar.set(Calendar.MONTH, Calendar.APRIL); // Note that months are zero-based, so April is represented by 3
    calendar.set(Calendar.DAY_OF_MONTH, 7);
    User testUser1= new User("Hoxha", "Kevi",calendar,"+355 67 480 1496","kcela21@epoka.edu.al", 20, Role.Admin, "123");
    assertTrue(userControllerIntegrationTest.writeUsertoFile(testUser1));
    User testUser2= new User("Kevi", "Hoxha",calendar,"+355 69 696 9696","hhoxha21@epoka.edu.al", 30, Role.Librarian, "123");
    assertTrue(userControllerIntegrationTest.writeUsertoFile(testUser2));
    UserController temp= new UserController(null);
    assertFalse(temp.writeUsertoFile(testUser2));

}
@Test
    void checkUserCredentialsTest() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 2002);
    calendar.set(Calendar.MONTH, Calendar.JUNE); // Note that months are zero-based, so April is represented by 3
    calendar.set(Calendar.DAY_OF_MONTH, 20);
    User testUser3 = new User("Hoxha", "Kevi", calendar, "+355 67 480 1496", "kcela21@epoka.edu.al", 20, Role.Manager, "123");
    assertTrue(userControllerIntegrationTest.writeUsertoFile(testUser3));
    try {
        assertEquals(testUser3, userControllerIntegrationTest.checkUserCredentials(testUser3.getName(), testUser3.getPassword()));

        assertThrows(InvalidCredentials.class, () -> userControllerIntegrationTest.checkUserCredentials("Hoxha", "1234"));
        assertThrows(InvalidCredentials.class, () -> userControllerIntegrationTest.checkUserCredentials("Kevi", "123"));

    } catch (Exception ex) {
        //
    }
}
    @Test
    void getUserListTest() {

        ArrayList<UserInterface> list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2002);
        calendar.set(Calendar.MONTH, Calendar.JUNE); // Note that months are zero-based, so April is represented by 3
        calendar.set(Calendar.DAY_OF_MONTH, 20);

        User testUser1= new User("Name1", "Surname1",calendar,"+355 67 480 1496","kcela21@epoka.edu.al", 20, Role.Admin, "123");
        User testUser2= new User("Name2", "Surname2",calendar,"+355 69 696 9696","hhoxha21@epoka.edu.al", 30, Role.Librarian, "123");
        User testUser3 = new User("Name3", "Surname3", calendar, "+355 67 480 1496", "kcela21@epoka.edu.al", 20, Role.Manager, "123");

        list.add(testUser1);
        list.add(testUser2);
        list.add(testUser3);

        userControllerIntegrationTest.writeUsertoFile(testUser1);
        userControllerIntegrationTest.writeUsertoFile(testUser2);
        userControllerIntegrationTest.writeUsertoFile(testUser3);
        ArrayList<UserInterface>  userList = userControllerIntegrationTest.getUserList();
        assertEquals(list,userList);
    }
    @Test
    void registerUserTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2002);
        calendar.set(Calendar.MONTH, Calendar.JUNE); // Note that months are zero-based, so April is represented by 3
        calendar.set(Calendar.DAY_OF_MONTH, 20);

        User testUser1= new User("Name1", "Surname1",calendar,"+355 67 480 1496","kcela21@epoka.edu.al", 20, Role.Admin, "123");

        userControllerIntegrationTest.registerUser(testUser1);
        userControllerIntegrationTest = new UserController(testfile);
        ArrayList<UserInterface>  userList = userControllerIntegrationTest.getUserList();
        ArrayList<UserInterface> list = new ArrayList<>();

        list.add(testUser1);

        assertEquals(list,userList);


    }
    @Test
    void deleteUserTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2002);
        calendar.set(Calendar.MONTH, Calendar.JUNE); // Note that months are zero-based, so April is represented by 3
        calendar.set(Calendar.DAY_OF_MONTH, 20);

        User testUser1= new User("Name1", "Surname1",calendar,"+355 67 480 1496","kcela21@epoka.edu.al", 20, Role.Admin, "123");
        User testUser2= new User("Name2", "Surname2",calendar,"+355 69 696 9696","hhoxha21@epoka.edu.al", 30, Role.Librarian, "123");
        User testUser3 = new User("Name3", "Surname3", calendar, "+355 67 480 1496", "kcela21@epoka.edu.al", 20, Role.Manager, "123");

        ArrayList<UserInterface> list = new ArrayList<>();
        list.add(testUser1);
        list.add(testUser2);
        list.add(testUser3);

        userControllerIntegrationTest.writeUsertoFile(testUser1);
        userControllerIntegrationTest.writeUsertoFile(testUser2);
        userControllerIntegrationTest.writeUsertoFile(testUser3);
        assertTrue(userControllerIntegrationTest.deleteUser(testUser3));
        userControllerIntegrationTest = new UserController(null);
        assertFalse(userControllerIntegrationTest.deleteUser(testUser3));
    }
    @Test
    void getTotalSalariesTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2002);
        calendar.set(Calendar.MONTH, Calendar.JUNE); // Note that months are zero-based, so April is represented by 3
        calendar.set(Calendar.DAY_OF_MONTH, 20);

        User testUser1= new User("Name1", "Surname1",calendar,"+355 67 480 1496","kcela21@epoka.edu.al", 20, Role.Admin, "123");
        User testUser2= new User("Name2", "Surname2",calendar,"+355 69 696 9696","hhoxha21@epoka.edu.al", 30, Role.Librarian, "123");
        User testUser3 = new User("Name3", "Surname3", calendar, "+355 67 480 1496", "kcela21@epoka.edu.al", 20, Role.Manager, "123");

        assertEquals(new ArrayList<UserInterface>(), userControllerIntegrationTest.getUserList());
        userControllerIntegrationTest.registerUser(testUser1);
        userControllerIntegrationTest.registerUser(testUser2);
        userControllerIntegrationTest.registerUser(testUser3);
        assertEquals(70, userControllerIntegrationTest.getTotalSalaries());
    }

}
