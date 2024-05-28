package test.java.modelT;

import model.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("test", p.getName(), "Project name did not get set to " +
                                                    "the desired input.");
        assertEquals("testing", p.getDescription(), "Project description did" +
                                        " not get set to the desired input");
    }

    /**
     * Tests the setName method.
     */
    @Test
    public void testSetName() {
        Project p = new Project("test", "testing");
        p.setName("new name");
        assertEquals("new name", p.getName(), "Project name did not get set " +
                                                    "to the desired input");
        assertEquals("testing", p.getDescription(), "Changing the name " +
                                                    "mutated the description");
    }

    /**
     * Tests the setDescription method.
     */
    @Test
    public void testSetDescription() {
        Project p = new Project("test", "testing");
        p.setDescription("new description");
        assertEquals("test", p.getName(), "Changing the description mutated " +
                                                    "the name");
        assertEquals("testing", p.getDescription(), "Project description did" +
                                    "not get changed to the desired input");
    }

    // TODO make a setBudget test
}
