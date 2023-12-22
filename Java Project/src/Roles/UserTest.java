package Roles;

import Helpers.Role;
import org.junit.jupiter.api.*;

import java.util.Calendar;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    private static Calendar calendarTest = Calendar.getInstance();
    private static User userTest;


    @BeforeAll
    static void createCalendar(){
        calendarTest.setTimeInMillis(200000000);
        userTest = new User("Hoxha","Xhaho", calendarTest,"+355674801496","hxhaho21@epoka.edu.al",300, Role.Revoked,"12345");
    }



    @Test
    @Order(1)
    void getNameTest() {
        String name = "Hoxha";
        Assertions.assertEquals(userTest.getName(), name);
    }

    @Test
    @Order(2)
    void getPasswordTest() {
        String password="12345";
        Assertions.assertEquals(userTest.getPassword(), password);
    }

    @Test
    @Order(3)
    void getRoleTest() {
        Assertions.assertEquals(userTest.getRole(), Role.Revoked);

    }

    @Test
    @Order(4)
    void testToStringTest() {
            Assertions.assertEquals(userTest.toString(), "Hoxha Xhaho");
    }


    @Test
    void setFirstNameTest() {
        userTest.setFirstName("Geri");
        Assertions.assertEquals(userTest.getName(), "Geri");

    }

    @Test
    @Order(5)
    void getLastNameTest() {
        Assertions.assertEquals(userTest.getLastName(), "Xhaho");
    }

    @Test
    void setLastNameTest() {
        userTest.setLastName("Hoxha");
        Assertions.assertEquals(userTest.getLastName(), "Hoxha");
    }

    @Test
    @Order(6)
    void getBirthdayTest() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(200000000);
        Assertions.assertTrue(userTest.getBirthday().compareTo(cal) == 0);
    }

    @Test
    @Order(17)
    void setBirthdayTest() {
        Calendar birthday= Calendar.getInstance();
        birthday.setTimeInMillis(200);

        userTest.setBirthday(birthday);
        Assertions.assertEquals(userTest.getBirthday(), birthday );
    }

    @Test
    @Order(7)
    void getPhoneTest() {
        String Phone= "+355674801496";
        Assertions.assertEquals(userTest.getPhone(), Phone);
    }

    @Test
    @Order(16)
    void setPhoneTest() {
        String Phone="+355692463103";
        userTest.setPhone(Phone);
        Assertions.assertEquals(userTest.getPhone(), Phone);

    }

    @Test
    @Order(8)
    void getEmailTest() {
        String Email="hxhaho21@epoka.edu.al";
        Assertions.assertEquals(userTest.getEmail(), Email);
    }

    @Test
    @Order(15)
    void setEmailTest() {
        String Email="gxhaho21@epoka.edu.al";
        userTest.setEmail(Email);
        Assertions.assertEquals(userTest.getEmail(), Email);
    }

    @Test
    @Order(9)
    void getSalaryTest() {
        int Salary= 300;
        Assertions.assertEquals(userTest.getSalary(), Salary);
    }

    @Test
    @Order(13)
    void setSalaryTest() {
        int Salary = 400;
        userTest.setSalary(Salary);
        Assertions.assertEquals(userTest.getSalary(), Salary);


    }

    @Test
    @Order(14)
    void setRoleTest() {
        userTest.setRole(Role.Librarian);
        Assertions.assertEquals(userTest.getRole(), Role.Librarian);
    }

    @Test
    @Order(12)
    void setPasswordTest() {
        String password = "Easy guess";
        userTest.setPassword(password);
        Assertions.assertEquals(userTest.getPassword(), password);

    }

    @Test
    @Order(10)
    void getAccesslevelTest() {
        int access= 0;
        Assertions.assertEquals(userTest.getAccesslevel(), access);
    }

    @Test
    @Order(11)
    void setAccesslevelTest() {
        int access= 2;
        userTest.setAccesslevel(access);
        Assertions.assertEquals(userTest.getAccesslevel(), access);
    }
}