package info.mermakov.itmo.ws.lab3.client;

import lombok.extern.java.Log;

import java.net.URI;
import java.net.URL;
import java.util.logging.Level;

@Log
public class Application {
    public static void main(String[] args) {
        try {
            URL serviceUrl = new URI("http://127.0.0.1:8080/ws/movie-service?wsdl").toURL();

            Client client = new Client(serviceUrl);
            client.startClient();
        } catch (Exception exception) {
            log.log(Level.SEVERE, exception.getMessage(), exception);
        }
    }
}
