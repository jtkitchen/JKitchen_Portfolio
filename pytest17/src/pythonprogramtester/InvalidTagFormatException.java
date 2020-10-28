package pythonprogramtester;

// TODO - Document this class
public class InvalidTagFormatException extends Exception {

    public InvalidTagFormatException(String message, String context) {
        super(message);
        errorContext = context;
    }

    public String getErrorContext() {
        return errorContext;
    }
    // ***** Instance Variable *****
    private String errorContext;
    private static final long serialVersionUID = 7526472295622776147L;
}