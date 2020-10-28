package pythonprogramtester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Validates that the given path for a pytest file is valid, and then validates 
 * that the file is tagged appropriately.
 *
 * @author Nicole
 */
public class PythonProgramValidator {
    
    String filePath;
    String exception;
    String tagInError;
    
    /**
     * Constructs a PythonProgramValidator with a path of the file
     * 
     * @param path The path of the file in question
     */
    public PythonProgramValidator( String path ) {
        this.filePath = path;
    }
    /**
     * Returns the message given from the exception.
     * 
     * @return exception the message given from the exception
     */
    public String getException() {
        return exception;    
    }
    /**
    * Returns the tag that was erroneous.
    * 
    * @return tagInError the tag that was erroneous
    */
    public String getTag() {
        return tagInError;
    }
    
    /**
     * Validates that the path is correct and, by using TagParser, that the 
     * tags are correct.
     * 
     * @return isValidFile the path and the tagging are valid or invalid
     * @throws IOException 
     */
    public boolean isValid() throws IOException {
        boolean isValidFile = true;
        List<String> fileText = null;
        List<String> tagNames = new ArrayList<String>();
        tagNames.add( "test" );
        tagNames.add( "input" );
        
        try{
            fileText = this.readFile();            
        }
        catch( IOException ex ) {
            // RGW: Added the following to provide more information about the error
            //      that was caught.
            // TODO: Might want to improve how this is handled, should it be rethrown, etc.             
                    throw ex;
        }catch( InvalidTagFormatException e ){
            tagInError = "** Exception: " + e.getMessage();             
                    isValidFile = false;
        }
        
        if( isValidFile ) {
            for ( String tag : tagNames ) {
                boolean prior = isValidFile;
                if( !prior ) {
                    break;
                }
                boolean valid = this.validateTagging( tag, fileText );
                if( !valid ) {
                    isValidFile = valid;
                }
            }
        }
        
        return isValidFile;
    }
    
    /**
     * Determines if the tag structure is valid. 
     * 
     * @param tagName
     * @param fileText
     * @return validTag the tagging is correct or incorrect
     */
    private boolean validateTagging( String tagName, List<String> fileText ) {
        boolean validTag = true;
        TagParser testParser = new TagParser( tagName );
           testParser.addText( fileText );
           if( testParser.hasNext() ) {
               try{
                   TagData data = testParser.next();
                   while( testParser.hasNext() ) {
                       data = testParser.next();
                   }
               }
               catch( InvalidTagFormatException ex ) {
                   tagInError = tagName;
                   exception = ex.getMessage();
                   validTag = false;
               }
           }
        return validTag;
    }
    
    /**
     * Reads a file with the TextFileReader and provides the lines from the file
     * in a List.
     * 
     * @return fileText a list of the lines in the file read
     * @throws IOException 
     */
    private List<String> readFile() throws IOException, InvalidTagFormatException {
        List<String> fileText = null;
        PytestFileReader reader = new PytestFileReader( filePath );
        fileText = reader.getText();
        return fileText;
    }
}
