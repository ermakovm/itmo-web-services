package info.mermakov.itmo.ws.lab4;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.URI;

public class Application {
    private static final URI BASE_URI = URI.create("http://localhost:8080/rest");

    public static void main(String[] args) {
        HttpServer server = null;
        try {

        } catch (IOException exception) {
            exception.printStackTrace();
            stopServer(server);
        }
    }

    private static void stopServer(HttpServer server) {
        if (server != null) {
            server.stop(0);
        }
    }
}
