package pythonprogramtester;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author somekittens
 */
public class TestTagParserTest {

    // Instance variables
    TestTagParser fileParser;
    List<String> fileText = new LinkedList<String>() {
        {
            add("Example #1");
            add("<test code=\"miles2kilometers.py\">");
            add("Miles to Kilometers Conversion Program");
            add("What is the distance in miles? <input>100</input>");
            add("<value>100</value> miles is <value>160.9</value> kilometers");
            add("</test>");
            add("Example #2");
            add("<test code=\"pocketChange.py\">");
            add("This program calculates the value of your pocket change.");
            add("How many quarters do you have? <input>3</input>");
            add("How many dimes do you have? <input>4</input>");
            add("How many nickels do you have? <input>2</input>");
            add("How many pennies do you have? <input>7</input>");
            add("The total value is $<value>1.32</value>");
            add("</test>");
            add("<test code=\"pocketChange.py\">");
            add("This program calculates the value of your pocket change.");
            add("How many quarters do you have? <input>10</input>");
            add("How many dimes do you have? <input>8</input>");
            add("How many nickels do you have? <input>6</input>");
            add("How many pennies do you have? <input>4</input>");
            add("The total value is $<value>3.64</value>");
            add("</test>");
            add("Example #3");
            add("<test code=\"futureValue.py\">");
            add("");
            add("Future Value of an Ordinary Annuity");
            add("");
            add("Periodic payment: <input>1000</input>");
            add("Interest rate: <input>.05</input>");
            add("Number of periods: <input>20</input>");
            add("");
            add("Future Value is <value>33065.95+/-0.005</value>");
            add("");
            add("");
            add("</test>");
        }
    };

    public TestTagParserTest() {
    }

    @Before
    public void setUpClass() {
        //Reset fileParser
        fileParser = new TestTagParser(fileText);
    }

//    /**
//     * Test of hasNext method, of class TRCTagParser.
//     */
//    @Test
//    public void testHasNext() throws InvalidTagFormatException {
//        System.out.println("* Testing hasNext");
//        //Test an empty parser (hasNext() == false)
//        TRCTagParser emptyParser = new TRCTagParser(new LinkedList<String>());
//        assertFalse(emptyParser.hasNext());
//
//        //Next, let's test fileParser (which *does* have stuff)
//        assertTrue(fileParser.hasNext());
//
//        //Finally, a real-world example.  We'll call .next() until fileParser is empty, then check if it's empty
//        //Verbosity is fun!
//        while (fileParser.hasNext()) {
//            try {
//                fileParser.next();
//            } catch (InvalidTagFormatException e) {
//                fail(".next() threw an exception, we weren't even testing that!");
//            }
//        }
//        assertFalse(fileParser.hasNext());
//    }

    /**
     * Test of the results next method, of class TRCTagParser. This test
     * asserts that the tag results are parsed correctly.
     */
    @Test
    public void testNextResults() throws Exception {
        System.out.println("* Testing next");
        TagBlock instance = fileParser.next();
        //These numbers are specific only to the data in fileParser, don't use them elsewhere
        assertEquals(3, instance.getData().size());
        String firstData = "Miles to Kilometers Conversion Program";
        assertEquals(firstData, instance.getData().get(0));

    }

    /**
     * Tests the full iteration through .next()
     */
    @Test
    public void testNextIter() throws Exception {
        System.out.println("* Testing next iteration");
        while (fileParser.hasNext()) {
            //Essentially checking that each iteration returns a TagBlock
            TagBlock instance = fileParser.next();
        }
    }

//    /**
//     * The next method throws several exceptions, let's try and find them!
//     */
//    @Test(expected = InvalidTagFormatException.class)
//    public void testNextExceptions() throws InvalidTagFormatException {
//        System.out.println("* Testing next Exceptions");
//        TRCTagParser badTags = new TRCTagParser(new LinkedList<String>() {
//
//            {
//                add("<test Earning more Points is fast & easy.");
//                add("Read this BonusMail® and visit the partner site.");
//                add("Respond to this offer. Receive 10 Points.");
//            }
//        });
//        badTags.next();
//    }
}
