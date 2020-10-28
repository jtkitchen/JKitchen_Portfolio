package pythonprogramtester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.LineNumberReader;

/**
 * A class to read PyTest files.
 * Roberts/Houghton 2015
 * @author RG Willhoft
 */

/** TODO
 *   - Document this class
 *   - Handle comments
 *   - Handle copy
 *   - Handle run
 */

public class PytestFileReader {

    private List<String> stringList;
    private String filePath;
    private int line;
    private List<String> includedFileList; 
    private int itr;

    public PytestFileReader(String filePath) throws IOException, InvalidTagFormatException {
        
        assert( filePath.endsWith( ".pytest") );
        
        this.filePath = filePath;
        File fileToRead = new File(filePath);
        BufferedReader fileReader = null;
        LineNumberReader lineNumber = null;


        try {
            fileReader = new BufferedReader(new FileReader(fileToRead));
            lineNumber = new LineNumberReader(new FileReader(fileToRead));
            String lineInFile = null;
            line = 1;
            stringList = new ArrayList<>();
            includedFileList = new ArrayList<>();
            String parentDirectory = fileToRead.getParent();

            while ((lineInFile = fileReader.readLine()) != null) {
                if (lineInFile.startsWith("<include")) {
                    if( includedFileList.contains( lineInFile )){
                        throw new InvalidTagFormatException( "Include tags is repeated - will not be able to run","");
                    }
                        IncludeList.add( lineInFile );
                        int firstQuote = lineInFile.indexOf("\"");
                        int lastQuote = lineInFile.lastIndexOf("\"");
                        String includeFileName = lineInFile.substring(firstQuote + 1, lastQuote);
                        PytestFileReader included
                            = new PytestFileReader(parentDirectory + "/" + includeFileName);
                        stringList.addAll(included.getText());
                        
                        includedFileList.add(includeFileName);
                // }
                
                // if (lineInFile.length() > 8 // RGW01 - TBD - Improve this
                   //     && lineInFile.substring(0, 8).equals("<include")) {
                   // stringList.add("");
                } else {
                    stringList.add(lineInFile);
                }
                //Brendan Villnave
                //added this while loop in order to help the user
                //see what line in the .pytest file they are getting an error on
                while((lineInFile = lineNumber.readLine()) != null) {
                    if(lineInFile.contains("<include")) {
                        line = lineNumber.getLineNumber();
                    }
                }
            }
        } catch ( StringIndexOutOfBoundsException e ){
            throw new InvalidTagFormatException("Line " + line + " <include tag is invalid - " + filePath, "");
        }
        
        catch (FileNotFoundException e) {
            // TODO - Handle this and other exceptions by re-throwing an error
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if(lineNumber != null) {
                    lineNumber.close();
                }
            } catch (IOException e) {
                throw e;
            }
            itr = 0;
        }
    }

    public List<String> getText() {
        return stringList;
    }
    
    public List<String> getIncludedFiles()
    {
        return includedFileList;
    }

    public boolean hasNext() {
        return itr < stringList.size();
    }

    public String next() {
        String line = stringList.get(itr++);
        return line;
    }

    private String trimEnd(String line) {
        return line.replaceAll("\\s+$", "");
        // int lastNonBlank = line.length();
        // while( ' ' == line.charAt( --lastNonBlank ) ) { }
        // return line.substring( 0, lastNonBlank+1 );
    }
    
    private static class IncludeList{
        private static List<String> includeList = new ArrayList<>();
        
        private static void add( String fileName ){
            includeList.add( fileName );
        }
        
        private static boolean contains( String fileName ){
            return includeList.contains( fileName );
        }
        
        private static List<String> getIncludeList(){
            return includeList;
        }
        
    }
    
}

// RGW01 - Fixed a problem if the line is shorter than 8 characters.  
