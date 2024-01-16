package TestSourcesRoot;

import Directories.Helpers.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void valuesTest() {
        Assertions.assertEquals(Role.values()[0], Role.Revoked);
    }

    @Test
    void valueOfTest() {
        assertEquals(Role.valueOf("Revoked"), Role.Revoked);
    }
}