
package exchangetask;

import java.util.Collection;

public class RequestRejectedException extends Exception {
    
    public RequestRejectedException() {
        super();
    }

    public RequestRejectedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RequestRejectedException(final String message) {
        super(message);
    }

    public RequestRejectedException(final Throwable cause) {
        super(cause);
    }
    
}
