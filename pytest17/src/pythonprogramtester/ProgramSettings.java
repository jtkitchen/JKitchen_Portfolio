/**
 * Python Program Tester
 * Roberts/Houghton 2015
 * 
 * Loads the settings menu and provides methods to save changes that are made
 * to the settings.
 * 
 * @author Willhoft
 */

package pythonprogramtester;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Loads the settings menu and provides methods to save changes that are made
 * to the settings.
 * 
 * @author Willhoft
 */
public class ProgramSettings {

    // Instance variables
    private String pythonExecutable;  // The path and name of the python executable file
    private String lastTestCase;
    private String lastTestingPath;
    private String tempProgramFile;
    private String tempInputFile;
    private String tempOutputFile;
    private String resultsFileName;

    public ProgramSettings() {
        if (!loadSettings()) {
            pythonExecutable = "";
            lastTestCase = "";
            lastTestingPath = "";
            tempProgramFile = "$P$P$T$PROGRAM$.PY";
            tempInputFile = "$P$P$T$INPUT$.TXT";
            tempOutputFile = "$P$P$T$OUTPUT$.TXT";
            resultsFileName = "$TEST$RESULTS$.TXT";
        }
    }

    /**
    * Loads the settings from the text file "$PYTEST$SETTINGS$.TXT"
    * 
    * @return true if the settings load correctly. Return false if there is 
    *  an IOException in the loading process.
    */
    // TODO: Replace these the flat file with an XML file
    public boolean loadSettings() {
        try {
            TextFileReader reader = new TextFileReader("$PYTEST$SETTINGS$.TXT");
            pythonExecutable = reader.next();
            // System.out.println( "Python Executable: " + pythonExecutable );
            lastTestCase = reader.next();
            lastTestingPath = reader.next();
            tempProgramFile = reader.next();
            tempInputFile = reader.next();
            tempOutputFile = reader.next();
            resultsFileName = reader.next();
            // System.out.println( "Results file name: " + resultsFileName );
            return true;
        } catch (IOException ex) {
            // TODO: Should do something else here
            JOptionPane errorBox = new JOptionPane();
            errorBox.showMessageDialog( null, "Settings not loaded (IO Exception)" );
            System.out.println("Settings not loaded");
            return false;
        //}catch ( InvalidTagFormatException e ){
        //    JOptionPane errorBox = new JOptionPane();
        //    errorBox.showMessageDialog( null, "Settings not loaded (Tag Exception)" );
            
        //    return false;
        }
    }
    
    /**
    * Saves the settings by writing them to the text file "$PYTEST$SETTINGS$.txt"
    * 
    * @return true if the settings save correctly. Return false if there is 
    *  an IOException in the writing process.
    */
    public boolean saveSettings() {
        try {
            TextFileWriter writer = new TextFileWriter("$PYTEST$SETTINGS$.TXT");
            writer.writeLine(pythonExecutable);
            writer.writeLine(lastTestCase);
            writer.writeLine(lastTestingPath);
            writer.writeLine(tempProgramFile);
            writer.writeLine(tempInputFile);
            writer.writeLine(tempOutputFile);
            writer.writeLine(resultsFileName);
            writer.close();
            return true;
        } catch (IOException ex) {
            // TODO: Should do something else here
            JOptionPane errorBox = new JOptionPane();
            errorBox.showMessageDialog( null, "Settings not saved (IO Exception)" );
            System.out.println("Settings not saved");
            return false;
        }
    }
    
    /**
    * @return a string containing the path and name of the python executable 
    *  file
    */
    public String getPythonExecutable() {
        return pythonExecutable;
    }
    
    /**
    * Sets a new Python Executable file.
    * 
    * @param exec a string containing the path and name of a new python executable 
    *  file, which is then set as the current python executable file.
    */
    public void setPythonExecutable(String exec) {
        pythonExecutable = exec;
    }
    
    /**
    * @return a string containing the last test case.
    */
    public String getLastTestCase() {
        return lastTestCase;
    }
    
    /**
    * Sets the last test case.
    * 
    * @param last a string containing a test case, which is then set as the
    * last test case
    */
    public void setLastTestCase(String last) {
        lastTestCase = last;
    }

    /**
    * @return a string containing the last testing path
    */
    public String getLastTestingPath() {
        return lastTestingPath;
    }
    
    /**
    * Sets the last testing path.
    * 
    * @param last a string containing a testing path, which is then set as the
    * last testing path
    */
    public void setLastTestingPath(String last) {
        lastTestingPath = last;
    }

    /**
    * @return a string containing the name of the temporary program file
    */
    public String getTempProgramFileName() {
        return tempProgramFile;
    }

    /**
    * Sets the temporary program file
    * 
    * @param name a string containing a program file name, which is then set 
    * as the temporary program file
    */
    public void setTempProgramFileName(String name) {
        tempProgramFile = name;
    }

    /**
    * @return a string containing the name of the temporary input file
    */
    public String getTempInputFileName() {
        return tempInputFile;
    }

    /**
    * Sets the temporary input file
    * 
    * @param name a string containing an input file name, which is then set 
    * as the temporary input file
    */
    public void setTempInputFileName(String name) {
        tempInputFile = name;
    }

    /**
    * @return a string containing the name of the temporary output file
    */
    public String getTempOutputFileName() {
        return tempOutputFile;
    }

    /**
    * Sets the temporary output file
    * 
    * @param name a string containing an output file name, which is then set 
    * as the temporary output file
    */
    public void setTempOutputFileName(String name) {
        tempOutputFile = name;
    }

    /**
    * @return a string containing the name of the results file
    */
    public String getResultsFileName() {
        return resultsFileName;
    }

    /**
    * Sets the results file
    * 
    * @param name a string containing a results file name, which is then set 
    * as the results file
    */
    public void setResultsFileName(String name) {
        resultsFileName = name;
    }
}
// Needed:
//   Path to the python executable
