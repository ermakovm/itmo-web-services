package info.mermakov.itmo.ws.lab3.exception;

public class MovieServiceFault {
    private static final String DEFAULT_MESSAGE = "Service error";

    protected String message;

    private MovieServiceFault() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static MovieServiceFault defaultInstance() {
        MovieServiceFault fault = new MovieServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}
