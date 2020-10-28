package pythonprogramtester;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * Tests the PythonProgramValidator; this test works locally, but will not work
 * anywhere else.
 * 
 * @author Nicole
 */
public class PythonProgramValidatorTest {
    
    final String NGPATH = "C:/Users/Nicole/Documents/Spring 2013/Software Engineering"
                + "/CSC 140 - Spring 2013 - Solutions/Lab14 (2102)";
    
    PythonProgramValidator validator_correct0;
    PythonProgramValidator validator_correct1;
    PythonProgramValidator validator_wrong0;
    PythonProgramValidator validator_wrong1;
    PythonProgramValidator validator_wrong2;
    PythonProgramValidator validator_wrong3;
    
    public PythonProgramValidatorTest() {
        this.validator_correct0 = new PythonProgramValidator( NGPATH+"/lab14.pytest" );
        this.validator_correct1 = new PythonProgramValidator( NGPATH+"/lab14_correct.pytest");
        this.validator_wrong0 = new PythonProgramValidator( NGPATH );
        this.validator_wrong1 = new PythonProgramValidator( NGPATH+"/lab14_wrong1.pytest" );
        this.validator_wrong2 = new PythonProgramValidator( NGPATH+"/lab14_wrong2.pytest" );
        this.validator_wrong3 = new PythonProgramValidator( NGPATH+"/lab14_wrong3.pytest" );
    }
    
    /**
     * Tests if a pytest file is valid based on its path, and then on its tags.
     *
     * @throws IOException
     * @throws InvalidTagFormatException
     * @throws ParserConfigurationException
     * @throws SAXException 
     */
    
    @Test
    @Ignore
    public void isValidTest() throws IOException, InvalidTagFormatException, ParserConfigurationException, SAXException {      
        assertTrue(validator_correct0.isValid());
        assertTrue(validator_correct1.isValid());
        
        //bad path
        assertFalse(validator_wrong0.isValid());
        
        //<test><test>
        /*See validator_wrong3
         */
        assertFalse(validator_wrong1.isValid());
        
        //<test </test>
        /*TagParser is FLAWED in this test.  Skips over any other < to find the
         * next >
         */
        assertFalse(validator_wrong2.isValid());
        
        //<test>
        /*TagParser is FLAWED in this test.  Skips over all the other <tags>
         *to find the next </tag>
         */
        assertFalse(validator_wrong3.isValid());
    }
}
