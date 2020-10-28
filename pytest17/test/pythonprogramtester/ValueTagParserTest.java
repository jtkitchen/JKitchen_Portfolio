package pythonprogramtester;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Nicholas
 */
public class ValueTagParserTest {

    public ValueTagParserTest() {
    }

    /**
     * Test of hasNext method, of class ValueTagParser.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext method test");
        String newLine = "<value>This is an awesome test</value>";
        ValueTagParser myParser = new ValueTagParser(newLine);
        assertEquals(true, myParser.hasNext());
    }

    /**
     * Test of next method, of class ValueTagParser.
     */
    @Test
    public void testNext() throws Exception {
        System.out.println("next method test");
        String newLine = "<value>This is an awesome test</value>";
        ValueTagParser myParser = new ValueTagParser(newLine);
        assertEquals(myParser.next(), "This is an awesome test");
    }

    /**
     * Test of removeValueTags method, of class ValueTagParser.
     */
    @Test
    public void testRemoveValueTags_List() {
        System.out.println("removeValueTags_list method test");
        List<String> text = new LinkedList<>();
        text.add("<value>this</value>");
        text.add("<value>is</value>");
        text.add("<value>a</value>");
        text.add("<value>test</value>");
        List expResult = new LinkedList();
        expResult.add("this");
        expResult.add("is");
        expResult.add("a");
        expResult.add("test");
        List result = ValueTagParser.removeValueTags(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeValueTags method, of class ValueTagParser.
     */
    @Test
    public void testRemoveValueTags_String() {
        System.out.println("removeValueTags_string method test");
        String line = "<value>This is a test</value>";
        assertEquals("This is a test", ValueTagParser.removeValueTags(line));
    }
}
