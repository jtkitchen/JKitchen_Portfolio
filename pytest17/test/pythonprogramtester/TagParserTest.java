/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pythonprogramtester;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author vandyne_aaron
 */
public class TagParserTest {
    
    public TagParserTest() {
    }

    /**
     * Tests addText method, of class TagParser.
     */
    @Test
    public void testAddText_String()throws InvalidTagFormatException {
        System.out.println("This is a test of the AddText method"
                           + " with a String argument");
        String lineToAdd = "This is a <avd>test</avd>!";
                
        TagParser test = new TagParser("avd");
        test.addText(lineToAdd);
        TagData tag = test.next();
        assertEquals(0, tag.getArgs().length());
        assertEquals("test", tag.getData());
    }

    /**
     * Tests addText method, of class TagParser.
     */
    @Test
    public void testAddText_List() throws InvalidTagFormatException{
        System.out.println("This is a test of the AddText "
                          + "method with a List argument.");
        List<String> lines = new LinkedList<String>();
        lines.add("This is line <avd>1</avd>.");
        lines.add("This is line <avd>2</avd>.");
        lines.add("Here is line <avd>3</avd>.");
        TagParser test = new TagParser("avd");
        test.addText(lines);
        TagData tag = test.next();
        assertEquals(0, tag.getArgs().length());
        assertEquals("1", tag.getData());
        tag = test.next();
        assertEquals(0, tag.getArgs().length());
        assertEquals("2", tag.getData());
        tag = test.next();
        assertEquals(0, tag.getArgs().length());
        assertEquals("3", tag.getData());
        
    }

    /**
     * Tests hasNext method, of class TagParser.
     */
    @Test
    public void testHasNext() throws InvalidTagFormatException{
        System.out.println("This is a test of the HasNext method");
        String lineToAdd = "This is a <avd>test</avd> and so is <avd>this</avd>!";
        TagParser test = new TagParser("avd");
        assertEquals(false, test.hasNext());
        test.addText(lineToAdd);
        assertEquals(true, test.hasNext());
    }

    /**
     * Tests next method, of class TagParser.
     */
    @Test
    public void testNext() throws Exception {
        System.out.println("This is a test of the next() method.");
        String lineToAdd = "This is a <avd>test</avd>!";
        
        TagParser test = new TagParser("avd");
        
        test.addText(lineToAdd);
        TagData tag = test.next();
        assertEquals(0, tag.getArgs().length());
        assertEquals("test", tag.getData());
        test.addText("This is a <avd>more</avd> "
                     + "complicated <avd check>test</avd>");
        test.addText("with multiple <avd>tags</avd>.");
        tag=test.next();
        assertEquals("more", tag.getData());
        tag=test.next();
        assertEquals("check", tag.getArgs());
        assertEquals("test", tag.getData());
        tag=test.next();
        assertEquals(0, tag.getArgs().length());
        assertEquals("tags", tag.getData());
        
    }
    
     /**
     * Ensures InvalidTagFormatException is thrown 
     * when a closing brace is missed on the start tag.
     */
    @Test(expected=InvalidTagFormatException.class)
    public void checkMissingEndOfStartTag() throws InvalidTagFormatException{
        System.out.println("Looks for invalid tag format exception "
                           + "when a closing brace is missed.");
        String lineToAdd = "This is a <avd test</avd>!";
        
        TagParser test = new TagParser("avd");
        
        test.addText(lineToAdd);
        TagData tag = test.next();
    }
    
    @Test(expected=java.util.NoSuchElementException.class)
    public void checkMissingStartTag() throws InvalidTagFormatException{
        System.out.println("Looks for invalid tag format exception "
                           + "when a closing brace is missed.");
        String lineToAdd = "This is a test</avd>!";
        TagParser test = new TagParser("avd");
        
        test.addText(lineToAdd);
        TagData tag = test.next();
    }
         /**
     * Checks for a NoSuchElementException when the opening brace of the
     * start tag is missed.
     */
    @Test(expected=java.util.NoSuchElementException.class)
    public void checkMissingStartofStartTag() throws InvalidTagFormatException{
        System.out.println("Looks for NoSuchElement exception when "
                           + "an opening brace is missed in the start tag.");
        String lineToAdd = "This is a avd> test</avd>!";
        
        ArrayList<String> avd = new ArrayList<>();
        TagParser test = new TagParser("avd");
        
        test.addText(lineToAdd);
        TagData tag = test.next();
    }
    
     /**
     * Checks to see if a InvalidTagFormatException is thrown
     * when the end tag is missing.
     */
    @Test(expected=InvalidTagFormatException.class)
    public void checkMissingEndTag() throws InvalidTagFormatException{
        System.out.println("Looks for invalid tag format exception"
                           + " when the end tag is missing.");
        String lineToAdd = "This is a <avd> test!";
        
        TagParser test = new TagParser("avd");
        
        test.addText(lineToAdd);
        TagData tag = test.next();
    }
    
     
    /**
     * Checks for an InvalidTagFormatException when the opening brace of the end
     * tag is missed.
     */
    @Test(expected=InvalidTagFormatException.class)
    public void checkMissingCloseOnEndTag() throws InvalidTagFormatException{
        System.out.println("Looks for InvalidTagFormatException when "
                           + "the closing brace of the end tag is missed.");
        String lineToAdd = "This is a <avd> test </avd!";
        
        TagParser test = new TagParser("avd");
        
        test.addText(lineToAdd);
        TagData tag = test.next();
    }
     
         /**
     * Checks for an InvalidTagFormatException when the opening brace of the end
     * tag is missed.
     */
    @Test(expected=InvalidTagFormatException.class)
    public void checkMissingStartOnEndTag() throws InvalidTagFormatException{
        System.out.println("Looks for InvalidTagFormatException when "
                           + "the opening brace of the end tag is missed.");
        String lineToAdd = "This is a <avd> test /avd>!";
        TagParser test = new TagParser("avd");
        
        test.addText(lineToAdd);
        TagData tag = test.next();
    }

}
