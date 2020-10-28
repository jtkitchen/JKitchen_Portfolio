package pythonprogramtester;

/**
   Allows replacement of substrings within a String; does not replace occurrences within quotation marks ("" or '').
   
   @author Joshua Kitchen
   @version 1.3
*/

// TODO - Modify to not replace parts of names
// TODO - Create method to take a List<String>


 // rewritten by Joshua Kitchen in 2020

import java.util.regex.Matcher;

public final class TextReplacer
{

   /**
      Replaces all substrings of the given value with another specified string.
      
      @param lineToChange the original, full string text to be manipulated.
      @param oldWord the original substring to be searched for and replaced.
      @param newWord the new substring to replace the old substring.
      @return a new string with all substrings outside of quotation marks replaced with the new substring
   */
   public static String replaceAll(String lineToChange, String oldWord, String newWord) 
   {
      String regex = "(?=(?:\"[^\"]*\"|\'[^'']*'|[^\"''])*$)" + Matcher.quoteReplacement(oldWord);
      String changedLine = lineToChange.replaceAll( regex, newWord );
      
      return changedLine;
   }
   
   /**
      Replaces the first substring of the given value with another specified string.
      
      @param lineToChange the original, full string text to be manipulated.
      @param oldWord the original substring to be searched for and replaced.
      @param newWord the new substring to replace the old substring.
      @return a new string with the first substring outside of quotation marks replaced with the new substring
   */  
   public static String replaceFirst(String lineToChange, String oldWord, String newWord)
   {
      String regex = "(?=(?:\"[^\"]*\"|\'[^'']*'|[^\"''])*$)" + Matcher.quoteReplacement(oldWord);
      String changedLine = lineToChange.replaceFirst( regex, newWord );
      
      return changedLine;
   }
}