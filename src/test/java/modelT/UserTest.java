package modelT;

import model.Project;
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
     * Tests the User constructor.
     */
    @Test
    public void testUserConstructor() {
        User user = new User("test", "1234", "someemail@email.com", "user");
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
        Assert.assertEquals("someemail@email.com", user.getEmail());
        Assert.assertEquals("user", user.getFirstName());
        Assert.assertEquals(0, user.getProjectList().getProjects().size());
    }

    /**
     * Tests the addProject method.
     */
    @Test
    public void testAddProject() {
        User user = new User("test", "1234", "someemail@email.com", "user");
        user.addProject(new Project("test", "test", 12.34));
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
        Assert.assertEquals("someemail@email.com", user.getEmail());
        Assert.assertEquals("user", user.getFirstName());
        Assert.assertEquals(1, user.getProjectList().getProjects().size());
    }

    /**
     * Tests the removeProject method.
     */
    @Test
    public void testRemoveProject() {
        User user = new User("test", "1234", "someemail@email.com", "user");
        Project p = new Project("test", "test", 12.34)
        user.addProject(p);
        user.removeProject(p);
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
        Assert.assertEquals("someemail@email.com", user.getEmail());
        Assert.assertEquals("user", user.getFirstName());
        Assert.assertEquals(0, user.getProjectList().getProjects().size());
    }

    /**
     * Tests the setUsername method.
     */
    @Test
    public void testSetUsername() {
        User user = new User("test", "1234", "someemail@email.com", "user");
        user.setUsername("Haha");
        Assert.assertEquals("Haha", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
        Assert.assertEquals("someemail@email.com", user.getEmail());
        Assert.assertEquals("user", user.getFirstName());
        Assert.assertEquals(0, user.getProjectList().getProjects().size());
    }

    /**
     * Tests the setPassword method.
     */
    @Test
    public void testSetPassword() {
        User user = new User("test", "1234", "someemail@email.com", "user");
        user.setPassword("4444");
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("4444", user.getPassword());
        Assert.assertEquals("someemail@email.com", user.getEmail());
        Assert.assertEquals("user", user.getFirstName());
        Assert.assertEquals(0, user.getProjectList().getProjects().size());
    }

    /**
     * Tests the setEmail method.
     */
    @Test
    public void testSetEmail() {
        User user = new User("test", "1234", "someemail@email.com", "user");
        user.setEmail("yahooman@yahoo.com");
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
        Assert.assertEquals("yahooman@yahoo.com", user.getEmail());
        Assert.assertEquals("user", user.getFirstName());
        Assert.assertEquals(0, user.getProjectList().getProjects().size());
    }

    /**
     * Tests the authenticate method
     */
    @Test
    public void testAuthenticate() {
        User user = new User("test", "1234", "someemail@email.com", "user");
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
        Assert.assertEquals("someemail@email.com", user.getEmail());
        Assert.assertEquals("user", user.getFirstName());
        Assert.assertEquals(0, user.getProjectList().getProjects().size());
        Assert.assertEquals(true, user.authenticate("test", "1234"));
    }

    /**
     * Tests the setFirstName method.
     */
    @Test
    public void testSetFirstName() {
        User user = new User("test", "1234", "someemail@email.com", "user");
        user.setFirstName("Waaaah!");
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
        Assert.assertEquals("someemail@email.com", user.getEmail());
        Assert.assertEquals("Waaaah!", user.getFirstName());
        Assert.assertEquals(0, user.getProjectList().getProjects().size());
    }
}
