package info.mermakov.itmo.ws.lab6.filters;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.Base64;
import java.util.Set;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";
    private static final String AUTH_SCHEME = "Basic";
    private static final String USERNAME = "test_user";
    private static final String PASSWORD = "test_password";
    private static final Set<String> FILTERED_METHODS = Set.of("DELETE", "PUT", "POST");

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String method = requestContext.getMethod();
        if (!FILTERED_METHODS.contains(method)) {
            return;
        }
        if ("POST".equals(method) && requestContext.getUriInfo().getPath().contains("movies/find")) {
            return;
        }

        String authHeader = requestContext.getHeaderString(AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith(AUTH_SCHEME)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        String encodedCredentials = authHeader.substring(AUTH_SCHEME.length()).trim();
        String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials));
        StringTokenizer tokenizer = new StringTokenizer(decodedCredentials, ":");

        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        if (!isValidUser(username, password)) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isValidUser(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Authorization required")
                        .build()
        );
    }
}
