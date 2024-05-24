package test.java.modelT;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.About;
//import org.junit.Test;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the About class.
 *
 * @author Bernard Bega
 * @version 1.0
 */
public class AboutTest {

    /** An About object used for testing. */
    private About A = new About();

    /**
     * Tests the getVersion method.
     */
    @Test
    public void testGetVersion() {
        assertEquals(0.1, A.getVersion());
    }

    /**
     * Tests the getDevTeam method.
     */
    @Test
    public void testGetDevTeam() {
        assertEquals("Javavengers", A.getDevTeam());
    }

    /**
     * Tests the getDevs method.
     */
    @Test
    public void testGetDevs(){
        assertArrayEquals(new String[]{"Ahmed Hassan", "Barno Tashpulatova",
                        "Bernard Bega", "Mahri Yalkapova"}, A.getDevs());
    }
}
