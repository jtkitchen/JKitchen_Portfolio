package pythonprogramtester;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import javax.swing.JTextArea;
import java.util.Date;
import java.util.Calendar;
import java.io.IOException;

// TODO separate reading and parsing test case file(s) and actually doing 
//      the comparisons


public class PythonProgramTester {
    // TODO - here and throughout - handle exceptions
    
    //Ty Carpenter Issue #39
    //Created new class called Preferences
    //Used as a single paramter for runTests()
    //Removed several variables to repace with method calls to Preferences p
    //There are notes next to these changes in case change causes error
    public static void runTests(Preferences p) throws InvalidTagFormatException, FileNotFoundException, IOException {
        
        String testCaseFile = p.getTestFile();
        String testingPath = p.getTestPath();
        String resultsFormat = p.getFormat();
        boolean ignoreSpaCap = p.getIgnoreSpaCap();
        boolean removeBlankLines= p.getRemoveBlankLines();
        boolean debug = p.getDebug();
        JTextArea statusTextArea = p.getStatusTextArea();
        ProgramSettings settings= p.getSettings();

        // ultimately we need to figure actual OS
        OperatingSystem opSys = new OperatingSystemWindows();  
        
       String tempProgramFileName = settings.getTempProgramFileName();
        String tempInputFileName = settings.getTempInputFileName();
        String tempOutputFileName = settings.getTempOutputFileName();
        // TODO Replace this with actual storage of name in settings
        String tempErrorFileName = settings.getTempOutputFileName();
        int lastPeriod = tempErrorFileName.lastIndexOf(".");
        tempErrorFileName = tempErrorFileName.substring(0, lastPeriod) + "Error$"
                + tempErrorFileName.substring(lastPeriod);
        String resultsFileName = settings.getResultsFileName();
        
        
        //Ty Carpenter
        //From issue #89, added a new statistic variable to see if the test passes or fails
        //NOCOMP tests don't keep track of statistics, only if the test runs, therefore a variable is need to see if that run fails
        //nocompInTests keeps track to see if there is a single nocomp test so the results prints ALL TESTS RAN
        boolean testsSuccessful = true;
        boolean nocompInTests = false;
        
        int incorrectValues = 0;
        int incorrectText = 0;
        int incorrectSpacing = 0;
        int extraBlankLines = 0;
        int missingBlankLines = 0;
        int canNotRun = 0;
        int executionErrors = 0;

        //Brendan Villnave lines 48-51
        //From issue #111 I added the time of testing in military format
        //also tells you the day you tested the file
        Date current = new Date();
        statusTextArea.append("Date and time tested: "+ current + "\n");
        statusTextArea.update(statusTextArea.getGraphics());
        testingPath = testingPath + "/";
        statusTextArea.append("Test Case File: " + p.getTestFile() + "\n");
        statusTextArea.update(statusTextArea.getGraphics());
        statusTextArea.append("Testing path: " + testingPath + "\n");
        statusTextArea.update(statusTextArea.getGraphics());


        List<String> tests = new LinkedList<>();
        List<String> includes = new ArrayList<>();
        //Define Lists for storing information about files tested and whether
        //testes passed or failed.
        List<String> fileNames = new ArrayList<>();
        List<Integer> testsPassed = new ArrayList<>();
        List<Integer> testsApproximate = new ArrayList<>();
        List<Integer> testsRun = new ArrayList<>();
        //new list that stores information about nocompFiles run
        List<Integer> nocompTestsRun = new ArrayList<>();
        
        //Ty Carpenter Issue #89
        //boolean variable if NOCOMP tag is present
        boolean noComp = false;
        
        try {
            PytestFileReader testFile = new PytestFileReader(testCaseFile);
            tests = testFile.getText();
            includes = testFile.getIncludedFiles();
        } catch (Exception ex) {
            statusTextArea.append("Can't read test file: " + testCaseFile + "\n");
        }
        
        //Ty Carpenter Issue #94
        //Finds a Run tag in tests and transforms it into a NOCOMP test tag
        
        for(int i = 0; i < tests.size(); i++)
        {
            
            String possibleRunTag = tests.get(i);
            String upperPossibleRunTag = possibleRunTag.toUpperCase();
            String keepFile = "";
            if(upperPossibleRunTag.length() > 10)
            {
                
                String t = upperPossibleRunTag.substring(0, 10);
                
                if(t.equals("<RUN CODE="))
                {
                    
                    if(upperPossibleRunTag.substring(upperPossibleRunTag.length()-1,upperPossibleRunTag.length()).equals(">") && upperPossibleRunTag.substring(upperPossibleRunTag.length()-2,upperPossibleRunTag.length()-1).equals("/"))
                    {
                        
                        keepFile = possibleRunTag.substring(10, possibleRunTag.length() - 2);
                        String newTag = "<test code=" + keepFile + "nocomp>";
                        tests.set(i, newTag);
                        tests.add(i+1, "</test>");
                    }
                }
            }
            
        }
        
        // Process any COPY tags to copy files from the test case folder to
        //   the folder under test
        
        String testCasePath = testCaseFile.substring( 0, testCaseFile.lastIndexOf( "/"));
        //testCaseFile.substring( 0, testCaseFile.lastIndexOf( "/"));
        
        
        for( String line : tests )
        {
            String upper = line.toUpperCase();
            // TODO: Not sure if the test for length is necessary
            // TODO: This is very demanding for spacing, etc.
            if( upper.length() > 13 && upper.startsWith( "<COPY FILE=") && upper.endsWith( "/>" ) )
            {
                String fileName = line.substring( line.indexOf( "\"" )+1, line.lastIndexOf( "\"" ) );
                String sourceFile = testCasePath + "/" + fileName;
                String destFile = testingPath + fileName;
                
                if( opSys.copy( sourceFile, destFile ) )
                {
                    statusTextArea.append( "COPIED: " + fileName + "\n" );
                
                }
                else
                {
                    statusTextArea.append( "*** NOT COPIED ***: " + fileName + "\n" );
                    
                    // might want to do something else
                }                
            }
        }       
        
        FileNameMapper fileNameMapper = 
                new FileNameMapper( null, true, tests, testingPath );
        
        if( 0 < fileNameMapper.unknownCount() )
        {
            fileNameMapper.setVisible( true );
            if( !fileNameMapper.okClicked() )
            {
                statusTextArea.append("Testing cancelled by user.");
                return;
            }
        }
        
        List<String> results = new LinkedList<>();
        
        if (!testCaseFile.endsWith(".pytest")) {
            statusTextArea.append("No tests were run because the Test Case File was not a PYTEST file.");
            // ?? : statusTextArea.update(statusTextArea.getGraphics()); 
            return;
        }
        
        for (String str : includes) {
            if (str.endsWith(".py")) {
                statusTextArea.append("The tag to include " + str + " was ignored because its argument was not a .pytest file.\n");
            }
        }
        
        TestTagParser testParser = new TestTagParser(tests);
        RUN_TESTS:
        while (testParser.hasNext()) {

            // Step #1 - Read the test case from the file
            TagBlock block = testParser.next();
            String pythonFileName = parseFileArg(block.getArgs(), statusTextArea );
            String userFileName = fileNameMapper.map( pythonFileName ); 
            List<String> testData = block.getData();
            //Find out if the file is currently in the List of tested files and
            //if so where.
            int positionInNameList = fileNames.indexOf(pythonFileName);
            if(positionInNameList==-1)
            {
                //Initialize a new element in the Lists for this new .py file.
                fileNames.add(pythonFileName);
                positionInNameList = fileNames.size()-1;
                testsPassed.add(0);
                testsApproximate.add(0);
                nocompTestsRun.add(0);
                testsRun.add(0);
            }
            //Ty Carpenter Issue #89
            //Wherever a statistic is counted (like canNotRun, missingBlankLines, etc>)
            //  check to see if there is a NOCOMP tag is present so that statistics are NOT counted in the test results
            //Either way, if there is an error, testsSuccessful becomes false
            try {
                //testPassed start out true and is set to false if the test fails.
                if( userFileName.equals( "(none)") )
                {
                    boolean testPassed = false;
                    if(!hasNoCompArg(block.getArgs()))
                    {
                        canNotRun++;
                        testsSuccessful = false;
                    }
                    else
                    {
                        testsSuccessful = false;
                    }
                    results.add("Skipped " + pythonFileName + " - NO USER FILE" );
                    results.add( "" );
                    statusTextArea.append("Skipped " + pythonFileName + " - NO USER FILE\n" );
                    statusTextArea.update(statusTextArea.getGraphics());
                }
                else
                {
                    boolean testPassed = true;
                    boolean testApproximate = true;
                    
                    if( userFileName.equals( pythonFileName ) )
                    {
                        results.add("Testing " + pythonFileName + "...");
                    }
                    else
                    {
                        results.add("Testing " + pythonFileName + 
                                    " >>> " + userFileName + "...");
                    }
                    if (true) // TODO should be if (verbose)
                    {
                        if( userFileName.equals( pythonFileName ) )
                        {
                            statusTextArea.append("Testing " + pythonFileName + "...\n");
                        }
                        else
                        {
                            statusTextArea.append("Testing " + pythonFileName + " (" + userFileName + ")...\n");
                        }
                        statusTextArea.update(statusTextArea.getGraphics());
                    }

                    // Step #2 - Modify the student's code
                    createModifiedProgram(testingPath, userFileName, tempProgramFileName);

                    // Step #3 - Create the file with the inputs to the program
                    createProgramInputs(testingPath, testData, tempInputFileName);

                    // Step #4 - Run the student's program
                    int rc = PythonExecutor.run(testingPath, tempProgramFileName,
                            tempInputFileName,
                            tempOutputFileName,
                            tempErrorFileName,
                            statusTextArea,
                            settings);

                    if( true ) // 0 == rc )
                    {
                        // Step #5 - Read the results from the output file
                        TextFileReader fileIn = new TextFileReader(testingPath + tempOutputFileName);
                        List<String> programOutput = fileIn.getText();

                        if( 0 != rc && !hasNoCompArg(block.getArgs()))
                        {
    //                        programOutput.add( "EXECUTION ERROR RC=" + rc );
    //                        TextFileReader errorIn = new TextFileReader(testingPath + tempErrorFileName);
    //                        List<String> errorText = errorIn.getText();
    //                        // Add the last two lines to the program output file, if present
    //                        if( errorText.size() >= 2 )
    //                        {
    //                            programOutput.add( errorText.get( errorText.size() - 2 ) );
    //                        }
    //                        if( errorText.size() >= 1 )
    //                        {
    //                            programOutput.add( errorText.get( errorText.size() - 1 ) );
    //                        }                       

                            executionErrors++;
                            testPassed = false;
                            testsSuccessful = false;
                        }
                        if(0 != rc && hasNoCompArg(block.getArgs()))
                        {
                            testsSuccessful = false;
                            
                        }

                        TextUtilities.removeTrailingBlanks( programOutput );

                        // Step #6 - Compare the results to the expected values
                        // TODO: move these inside the comparitor ??
                        testData = InputTagParser.removeInputTags(testData);
                        // List<String> outputText = ValueTagParser.removeValueTags( testData );
                        
                        int width = TextUtilities.maximumLength( programOutput );

                        
                        if (removeBlankLines) {
                            while (testData.remove("")) { /*
                                 * Do Nothing
                                 */ }
                            while (programOutput.remove("")) { /*
                                 * Do Nothing
                                 */ }
                        }
                        
                        if (!hasNoCompArg(block.getArgs())) {
                            // TODO: Convert to use iterators
                            while (0 != testData.size() && 0 != programOutput.size()) {
                                String testLine = testData.get(0);
                                String progLine = programOutput.get(0);

                                if (testLine.equals("") && !progLine.equals("")) {
                                    results.add("! missing blank line !");
                                    testData.remove(0);
                                    missingBlankLines++;
                                    testsSuccessful = false;
                                    testPassed = false;
                                } else if (!testLine.equals("") && progLine.equals("")) {
                                    results.add("! extra blank line !");
                                    programOutput.remove(0);
                                    extraBlankLines++;
                                    testsSuccessful = false;
                                    testPassed = false;
                                } else {
                                    TextLineComparator comp =
                                            new TextLineComparator(testLine, progLine);
                                    //TODO: Count multiple errors on one line?
                                    boolean exact = comp.equalsExact();
                                    boolean approx = comp.equalsSomeWhat();

                                    if (exact) {
                                        results.add("= " + testLine);
                                    } else if (approx) {
                                        if (ignoreSpaCap) {
                                            results.add("~ " + testLine);
                                        } else {
                                            testPassed=false;
                                            incorrectText++;
                                            incorrectSpacing++;
                                            testsSuccessful = false;

                                            if (resultsFormat.equals("Sequential")) {
                                                results.add("~ " + progLine);
                                                results.add("> " + ValueTagParser.removeValueTags(testLine));
                                            } else {
                                                results.add("~ " + TextUtilities.adjustLength( progLine, width ) + " >-SHOULD BE-> "
                                                        + ValueTagParser.removeValueTags(testLine));
                                            }
                                        }
                                    } // else if( comp.equalValues() )
                                    // {
                                    // 	results.add( "# " + programOutput.get(i) );
                                    //      results.add( "> " + ValueTagParser.removeValueTags(testData.get(i)) );
                                    // }
                                    else {
                                        incorrectText++;
                                        testsSuccessful = false;
                                        testPassed = false;
                                        testApproximate = false;
                                        if (resultsFormat.equals("Sequential")) {
                                            results.add("X " + progLine);
                                            results.add("> " + ValueTagParser.removeValueTags(testLine));
                                        } else {
                                            results.add("X " + TextUtilities.adjustLength( progLine, width ) + " >-SHOULD BE-> "
                                                    + ValueTagParser.removeValueTags(testLine));
                                        }
                                    }
                                    testData.remove(0);
                                    programOutput.remove(0);
                                }
                            }
                            while (0 != testData.size()) {
                                testApproximate = false;
                                testPassed = false;
                                if( 0 == rc )
                                {
                                    // Only add this if there was not a python error
                                    incorrectText++;
                                    testsSuccessful = false;
                                    if (resultsFormat.equals("Sequential")) {
                                        results.add("X **NO*OUTPUT*LINE**");
                                        results.add("> " + ValueTagParser.removeValueTags(testData.get(0)));
                                    } else {
                                        results.add("X " +  TextUtilities.adjustLength( "**NO*OUTPUT*LINE**", width) + " >-SHOULD BE-> "
                                                + ValueTagParser.removeValueTags(testData.get(0)));
                                    }
                                }
                                testData.remove(0);
                            }
                            while (0 != programOutput.size()) {
                                testApproximate = false;
                                testPassed = false;
                                incorrectText++;
                                testsSuccessful = false;
                                if (resultsFormat.equals("Sequential")) {
                                    results.add("X " + programOutput.get(0));
                                    results.add("> **NO*OUTPUT*LINE**");
                                } else {
                                    results.add("X " +  TextUtilities.adjustLength( programOutput.get(0), width )
                                        + " >-SHOULD BE-> **NO*OUTPUT*LINE**");
                                }
                                programOutput.remove(0);
                            }
                        } else {
                            
                            
                            //Ty Carpenter Issue #89
                            //Variable when NOCOMP tag is found becomes true
                            // NoComp argument present
                            //nocompInTests is true so now will print ALL TESTS RUN if they all pass
                            nocompInTests = true;
                            noComp = true;
                            for (int i = 0; i < programOutput.size(); i++) {
                                results.add("  " + programOutput.get(i));
                            }
                        }
                    }
                    else
                    {
                        if(!hasNoCompArg(block.getArgs()))
                        {
                            executionErrors++;
                            testsSuccessful = false;
                        }
                        else
                        {
                            testsSuccessful = false;
                        }
                        testPassed = false;
                        testApproximate = false;
                        if (debug) {
                            break RUN_TESTS;
                        }
                    }
                    //Ty Carpenter Issue #89
                    //If there is no NOCOMP tag, testPassed and testApproximate are counted
                    if(noComp == false)
                    {

                        TextFileReader errorIn = new TextFileReader(testingPath + tempErrorFileName);
                        List<String> errorOutput = errorIn.getText();
                        for( String line : errorOutput )
                        {
                            line = line.replace( testingPath+tempProgramFileName, userFileName );
                            results.add( "E " + line );
                        }

                        results.add("");


                        if( testPassed )
                        {
                            testsPassed.set(positionInNameList,
                                            testsPassed.get(positionInNameList)+1);
                        }
                    
                        if( testApproximate )
                        {
                            testsApproximate.set(positionInNameList,
                                                testsApproximate.get(positionInNameList)+1);
                        }
                    }
                    //Ty Carpenter Issue #89
                    //Resets NOCOMP argument for future tests
                    else
                    {
                        //nocompTestsRun is counted
                        nocompTestsRun.set(positionInNameList,
                             testsRun.get(positionInNameList)+1);
                        noComp = false;
                    }
                    testsRun.set(positionInNameList,
                             testsRun.get(positionInNameList)+1);

                } 
            }    
            catch (Exception ex) 
            {
                results.add("Exception: " + ex.getMessage() + "\n");
                statusTextArea.append("Can't run test: " + pythonFileName + ex.getMessage() + "\n");
                statusTextArea.update(statusTextArea.getGraphics());
                if(!hasNoCompArg(block.getArgs()))
                {
                    canNotRun++;
                    testsSuccessful = false;
                }
                else
                {
                    testsSuccessful = false;
                }
                if (debug) 
                {
                    break RUN_TESTS;
                }
            }
        }
        //Brendan Villnave
        //Edited the following lines so everything after "Run Completed!"
        //gets added to the $TEST$RESULTS$.txt file as well
        TextFileWriter outFile = new TextFileWriter(testingPath + resultsFileName);
        if (!debug) {
            deleteFile(testingPath + tempProgramFileName);
            deleteFile(testingPath + tempInputFileName);
            deleteFile(testingPath + tempOutputFileName);
            deleteFile(testingPath + tempErrorFileName);
        }
        statusTextArea.append("Run completed!\n\n");
        //Ty Carpenter Issue #89
        //testsSuccessful variable is now counted in overal statistics of the tests
        
        if (incorrectValues == 0
                && incorrectText == 0
                && incorrectSpacing == 0
                && extraBlankLines == 0
                && missingBlankLines == 0
                && canNotRun == 0
                && executionErrors == 0
                && testsSuccessful == true) {
            // TODO - in debug mode, this gets printed sometimes when it shouldn't
            //Determines ALL TESTS RUN vs ALL TESTS RUN EXACTLY CORRECTLY
            if(nocompInTests==true)
            {
                statusTextArea.append("ALL TESTS RAN\n");
                results.add("ALL TESTS RUN EXACTLY CORRECTLY\n");
            }
            else
            {
                statusTextArea.append("ALL TESTS EXACTLY CORRECT\n");
                results.add("ALL TESTS RUN EXACTLY CORRECTLY\n");
            }
        } else {
            // TODO - give option to open student's code
            statusTextArea.append("Incorrect values ............ " 
                    + incorrectValues + "\n");
            results.add("Incorrect values ............ " 
                    + incorrectValues + "\n");
            statusTextArea.append("Lines with errors ........... " 
                    + incorrectText + "\n");
            results.add("Lines with errors ........... " 
                    + incorrectText + "\n");
            statusTextArea.append("Bad spacing/capitalization .. " 
                    + incorrectSpacing + "\n");
            results.add("Bad spacing/capitalization .. " 
                    + incorrectSpacing + "\n");
            statusTextArea.append("Extra/missing blank lines ... "
                    + (extraBlankLines + missingBlankLines) + "\n");
            results.add("Extra/missing blank lines ... "
                    + (extraBlankLines + missingBlankLines) + "\n");
            statusTextArea.append("Python errors ............... " 
                    + (executionErrors)+"\n");
            results.add("Python errors ............... " 
                    + (executionErrors)+"\n");
            statusTextArea.append("Can't run test .............. " 
                    + canNotRun +"\n");
            results.add("Can't run test .............. " 
                    + canNotRun +"\n");
            statusTextArea.append("Code was successful .............. " 
                    + testsSuccessful +"\n");
            results.add("Code was successful .............. " 
                    + testsSuccessful +"\n");
        }
        statusTextArea.append("\n");
        
        for(int i=0; i<fileNames.size(); i++)
        {
        int passes = testsPassed.get(i);
        int approximate = testsApproximate.get(i);
        int run = testsRun.get(i);
        int nocompRun = nocompTestsRun.get(i);
         
        String dots = " ................................................";
        statusTextArea.append( ( fileNames.get(i)+dots).substring(0,41)+" ");
        results.add(( fileNames.get(i)+dots).substring(0,41)+" ");
        
        
        if(run == 0 )
             
        {
            statusTextArea.append("NO TESTS RUN\n");
            results.add("NO TESTS RUN\n");
        }
        //new results output All Tests Ran (check output)
        else if(nocompRun == run)
        {
            statusTextArea.append("All Tests Ran (check output)\n");
            results.add("All Tests Ran (check output)\n");
        }
        else if(passes == run)
        {
            statusTextArea.append("All Tests Passed\n");
            results.add("All Tests Passed\n");
        }
        else{
            
        statusTextArea.append( passes + "/" + approximate + "/" + 
                            run + " tests passed/approximate/run\n");
        results.add(passes + "/" + approximate + "/" + 
                            run + " tests passed/approximate/run\n");
         }
        }

        statusTextArea.update(statusTextArea.getGraphics());
        outFile.writeLines(results);
        outFile.close();
        
    }

