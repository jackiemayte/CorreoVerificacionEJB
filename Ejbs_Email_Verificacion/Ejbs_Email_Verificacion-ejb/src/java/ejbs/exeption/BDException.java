
package ejbs.exeption;

/**
 *
 * @author mayteEcheverry
 */
public class BDException extends Exception{

    public BDException() {
    }
    
    public BDException(String message) {
        super(message);
    }

    public BDException(Throwable cause) {
        super(cause);
    }
    
}
