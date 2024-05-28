package test.java.modelT;

import model.LoginModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LoginModel class.
 *
 * @author Bernard Bega
 * @version 1.0
 */
public class LoginModelTest {

    /**
     * Tests the LoginModel constructor.
     */
    @Test
    public void testLoginModelConstructor() {
        LoginModel lm = new LoginModel();
        assertEquals("", lm.getUsername(), "Username is not set to empty");
        assertEquals("", lm.getPin(), "PIN is not set to empty");
        assertFalse(lm.isLoggedIn(), "Login status is not set to false");
    }

    /**
     * Tests the setUsername method.
     */
    @Test
    public void testSetUsername() {
        LoginModel lm = new LoginModel();
        lm.setUsername("Bernard");
        assertEquals("Bernard", lm.getUsername(), "Username did not change " +
                                                            "to the desired input");
        assertEquals("", lm.getPin(), "Changing the username mutated the PIN");
        assertFalse(lm.isLoggedIn(), "Changing the username mutated the " +
                                        "login state");
    }

    /**
     * Tests the setPIN method.
     */
    @Test
    public void testSetPIN() {
        LoginModel lm = new LoginModel();
        lm.setPin("1234");
        assertEquals("", lm.getUsername(), "Changing the PIN mutated the " +
                                                    "username");
        assertEquals("1234", lm.getPin(), "PIN did not change to the desired" +
                                                    " input");
        assertFalse(lm.isLoggedIn(), "Changing the PIN mutated the login " +
                                        "state");
    }

    /**
     * Tests the setLoggedIn method.
     */
    @Test
    public void testSetLoggedIn() {
        LoginModel lm = new LoginModel();
        lm.setLoggedIn(true);
        assertEquals("", lm.getUsername(), "Changing the login state mutated" +
                " the username");
        assertEquals("", lm.getPin(), "Changing the login state mutated the " +
                                                "PIN");
        assertTrue(lm.isLoggedIn(), "Login state did not change to the " +
                                    "desired state");
    }

    /**
     * Tests the authenticate method when it should fail.
     */
    @Test
    public void testAuthenticateFails() {
        LoginModel lm = new LoginModel();
        assertFalse(lm.authenticate(), "Authentication passed for an invalid" +
                                        " user or the default user is " +
                                        "authorized");
    }

    /**
     * Tests the authenticate method when it should pass.
     */
    @Test
    public void testAuthenticatePasses() {
        LoginModel lm = new LoginModel();
        // The username and PIN used here depend on what is used in the
        // authenticate method.
        lm.setUsername("admin");
        lm.setPin("1234");
        assertTrue(lm.authenticate(), "Authentication failed");
    }
}
