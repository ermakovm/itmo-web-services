package info.mermakov.itmo.ws.lab3.exception;

import jakarta.xml.ws.WebFault;

@WebFault(faultBean = "info.mermakov.itmo.ws.lab3.exception.MovieServiceFault")
public class InvalidBodyException extends Exception {
    private final MovieServiceFault fault;

    public InvalidBodyException(final MovieServiceFault fault) {
        super(fault.getMessage());
        this.fault = fault;
    }

    public InvalidBodyException(final MovieServiceFault fault, Throwable cause) {
        super(fault.getMessage(), cause);
        this.fault = fault;
    }

    public MovieServiceFault getFaultInfo() {
        return fault;
    }
}
