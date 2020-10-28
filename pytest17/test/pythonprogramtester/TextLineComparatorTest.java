package pythonprogramtester;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Tim
 */
public class TextLineComparatorTest {
    TextLineComparator comp1 = new TextLineComparator( "hello", "hello" ); 
    TextLineComparator comp2 = new TextLineComparator( "Hello", "hello" );
    TextLineComparator comp3 = new TextLineComparator( "hello ", "hello" );
    TextLineComparator comp4 = new TextLineComparator( "he llo", "hello" );
    TextLineComparator comp5 = new TextLineComparator( "hello", "goodbye" );
    // TODO these are for testing equalValue - must first be implemented
    // TextLineComparator comp6 = new TextLineComparator( "100 miles", "1.906 kilometers" );
    // TextLineComparator comp7 = new TextLineComparator( "100 kilometers", "1.906 miles" );
    // TextLineComparator comp8 = new TextLineComparator( "5 days", "50 days" );
    
    /**
     *
     */
    public TextLineComparatorTest() {
    }
    
    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    /**
     * Test of equalsExact method, of class TextLineComparator.
     */
    @Test
    public void testEqualsExact() {
        assertTrue( comp1.equalsExact() );   
        assertFalse( comp2.equalsExact() );
        assertTrue( comp3.equalsExact() );
        assertFalse( comp4.equalsExact() );
        assertFalse( comp5.equalsExact() );
    }

    /**
     * Test of equalsSomeWhat method, of class TextLineComparator.
     */
    @Test
    public void testEqualsSomeWhat() {
        assertTrue( "somewhat 1", comp1.equalsSomeWhat() );
        assertTrue( "somewhat 2", comp2.equalsSomeWhat() );
        assertTrue( comp3.equalsSomeWhat() );
        assertTrue( comp4.equalsSomeWhat() );
        assertFalse( comp5.equalsSomeWhat() );
    }

    /**
     * Test of equalValues method, of class TextLineComparator.
     */
    @Test
    @Ignore // TEH - not sure what testEqualValues is trying to get
    public void testEqualValues() {
        // assertTrue( comp6.equalValues() );
        // assertTrue( comp7.equalValues() );
        // assertFalse( comp8.equalValues() );
    }
}
