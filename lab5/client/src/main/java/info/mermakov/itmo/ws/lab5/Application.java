package info.mermakov.itmo.ws.lab5;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

import java.net.URI;

public class Application {
    private static final URI BASE_URI = URI.create("http://localhost:8080/rest/movies");

    public static void main(String[] args) {
        try (Client client = ClientBuilder.newClient()) {
            WebClient webClient = new WebClient(client, BASE_URI);
            webClient.startClient();
        }
    }
}
