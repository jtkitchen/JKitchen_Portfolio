package pythonprogramtester;

import java.util.LinkedList;
import java.util.List;

/**
 * A tag parser that finds &lt;value&gt; and &lt;/value&gt; tags. It will return
 * the data items between the tags, one at a time.
 * @author unknown
 */
public class ValueTagParser {

    // Instance Variables
    TagParser parser;
    
    /**
     * Create a new parser.
     *
     * @param line The line to parse.
     */
    public ValueTagParser(String line) {
        parser = new TagParser("value");
        parser.addText(line);
    }

    /**
     * Indicates if the text has more tag values (in other words, returns true
     * if next would return a String rather than a null value).
     *
     * @return true if there are more tag values
     */
    public boolean hasNext() {
        return parser.hasNext();
    }

    /**
     * Get the data from the next &lt;value&gt; tag.
     *
     * @return data between &lt;value&gt; and &lt;/value&gt; tags
     */
    public String next() throws InvalidTagFormatException {
        TagData tag = parser.next();
        if (tag != null) {
            return tag.getData();
        } else {
            return null;
        }
    }

    /**
     * Replaces the tags of the lines given in the list with empty tags
     *
     * @param line the line in which to replace the tags
     * @return the line with the modified tags
     */
    public static List<String> removeValueTags(List<String> text) {
        List<String> result = new LinkedList<>();
        for (String line : text) {
            result.add(removeValueTags(line));
        }
        return result;
    }

    /**
     * Replaces the tags in a single line with empty tags
     *
     * @param line the line in which to replace the tags
     * @return the line with the modified tags
     */
    public static String removeValueTags(String line) {
        // TODO - Need to handle removing the +/- values
        line = line.replaceAll("<value>", "");
        line = line.replaceAll("</value>", "");
        return line;
    } 
}