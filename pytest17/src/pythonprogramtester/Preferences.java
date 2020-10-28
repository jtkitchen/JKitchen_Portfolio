package pythonprogramtester;

//Ty Carpenter
//Preferences.java
//Class used to store the run options for the PythonProgramTester to use
// 5/11/2020

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import javax.swing.JTextArea;
import java.util.Date;
import java.util.Calendar;
import java.awt.Graphics2D;
import java.awt.*;

/**
 *
 * @author Ty Carpenter
 */
public class Preferences {
    
    public static String testFile; //Orignal testFile variable in PythonProgramTester
    public static String testPath; //Orignal testingPath variable in PythonProgramTester
    public static String format; //Orignal resultsFormat variable in PythonProgramTester
    public static boolean ignoreSpaCap; //Orignal ignoreSpaCap variable in PythonProgramTester
    public static boolean removeBlankLines; //Orignal removeBlankLines variable in PythonProgramTester
    public static boolean debug; //Orignal debug variable in PythonProgramTester
    public static JTextArea statusTextArea; //Orignal statusTextArea variable in PythonProgramTester
    public static ProgramSettings settings; //Orignal settings variable in PythonProgramTester
    
    //Constructor for Preferences
    public Preferences(String tF, String tP, String f, boolean iSpC, boolean rBL, boolean d, JTextArea rTA, ProgramSettings s)
    {
        testFile = tF;
        testPath = tP;
        format = f;
        ignoreSpaCap = iSpC;
        removeBlankLines = rBL;
        debug = d;
        statusTextArea = rTA;
        settings = s;
        
    }
    //Test File Accessor
    public static String getTestFile()
    {
        return testFile;
    }
    //TestPath Accessor
    public static String getTestPath()
    {
        return testPath;
    }
    //Format Accessor
    public static String getFormat()
    {
        return format;
    }
    //IgnoreSpaceCapital Accessor
    public static boolean getIgnoreSpaCap()
    {
        return ignoreSpaCap;
    }
    //RemoveBlankLines Accessor
    public static boolean getRemoveBlankLines()
    {
        return removeBlankLines;
    }
    //Debug Accessor
    public static boolean getDebug()
    {
        return debug;
    }
    //StatusTextArea Accessor
    public static JTextArea getStatusTextArea()
    {
        return statusTextArea;
    }
    //settings Accessor
    public static ProgramSettings getSettings()
    {
        return settings;
    }
    //Test File Mutator
    public static void setTestFile(String tF)
    {
        testFile = tF;
    }
    //TestPath Mutator
    public static void setTestPath(String tP)
    {
        testPath = tP;
    }
    //Format Mutator
    public static void setFormat(String f)
    {
        format = f;
    }
    //IgnoreSpaceCapital Mutatorr
    public static void setIgnoreSpaCap(boolean iSpC)
    {
        ignoreSpaCap = iSpC;
    }
    //RemoveBlankLines Mutator
    public static void setRemoveBlankLines(boolean rBL)
    {
        removeBlankLines = rBL;
    }
    //Debug Mutator
    public static void setDebug(boolean d)
    {
        debug = d;
    }
    //StatusTextArea Mutator
    public static void setStatusTextArea(JTextArea rTA)
    {
        statusTextArea = rTA;
    }
    //settings Mutator
    public static void setSettings(ProgramSettings s)
    {
        settings = s;
    }
    //Creates a substring from testFile
    public static String testFileSubstring(int start, int end)
    {
        String s = testFile.substring(start, end);
        return s;
    }
    //Finds what testFile ends with
    public static boolean testFileEndsWith(String str)
    {
        boolean b = testFile.endsWith(str);
        return b;
    }
    //Finds the last index of testFile
    public static int testFileLastIndexOf(String str)
    {
        int last = testFile.lastIndexOf(str);
        return last;
    }
    //format comparison to given string
    public static boolean formatEquals(String str)
    {
        boolean f = format.equals(str);
        return f;
    }
    //Note - Method not used within PythonProgramTester as it creates an error
    //Need a future fix for this^
    /*
    public static boolean debugDeleteFile(String tP, String fN)
    {
        return PythonProgramTester.deleteFile(tP + fN);
        
    }*/
    //adds text to end of statusTextArea
    public static void sTAAppend(String str)
    {
        statusTextArea.append(str);
        
    }
    //gets the text area of statusTextArea
    public static Graphics sTAGetGraphics()
    {
        Graphics g = statusTextArea.getGraphics();
        return g;
    }
    //updates statusTextArea
    public static void sTAUpdate(Graphics g)
    {
        statusTextArea.update(g);
    }
    //accesses TempProgramFile name within settings
    public static String settingsGetTempProgramFileName()
    {
        return settings.getTempProgramFileName();
    }
    //accesses TempInputFile name within settings
    public static String settingsGetTempInputFileName()
    {
        return settings.getTempInputFileName();
    }
    //accesses TempOutputFile name within settings
    public static String settingsGetTempOutputFileName()
    {
        return settings.getTempOutputFileName();
    }
    //accesses ResultsFile within name settings
    public static String settingsGetResultsFileName()
    {
        return settings.getResultsFileName();
    }
}
