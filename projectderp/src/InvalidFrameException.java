/**
 * Frame Exception class for the Game Engine.
 *
 * Thrown when frame width * frame count != image width.
 *
 * @author Tero Pykälämäki
 * @version 2014.1216
 * @since 1.7
 */
import java.lang.RuntimeException;

class InvalidFrameException extends RuntimeException {
    
    /**
     * Creates the exception with specified message.
     *
     * @param message Describes the exception event.
     */
    public InvalidFrameException(String message) {
        super(message);
    }
}