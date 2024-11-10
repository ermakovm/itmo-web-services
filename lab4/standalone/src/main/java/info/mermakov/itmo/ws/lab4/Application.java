package info.mermakov.itmo.ws.lab4;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Application {
    private static final URI BASE_URI = URI.create("http://localhost:8080/rest");

    public static void main(String[] args) {
        HttpServer server = null;
        try {
            ResourceConfig resourceConfig = new ResourceConfig(MovieResource.class);
            server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
            System.out.println("Server started");
            System.in.read();
            stopServer(server);
        } catch (IOException exception) {
            exception.printStackTrace();
            stopServer(server);
        }
    }

    private static void stopServer(HttpServer server) {
        if (server != null) {
            server.shutdownNow();
        }
    }
}
