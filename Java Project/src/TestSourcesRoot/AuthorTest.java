package TestSourcesRoot;

import Directories.Helpers.Author;
import Directories.Helpers.Gender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AuthorTest {

    private static Author authorTest;

    @BeforeAll
    static void createAuthorTest(){
         authorTest = new Author("Geri", "Hoxha", Gender.FEMALE);
    }
    @Test
    @Order(1)
    void getFirstNameTest(){
        String name = "Geri";
        Assertions.assertEquals(authorTest.getFirstName(), name);
    }


    @Test
    @Order(2)
    void getLastNameTest(){
        String lastname = "Hoxha";
        Assertions.assertEquals(authorTest.getLastName(), lastname);
    }

    @Test
    @Order(3)
    void getGenderTest(){
        Assertions.assertEquals(authorTest.getGender(), Gender.FEMALE);
    }

    @Test
    @Order(4)
    void testToStringTest() {
        Assertions.assertEquals(authorTest.toString(), "Geri Hoxha");
    }

    @Test
    @Order(5)
    void setFirstNameTest(){
        authorTest.setFirstName("Henri");
        Assertions.assertEquals(authorTest.getFirstName(), "Henri");
    }

    @Test
    @Order(6)
    void setLastNameTest(){
        authorTest.setLastName("Xhaho");
        Assertions.assertEquals(authorTest.getLastName(), "Xhaho");
    }

    @Test
    @Order(7)
    void setGenderTest(){
        authorTest.setGender(Gender.MALE);
        Assertions.assertEquals(authorTest.getGender(), Gender.MALE);
    }










}
