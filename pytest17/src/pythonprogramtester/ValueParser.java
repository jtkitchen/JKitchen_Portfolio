/**
 * Python Program Tester
 * Roberts/Houghton 2015
 * 
 * This class is used to take a line of text and find the values . It breaks the
 * the line into parts, each part is either a text string (not holding a value)
 * or a value. Values can be returned as strings (to be converted by the user)
 * or as doubles.
 * 
 * @author unknown
 */

package pythonprogramtester;

import java.util.LinkedList;
import java.util.List;


public class ValueParser {
    
    List<Part> parts;

    /**
     * Create a new ValueParser with that will parse the given line of text.
     * 
     * @param line a line to be parsed
     */
    public ValueParser(String line) {
        parts = new LinkedList<Part>();
        
        StringBuffer input = new StringBuffer(line);
        StringBuffer part = new StringBuffer();

        while (0 < input.length()) {
            int len = 0;
            int blank = 1 + input.indexOf(" ");
            if (0 == blank) {
                blank = input.length();
            }

            for (int i = blank; i > 0; i--) {
                if (isValue(input.substring(0, i))) {
                    len = i;
                    break;
                }
            }
            if (len > 0) {
                if (0 < part.length()) {
                    parts.add(new TextPart(part.toString()));
                    part = new StringBuffer();
                }
                String valuePart = input.substring(0, len);
                parts.add(new ValuePart(valuePart, Double.parseDouble(valuePart)));
                input.delete(0, len);
            } else {
                part.append(input.charAt(0));
                input.delete(0, 1);
            }
        }
        if (0 < part.length()) {
            parts.add(new TextPart(part.toString()));
        }
    }

    /**
     * Determines if a string  may be parsed as a double
     * 
     * @param text a portion of text that might be parsed as a Double
     * @return true if the string may be parsed as a double, false otherwise
     */
    private boolean isValue(String text) {
        if (text.charAt(0) == ' ') {
            return false;
        } else if (text.charAt(text.length() - 1) == ' ') {
            return false;
        } else {
            try {
                Double.parseDouble(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    /**
     * Determines if there are more parts (either text or value) to be
     * returned by the parser.
     * 
     * @return true if the parts linked list has non-zero size, false otherwise.
     */
    public boolean hasNextPart() {
        return 0 != parts.size();
    }

    /**
     * Determines if the next part in the parts linked list is a value
     * as opposed to text. 
     * 
     * @return true if the next part that is to be returned is a value.
     */
    public boolean nextPartIsValue() {
        return parts.get(0) instanceof ValuePart;
    }

    /**
     * Returns the next part.
     * 
     * @return the next part
     */
    public String nextPart() {
        return parts.remove(0).getPart();
    }

    /**
     * Determines if the parts linked list has any more part instances 
     * which can be parsed as a value
     * 
     * @return true if there is a ValuePart anywhere in the parts linked list
     */
    public boolean hasNextValue() {
        for (Part part : parts) {
            if (part instanceof ValuePart) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the next ValuePart, removing all other parts before it.
     * 
     * @return the next ValuePart in the parts linked list
     */
    public String nextValue() {
        Part part;
        do {
            part = parts.remove(0);
        } while (!(part instanceof ValuePart));

        return part.getPart();
    }

    /**
     * Creates a Part, which holds a String.
     */
    private class Part {

        public Part(String part) {
            this.part = part;
        }
        
        public String getPart() {
            return part;
        }
        String part;
    }

    /**
     * Creates a Part extended to be a ValuePart, which holds both a String
     * and a Double.
     */
    private class ValuePart extends Part {

        public ValuePart(String part, double value) {
            super(part);
            this.value = value;
        }

        public double getValue() {
            return value;
        }
        double value;
    }

    /**
     * Creates a Part extended to be a TextPart, which holds a String.
     */
    private class TextPart extends Part {

        public TextPart(String part) {
            super(part);
        }
    }
}
