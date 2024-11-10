package info.mermakov.itmo.ws.lab6.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidBodyExceptionMapper implements ExceptionMapper<InvalidBodyException> {
    @Override
    public Response toResponse(InvalidBodyException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
