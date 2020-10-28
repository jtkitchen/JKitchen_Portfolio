package pythonprogramtester;

// TODO - Find out who the author of this class is
import java.util.List;

/**
 * This holds the arguments and data in a tag. Also can retrieve this information
 * 
 * @author
 */
public class TagBlock {

    // ***** Instance Variables *****
    String tagArgs;
    List<String> tagData;
    /*
        String tagName;
    */
    
    /**
     * This constructs a TagBlock object with arguments and data
     * 
     * @param args Arguments of the tag
     * @param data A List<String> of data in the tag 
     */
    public TagBlock(String args, List<String> data /*, String name */) {
        tagArgs = args;
        tagData = data;
        /*
        tagName = name;
        */
    }
    
    /**
     * A getter method; retrieves the inputted arguments
     * 
     * @return tagArgs the tag arguments
     */
    public String getArgs() {
        return tagArgs;
    }

    /**
     * A getter method; retrieves the inputted data
     * 
     * @return tagData the tag's data 
     */
    public List<String> getData() {
        return tagData;
    }
    
    /*
    public String getName() {
        return tagname;
    }
    */

}