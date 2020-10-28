/**
 * Python Program Tester
 * Roberts/Houghton 2015
 * 
 * parses the date between tags in a .pytest file
 * @author 
 */
package pythonprogramtester;

import java.lang.StringBuffer;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A tag parser that finds the data between the given tag values. It will return
 * the data items between the tags, one at a time.
 *
 * @author unknown
 * @author avandyne
 */
public class TagParser {

// ***** Instance Variables *****
    private String tagName;
    private StringBuffer workingText;

    /**
     * Create a new parser. The user of the class specifies the tag name.
     *
     * @param tag The specific tag name for this parser.
     */
    public TagParser(String tag) {
        tagName = tag;
        workingText = new StringBuffer();
    }

    /**
     * Adds the given line to list of lines to be parsed.
     *
     * @param line The line to add
     */
    public void addText(String line) {
        workingText.append(line + "\n");
    }

    /**
     * Adds the given lines to list of lines to be parsed.
     *
     * @param lines The lines to add
     */
    public void addText(List<String> lines) {
        for (String line : lines) {
            addText(line);
        }
    }

    /**
     * Indicates if the text has more tag values. (In other words, returns true
     * if next would return a TagData object rather than a null value.)
     *
     * @return true if there are more tag values
     */
    public boolean hasNext() {
        return -1 != workingText.indexOf("<" + tagName);
    }

    /**
     * Returns the data from the next tag found. The data (the information
     * between the start and end tags) and the arguments (the information in the
     * start tag) in a TagData object.
     *
     * 4/22/13 NGanger - Added nextIndex, which gives the index of the next tag
     * or start of the next tag. Solves: 1. When a beginning tag is &lttagName
     * it will not believe the tag is &lttagName.....&ltotherTag&gt 2. When a
     * ending tag is incorrectly &lttagName&gt it will not jump over into the
     * next set of tags to match with that ending tag. Proved in
     * PythonProgramValidatorTest
     *
     * Finds the next start tag, which is to say the next instance of &lt; tag.
     * This should potentially be followed by a tagArg and must then have &gt;.
     * The method stores the tagArg and tagData as a TagData object. The tagData
     * is the text between the end of the start tag and the end tag, which
     * should be &lt; tag &gt; .
     *
     * @return the data and args from the next matching tag.
     * @throws InvalidTagFormatException if one of the tags is not in the format
     * &lt;tag tagArgs&gt; or the end tag is missing.
     */
    public TagData next() throws InvalidTagFormatException {
        String tagArgs = null;
        String tagData = null;

        int index = workingText.indexOf("<" + tagName);
        int nextIndex;

        //If there is another tag process it.
        if (0 <= index) {
            // remove everything up to and including the tag
            workingText.delete(0, index + 1 + tagName.length());
            // look for the end of the start tag
            index = workingText.indexOf(">");
            /*
            singleTagIndex = workingText.indexOf("/>");
            */
            nextIndex = workingText.indexOf("<");
            /*
            // If the tag is a single tag (without a body)
            if( 0 <= singleTagIndex && singleTagIndex < index ) {
                tagArgs = workingText.substring(0, singleTagIndex).trim();
                workingText.delete(0, singleTagIndex+ 1);
            
                tagData = null; // This is a single tag, it does not have a body with data in it
            */
            //If there is an end to the start tag, look at the tagArgs inside.
            /* else */if (0 <= index && index < nextIndex) {
                //The tagArgs is what is between the tagName and the ">".
                tagArgs = workingText.substring(0, index).trim();
                workingText.delete(0, index + 1);
                // look for the ending tag
                index = workingText.indexOf("</" + tagName + ">");
                if (this.hasNext()) {
                    nextIndex = workingText.indexOf("<" + tagName);
                } else {
                    nextIndex = workingText.length();
                }
                //If there is an end tag, then the part of the string between
                //the start tag and end tag is the tagData.
                if (0 <= index && index < nextIndex) {
                    tagData = workingText.substring(0, index);
                    workingText.delete(0, index + 3 + tagName.length());
                } //If no end tag is found, throw an Exception.
                else {
                    /*
                     * Changed from workingText.substring(0,50) because a
                     * StringIndexOutOfBoundsException was generated when
                     * workingText had a length less than 50. This applies to
                     * both InvalidTagFormatException throws below. AVD 01/23/13
                     */
                    throw new InvalidTagFormatException("Missing end tag",
                            workingText.toString());
                }
            } //If no end to the start tag is found, throw an Exception.
            else {
                throw new InvalidTagFormatException("Missing > on start tag",
                        workingText.toString());
            }
        } //If there is not another tag to process, then throw an Exception.
        else {
            workingText.setLength(0);
            throw new NoSuchElementException("No more tags");
        }
        return new TagData(tagArgs, tagData);
    }
}