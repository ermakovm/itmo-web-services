package info.mermakov.itmo.ws.lab6.filters;

import info.mermakov.itmo.ws.lab6.exception.ThrottlingException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.concurrent.Semaphore;

@Provider
public class ThrottlingRequestFilter implements ContainerRequestFilter {
    private static final int MAX_REQUESTS = 1;
    private final Semaphore semaphore = new Semaphore(MAX_REQUESTS, true);

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (!semaphore.tryAcquire()) {
            throw new ThrottlingException("Too many requests. Try again later.");
        }
        containerRequestContext.setProperty("semaphore", semaphore);
    }
}
