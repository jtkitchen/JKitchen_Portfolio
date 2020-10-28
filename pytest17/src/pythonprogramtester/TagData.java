/**
 * Python Program Tester
 * Roberts/Houghton 2015
 * 
 * Holds the data between the start and end tag
 * @author 
 */
package pythonprogramtester;

// TODO - Document this class
public class TagData {

        // ***** Instance Variables *****
    String tagArgs;
    String tagData;
    /*
        String tagName;
    */
    
    /**
     * Creates a new TagData object
     * @param args
     * @param data 
     */
    public TagData(String args, String data /* String name */) {
        tagArgs = args;
        tagData = data;
        /*
        tagName = name
        */
    }
    
    /**
     * Returns the data in the start tag
     * @return 
     */
    public String getArgs() {
        return tagArgs;
    }

    /**
     * Returns the data between the start and end tags
     * @return 
     */
    public String getData() {
        return tagData;
    }
    
    /*
    public String getName() {
        return tagName;
    }
    */

}
