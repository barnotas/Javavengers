package test.java.modelT;

import model.About;
import org.junit.Test;
import org.junit.*;

/**
 * Unit tests for the About class.
 *
 * @author Bernard Bega
 * @version 1.0
 */
public class AboutTest {

    /** An About object used for testing. */
    private final About A = new About();

    /**
     * Tests the getVersion method.
     */
    @Test
    public void testGetVersion() {
        Assert.assertEquals(0.1, A.getVersion(), 0.1);
    }

    /**
     * Tests the getDevTeam method.
     */
    @Test
    public void testGetDevTeam() {
        Assert.assertEquals("Javavengers", A.getDevTeam());
    }

    /**
     * Tests the getDevs method.
     */
    @Test
    public void testGetDevs(){
        Assert.assertEquals(new String[]{"Ahmed Hassan", "Barno Tashpulatova",
                        "Bernard Bega", "Mahri Yalkapova"}, A.getDevs());
    }
}
