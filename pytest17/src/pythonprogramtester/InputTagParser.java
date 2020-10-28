package pythonprogramtester;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A tag parser that finds &lt;input&gt; and &lt;/input&gt; tags. It will
 * return the data items between the tags, one at a time.
 */
public class InputTagParser implements Iterable<String>, Iterator<String> {
    
    //Instance Variables
    TagParser parser;
    
    /**
     * Creates a new parser.
     *
     * @param lines A list of lines to parse.
     */
    public InputTagParser(List<String> lines) {
        parser = new TagParser("input");
        for (String line : lines) {
            parser.addText(line);
        }
    }

    /**
     * Indicates if the text has more tag values. (In other words, returns true
     * if next would return a String rather than a null value.
     *
     * @return true if there are more tag values
     */
    public boolean hasNext() {
        return parser.hasNext();
    }

    /**
     * Get the data from the next &lt;input&gt; tag.
     *
     * @return data between &lt;input&gt; and &lt;/input&gt; tags
     * @throws InvalidTagFormatException
     */
    public String next() {
        try {
            TagData tag = parser.next();
            if (tag != null) {
                return tag.getData();
            } else {
                return null;
            }
        } catch (InvalidTagFormatException e) {
        }
        return null;
    }

    /**
     * 
     * @throws UnsupportedOperationException
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets an instance of this iterator
     * @returns an instance of this iterator
     */
    public Iterator<String> iterator() {
        return this;
    }

    /**
     * Gets all the tag data inputs
     * @return result, a list of all tag input
     */
    public List<String> allInputs() {
        List<String> result = new LinkedList<String>();
        while (hasNext()) {
            result.add(next());
        }
        return result;
    }

    /**
     * Removes input tags from a list, text
     * @param text
     * @return result
     */
    public static List<String> removeInputTags(List<String> text) {
        List<String> result = new LinkedList<String>();
        for (String line : text) {
            result.add(removeInputTags(line));
        }
        return result;
    }

    /**
     * Removes all input tags from a string, line
     * @param line
     * @return line
     */
    public static String removeInputTags(String line) {
        line = line.replaceAll("<input>", "");
        line = line.replaceAll("</input>", "");
        return line;
    }
}