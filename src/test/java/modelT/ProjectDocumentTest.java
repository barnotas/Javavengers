package modelT;

import model.ProjectDocument;
import org.junit.*;

/**
 * Unit tests for the ProjectDocument class.
 *
 * @author Bernard Bega
 * @version 1.0
 */
public class ProjectDocumentTest {

    /**
     * Tests the ProjectDocument constructor
     */
    @Test
    public void testProjectDocumentConstructor() {
        ProjectDocument pd = new ProjectDocument("something.yes", "place\\somewhere");
        Assert.assertEquals("something.yes", pd.name());
        Assert.assertEquals("place\\somewhere", pd.getFilepath());
    }
}
