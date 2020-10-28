/**
	* Python Program Tester
	* 
	* Compares lines and eliminates spaces.
	* @author unknown, edited by Brendan L. 
	*/

package pythonprogramtester;

public class TextLineComparator {
    
    // TEH002 removed static from both variables
    private String actual;
    private String expected;

    /**
     * Constructs expected and actual string.
     *
     * @param expectedCon
     * @param actualCon
     */
    public TextLineComparator(String expectedCon, String actualCon) {
        expected = trimRight( expectedCon );
        actual = trimRight( actualCon );
    }
    
    private String trimRight( String str )
    {
        // TODO: This is inefficient, should be replaced
        while( str.endsWith( " " ) )
        {
            str = str.substring( 0, str.length() - 1 );
        }
        return str;
    }

    /**
     * Compares two strings and determines whether they are the exact same
     * strings or not.
     *
     * @return if the two strings are exactly equal, returns true. Otherwise,
     * returns false.
     */
    public boolean equalsExact() {
        // TEH003 change variable name expectedNotags to expectedNoTags
        String expectedNoTags = ValueTagParser.removeValueTags(expected);  // TODO this needs to be removed when value tags are supported
        if (expectedNoTags.equals(actual)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Takes out the spaces and ignores upper and lower case letters and then
     * compares.
     *
     * @return if the two strings are approximately true, returns true.
     * Otherwise, returns false.
     */
    public boolean equalsSomeWhat() {
        String expectedNotags = ValueTagParser.removeValueTags(expected);
        String expectedNoblanks = expectedNotags.replaceAll(" ", "");
        String actualNoblanks = actual.replaceAll(" ", "");

        if (expectedNoblanks.equalsIgnoreCase(actualNoblanks)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines if the two values are equal in size.
     *
     * @return if the two values are equivalent, returns true. Otherwise,
     * returns false.
     */
    public boolean equalValues() {

        try {
            ValueParser vp = new ValueParser(actual);
            ValueTagParser vtp = new ValueTagParser(expected);
            // TODO - what if the number of values in the actual and expected are different ?
            if (vtp.hasNext()) {
                String actualValue = vp.nextValue();
                String expectedValue = vtp.next();
                // System.out.println( actualValue + " =? " + expectedValue );
                boolean result = ValueComparator.compareTolerance(actualValue,
                        expectedValue);
                if (!result) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            // TODO - Decide how to handle the exception
            return false;
        }
    }
}