package modelT;

import model.LoginModel;
import org.junit.*;

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
        Assert.assertEquals("", lm.getUsername());
        Assert.assertEquals("", lm.getPin());
        Assert.assertEquals(false, lm.isLoggedIn());
    }

    /**
     * Tests the setUsername method.
     */
    @Test
    public void testSetUsername() {
        LoginModel lm = new LoginModel();
        lm.setUsername("Bernard");
        Assert.assertEquals("Bernard", lm.getUsername());
        Assert.assertEquals("", lm.getPin());
        Assert.assertEquals(false, lm.isLoggedIn());
    }

    /**
     * Tests the setPIN method.
     */
    @Test
    public void testSetPIN() {
        LoginModel lm = new LoginModel();
        lm.setPin("1234");
        Assert.assertEquals("", lm.getUsername());
        Assert.assertEquals("1234", lm.getPin());
        Assert.assertEquals(false, lm.isLoggedIn());
    }

    /**
     * Tests the setLoggedIn method.
     */
    @Test
    public void testSetLoggedIn() {
        LoginModel lm = new LoginModel();
        lm.setLoggedIn(true);
        Assert.assertEquals("", lm.getUsername());
        Assert.assertEquals("", lm.getPin());
        Assert.assertEquals(true, lm.isLoggedIn());
    }

    /**
     * Tests the authenticate method when it should fail.
     */
    @Test
    public void testAuthenticateFails() {
        LoginModel lm = new LoginModel();
        Assert.assertEquals(false, lm.authenticate());
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
        Assert.assertEquals(true, lm.authenticate());
    }
}
