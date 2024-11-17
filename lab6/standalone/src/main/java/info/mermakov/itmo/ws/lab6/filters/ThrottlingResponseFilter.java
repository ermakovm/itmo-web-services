package info.mermakov.itmo.ws.lab6.filters;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.concurrent.Semaphore;

@Provider
public class ThrottlingResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        Semaphore semaphore = ((Semaphore) containerRequestContext.getProperty("semaphore"));

        if (semaphore != null) {
            semaphore.release();
        }
    }
}
