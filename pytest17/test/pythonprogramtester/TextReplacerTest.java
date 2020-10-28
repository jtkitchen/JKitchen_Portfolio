/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pythonprogramtester;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

/**
 *
 * @author Willhoft
 */
public class TextReplacerTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   // Basic tests
   @Test public void Basic1() {
      Assert.assertEquals( "No change", "This is a test", 
                           TextReplacer.replaceAll("This is a test","hard","easy") );
   }
   
   @Test public void Basic2() {
      Assert.assertEquals( "Replace same size", "Replace easy word", 
                           TextReplacer.replaceAll("Replace hard word","hard","easy") );
   }
   
   @Test public void Basic3() {
      Assert.assertEquals( "Replace with shorter", "Replace short word", 
                           TextReplacer.replaceAll("Replace longer word","longer","short") );
   }
   
   @Test public void Basic4() {
      Assert.assertEquals( "Replace with longer", "Replace longer word", 
                           TextReplacer.replaceAll("Replace short word","short","longer") );
   }
   
   @Test public void Basic5() {
      Assert.assertEquals( "Empty string", "", TextReplacer.replaceAll("","Hard","Easy") );
   }
   
   @Test public void Basic6() {
      Assert.assertEquals( "Replace at beginning", "Python is really easy",
                           TextReplacer.replaceAll("Java is really easy","Java","Python") );
   }
   
   @Test public void Basic7() {
      Assert.assertEquals( "Replace at end", "I love Java", 
                           TextReplacer.replaceAll("I love Python","Python","Java") );
   }
   
   @Test public void Basic8() {
      Assert.assertEquals( "Replace the entire string", "Easy", TextReplacer.replaceAll("Hard","Hard","Easy") );
   }
   
   @Test public void Basic9() {
      Assert.assertEquals( "Replace longer than string", "Ford", TextReplacer.replaceAll("Ford","Chevy","Dodge") );
   } 
   
   @Test public void Basic10() {
      String line =     "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_+-=[]\\{}|;:,.<>?";
      String expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_+-=[]\\{}|;:,.<>?";
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Empty string", expected, actual );
   }
    
   // Test that it works just like String.replaceFirst on a string with no quotes      
   @Test public void Test1() {
      String line = "This little piggy went to market, this little piggy stayed home";
      String expected = line.replaceAll( "piggy", "puppy" );
      String actual = TextReplacer.replaceAll( line, "piggy", "puppy");
      Assert.assertEquals( "Works like replaceFirst", expected, actual );
   }
   
   // Test that it works just like String.replaceAll on a string with no quotes      
   @Test public void Test2() {
      String line = "This little piggy went to market, this little piggy stayed home";
      String expected = line.replaceFirst( "piggy", "puppy" );
      String actual = TextReplacer.replaceFirst( line, "piggy", "puppy");
      Assert.assertEquals( "Works like replaceFirst", expected, actual );
   }

   // Test that it doesn't replace inside quotes
   @Test public void Test3() {
      String line =     "He said \"This is a test of this program...\" about the program";
      String expected = "He said \"This is a test of this program...\" about the code";
      String actual = TextReplacer.replaceFirst(line, "program", "code");
      Assert.assertEquals( "No replace inside double quotes", expected, actual );
   }
   
   @Test public void Test4() {
      String line =     "The program said 'This is a test of this program...' about the program";
      String expected = "The code said 'This is a test of this program...' about the code";
      String actual =TextReplacer.replaceAll(line, "program", "code");
      Assert.assertEquals( "No replace inside single quotes", expected, actual );
   }

   // Test that it ignores the opposite quotes inside quotes
   @Test public void Test5() {
      String line =     "Java \"Java 'Java' Java\" Java";
      String expected = "Python \"Java 'Java' Java\" Python";
      String actual =TextReplacer.replaceAll(line, "Java", "Python");
      Assert.assertEquals( "Ignores single inside double", expected, actual );
   }

   @Test public void Test6() {
      String line =     "Python 'Python \"Python\" Python' Python";
      String expected = "Java 'Python \"Python\" Python' Java";
      String actual =TextReplacer.replaceAll(line, "Python", "Java");
      Assert.assertEquals( "Ignores double inside single", expected, actual );
   }
        
   // Escaped quotes
   @Test public void Test7() {
      String line =     "Ignore this quote \\\", but change Java";
      String expected = "Ignore this quote \\\", but change Python";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "Java", "Python");
      Assert.assertEquals( "Escaped double quote outside quote", expected, actual );
   }
   
   @Test public void Test8() {
      String line =     "Ignore this quote \\\', but change Python";
      String expected = "Ignore this quote \\\', but change Java";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "Python", "Java");
      Assert.assertEquals( "Escaped single quote outside quote", expected, actual );
   }

   @Test public void Test9() {
      String line =     "It said \"Ignore this \\\" in Java\", but change Java";
      String expected = "It said \"Ignore this \\\" in Java\", but change Python";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "Java", "Python");
      Assert.assertEquals( "Escaped double quote inside double quote", expected, actual );
   }
   
   @Test public void Test10() {
      String line =     "It said \"Ignore this \\' in Java\", but change Java";
      String expected = "It said \"Ignore this \\' in Java\", but change Python";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "Java", "Python");
      Assert.assertEquals( "Escaped single quote inside double quote", expected, actual );
   }

   @Test public void Test11() {
      String line =     "It said \'Ignore this \\\" in Python\', but change Python";
      String expected = "It said \'Ignore this \\\" in Python\', but change Java";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "Python", "Java");
      Assert.assertEquals( "Escaped double quote inside single quote", expected, actual );
   }
   
   @Test public void Test12() {
      String line =     "It said \'Ignore this \\' in Python\', but change Python";
      String expected = "It said \'Ignore this \\' in Python\', but change Java";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "Python", "Java");
      Assert.assertEquals( "Escaped single quote inside single quote", expected, actual );
   }
   
   @Ignore( "Future enhancement - don't replace parts of names" )
   @Test public void Test13() {
      // Some of these are invalid Python syntax, but still should be handled correctly 
      String line =     "input _input input_ 4input input4";
      String expected = "_pytest_input_ _input input_ 4_pytest_input_ input4";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Empty string", expected, actual );
   }
   
   // PyTest Common Usage
   
   @Test public void PyTest1() {
      String line =     "age = int( input( \"How old are you (in years)? \" ) )";
      String expected = "age = int( _pytest_input_( \"How old are you (in years)? \" ) )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Typical PyTest", expected, actual );
   }
   
   @Test public void PyTest2() {
      String line =     "age = int( input( \"Please input your name: \" ) )";
      String expected = "age = int( _pytest_input_( \"Please input your name: \" ) )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Ignore prompt", expected, actual );
   }
   
   @Ignore( "Future enhancement - don't replace parts of names" )
   @Test public void PyTest3() {
      String line =     "inputYesNo( \"Are you enjoying the CSC 140 course? \" )";
      String expected = "inputYesNo( \"Are you enjoying the CSC 140 course? \" )";
      System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Beginning of method name", expected, actual );
   }
   
   @Test public void PyTest4() {
      String line =     "width = float( input( \"Width: \" ) )";
      String expected = "width = float( _pytest_input_( \"Width: \" ) )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Inside function call", expected, actual );
   }

   @Test public void PyTest5() {
      String line =     "width = float( getInput( \"Width: \" ) )";
      String expected = "width = float( getInput( \"Width: \" ) )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Different case", expected, actual );
   }
   
   @Ignore( "Future enhancement - don't replace parts of names" )
   @Test public void PyTest6() {
      String line =     "width = float( get_input( \"Width: \" ) )";
      String expected = "width = float( get_input( \"Width: \" ) )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "End of method name", expected, actual );
   }
   
   @Test public void PyTest7() {
      String line =     "data = \"\" + input( \"\" )";
      String expected = "data = \"\" + _pytest_input_( \"\" )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Empty string", expected, actual );
   }
   
   @Test public void PyTest8() {
      String line =     "data = '' + input( '' )";
      String expected = "data = '' + _pytest_input_( '' )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Empty string", expected, actual );
   }
   
   @Test public void PyTest9() {
      String line =     "print( \"$\" + str( dollars ) )";
      String expected = "print( \"$\" + str( dollars ) )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Empty string", expected, actual );
   }
   
   @Test public void PyTest10() {
      String line =     "print( \"Test #\", item, sep=\"\"  )";
      String expected = "print( \"Test #\", item, sep=\"\"  )";
      // System.out.println(expected);
      String actual =TextReplacer.replaceAll(line, "input", "_pytest_input_");
      Assert.assertEquals( "Empty string", expected, actual );
   }
}
