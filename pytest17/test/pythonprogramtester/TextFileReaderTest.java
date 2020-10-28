package pythonprogramtester;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// Imports for Reading
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Imports for Lists & Iterating
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class TextFileReaderTest {

   public List<String> testText1 = new ArrayList<String>();
   public List<String> testText2 = new ArrayList<String>();
   public List<String> testText3 = new ArrayList<String>();
   public TextFileReader testReader;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() throws FileNotFoundException {
      testText1.add("Hello!");
      testText1.add("My name is Sarah.");
      testText1.add("This is a text file that I made");
      testText1.add("In order to test my FileTextReader class.");
      testText1.add("I hope it works!");
      
      testText2.add("Hello!");
      testText2.add("");
      testText2.add("My name is Sarah.");
      testText2.add("");
      testText2.add("This is a text file that I made");
      testText2.add("");
      testText2.add("");
      testText2.add("That has blank lines.");
      testText2.add("");
      testText2.add("");
      testText2.add("In order to test my FileTextReader class.");
      testText2.add("I hope it works!");
      
      testText3.add("");
      testText3.add("");
      testText3.add("");
      testText3.add("Hello!");
      testText3.add("");
      testText3.add("My name is Sarah.");
      testText3.add("");
      testText3.add("This is a text file that I made");
      testText3.add("");
      testText3.add("");
      testText3.add("That has blank lines.");
      testText3.add("");
      testText3.add("");
      testText3.add("In order to test my FileTextReader class.");
      testText3.add("I hope it works!");

      
   }
   
   /** Tests **/
   
   // tests getText() from existent file
   @Test public void test1() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_SBH.txt");
      Assert.assertEquals("Same", testReader.getText(), testText1);
   }
   
   // tests hasNext() (true without blank lines)
   @Test public void test2() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_SBH.txt");
      Assert.assertTrue("Has Next (does)", testReader.hasNext());
   }
   
   // Tests hasNext() (false without blank lines)
   @Test public void test3() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_SBH.txt");
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      Assert.assertFalse("Has Next (doesn't)", testReader.hasNext());
   }
   
   // Tests Nonexistent File
   @Test public void test4() throws FileNotFoundException {
      try {
         testReader = new TextFileReader("test/pythonprogramtester/test_nonexistent.txt");
      } catch (FileNotFoundException ex) {
         //System.out.println("Sorry, file not found");
         //throw ex;
      }
   }
   
   // Tests getText() for file with blank lines
   @Test public void test5() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_blank_lines.txt");
      Assert.assertEquals("Same", testReader.getText(), testText2);
   }
   
   // Tests hasNext() (true with blank lines)
   @Test public void test6() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_blank_lines.txt");
      testReader.next();
      testReader.next(); // blank line
      Assert.assertTrue(testReader.hasNext());
   }
   
   // Tests hasNext() (false with blank lines)
   @Test public void test7() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_blank_lines.txt");
      
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      
      Assert.assertFalse(testReader.hasNext());
   }
   
   // Tests next() (without blank lines)
   @Test public void test8() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_SBH.txt");
      Assert.assertEquals("Same", testText1.get(0), testReader.next());
   }
   
   // Tests next() (with blank lines)
   @Test public void test9() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_blank_lines.txt");
      testReader.next();
      Assert.assertEquals("Same", testText2.get(1), testReader.next());
   }
   
   // Tests next() for a file that starts with blank lines
   @Test public void test10() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_start_blank.txt");
      Assert.assertEquals("Same", testText3.get(0), testReader.next());
   }
   
   // tests getText() for a file that starts with blank lines
   @Test public void test11() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_start_blank.txt");
      Assert.assertEquals("Same", testReader.getText(), testText3);
   }
   
   // tests reset() 
   @Test public void test12() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_SBH.txt");
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.reset();
      Assert.assertEquals("Same", testReader.next(), testText1.get(0));
   }
   
   // tests reset() for a file that starts with blank lines
   @Test public void test13() throws FileNotFoundException {
      testReader = new TextFileReader("test/pythonprogramtester/test_start_blank.txt");
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.next();
      testReader.reset();
      Assert.assertEquals("Same", testReader.next(), testText3.get(0));
   }

}
