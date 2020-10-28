import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TextUtilitiesTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /**
    *This is a test to make sure the program removes more than one trailing blank
    *@param s is the string that will have blanks removed
    *
    */
   @Test public void test1() {
      
         String s = "Trail    ";
         Assert.assertEquals("Remove Trailing Blanks of String", TextUtilities.removeTrailingBlanks(s));
   }
   
   /**
    *This is a test to make sure the program removes one trailing blank
    *@param s is the string that will have blanks removed
    *
    */
   @Test public void test2() {
      try {
         String s = "Trail ";
         Assert.assertEquals("Remove Trailing Blanks of String", TextUtilities.removeTrailingBlanks(s));
      } catch (IOException e) {
         System.out.println("Cannot remove trailing blanks");
      }
         
   }
   
   /**
    *This is a test to make sure the program does not remove anything when removing trailing blanks
    *@param s is the string that will be used but should not change
    *
    */
   @Test public void test3() {
      try {
         String s = "Trail";
         Assert.assertEquals("Remove Trailing Blanks of String", TextUtilities.removeTrailingBlanks(s));
      } catch (IOException e) {
         System.out.println("Cannot remove trailing blanks");
      }
   }


   /**
    *This is a test to make sure the program removes all the trailing blanks
    * from strings in a List
    *@param l is a List of strings that will have blanks removed
    *@param s is the string that will be stored in l
    *@param t is the string that will be stored in l
    *@param r is the string that will be stored in l
    *
    */
   @Test public void test4() {
      try {
         List<String> l = new ArrayList<String>();
         String s = "Trail   ";
         String t = "Trailing    ";
         String r = "Trailing Blanks      ";
         
         l.add(s);
         l.add(t);
         l.add(r);
         
         Assert.assertEquals("Remove Trailing Blanks of a List of Strings", TextUtilities.removeTrailingBlanks(l));
      } catch (IOException e) {
         System.out.println("Cannot remove trailing blanks from list");
      }
   }
   
   /**
    *This is a test to make sure the program removes one trailing blank
    * from strings in a List
    *@param l is a List of strings that will have blanks removed
    *@param s is the string that will be stored in l
    *@param t is the string that will be stored in l
    *@param r is the string that will be stored in l
    *
    */
   @Test public void test5() {
      try {
         List<String> l = new ArrayList<String>();
         String s = "Trail ";
         String t = "Trailing ";
         String r = "Trailing Blanks ";   
         
         l.add(s);
         l.add(t);
         l.add(r);
         
         Assert.assertEquals("Remove Trailing Blanks of a List of Strings", TextUtilities.removeTrailingBlanks(l));
      } catch (IOException e) {
         System.out.println("Cannot remove trailing blanks from list");
      }
   }
   
   /**
    *This is a test to make sure the program does not remove any trailing blanks
    * from strings in a List
    *@param l is a List of strings that the program will attempt to have blanks removed
    *@param s is the string that will be stored in l
    *@param t is the string that will be stored in l
    *@param r is the string that will be stored in l
    *
    */
   @Test public void test6() {
      try {
         List<String> l = new ArrayList<String>();
         String s = "Trail";
         String t = "Trailing";
         String r = "Trailing Blanks";
         
         l.add(s);
         l.add(t);
         l.add(r);
         
         Assert.assertEquals("Remove Trailing Blanks of a List of Strings", TextUtilities.removeTrailingBlanks(l));
      } catch (IOException e) {
         System.out.println("Cannot remove trailing blanks from list");
      }
   }
   
   /**
    *This is a test to make sure the program does not adjust the length of the string
    *@param s is the string that the program will attempt to adjust its length
    *
    */
   @Test public void test7() {
      try {
         String s = "Adjust";
         Assert.assertEquals("Adjusts length of String", TextUtilities.adjustLength(s, 5));
      } catch (IOException e) {
         System.out.println("Cannot adjust length");
      }
   }
   
   /**
    *This is a test to make sure the program does not adjust the length of the string
    *@param s is the string that the program will attempt to adjust its length
    *
    */
   @Test public void test8() {
      try {
         String s = "Adjust";
         Assert.assertEquals("Adjusts length of String", TextUtilities.adjustLength(s, 6));
      } catch (IOException e) {
         System.out.println("Cannot adjust length");
      }
   }
   
   /**
    *This is a test to make sure the program adjusts the length of the string by +1
    *@param s is the string that the program will attempt to adjust its length
    *
    */
   @Test public void test9() {
      try {
         String s = "Adjust";
         Assert.assertEquals("Adjusts length of String", TextUtilities.adjustLength(s, 7));
      } catch (IOException e) {
         System.out.println("Cannot adjust length");
      }
   }
   
   /**
    *This is a test to make sure the program adjusts the length of the string
    *@param s is the string that the program will attempt to adjust its length
    *
    */
   @Test public void test10() {
      try {
         String s = "Adjust";
         Assert.assertEquals("Adjusts length of String", TextUtilities.adjustLength(s, 10));
      } catch (IOException e) {
         System.out.println("Cannot adjust length");
      }
   }
   
   /**
    *This is a test to make sure the program adjusts the lengths of strings stored in a
    * List to the longest string
    *@param l is the List which the strings will be adjusted
    *@param s is the string that is stored in l
    *@param r is the string that is stored in l
    *@param t is the string that is stored in l
    *
    */
   @Test public void test11() {
      try {
         List<String> l = new ArrayList<String>();
         String s = "Ray Ottman is soft";
         String t = "Ray Ottman is not tough";
         String r = "Ray Ottman gets babied by his mom";
   
         Assert.assertEquals("Adjusts length of List of Strings", TextUtilities.adjustLength(l));
      } catch (IOException e) {
         System.out.println("Cannot adjust lengths of list");
      }
   }
   
   /**
    *This is a test to make sure the program attempts to adjust the lengths of strings stored in a
    * List to the longest string
    *@param l is the List which the strings will be adjusted
    *@param s is the string that is stored in l
    *@param r is the string that is stored in l
    *@param t is the string that is stored in l
    *
    */
   @Test public void test12() {
      try {
         List<String> l = new ArrayList<String>();
         String s = "Ray Ottman is soft";
         String t = "Ray Ottman is soft";
         String r = "Ray Ottman is soft";
   
         Assert.assertEquals("Adjusts length of List of Strings", TextUtilities.adjustLength(l));
      } catch (IOException e) {
         System.out.println("Cannot adjust lengths of list");
      }
   }
   
   /**
    *This is a test to make sure the program finds the largest length of the strings stored in a
    * List
    *@param l is the List of strings where the length of the longest will be identified
    *@param s is the string that is stored in l
    *@param r is the string that is stored in l
    *@param t is the string that is stored in l
    *
    */
   @Test public void test13() {
      try {
         List<String> l = new ArrayList<String>();
         String s = "Ray Ottman is soft";
         String t = "Ray Ottman is not tough";
         String r = "Ray Ottman gets babied by his mom";
         Assert.assertEquals("Gets max length from list of strings", TextUtilities.maximumLength(l));
      } catch (IOException e) {
         System.out.println("Cannot find maximum length");
      }
   }
   
   /**
    *This is a test to make sure the program finds the largest length of the strings stored in a
    * List
    *@param l is the List of strings where the length of the longest will be identified
    *@param s is the string that is stored in l
    *@param r is the string that is stored in l
    *@param t is the string that is stored in l
    *
    */
   @Test public void test14() {
      try {
         List<String> l = new ArrayList<String>();
         String s = "Ray Ottman is soft";
         String t = "Ray Ottman is soft";
         String r = "Ray Ottman is soft";
         Assert.assertEquals("Gets max length from list of strings", TextUtilities.maximumLength(l));
      } catch (IOException e) {
         System.out.println("Cannot find maximum length");
      }
   }
  


}
