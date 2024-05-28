package modelT;

import org.junit.jupiter.api.Test;
import model.ProjectDocument;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ProjectDocument pd = new ProjectDocument("test", "test\\testing.txt");
        // TODO check name
        assertEquals("test\\testing.txt", pd.getFilepath(), "Filepath was " +
                                            "not set to the desired input");
    }
}
