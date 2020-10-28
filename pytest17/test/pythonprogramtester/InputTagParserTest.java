package pythonprogramtester;

import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author curtipus
 */
public class InputTagParserTest {

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
    InputTagParser parser = new InputTagParser(fileText);

    public InputTagParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of hasNext method, of class InputTagParser.
     */
    @Test
    public void testHasNext() {
        System.out.println("Testing InputTagParser.hasNext()");
        //Test hasNext with an empty list
        InputTagParser emptyParser = new InputTagParser(new LinkedList<String>());
        boolean result = emptyParser.hasNext();
        assertEquals(false, result);

        //Test a non-empty list
        result = parser.hasNext();
        assertEquals(true, result);

        //call hasNext until empty
        while (parser.hasNext()) {
            parser.next();
        }
        result = parser.hasNext();
        assertEquals(false, result);
    }

    /**
     * Test of next method, of class InputTagParser.
     */
    @Test
    public void testNext() {
        System.out.println("Testing InputTagParser.next()");

        //Grab the next() input
        String parsedText = parser.next();
        assertEquals("100", parsedText);

        //we'll continue to test the whole file
        parsedText = parser.next();
        assertEquals("3", parsedText);
        parsedText = parser.next();
        assertEquals("4", parsedText);
        parsedText = parser.next();
        assertEquals("2", parsedText);
        parsedText = parser.next();
        assertEquals("7", parsedText);
        parsedText = parser.next();
        assertEquals("10", parsedText);
        parsedText = parser.next();
        assertEquals("8", parsedText);
        parsedText = parser.next();
        assertEquals("6", parsedText);
        parsedText = parser.next();
        assertEquals("4", parsedText);
        parsedText = parser.next();
        assertEquals("1000", parsedText);
        parsedText = parser.next();
        assertEquals(".05", parsedText);
        parsedText = parser.next();
        assertEquals("20", parsedText);

        //Test an empty input tag
        LinkedList<String> emptyCase = new LinkedList<String>() {
            {
                add("<input></input>");
            }
        };
        InputTagParser emptyParse = new InputTagParser(emptyCase);
        parsedText = emptyParse.next();
        assertEquals("", parsedText);
    }

    /**
     * Test of remove method, of class InputTagParser.
     */
    @Test
    public void testRemove() {
        System.out.println("Testing InputTagParser.remove()");
        System.err.println("InputTagParser.remove() THIS IS NOT SUPPORTED YET!!!");
    }

    /**
     * Test of iterator method, of class InputTagParser.
     */
    @Test
    public void testIterator() {
        System.out.println("Testing InputTagParser.iterator()");
        System.err.println("InputTagParser.iterator() test not Implemented yet!");

    }

    /**
     * Test of allInputs method, of class InputTagParser.
     */
    @Test
    public void testAllInputs() {
        System.out.println("Testing InputTagParser.allInputs()");
        List<String> allInputList = new LinkedList<String>() {
            {
                add("100");
                add("3");
                add("4");
                add("2");
                add("7");
                add("10");
                add("8");
                add("6");
                add("4");
                add("1000");
                add(".05");
                add("20");
            }
        };
        List<String> allInputListOut = parser.allInputs();
        assertEquals(allInputList, allInputListOut);

    }

    /**
     * Test of removeInputTags method, of class InputTagParser.
     */
    @Test
    public void testRemoveInputTags_List() {
        System.out.println("Testing InputTagParser.removeInputTags()");
        LinkedList<String> rawList = new LinkedList<String>() {
            {
                add("This is line <input>one</input>");
                add("This is another line, line <input>two</input>");
                add("This is a third line with no input");
            }
        };
        LinkedList<String> removedList = new LinkedList<String>() {
            {
                add("This is line one");
                add("This is another line, line two");
                add("This is a third line with no input");
            }
        };
        List<String> listOut = InputTagParser.removeInputTags(rawList);
        assertEquals(removedList, listOut);

    }

    /**
     * Test of removeInputTags method, of class InputTagParser.
     */
    @Test
    public void testRemoveInputTags_String() {
        System.out.println("Testing InputTagParser.removeInputTags()");

        //Test to see that <input> and </input> are removed from String
        String parsedText = "<input>This is a line</input>";
        parsedText = parser.removeInputTags(parsedText);
        assertEquals("This is a line", parsedText);

        //Test to see that <input> and </input> missing has no effect
        parsedText = "This is a line with no inputs";
        parsedText = parser.removeInputTags(parsedText);
        assertEquals("This is a line with no inputs", parsedText);
    }
}
