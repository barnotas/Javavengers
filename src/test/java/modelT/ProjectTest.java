package modelT;

import model.Project;
import org.junit.*;

/**
 * Unit tests for the Project class.
 *
 * @author Bernard Bega
 * @version 1.0
 */
public class ProjectTest {

    /**
     * Tests the Project constructor.
     */
    @Test
    public void testProjectConstructor() {
        Project p = new Project("test", "testing");
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("testing", p.getDescription());
    }

    /**
     * Tests the setName method.
     */
    @Test
    public void testSetName() {
        Project p = new Project("test", "testing");
        p.setName("new name");
        Assert.assertEquals("new name", p.getName());
        Assert.assertEquals("testing", p.getDescription());
    }

    /**
     * Tests the setDescription method.
     */
    @Test
    public void testSetDescription() {
        Project p = new Project("test", "testing");
        p.setDescription("new description");
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("new description", p.getDescription());
    }
}
