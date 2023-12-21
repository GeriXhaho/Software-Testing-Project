package Roles;

import Helpers.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class UserTest {
    private static Calendar calendarTest = Calendar.getInstance();
    private static User userTest;


    @BeforeAll
    static void createCalendar(){
        calendarTest.setTimeInMillis(200000000);
        userTest = new User("Hoxha","Xhaho", calendarTest,"+355674801496","hxhaho21@epoka.edu.al",300, Role.Revoked,"12345");
    }

    @Test
    void getNameTest() {
        String name = "Hoxha";
        Assertions.assertEquals(userTest.getName(), name);
    }

    @Test
    void getPasswordTest() {
        String password="12345";
        Assertions.assertEquals(userTest.getPassword(), password);
    }

    @Test
    void getRoleTest() {
        Assertions.assertEquals(userTest.getRole(), Role.Revoked);

    }

    @Test
    void testToStringTest() {
            Assertions.assertEquals(userTest.toString(), "Hoxha Xhaho");
    }


    @Test
    void setFirstNameTest() {

    }

    @Test
    void getLastNameTest() {
        Assertions.assertEquals(userTest.getLastName(), "Xhaho");
    }

    @Test
    void setLastNameTest() {
    }

    @Test
    void getBirthdayTest() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(200000000);
        Assertions.assertTrue(userTest.getBirthday().compareTo(cal) == 0);
    }

    @Test
    void setBirthdayTest() {
    }

    @Test
    void getPhoneTest() {
        String Phone= "+355674801496";
        Assertions.assertEquals(userTest.getPhone(), Phone);
    }

    @Test
    void setPhoneTest() {

    }

    @Test
    void getEmailTest() {
        String Email="hxhaho21@epoka.edu.al";
        Assertions.assertEquals(userTest.getEmail(), Email);
    }

    @Test
    void setEmailTest() {
    }

    @Test
    void getSalaryTest() {
        int Salary= 300;
        Assertions.assertEquals(userTest.getSalary(), Salary);
    }

    @Test
    void setSalaryTest() {
    }

    @Test
    void setRoleTest() {
    }

    @Test
    void setPasswordTest() {
    }

    @Test
    void getAccesslevelTest() {
        int access= 0;
        Assertions.assertEquals(userTest.getAccesslevel(), access);
    }

    @Test
    void setAccesslevelTest() {
    }
}