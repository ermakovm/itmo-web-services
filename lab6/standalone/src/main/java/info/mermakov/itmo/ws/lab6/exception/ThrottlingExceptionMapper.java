package info.mermakov.itmo.ws.lab6.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ThrottlingExceptionMapper implements ExceptionMapper<ThrottlingException> {
    @Override
    public Response toResponse(ThrottlingException e) {
        return Response.status(Response.Status.TOO_MANY_REQUESTS).entity(e.getMessage()).build();
    }
}
