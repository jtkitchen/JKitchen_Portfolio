package pythonprogramtester;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A class for parsing through test tags.  What else were you expecting?
 * Roberts/Houghton 2015
 * @author somekittens
 * 
 * Run tag should be implemented in the TestTagParser.java file.
 * Since no input is taken, the run tag should simply be a run command. 
 * It is the same as Test with no arguments. 
 */
public class TestTagParser {
    
    // Instance Variables
    TagParser parser;
    private final static String TEST_TAG_NAME = "test";
    
    /**
     * Accepts a List of lines and adds them to a TagParser.
     * @param lines List of lines to be added to the TagParser
     */
    public TestTagParser(List<String> lines) {
        parser = new TagParser(TEST_TAG_NAME);
        for (String line : lines) {
            parser.addText(line);
        }
    }

    /**
     * Returns true if parser has more data to give, false otherwise.
     * @return boolean
     */
    public boolean hasNext() {
        return parser.hasNext();
    }

    /**
     * Processes the next chunk of TagData in parser.
     * @return The next chunk of tags as a TagBlock
     * @throws InvalidTagFormatException 
     */
    public TagBlock next() throws InvalidTagFormatException {
        TagData tag = parser.next();
        String[] lines = tag.getData().split("\n");
        List<String> block = new LinkedList<String>();

        if (lines != null) {
            block.addAll(Arrays.asList(lines));
        }
        trimBlock(block);

        return new TagBlock(tag.getArgs(), block);
    }

    /**
     * Removes all empty entries in a List at the beginning and end.
     * NOTE: This deliberately doesn't remove empty entries in the middle of the List.
     * @param block 
     */
    private void trimBlock(List<String> block) {
        // RGW: added check for non-empty block
        while (block.size() > 0 && block.get(0).equals("")) {
            block.remove(0);
        }
        while (block.size() > 0 && block.get(block.size() - 1).equals("")) {
            block.remove(block.size() - 1);
        }
    }

}