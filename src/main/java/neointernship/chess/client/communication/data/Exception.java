package neointernship.chess.client.communication.data;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Exception{
    private final java.lang.Exception exception;

    public Exception(final java.lang.Exception exception) {
        this.exception = exception;
    }

    public java.lang.Exception getData() {
        return exception;
    }
}
