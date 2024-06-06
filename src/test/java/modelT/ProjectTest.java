package modelT;

import model.Project;
import model.ProjectDocument;
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
        Project p = new Project("test", "testing", 99.99);
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("testing", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
    }

    /**
     * Tests the setName method.
     */
    @Test
    public void testSetName() {
        Project p = new Project("test", "testing", 99.99);
        p.setName("new name");
        Assert.assertEquals("new name", p.getName());
        Assert.assertEquals("testing", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
    }

    /**
     * Tests the setDescription method.
     */
    @Test
    public void testSetDescription() {
        Project p = new Project("test", "testing", 99.99);
        p.setDescription("new description");
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("new description", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
    }

    /**
     * Tests the setPIN method.
     */
    @Test
    public void testSetPIN() {
        Project p = new Project("test", "testing", 99.99);
        p.setPin("1234");
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("testing", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
        Assert.assertEquals("1234", p.getPin());
    }

    /**
     * Tests the setPrivate method.
     */
    @Test
    public void testSetPrivate() {
        Project p = new Project("test", "testing", 99.99);
        p.setPrivate(true);
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("testing", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
        Assert.assertEquals(true, p.isPrivate());
    }

    /**
     * Tests the addDocument method.
     */
    @Test
    public void testAddDocument() {
        Project p = new Project("test", "testing", 99.99);
        ProjectDocument pd = new ProjectDocument("something.yes", "place/child");
        p.addDocument(pd);
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("testing", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
        Assert.assertEquals(1, p.getDocuments().size());
    }

    /**
     * Tests the removeDocument method.
     */
    @Test
    public void testRemoveDocument() {
        Project p = new Project("test", "testing", 99.99);
        ProjectDocument pd = new ProjectDocument("something.yes", "place/child")
        p.addDocument(pd);
        p.removeDocument(pd);
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("testing", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
        Assert.assertEquals(0, p.getDocuments().size());
    }

    /**
     * Tests the addExpense method.
     */
    @Test
    public void testAddExpense() {
        Project p = new Project("test", "testing", 99.99);
        p.addExpense(50);
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("testing", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
        Assert.assertEquals(50, p.getExpenses());
    }

    /**
     * Tests the setExpenses method.
     */
    @Test
    public void testAddExpense() {
        Project p = new Project("test", "testing", 99.99);
        p.setExpenses(100);
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals("testing", p.getDescription());
        Assert.assertEquals(99.99, p.getBudget());
        Assert.assertEquals(100, p.getExpenses());
    }
}
