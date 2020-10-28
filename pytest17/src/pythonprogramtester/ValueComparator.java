/**
 * Python Program Tester
 * Roberts/Houghton 2015
 * 
 * Compares value computed in student code with actual, "correct" value.
 * @author unknown
 */

package pythonprogramtester;
/**
 * This class performs comparison between the output of student Python code
 * and the expected output. 
 * 
 * @author necoechea_gabriel
 */
public class ValueComparator {
    /**
    * Determines whether or not a student's value is within
    * an acceptable distance of the actual value. The student's value, the 
    * actual value, and the acceptable distance are all parameters
    * specified as doubles in this implementation of the method.
    * 
    * @param students the student's value, input as a double
    * @param actual the actual value to be expected, input as a double
    * @param tolerance the absolute tolerance for error, input as a double. 
    *        A tolerance of 1 will allow student values to be accepted if they
    *        are no more than 1 to the left or right of actual. 
    *        In interval notation, the tolerance band is 
    *        [actual - tolerance, actual + tolerance]. 
    *        
    * @return true if the student's value, stored as students, is within
    *         the tolerance band, false otherwise. 
    */
    public static boolean compareTolerance(double students, double actual, double tolerance) {
        return actual - tolerance <= students && students <= actual + tolerance;
    }
    /**
     * Determines whether or not a student's value is within
     * an acceptable distance of the actual value. The student's value and 
     * the actual value are parameters specified as strings and then parsed
     * as doubles in the method body. During the parsing of the actual value
     * string, the acceptable distance, referred to as the tolerance, 
     * is also extracted when the string is formatted to include a tolerance. 
     * If the actual value string does not include a properly formatted
     * tolerance then the tolerance is set to zero. 
     * 
     * @param studentStr the student's value, input as a String
     * @param actualStr the actual expected value, input as a String
     * @return true if the double implementation of compareTolerance returns 
     *         true using the parsed values for the student value, the actual
     *         value, and the tolerance as parameters. 
     */
    public static boolean compareTolerance(String studentStr, String actualStr) {
        try {
            double students = Double.parseDouble(studentStr);
            int pos = actualStr.indexOf("+/-");
            double actual, tolerance;
            if (pos == -1) {
                actual = Double.parseDouble(actualStr);
                tolerance = 0;
            } else {
                actual = Double.parseDouble(actualStr.substring(0, pos));
                tolerance = Double.parseDouble(actualStr.substring(pos + 3));
            }
            return compareTolerance(students, actual, tolerance);
        } catch (NumberFormatException ex) {
            // TODO - Need to handle error
            return false;
        }
    }
    /**
     * BUILT-IN-SELF-TEST
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        ValueTagParser parser1 = new ValueTagParser("<value>100</value> miles is <value>160.9</value> kilometers");
        double actual = 100;
        while (parser1.hasNext()) {
            try {
                String s = parser1.next();


                double t = Double.valueOf(s.trim()).doubleValue();
                boolean results = compareTolerance(t, actual, 0.05);
                System.out.println(results);
            } catch (InvalidTagFormatException obj) {
            }
        }

        System.out.println(compareTolerance("25", "25"));
        System.out.println(compareTolerance("25.0", "25.1"));
        System.out.println(compareTolerance("3.14158", "3.14159+/-.00001"));
        System.out.println(compareTolerance("3.14157", "3.14159+/-.00001"));

    }
}
