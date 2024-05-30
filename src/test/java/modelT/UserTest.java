package modelT;

import model.User;
import org.junit.*;

/**
 * Unit tests for the User class.
 *
 * @author Bernard Bega
 * @version 1.0
 */
public class UserTest {

    /**
     * Tests User constructor.
     */
    @Test
    public void testUserConstructor() {
        User user = new User("test", "1234");
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
    }

    @Test
    public void testGetUsername() {


    }

    @Test
    public void testSetUsername() {

    }

    @Test
    public void testGetPassword() {

    }

    @Test
    public void testSetPassword() {

    }

    @Test
    public void testGetFirstName() {

    }

    @Test
    public void testSetFirstName() {

    }

    @Test
    public void testGetEmail() {

    }

    @Test
    public void testSetEmail() {

    }

    @Test
    public void testAuthenticate() {

    }
}