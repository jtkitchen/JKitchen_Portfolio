/**
 * Python Program Tester
 * Roberts/Houghton 2015
 * 
 * This program runs Python programs in any environment. It allows input
 * and output redirections to files. It can also print the results of the python
 * programs.
 * @author Willhoft
 * @author Sarah Hutchinson
 */

package pythonprogramtester;

// TODO - really should remove passing settings - this is too tightly coupled
import java.io.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextArea;

public class PythonExecutor {

    /**
     * Execute a Python program
     *
     * @param path the folder with inFile, outFile, and destination for errorFile 
     * @param program the file containing the program to be run.
     * @param input the file containing the various inputs that are required
     * for the program.
     * @param output the output of running the program. BTW, unless the program
     * echos the input, the input will not be included in this file.
     * @param error a file containing error messages.
     * @param statusTextArea a TextArea in which to write status messages.
     * @param settings a reference to the object that contains the settings.
     * @return the return code provided by the operating system, 0 indicates
     * success.
     */
    
    public static int run(String path,
                          String program,
                          String input,
                          String output,
                          String error,
                          JTextArea statusTextArea,
                          ProgramSettings settings) {
        
        program = program.replace("\\", "/");
        boolean wait; 
        
        int slash = program.lastIndexOf("/");
        if (slash > 0) {
            path = program.substring(0, slash + 1);
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    settings.getPythonExecutable(), path + "/" + program );
            File workDir = new File( path );
            pb.directory( workDir );
            File inFile = new File( path + "/" + input );
            pb.redirectInput( inFile );
            File outFile = new File( path + "/" + output );
            pb.redirectOutput( outFile );
            File errorFile = new File( path + "/" + error );
            pb.redirectError( errorFile );
            
            Process process = pb.start();
            // If program takes longer than 10 seconds to run, destroy
            wait = process.waitFor(15, TimeUnit.SECONDS);
            process.destroy();
            process.waitFor(); // wait for the process to terminate
            int rc2 = process.exitValue();

            if (0 != rc2) {
                // command was unsuccessful
                if (verbose) {
                    TextFileReader errorFileReader = new TextFileReader(path + errorFile);
                    while (errorFileReader.hasNext()) {
                        statusTextArea.append(errorFileReader.next() + "\n");
                    }
                    statusTextArea.update(statusTextArea.getGraphics());
                }
                if (!wait) {
                    statusTextArea.append("ERROR " + rc2 + ": Process timed out; check for infinite loop \n");
                } else {
                    statusTextArea.append("ERROR: exitValue = " + rc2 + "\n");
                    // TODO:  Need a better way to deal with execution problems
                    // TextFileWriter errorFile = new TextFileWriter(path + outFile);
                    // errorFile.writeFile("*** EXECUTION ERROR - exitValue = " + rc + " ***");
                }
            }
            return rc2;
            
        } catch (Exception e) {
            // e.printStackTrace();
            statusTextArea.append(e.getMessage() + "\n");
            statusTextArea.update(statusTextArea.getGraphics());
            return -1;
        }
    }
    
    /**
     * Executes a test program to ensure python executable path is correct
     *
     * @param settings a reference to the object that contains the settings.
     * @return the message containing valid python version or invalid path notice
     */
    public static String runTestPath(String pythonPath) throws IOException {
        final File pathFile = new File(".");
        String path = pathFile.getAbsoluteFile().getParent();
        File testFile = new File(path + "/" + "$TEST$PYTHON$PATH$.py");
        writeTestFile(testFile);
        String message = "";
        
        
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    pythonPath, testFile.getPath() );
            File workDir = new File( path );
            pb.directory( workDir );
            File outFile = new File( path + "/" + "$OUTPUT$" );
            pb.redirectOutput( outFile );
            File errorFile = new File( path + "/" + "$ERROR$" );
            pb.redirectError( errorFile );
            
            Process process = pb.start();
            int rc1 = process.waitFor();
            int rc2 = process.exitValue();

            if (0 != rc2) {
                // there was an error
                TextFileReader errorFileReader = new TextFileReader(errorFile.toString());
                while (errorFileReader.next() != " ") {
                    message = "Invalid python path";
                }
            } else {
                TextFileReader outFileReader = new TextFileReader(outFile.toString());
                message = outFileReader.next();
            }   
            outFile.delete();
            errorFile.delete();
            pathFile.delete();
            testFile.delete();
            
        } catch (Exception e) {
            //message = e.toString();
            message = "Invalid python path";
        }
        return message;
    }
    /**
     * @param file the test file to be written to.
     */
    public static void writeTestFile(File file) throws IOException {
    FileWriter fileWriter = new FileWriter(file);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print("import sys \n");
    printWriter.printf("print( \"Version\", (sys.version_info)[0])");
    printWriter.close();
}

    /**
     * @param mode ether true or false. If true, it sets verbose to true.
     * If false, it sets verbose to false. 
     */
    public static void setVerboseMode(boolean mode) {
        verbose = mode;
    }
    private static boolean verbose = false;
}
