package drtools.loader.adapter.out.exception;

public class InputLoadingException extends Exception {
    public InputLoadingException(String message) {
        super(message);
    }

    public InputLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputLoadingException(Throwable cause) {
        super(cause);
    }
}
