package modelT;

import org.junit.*;
import model.ProjectList;
import model.Project;

/**
 * Unit tests for the ProjectList class.
 *
 * @author Bernard Bega
 * @version 1.0
 */
public class ProjectListTest {

    /**
     * Tests the ProjectList constructor.
     */
    @Test
    public void testProjectListConstructor() {
        ProjectList pl = new ProjectList();
        Assert.assertEquals(0, pl.getProjects().size());
    }

    /**
     * Tests the addProject method.
     */
    @Test
    public void testAddProject() {
        ProjectList pl = new ProjectList();
        Project p = new Project("Ha!", "I don't know what this is.", 49.94);
        pl.addProject(p);
        Assert.assertEquals(1, pl.getProjects().size());
    }
}
