/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pythonprogramtester;

import java.util.LinkedList;
import java.util.List;


//Ty Carpenter
//CSC 345
//TextUtilitiesSpecifications
// 1/22/2020

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TextUtilities
{
   /* Removes any and all trailing blanks within a string*/
   public static String removeTrailingBlanks(String line)
   {
      int currentLen = line.length();
      for(int i = currentLen; i > 0; i--)
      {
         String sub = line.substring(i-1, i);
         if(sub.equals(" "))
         {
            line = line.substring(0, i-1);
         }
         else
         {
            i = 0;
         }
      }
      return line;
   }
   /*Removes any and all trailing blanks of strings stored in a List*/
   public static List<String> removeTrailingBlanks(List<String> oldList)
   {
      List<String> newList = new ArrayList<String>();
      
      for(int i = 0; i < oldList.size(); i++)
      {
         String addToNewList;
         addToNewList = TextUtilities.removeTrailingBlanks(oldList.get(i));
         newList.add(addToNewList);
      }
      
      return newList;
   }
   /*Adjusts the length of a string to a given length n, if the length
   of said string is greater than or equal to n, string does not adjust*/      
   public static String adjustLength(String line, int n)
   {
      int currentLen = line.length();
      
     
      
      if(currentLen < n)
      {
         int spaceAdd = n - currentLen;
         
         
         String spaces = "";
         
         for(int i = 0; i < spaceAdd; i++)
         {
            spaces = spaces.concat(" ");
            
         }
         line = line.concat(spaces);
         
      }
      return line;
   
   }
   /*Adjusts lengths of strings stored within a List*/
   public static List<String> adjustLength(List<String> list)
   {
      List<String> newList = new ArrayList<String>();
      int longestLine = TextUtilities.maximumLength(list);
      
      for(int i = 0; i < list.size(); i++)
      {
         String newListAdd;
         newListAdd = TextUtilities.adjustLength(list.get(i), longestLine);
         newList.add(newListAdd);
      }
      return newList;
   
   }
   /*Finds the length of the largest string stored within a List*/
   public static int maximumLength(List<String> list)
   {
      int maxLen = 0;
      for(int i = 0; i < list.size(); i++)
      {
         int newLen = list.get(i).length();
         
         if(newLen > maxLen)
         {
            maxLen = newLen;
         }
      }
      return maxLen;
   
   }
}


/**
 *
 * @author Willhoft*/
 /*
public class TextUtilities {
    
    // TODO: Both of these routines seem to do the same thing, remove one
    // I would suggest naming them after the routines in the python
    // string library.
    
    public static void removeTrailingBlanks( List<String> text )
    {
        for( int i=0; i<text.size(); i++ )
        {
            text.set( i, trimRight( text.get(i) ) );
        }
    }
    
    public static String trimRight( String line )
    {
        while( line.endsWith( " " ) )
        {
            line = line.substring( 0, line.length()-1 );
        }
        return line;
    }
    
    public static String ljust( String line, int width )
    {
        StringBuilder work = new StringBuilder( line );
        while( work.length() < width )
        {
            work.append( " " );
        }
        return work.toString();
    }
    
    public static int findLongestLine( List<String> lines )
    {
        int longest = 0;
        for( String line : lines )
        {       
            longest = Math.max( longest, line.length() );
        }
        return longest;
    }
    
    // TODO: Replace with jUnit testing
    public static void main(String[] args)
    {
        String one = "One";
        String two = "Two  ";
        String three = "   Three";
        String four = "    Four    ";
        String five = " One  Two   Three    Four     ";

        System.out.println( ">" + trimRight( one ) + "<" );
        System.out.println( ">" + trimRight( two ) + "<" );
        System.out.println( ">" + trimRight( three ) + "<" );
        System.out.println( ">" + trimRight( four ) + "<" );
        
        List<String> test = new LinkedList<>();
        
        test.add(one);
        test.add(two);
        test.add(three);
        test.add(four);
        
        System.out.println( test );
        removeTrailingBlanks( test );
        System.out.println( test );
        
        for( String str : test )
        {
            System.out.println( ">" + str + "<" );
        }
        
        
        System.out.println( ">" + ljust( "1234567890123456789012345678901234567890", 40 ) + "<" );
        System.out.println( ">" + ljust( "Test", 40 ) + "<" );
        System.out.println( ">" + ljust( "This is a longer test", 40 ) + "<" );
        System.out.println( ">" + ljust( "Now is the time for all good men to come to the aid of their country", 40 ) + "<" );
        
    }
}*/
