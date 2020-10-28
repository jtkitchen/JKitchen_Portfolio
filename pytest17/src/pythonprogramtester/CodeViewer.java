/**
 * Python Program Tester
 * Roberts/Houghton 2015
 * 
 * Opens the student's code
 * @author Tim
 */

package pythonprogramtester;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class CodeViewer {
    private String idleCommand;
    Runtime run = Runtime.getRuntime();
    
    /**
     * Finds the location of the Python executable
     */
    private void findIdleLocation(){
        ProgramSettings settings = new ProgramSettings();
        String executableLocation = settings.getPythonExecutable();
        // TODO: this code depends on the executable location being enclosed in 
        // quotes. Also depends on a fixed size of the python.exe.
        // This whole thing with executable location needs to be redesigned.
        idleCommand = "\"" + executableLocation + "\" \"" 
                      + executableLocation.substring( 0, executableLocation.length()-11 )
                      + "/lib/idlelib/idle.py\" " + "-e "; 
    }

    
    /**
     * Executes the command built by buildCommand
     * @param command the command to be run
     */
    private void runCommand( String command ){
        try {
            // should check to make sure it is valid before we run it for 
            // security purposes
            run.exec( command );
        } catch (IOException ex) {
            System.out.println( "Cannot open the file" );
        }
    }   
    
    /**
     * 
     * @param fileName file to be opened in Idle
     * @return a command line command
     */
    private String buildCommand( String fileName ){
        String command = idleCommand + "\"" + fileName + "\"";
        return command;
    }
    
    /**
     * opens the code
     * @param fileName Code to be opened.  Must use forward slashes.
     * @param frame JFrame for JOptionPane
     * @return If the file exists, returns true, otherwise returns false.
     */
    public boolean openCode( String fileName, JFrame frame ){
        if( fileName != null){
            
            File file = new File( fileName );
            //fileName has two lines if last index of \n is more than 0
            
            //Seperate these so that it checks if the fileName has .py
            
            if( fileName.lastIndexOf("\n") == -1 || fileName.lastIndexOf(".pytest") > -1){
                if( file.exists() && (fileName.substring(fileName.length()-3).equals(".py") 
                || fileName.substring(fileName.length()-4).equals(".py/"))){
                
                    
                    findIdleLocation();
                    runCommand( buildCommand( fileName ) );
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(frame,"File not found");
                    return false;
                }
            }else{
                return false;
            }
        }
        else{
            System.out.println( "File name cannot be null" );
            return false;
        }
    }
}
