package pythonprogramtester;

import static org.junit.Assert.*;
import org.junit.Test;

/** ValueParserTest.java
 * 
 * @author Nicole
 */
public class ValueParserTest {
    
    public ValueParserTest() {
    }
    
    /** 
     * Tests ValueParser methods nextPart() and hasNextPart().
     */
    @Test
    public void testParts() {
        System.out.println("ValueParser test1 - testParts()");
        ValueParser vp = new ValueParser("0.000 is +1E27 a -29 test");
        assertTrue( vp.hasNextPart() );
        assertEquals( "0.000",vp.nextPart() );
        assertEquals( " is ",vp.nextPart() );
        assertEquals( "+1E27",vp.nextPart() );
        assertEquals( " a ",vp.nextPart() );
        assertEquals( "-29",vp.nextPart() );
        assertEquals( " test",vp.nextPart() );
        assertFalse( vp.hasNextPart() );
    }
    
    /**
     * Tests ValueParser methods nextPartIsValue(), nextValue(),
     * hasNextValue(), and hasNextPart().
     */
    @Test
    public void testValues() {
        System.out.println("ValueParser test2 - testValues()");
        ValueParser vp = new ValueParser("1234 + 8765 = 9999");
        assertTrue( vp.nextPartIsValue() );
        assertEquals( "1234",vp.nextValue() );
        assertFalse( vp.nextPartIsValue() );
        assertEquals( " + ",vp.nextPart() );
        assertTrue( vp.nextPartIsValue() );
        assertEquals( "8765",vp.nextValue() );
        assertFalse( vp.nextPartIsValue() );
        assertEquals( " = ",vp.nextPart() );
        assertTrue( vp.nextPartIsValue() );
        assertEquals( "9999",vp.nextValue() );
        
        ValueParser vp2 = new ValueParser("100 miles is 160.9 kilometers");
        assertTrue( vp2.hasNextValue() );
        assertEquals( "100",vp2.nextValue() );
        assertTrue( vp2.hasNextValue() );
        assertEquals( "160.9",vp2.nextValue() );
        assertFalse( vp2.hasNextValue() );
        assertTrue( vp2.hasNextPart() );
        
    }
}