    // TODO - Don't know why this throws FileNotFoundException
    public static void createModifiedProgram(String testPath,
            String pythonFileName,
            String tempProgramFileName) throws FileNotFoundException, Exception {
        // Read the program 

        try {
            TextFileReader pythonCodeFileIn = new TextFileReader(testPath + pythonFileName);
            List<String> pythonCode = pythonCodeFileIn.getText();

            // TODO - Jay's class needs to handle List<String>
            List<String> newText = new LinkedList<>();
            for (String text : pythonCode) {
                // text = TextReplacer.replaceAll( text, "raw_input", "_test_raw_in_" );  // if python 2
                text = TextReplacer.replaceAll(text, "input", "_pytest_input_");
 
                newText.add(text);
            }

            TextFileWriter pythonCodeFileOut = new TextFileWriter(testPath + tempProgramFileName);

            pythonCodeFileOut.writeLine("# *********************************");
            pythonCodeFileOut.writeLine("# ** Added for automatic grading **");
            pythonCodeFileOut.writeLine("def _pytest_input_( prompt = \"\" ):");
            pythonCodeFileOut.writeLine("    answer = input( prompt )");
            pythonCodeFileOut.writeLine("    print( answer )");
            pythonCodeFileOut.writeLine("    return answer");
            pythonCodeFileOut.writeLine("# *********************************");
            pythonCodeFileOut.writeLine("");

            pythonCodeFileOut.writeLines(newText);
            pythonCodeFileOut.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void createProgramInputs(String testPath,
            List<String> testData,
            String tempInputFileName) throws FileNotFoundException, IOException {
        InputTagParser parser = new InputTagParser(testData);
        TextFileWriter fileOut = new TextFileWriter(testPath + tempInputFileName);
        while (parser.hasNext()) {
            fileOut.writeLine(parser.next());
        }
        fileOut.close();
    }

    public static String parseFileArg(String args, JTextArea statusTextArea ) {
        // TODO - Need to handle the case when there is not a file arg present
        if( args == null || args.length() < 1 ){
            throw new NullPointerException("There is no File name" );
        }
        int start = args.indexOf("code=\"") + 6;
        if( start < 6 ){
            start = args.indexOf("file=\"") +6; //"file=\" is being used
            //Could have error here because in method
            statusTextArea.append( "Warning: Using deprecated test tag attribute: file=, replace with code=" + "\n");
        }
        String fileArg = args.substring(start);

        int end = fileArg.indexOf("\"");

        fileArg = fileArg.substring(0, end);

        return fileArg;
    }

    public static boolean hasNoCompArg(String args) {
        // TODO - Need to handle the case when there is not a file arg present
        return 0 <= args.toUpperCase().indexOf("NOCOMP");
    }

    private static boolean deleteFile(String fileName) {
        // from: http://www.java2s.com/Code/Java/File-Input-Output/DeletefileusingJavaIOAPI.htm

        // TODO: Needs to be cleaned up

        // A File object to represent the filename
        File f = new File(fileName);

        // Make sure the file or directory exists and isn't write protected
//        if (!f.exists())
//        throw new IllegalArgumentException(
//              "Delete: no such file or directory: " + fileName);
//
//    if (!f.canWrite())
//      throw new IllegalArgumentException("Delete: write protected: "
//          + fileName);

        // If it is a directory, make sure it is empty
//    if (f.isDirectory()) {
//      String[] files = f.list();
//      if (files.length > 0)
//        throw new IllegalArgumentException(
//            "Delete: directory not empty: " + fileName);
//    }

        // Attempt to delete it
        boolean success = f.delete();

//    if (!success)
//      throw new IllegalArgumentException("Delete: deletion failed");
        return success;
    }
}
