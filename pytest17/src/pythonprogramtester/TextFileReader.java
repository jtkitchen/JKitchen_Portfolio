package pythonprogramtester;

// Imports for Reading
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// Imports for Lists & Iterating
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A FileTextReader reads a text file and returns its lines in a list.
 * @author Sarah Hutchinson
 * @version 1.0
 */
public class TextFileReader implements Iterable<String>{  
   
   /**
    * The list of lines in the text file.
    */
   public List<String> fileText = new ArrayList<String>();
   
   /**
    * The index value of the current line in the text file
    */
   public int currentLineIndex = -1;
   
   /**
    * The number of total lines in the text file.
    */
   private int totalLines;
   
   /**
    * Reads a file and adds the file's text to a list.
    * @param filename the name of the file
    * @throws FileNotFoundException if filename fails
    */   
   TextFileReader ( String filename ) throws FileNotFoundException {
      File inputFile = new File( filename );
      try {
         Scanner myFile = new Scanner( inputFile );
         while( myFile.hasNextLine() ) {
            String line = myFile.nextLine();
            fileText.add(line);
            totalLines++;
         }
         myFile.close();
      } 
      catch ( FileNotFoundException ex ) {
         throw ex;
      }
   }
   
   /**
    * Iterates through fileText.
    * @return iterator of fileText
    */
   public Iterator<String> iterator() {
      return fileText.iterator();
   }

   /**
    * Gets all the lines of the file in a list.
    * @return fileText
    */   
   public List<String> getText() {
      return fileText;
   }
   
   /**
    * Determines if there is another line after the current line in the fileText.
    * @return true if there is another line, else false
    */   
   public boolean hasNext() {
      if (currentLineIndex + 1 < totalLines) {
         return true;
      } 
      else {
         return false;
      }
   }
   
   /**
    * Gets the next line in fileText.
    * @return the next line in fileText
    */   
   public String next() {
      if (hasNext()) {
         String nextLine = fileText.get(currentLineIndex + 1);
         currentLineIndex++;
         return nextLine;
      }
      else {
         return null;
      }  
   }
   
   /**
    * Resets the current line in fileText to the first.
    */   
   public void reset() {
      currentLineIndex = -1;
   }
}