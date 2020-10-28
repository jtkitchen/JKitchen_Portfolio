package pythonprogramtester;

/**
 *Brendan Villnave
 *TextFileWriter version 2
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TextFileWriter {
   String fully_qualified;
   private FileWriter writeFile;
   
   /**
    *Constructor that takes @fully-qualified file name and opens it
    */
   public TextFileWriter(String fully_qualified) throws IOException {
      this.fully_qualified = fully_qualified;
      writeFile = new FileWriter(fully_qualified);       
   }
   
   /**
    *Writes a line using the String @text and puts it into the output file
    */
   public void writeLine(String text) throws IOException {
     writeFile.write(text + "\n");
   }
   /**
    *Writes multiple lines using the String @text and writes it to the output file
    */
   public void writeLines(List<String> text) throws IOException {
      for (int i = 0; i < text.size(); i++) {
         writeFile.write(text.get(i) + "\n");
      }
      
   }
   
   public void close() throws IOException {
      writeFile.close();
   }
      
  }

